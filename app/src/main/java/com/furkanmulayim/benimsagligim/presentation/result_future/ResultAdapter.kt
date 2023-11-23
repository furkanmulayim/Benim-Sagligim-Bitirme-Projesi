package com.furkanmulayim.benimsagligim.presentation.result_future

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.furkanmulayim.benimsagligim.R
import com.furkanmulayim.benimsagligim.domain.model.ResultDisease


class ResultAdapter(
    private val dataList: List<ResultDisease>
) : RecyclerView.Adapter<ResultAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val hastalikAdi: TextView = itemView.findViewById(R.id.resultHastalikAdi)
        val gorseli: ImageView = itemView.findViewById(R.id.shapeableImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_result, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.hastalikAdi.text = item.name
        //holder.gorseli.setBackgroundResource(R.drawable.sil)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}

