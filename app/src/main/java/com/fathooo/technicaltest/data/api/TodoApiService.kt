package com.fathooo.technicaltest.data.api

import com.fathooo.technicaltest.data.model.Todo
import retrofit2.http.GET
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface TodosApiService {
    @GET("todos")
    suspend fun getTodos(): List<Todo>

    @GET("todos/{id}")
    suspend fun getTodoById(@Path("id") id: Int): Todo

    @POST("todos")
    suspend fun createTodo(
        @Body todo: Todo
    ): Todo

    @PUT("todos/{id}")
    suspend fun updateTodo(
        @Path("id") id: Int,
        @Body todo: Todo
    ): Todo

    @DELETE("todos/{id}")
    suspend fun deleteTodo(@Path("id") id: Int)
}