package com.furkanmulayim.benimsagligim.presentation.result

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.furkanmulayim.benimsagligim.R
import com.furkanmulayim.benimsagligim.domain.model.Disease
import com.furkanmulayim.benimsagligim.util.ProgressBarr
import com.furkanmulayim.benimsagligim.util.loadImage


class ResultAdapter(
    private val dataList: ArrayList<Disease>
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
        holder.hastalikAdi.text = item.adi
        holder.gorseli.loadImage(item.gorselLinki, ProgressBarr(holder.itemView.context))

        holder.itemView.setOnClickListener {
            val action =
                ResultFragmentDirections.actionResultFragmentToDiseaseDetailFragment(item.uuid)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: ArrayList<Disease>) {
        dataList.clear()
        dataList.addAll((newList))
        notifyDataSetChanged()
    }
    @SuppressLint("NotifyDataSetChanged")
    fun clearList() {
        dataList.clear()
        notifyDataSetChanged()
    }
}

