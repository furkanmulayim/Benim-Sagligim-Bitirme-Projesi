package com.furkanmulayim.benimsagligim.presentation.detail_future

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.furkanmulayim.benimsagligim.R
import com.furkanmulayim.benimsagligim.domain.model.Disease
import com.furkanmulayim.benimsagligim.util.loadImage


class SimilarDiseaseAdapter(
    private val dataList: ArrayList<Disease>
) : RecyclerView.Adapter<SimilarDiseaseAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val hastalikAdi: TextView = itemView.findViewById(R.id.similarDiseaseName)
        val gorseli: ImageView = itemView.findViewById(R.id.similarDiseaseImage)
        val button: ConstraintLayout = itemView.findViewById(R.id.similarItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_disease_most_views, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position !=null){
        val item = dataList[position]

            holder.hastalikAdi.text = item.adi
            holder.gorseli.loadImage(
                item.gorselLinki, CircularProgressDrawable(holder.itemView.context)
            )
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

