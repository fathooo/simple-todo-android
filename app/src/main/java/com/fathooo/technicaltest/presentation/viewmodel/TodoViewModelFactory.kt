package com.fathooo.technicaltest.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fathooo.technicaltest.domain.usecase.CreateTodoUseCase
import com.fathooo.technicaltest.domain.usecase.DeleteTodoUseCase
import com.fathooo.technicaltest.domain.usecase.GetTodosUseCase
import com.fathooo.technicaltest.domain.usecase.UpdateTodoUseCase

class TodoViewModelFactory(
    private val getTodosUseCase: GetTodosUseCase,
    private val createTodoUseCase: CreateTodoUseCase,
    private val updateTodoUseCase: UpdateTodoUseCase,
    private val deleteTodoUseCase: DeleteTodoUseCase
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodoViewModel::class.java)) {
            return TodoViewModel(getTodosUseCase, createTodoUseCase, updateTodoUseCase, deleteTodoUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}