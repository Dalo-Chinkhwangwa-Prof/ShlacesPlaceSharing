package com.americanairlines.shlaces_shareplaces.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.americanairlines.shlaces_shareplaces.R
import com.americanairlines.shlaces_shareplaces.model.data.Shlace
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ShlacesAdapter(private var shlacesList: List<Shlace>) :
    RecyclerView.Adapter<ShlacesAdapter.ShlaceViewHolder>() {

    inner class ShlaceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val shlaceImageView: ImageView = itemView.findViewById(R.id.shlace_place_imageview)
        val shlaceDescription: TextView = itemView.findViewById(R.id.descriprion_textview)
        val shlaceSharedBy: TextView = itemView.findViewById(R.id.posted_by_textview)

//        fun bindShlace(shlace: Shlace){
//
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShlaceViewHolder {

        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.shlace_item_layout, parent, false)
        return ShlaceViewHolder(itemView)
    }

    override fun getItemCount(): Int = shlacesList.size

    override fun onBindViewHolder(holder: ShlaceViewHolder, position: Int) {
        //holder.bindShlace(shlacesList[position])
        val shlace = shlacesList[position]
        holder.apply {
            Glide.with(itemView.context)
                .applyDefaultRequestOptions(RequestOptions().centerCrop())
                .load(shlace.imageUrl)
                .into(shlaceImageView)
            shlaceDescription.text = "${shlace.description} located at ${shlace.address}"
            shlaceSharedBy.text = "Shared by ${shlace.postedBy}"
        }
    }

    fun updateShlaces(shlacesList: List<Shlace>){
        this.shlacesList = shlacesList
        notifyDataSetChanged()
    }
}







