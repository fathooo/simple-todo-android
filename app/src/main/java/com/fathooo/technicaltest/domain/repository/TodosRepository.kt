package com.fathooo.technicaltest.domain.repository

import com.fathooo.technicaltest.data.model.Todo

interface TodosRepository {
    suspend fun getTodos(): List<Todo>
    suspend fun createTodo(todo: Todo): Todo
    suspend fun updateTodo(todo: Todo): Todo
    suspend fun deleteTodoById(id: Int)
}