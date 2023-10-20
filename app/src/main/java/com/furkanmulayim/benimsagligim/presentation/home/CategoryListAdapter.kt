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
import com.furkanmulayim.benimsagligim.domain.model.CategoryListDisease


class CategoryListAdapter(
    private val dataList: List<CategoryListDisease>
) : RecyclerView.Adapter<CategoryListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemAdi: TextView = itemView.findViewById(R.id.categoryName)
        val itemBack: ConstraintLayout = itemView.findViewById(R.id.categoryBack)
        val itemFront: ImageView = itemView.findViewById(R.id.categoryFront)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_disease_category, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Pozisyondaki veri
        val item = dataList[position]
        //Pozisyondaki verileri iteme yerleştiriyoruz.
        holder.itemAdi.text = item.name
        holder.itemBack.setBackgroundResource(item.background)
        holder.itemFront.setBackgroundResource(item.foreground)

        //Hangi iteme tıklanırsa kategori detay sayfasına bilgileri gönderiyor.
        holder.itemView.setOnClickListener {
          // val action = HomeFragmentDirections.actionHomeFragmentToDiseaseCategoryFragment(item)
          // Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}

