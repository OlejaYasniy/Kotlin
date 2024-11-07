package com.example.kotlin5_6.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin5_6.R
import com.example.kotlin5_6.databinding.ListItemBinding
import com.example.kotlin5_6.database.Todo

class TodoAdapter : ListAdapter<Todo, TodoAdapter.Holder>(Comparator()) {

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ListItemBinding.bind(view)

        fun bind(todoEntity: Todo) = with(binding) {
            title.text = todoEntity.id.toString() + "\n" + todoEntity.todo +
                    "\n" + todoEntity.completed.toString() + "\n" + todoEntity.userId.toString()
        }
    }

    class Comparator : DiffUtil.ItemCallback<Todo>() {
        override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }
}
