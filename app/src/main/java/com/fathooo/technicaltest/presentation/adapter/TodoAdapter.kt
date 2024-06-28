package com.fathooo.technicaltest.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fathooo.technicaltest.data.model.Todo
import com.fathooo.technicaltest.databinding.ItemTodoBinding

class TodoAdapter(
    private var todos: List<Todo>,
    private val onEdit: (Todo) -> Unit,
    private val onDelete: (Int) -> Unit
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemTodoBinding.inflate(inflater, parent, false)
        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(todos[position])
    }

    override fun getItemCount(): Int = todos.size

    inner class TodoViewHolder(private val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(todo: Todo) {
            binding.todo = todo
            binding.btnEdit.setOnClickListener { onEdit(todo) }
            binding.btnDelete.setOnClickListener { onDelete(todo.id) }
            binding.executePendingBindings()
        }
    }

    fun setTodos(newTodos: List<Todo>) {
        todos = newTodos
        notifyDataSetChanged()
    }
}