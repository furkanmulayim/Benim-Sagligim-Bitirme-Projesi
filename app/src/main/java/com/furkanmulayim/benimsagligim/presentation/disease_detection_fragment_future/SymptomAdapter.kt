package com.furkanmulayim.benimsagligim.presentation.disease_detection_fragment_future

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.furkanmulayim.benimsagligim.R


class SymptomAdapter(
    private val dataList: List<String>,
    private val context: Context,
    private val onClick: (MutableSet<String>) -> Unit
) : RecyclerView.Adapter<SymptomAdapter.ViewHolder>() {

    private val selectedItems = mutableSetOf<String>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemTextView: TextView = itemView.findViewById(R.id.item_text_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_symptom_general, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.itemTextView.text = item
        holder.itemView.setOnClickListener {
            if (selectedItems.contains(item)) {
                // Kullanıcı ikinci kez tıkladığında öğeyi seçmekten vazgeç
                holder.itemTextView.setBackgroundResource(R.drawable.semptom_unselected)
                val color = ContextCompat.getColor(context, R.color.orange)
                holder.itemTextView.setTextColor(color)
                selectedItems.remove(item)
            } else {
                // Öğeyi seç
                holder.itemTextView.setBackgroundResource(R.drawable.semptom_selected)
                val color = ContextCompat.getColor(context, R.color.white)
                holder.itemTextView.setTextColor(color)
                selectedItems.add(item)
            }
            onClick(selectedItems)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}

