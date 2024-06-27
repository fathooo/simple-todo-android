package com.fathooo.technicaltest.domain.usecase

import com.fathooo.technicaltest.domain.repository.TodosRepository

class DeleteTodoUseCase(private val repository: TodosRepository) {
    suspend operator fun invoke(id: Int) {
        return repository.deleteTodoById(id)
    }
}