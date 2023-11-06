package com.furkanmulayim.benimsagligim.presentation.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.furkanmulayim.benimsagligim.R
import com.furkanmulayim.benimsagligim.domain.model.CategoryListDisease
import com.furkanmulayim.benimsagligim.util.loadImageCategpry


class CategoryListAdapter(
    private val dataList: ArrayList<CategoryListDisease>
) : RecyclerView.Adapter<CategoryListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemAdi: TextView = itemView.findViewById(R.id.categoryName)
        val itemBack: ImageView = itemView.findViewById(R.id.categoryBacke)
        val itemFront: ImageView = itemView.findViewById(R.id.categoryFronte)

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
        holder.itemAdi.text = item.Adi
        holder.itemFront.loadImageCategpry(item.Foreground)
        holder.itemBack.loadImageCategpry(item.Background)


        //Hangi iteme tıklanırsa kategori detay sayfasına bilgileri gönderiyor.
        holder.itemView.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToDiseaseCategoryFragment(
                item.Adi, item.Background, item.Foreground
            )
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<CategoryListDisease>) {
        dataList.clear()
        dataList.addAll((newList))
        notifyDataSetChanged()
    }
}

