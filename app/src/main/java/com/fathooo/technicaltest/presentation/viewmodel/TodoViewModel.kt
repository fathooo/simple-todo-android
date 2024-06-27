package com.fathooo.technicaltest.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fathooo.technicaltest.data.api.RetrofitClient
import com.fathooo.technicaltest.data.model.Todo
import kotlinx.coroutines.launch

class TodoViewModel() : ViewModel() {
    private val _todos = MutableLiveData<List<Todo>>()
    val todos: LiveData<List<Todo>> get() = _todos

    fun loadTodos() {
        viewModelScope.launch {
            val todoList = RetrofitClient.api.getTodos()
            _todos.value = todoList
        }
    }

//    TODO: Implement uses cases
}