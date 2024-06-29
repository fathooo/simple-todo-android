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

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    private val _success = MutableLiveData<String?>()
    val success: LiveData<String?> get() = _success

    private val cache = mutableListOf<Todo>()
    private var isDataLoaded = false

    init {
        loadTodos()
    }
    fun loadTodos() {
        if (!isDataLoaded) {
            viewModelScope.launch {
                val result = getTodosUseCase.invoke()
                if (result != null) {
                    cache.addAll(result)
                    _todos.value = cache
                    isDataLoaded = true
                } else {
                    _error.value = "Error loading todos"
                }
            }
        } else {
            _todos.value = cache
        }
    }

    fun addTodo(todo: Todo) {
        viewModelScope.launch {
            val result = createTodoUseCase.invoke(todo)
            if (result != null) {
                cache.add(todo)
                cache.sortByDescending { it.id }
                _todos.value = cache
                _success.value = "Todo added: ${result.title}"
            } else {
                _error.value = "Error adding todo"
            }
        }
    }

    fun editTodo(todo: Todo) {
        viewModelScope.launch {
            val result = updateTodoUseCase.invoke(todo)
            if (result != null) {
                cache.replaceAll { if (it.id == todo.id) todo else it }
                _todos.value = cache
                _success.value = "Todo edited: ${todo.title}"
            } else {
                _error.value = "Error editing todo"
            }
        }
    }

    fun removeTodoById(id: Int) {
        viewModelScope.launch {
            val result = deleteTodoUseCase.invoke(id)
            if (result != null) {
                cache.removeAll { it.id == id }
                _todos.value = cache
                _success.value = "Todo deleted"
            } else {
                _error.value = "Error deleting todo"
            }
        }
    }

    fun getLength(): Int {
        return cache.size
    }

    fun errorHandled() {
        _error.value = null
    }

    fun successHandled() {
        _success.value = null
    }
}