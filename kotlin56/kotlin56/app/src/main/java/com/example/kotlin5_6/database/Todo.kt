package com.example.kotlin5_6.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
data class Todo(
    @PrimaryKey val id: Int,
    val todo: String,
    val completed: Boolean,
    val userId: Int
)


