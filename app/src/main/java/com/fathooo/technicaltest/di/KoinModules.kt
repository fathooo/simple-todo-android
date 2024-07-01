package com.fathooo.technicaltest.di

import com.fathooo.technicaltest.data.api.RetrofitClient
import com.fathooo.technicaltest.data.repository.TodosRepositoryImpl
import com.fathooo.technicaltest.domain.repository.TodosRepository
import com.fathooo.technicaltest.domain.usecase.CreateTodoUseCase
import com.fathooo.technicaltest.domain.usecase.DeleteTodoUseCase
import com.fathooo.technicaltest.domain.usecase.GetTodosUseCase
import com.fathooo.technicaltest.domain.usecase.UpdateTodoUseCase
import com.fathooo.technicaltest.presentation.viewmodel.TodoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val apiModule = module {
    single { RetrofitClient.api }
}

val repositoryModule = module {
    single<TodosRepository> { TodosRepositoryImpl(get()) }
}

val useCaseModule = module {
    factory { GetTodosUseCase(get()) }
    factory { CreateTodoUseCase(get()) }
    factory { UpdateTodoUseCase(get()) }
    factory { DeleteTodoUseCase(get()) }
}

val viewModelModule = module {
    viewModel { TodoViewModel(get(), get(), get(), get()) }
}