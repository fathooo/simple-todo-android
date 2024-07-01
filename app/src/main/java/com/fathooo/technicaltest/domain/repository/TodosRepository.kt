package com.fathooo.technicaltest.domain.repository

import com.fathooo.technicaltest.data.model.Todo

interface TodosRepository {
    suspend fun getTodos(): Result<List<Todo>>
    suspend fun createTodo(todo: Todo): Result<Todo>
    suspend fun updateTodo(todo: Todo): Result<Todo>
    suspend fun deleteTodoById(id: Int): Result<Unit>
}