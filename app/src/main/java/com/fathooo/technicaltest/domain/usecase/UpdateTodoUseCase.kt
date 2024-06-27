package com.fathooo.technicaltest.domain.usecase

import com.fathooo.technicaltest.data.model.Todo
import com.fathooo.technicaltest.domain.repository.TodosRepository

class UpdateTodoUseCase(private val repository: TodosRepository) {
    suspend operator fun invoke(todo: Todo): Todo {
        return repository.updateTodo(todo)
    }
}