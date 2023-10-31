package com.furkanmulayim.benimsagligim.presentation.category_future

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.furkanmulayim.benimsagligim.R
import com.furkanmulayim.benimsagligim.domain.model.Disease
import com.furkanmulayim.benimsagligim.domain.model.ItemDisease


class DiseaseCategoryAdapter(
    private val dataList: ArrayList<Disease>) : RecyclerView.Adapter<DiseaseCategoryAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val button: ConstraintLayout = itemView.findViewById(R.id.hastalikButton)
        val itemAdi: TextView = itemView.findViewById(R.id.hastalikAdi)
        val itemLatincesi: TextView = itemView.findViewById(R.id.hastalikAdiLatince)
        val itemEtiket: TextView = itemView.findViewById(R.id.etiketler)
        val itemDerecelendirme: TextView = itemView.findViewById(R.id.riskText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_disease, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = dataList[position]

        holder.itemAdi.text = item.adi
        holder.itemLatincesi.text = item.latinAd
        holder.itemEtiket.text = item.etiketler
        holder.itemDerecelendirme.text = "%" + item.riskOrani

        holder.button.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_diseaseCategoryFragment_to_diseaseDetailFragment)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<Disease>) {
        dataList.clear()
        dataList.addAll((newList))
        notifyDataSetChanged()
    }
}

