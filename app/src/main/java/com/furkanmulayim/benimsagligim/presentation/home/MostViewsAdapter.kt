package com.furkanmulayim.benimsagligim.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.furkanmulayim.benimsagligim.R
import com.furkanmulayim.benimsagligim.domain.model.ResultDisease


class MostViewsAdapter(
    private val dataList: List<ResultDisease>
) : RecyclerView.Adapter<MostViewsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val hastalikAdi: TextView = itemView.findViewById(R.id.similarDiseaseName)
        val gorseli: ImageView = itemView.findViewById(R.id.similarDiseaseImage)
        val button: ConstraintLayout = itemView.findViewById(R.id.similarItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_disease_most_views, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.hastalikAdi.text = item.name
        holder.gorseli.setBackgroundResource(R.drawable.sil)

        holder.button.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_diseaseDetailFragment2,)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}

