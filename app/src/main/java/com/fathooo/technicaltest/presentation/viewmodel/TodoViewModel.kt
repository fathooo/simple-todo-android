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

    private val cache = mutableListOf<Todo>()
    private var isDataLoaded = false

    init {
        loadTodos()
    }

    fun loadTodos() {
        if (!isDataLoaded) {
            viewModelScope.launch {
                val todoList = getTodosUseCase()
                cache.addAll(todoList)
                _todos.value = cache
                isDataLoaded = true
            }
        } else {
            _todos.value = cache
        }
    }

    fun addTodo(todo: Todo) {
        viewModelScope.launch {
            createTodoUseCase(todo)
            cache.add(todo)
            _todos.value = cache
        }
    }

    fun editTodo(todo: Todo) {
        viewModelScope.launch {
            updateTodoUseCase(todo)
            cache.replaceAll { if (it.id == todo.id) todo else it }
            _todos.value = cache
        }
    }

    fun removeTodoById(id: Int) {
        viewModelScope.launch {
            deleteTodoUseCase(id)
            cache.removeAll { it.id == id }
            _todos.value = cache
        }
    }

    fun getLength(): Int {
        return cache.size
    }
}