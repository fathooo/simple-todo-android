package com.fathooo.technicaltest.domain.usecase

import android.util.Log
import com.fathooo.technicaltest.data.model.Todo
import com.fathooo.technicaltest.domain.repository.TodosRepository

class GetTodosUseCase(private val repository: TodosRepository) {
    suspend operator fun invoke(): List<Todo>? {
        val result =  repository.getTodos()
        if (result.isFailure) {
            Log.d("DEBUG: GetTodosUseCase", result.exceptionOrNull().toString())
            return null
        }
        val data = result.getOrThrow()
        return data
    }
}