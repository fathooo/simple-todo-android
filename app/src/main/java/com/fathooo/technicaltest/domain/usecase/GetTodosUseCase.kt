package com.fathooo.technicaltest.domain.usecase

import com.fathooo.technicaltest.data.model.Todo
import com.fathooo.technicaltest.domain.repository.TodosRepository

class GetTodosUseCase(private val repository: TodosRepository) {
    suspend operator fun invoke(): List<Todo> {
        return repository.getTodos()
    }
}