package com.fathooo.technicaltest.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fathooo.technicaltest.data.api.RetrofitClient
import com.fathooo.technicaltest.data.model.Todo
import com.fathooo.technicaltest.data.repository.TodosRepositoryImpl
import com.fathooo.technicaltest.databinding.ActivityMainBinding
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
    private val adapter by lazy { TodoAdapter(emptyList(), onEdit = { todo -> editTodoDialog(todo) }, onDelete = { todoId -> deleteTodoDialog(todoId) }) }

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

        todoViewModel.loadTodos()
    }

    private fun addTodoDialog() {
        // Implementar un diálogo para añadir una nueva tarea
        // Crear la nueva tarea y llamando a todoViewModel.addTodo(newTodo)
        Log.d("TAG", "addTodoDialog")
    }

    private fun editTodoDialog(todo: Todo) {
        // Implementar un diálogo para editar la tarea existente
        // Actualizando la tarea y llamando a todoViewModel.editTodo(updatedTodo)
        Log.d("TAG", "editTodoDialog")
    }

    private fun deleteTodoDialog(todoId: Int) {
        // Implementar un diálogo para eliminar la tarea existente
        // Llamando a todoViewModel.removeTodoById(todoId)
        Log.d("TAG", "deleteTodoDialog")
    }

}