package com.fathooo.technicaltest.presentation.view

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.fathooo.technicaltest.databinding.ActivityMainBinding
import com.fathooo.technicaltest.presentation.adapter.TodoAdapter
import com.fathooo.technicaltest.presentation.viewmodel.TodoViewModel

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding
    private val todoViewModel: TodoViewModel by viewModels()
    private val adapter by lazy { TodoAdapter(emptyList()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        Log.d("MainActivity", "onCreate")
        todoViewModel.todos.observe(this, Observer { todos ->
            Log.d("MainActivity", "onCreate: $todos")
            adapter.setTodos(todos)
        })

        todoViewModel.loadTodos()
    }
}