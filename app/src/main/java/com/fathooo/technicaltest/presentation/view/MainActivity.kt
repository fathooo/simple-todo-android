package com.fathooo.technicaltest.presentation.view

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fathooo.technicaltest.R
import com.fathooo.technicaltest.data.api.RetrofitClient
import com.fathooo.technicaltest.data.model.Todo
import com.fathooo.technicaltest.data.repository.TodosRepositoryImpl
import com.fathooo.technicaltest.databinding.ActivityMainBinding
import com.fathooo.technicaltest.databinding.DialogAddTodoBinding
import com.fathooo.technicaltest.databinding.DialogEditTodoBinding
import com.fathooo.technicaltest.databinding.NotifyToastBinding
import com.fathooo.technicaltest.domain.usecase.CreateTodoUseCase
import com.fathooo.technicaltest.domain.usecase.DeleteTodoUseCase
import com.fathooo.technicaltest.domain.usecase.GetTodosUseCase
import com.fathooo.technicaltest.domain.usecase.UpdateTodoUseCase
import com.fathooo.technicaltest.presentation.adapter.TodoAdapter
import com.fathooo.technicaltest.presentation.viewmodel.TodoViewModel
import com.fathooo.technicaltest.presentation.viewmodel.TodoViewModelFactory

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var todoViewModel: TodoViewModel
    private val adapter by lazy { TodoAdapter(emptyList(), onEdit = { todo -> editTodoDialog(todo) }, onDelete = { todoId -> deleteTodoDialog(todoId) }, onCompletedChanged = { updatedTodo -> todoViewModel.editTodo(updatedTodo)}) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val apiService = RetrofitClient.api
        val repository = TodosRepositoryImpl(apiService)

        val getTodosUseCase = GetTodosUseCase(repository)
        val createTodoUseCase = CreateTodoUseCase(repository)
        val updateTodoUseCase = UpdateTodoUseCase(repository)
        val deleteTodoUseCase = DeleteTodoUseCase(repository)

        val viewModelFactory = TodoViewModelFactory(getTodosUseCase, createTodoUseCase, updateTodoUseCase, deleteTodoUseCase)
        todoViewModel = ViewModelProvider(this, viewModelFactory).get(TodoViewModel::class.java)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        binding.fabAddTodo.setOnClickListener { addTodoDialog() }

        todoViewModel.todos.observe(this, { todos ->
            adapter.setTodos(todos)
        })
        todoViewModel.error.observe(this, { errorMessage ->
            errorMessage?.let {
                showToastMessage(it, false)
                todoViewModel.errorHandled()
            }
        })

        todoViewModel.success.observe(this, { successMessage ->
            successMessage?.let {
                showToastMessage(it, true)
                todoViewModel.successHandled()
            }
        })


    }

    private fun addTodoDialog() {
        val dialogBinding = DialogAddTodoBinding.inflate(layoutInflater)

        val dialog = AlertDialog.Builder(this)
            .setTitle("Add Todo")
            .setView(dialogBinding.root)
            .setPositiveButton("Add") { _, _ ->
                val title = dialogBinding.addTodo.text.toString()
                if (title.isNotEmpty()) {
                    val newTodo = Todo(
                        id = todoViewModel.getLength() + 1,
                        userId = 1,
                        title = title,
                        completed = false
                    )
                    todoViewModel.addTodo(newTodo)
                }
            }
            .setNegativeButton("Cancel", null)
            .create()
        dialog.show()
    }

    private fun editTodoDialog(todo: Todo) {
        val dialogBinding = DialogEditTodoBinding.inflate(layoutInflater)
        dialogBinding.editTodo.setText(todo.title)

        val dialog = AlertDialog.Builder(this)
            .setTitle("Edit Todo")
            .setView(dialogBinding.root)
            .setPositiveButton("Save") { _, _ ->
                val updatedTitle = dialogBinding.editTodo.text.toString()
                if (updatedTitle.isNotEmpty()) {
                    val updatedTodo = todo.copy(title = updatedTitle)
                    todoViewModel.editTodo(updatedTodo)
                }
            }
            .setNegativeButton("Cancel", null)
            .create()
        dialog.show()
    }

    private fun deleteTodoDialog(todoId: Int) {
        val dialog = AlertDialog.Builder(this)
            .setMessage("Do you want to delete this Todo?")
            .setPositiveButton("Delete") { _, _ ->
                todoViewModel.removeTodoById(todoId)
            }
            .setNegativeButton("Cancel", null)
            .create()
        dialog.show()
    }

    private fun showToastMessage(message: String, isSuccess: Boolean) {
        val toast = Toast(applicationContext)
        toast.duration = Toast.LENGTH_LONG
        toast.setGravity(Gravity.CENTER, 0, 0)

        val toastNotifyBinding = NotifyToastBinding.inflate(layoutInflater)
        toastNotifyBinding.toastText.text = message
        toastNotifyBinding.toastIcon.setImageResource(
            if (isSuccess) R.drawable.ic_check_circle_black_24dp else android.R.drawable.ic_dialog_alert
        )
        toast.view = toastNotifyBinding.root
        toast.show()
    }
}