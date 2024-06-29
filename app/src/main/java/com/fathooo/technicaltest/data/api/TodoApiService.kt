package com.fathooo.technicaltest.data.api

import com.fathooo.technicaltest.data.model.Todo
import retrofit2.http.GET
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface TodosApiService {
    @GET("todos")
    suspend fun getTodos(): List<Todo>

    @POST("todos")
    suspend fun createTodo(
        @Body todo: Todo
    ): Todo

    @PATCH("todos/{id}")
    suspend fun updateTodo(
        @Path("id") id: Int,
        @Body todo: Todo
    ): Todo

    @DELETE("todos/{id}")
    suspend fun deleteTodo(@Path("id") id: Int)
}