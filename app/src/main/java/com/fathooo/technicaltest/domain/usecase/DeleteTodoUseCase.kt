package com.fathooo.technicaltest.domain.usecase

import android.util.Log
import com.fathooo.technicaltest.domain.repository.TodosRepository

class DeleteTodoUseCase(private val repository: TodosRepository) {
    suspend operator fun invoke(id: Int): Boolean? {
        val result =  repository.deleteTodoById(id)
        if (result.isFailure) {
            Log.d("DEBUG: DeleteTodoUseCase", result.exceptionOrNull().toString())
            return null
        }
        return true
    }
}