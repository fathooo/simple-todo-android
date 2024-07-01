package com.fathooo.technicaltest.domain.usecase

import android.util.Log
import com.fathooo.technicaltest.data.model.Todo
import com.fathooo.technicaltest.domain.repository.TodosRepository

class UpdateTodoUseCase(private val repository: TodosRepository) {
    suspend operator fun invoke(todo: Todo): Todo? {
        val result =  repository.updateTodo(todo)
        if (result.isFailure) {
            Log.d("DEBUG: UpdateTodoUseCase", result.exceptionOrNull().toString())
            return null
        }
        val data = result.getOrThrow()
        return data
    }
}