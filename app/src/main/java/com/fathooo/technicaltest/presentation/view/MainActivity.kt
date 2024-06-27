package com.fathooo.technicaltest.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fathooo.technicaltest.data.api.RetrofitClient
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
    private val adapter by lazy { TodoAdapter(emptyList()) }

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

        Log.d("MainActivity", "onCreate")
        todoViewModel.todos.observe(this, { todos ->
            Log.d("MainActivity", "onCreate: $todos")
            adapter.setTodos(todos)
        })

        todoViewModel.loadTodos()
    }
}