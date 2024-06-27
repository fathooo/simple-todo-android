package com.fathooo.technicaltest.data.model;
class Todos : ArrayList<Todo>()

data class Todo(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
)