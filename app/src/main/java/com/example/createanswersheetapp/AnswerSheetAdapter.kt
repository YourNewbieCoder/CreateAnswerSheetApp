package com.example.createanswersheetapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.createanswersheetapp.databinding.ItemAnswerSheetBinding

class AnswerSheetAdapter(
    private  val sheets: List<AnswerSheet>,
    private val onEdit: (AnswerSheet) -> Unit,
    private val onDelete: (AnswerSheet) -> Unit
): RecyclerView.Adapter<AnswerSheetAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemAnswerSheetBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val binding = ItemAnswerSheetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sheet = sheets[position]
        holder.binding.sheetName.text = sheet.sheetName
        holder.binding.editButton.setOnClickListener { onEdit(sheet) }
        holder.binding.deleteButton.setOnClickListener { onDelete(sheet) }
    }

    override fun getItemCount(): Int = sheets.size
}