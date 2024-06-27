package com.fathooo.technicaltest.data.repository

import com.fathooo.technicaltest.data.api.TodosApiService
import com.fathooo.technicaltest.data.model.Todo
import com.fathooo.technicaltest.domain.repository.TodosRepository

class TodosRepositoryImpl(private val apiService: TodosApiService) : TodosRepository {

    override suspend fun getTodos(): List<Todo> {
        return apiService.getTodos()
    }

    override suspend fun createTodo(todo: Todo): Todo {
        return apiService.createTodo(todo)
    }

    override suspend fun updateTodo(todo: Todo): Todo {
        return apiService.updateTodo(todo.id, todo)
    }

    override suspend fun deleteTodoById(id: Int) {
        return apiService.deleteTodo(id)
    }
}