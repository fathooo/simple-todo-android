package com.fathooo.technicaltest.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fathooo.technicaltest.data.model.Todo
import com.fathooo.technicaltest.domain.usecase.*
import kotlinx.coroutines.launch

class TodoViewModel(
    private val getTodosUseCase: GetTodosUseCase,
    private val createTodoUseCase: CreateTodoUseCase,
    private val updateTodoUseCase: UpdateTodoUseCase,
    private val deleteTodoUseCase: DeleteTodoUseCase
) : ViewModel() {

    private val _todos = MutableLiveData<List<Todo>>()
    val todos: LiveData<List<Todo>> get() = _todos

    fun loadTodos() {
        viewModelScope.launch {
            val todoList = getTodosUseCase()
            _todos.value = todoList
        }
    }

    fun addTodo(todo: Todo) {
        viewModelScope.launch {
            createTodoUseCase(todo)
            loadTodos() // To refresh the list
        }
    }

    fun editTodo(todo: Todo) {
        viewModelScope.launch {
            updateTodoUseCase(todo)
            loadTodos() // To refresh the list
        }
    }

    fun removeTodoById(id: Int) {
        viewModelScope.launch {
            deleteTodoUseCase(id)
            loadTodos() // To refresh the list
        }
    }
}


