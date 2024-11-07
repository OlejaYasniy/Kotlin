package com.example.kotlin5_6.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTodos(todos: List<Todo>): List<Long>




    @Query("SELECT * FROM todo")
    fun getAllTodos(): LiveData<List<Todo>>
}
