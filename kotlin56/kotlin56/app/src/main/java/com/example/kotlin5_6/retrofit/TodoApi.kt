package com.example.kotlin5_6.retrofit

import com.example.kotlin5_6.database.Todo
import retrofit2.http.GET
import retrofit2.http.Path

interface TodoApi {
    @GET("todos/{id}")
    suspend fun getTodoByID(@Path("id") id: Int): Todo

    @GET("todos")
    suspend fun getAllTodos(): Todos
}
