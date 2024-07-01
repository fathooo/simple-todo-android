package com.fathooo.technicaltest.data.repository

import com.fathooo.technicaltest.data.api.TodosApiService
import com.fathooo.technicaltest.data.model.Todo
import com.fathooo.technicaltest.domain.repository.TodosRepository

class TodosRepositoryImpl(private val apiService: TodosApiService) : TodosRepository {

    override suspend fun getTodos(): Result<List<Todo>> {
        return try {
            val todos = apiService.getTodos()
            Result.success(todos)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun createTodo(todo: Todo): Result<Todo> {
        return try {
            val createdTodo = apiService.createTodo(todo)
            Result.success(createdTodo)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun updateTodo(todo: Todo): Result<Todo> {
        return try {
            val updatedTodo = apiService.updateTodo(todo.id, todo)
            Result.success(updatedTodo)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun deleteTodoById(id: Int): Result<Unit> {
        return try {
            apiService.deleteTodo(id)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}