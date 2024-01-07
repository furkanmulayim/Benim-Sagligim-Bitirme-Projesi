package com.furkanmulayim.benimsagligim.presentation.category

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.furkanmulayim.benimsagligim.R
import com.furkanmulayim.benimsagligim.domain.model.Disease
import com.furkanmulayim.benimsagligim.util.ProgressBarr
import com.furkanmulayim.benimsagligim.util.hastagsCutTheSmall
import com.furkanmulayim.benimsagligim.util.hastalikRiskOraniHesapla
import com.furkanmulayim.benimsagligim.util.loadImage
import com.furkanmulayim.benimsagligim.util.toDrawableResource


class DiseaseCategoryAdapter(
    private val dataList: ArrayList<Disease>
) : RecyclerView.Adapter<DiseaseCategoryAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image:ImageView = itemView.findViewById(R.id.shapeableImageView)
        val button: ConstraintLayout = itemView.findViewById(R.id.hastalikButton)
        val itemAdi: TextView = itemView.findViewById(R.id.hastalikAdi)
        val itemLatincesi: TextView = itemView.findViewById(R.id.hastalikAdiLatince)
        val itemEtiket: TextView = itemView.findViewById(R.id.etiketler)
        val itemDerecelendirme: TextView = itemView.findViewById(R.id.riskText)
        val itemDereceBack: ConstraintLayout = itemView.findViewById(R.id.riskBack)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_disease, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]

        var riskText =""
        if (item.riskOrani.isNotEmpty()){
            riskText = hastalikRiskOraniHesapla(item.riskOrani)
        }

        //veri listemizdeki bilgileri arayüze gönderiyoruz.
        holder.itemAdi.text = item.adi
        holder.itemLatincesi.text = item.latinAd
        holder.itemEtiket.text =item.etiketler.hastagsCutTheSmall()
        holder.itemDerecelendirme.text = riskText
        if (riskText.isNotEmpty()){
            holder.itemDereceBack.setBackgroundResource(riskText.toDrawableResource())
        }
        holder.image.loadImage(url = item.gorselLinki, ProgressBarr(holder.itemView.context))


        //herhangi bir iteme tıklanınca olacaklar
        holder.button.setOnClickListener {
            //Navigasyon ile detay sayfasına geçiş yaparken listemizi de bundle ile gönderiyoruz
            val action = DiseaseCategoryFragmentDirections.actionDiseaseCategoryFragmentToDiseaseDetailFragment(item.uuid)
            Navigation.findNavController(it).navigate(action)
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

