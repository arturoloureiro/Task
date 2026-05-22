package com.arthur_loureiro.task.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.arthur_loureiro.task.R
import com.arthur_loureiro.task.data.model.Status
import com.arthur_loureiro.task.data.model.Task
import com.arthur_loureiro.task.databinding.ItemTaskBinding

class TaskAdapter(
    private val context: Context,
    private val taskList: List<Task>
) : RecyclerView.Adapter<TaskAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    private fun setIndicators(task: Task, holder: MyViewHolder){
        when (task.status){
            Status.TODO -> {
                holder.binding.buttonBack.isVisible = false
            }
            Status.DOING -> {
                //configurar a cor diferente para as setas
                holder.binding.buttonBack.setColorFilter( ContextCompat.getColor(context, R.color.color_status_todo))
                holder.binding.buttonForward.setColorFilter( ContextCompat.getColor(context, R.color.color_status_done))

            }
            Status.DONE -> {
                holder.binding.buttonForward.isVisible = false
            }
        }
    }

    override fun getItemCount() = taskList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val task = taskList[position]
        holder.binding.textDescription.text = task.description
        setIndicators(task, holder) // ← aqui task e holder existem
    }

    inner class MyViewHolder(val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root)

}