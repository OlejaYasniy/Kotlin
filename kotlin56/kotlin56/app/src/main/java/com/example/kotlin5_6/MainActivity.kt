package com.example.kotlin5_6

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin5_6.adapter.TodoAdapter
import com.example.kotlin5_6.databinding.ActivityMainBinding
import com.example.kotlin5_6.database.AppDatabase
import com.example.kotlin5_6.database.Todo
import com.example.kotlin5_6.retrofit.TodoApi
import org.koin.android.ext.android.inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: TodoAdapter
    private lateinit var binding: ActivityMainBinding

    // Inject dependencies
    private val todoApi: TodoApi by inject()
    private val database: AppDatabase by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize RecyclerView
        adapter = TodoAdapter()
        binding.recycler.layoutManager = LinearLayoutManager(this)
        binding.recycler.adapter = adapter

        // Observe data from Room database and update RecyclerView
        database.todoDao().getAllTodos().observe(this) { todos ->
            adapter.submitList(todos)
        }

        // Fetch data from API and store in database if database is empty
        lifecycleScope.launch(Dispatchers.IO) {
            val todosFromDb = database.todoDao().getAllTodos().value
            if (todosFromDb.isNullOrEmpty()) {
                fetchAndStoreTodos()
            }
        }
    }

    private suspend fun fetchAndStoreTodos() {
        val todosResponse = todoApi.getAllTodos() // Fetch data from API
        val todosList = todosResponse.todos

        // Insert data into Room database
        withContext(Dispatchers.IO) {
            database.todoDao().insertTodos(todosList)
        }
    }
}
