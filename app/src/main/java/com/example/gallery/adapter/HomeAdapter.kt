package com.example.gallery.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gallery.R
import com.example.gallery.model.Photo
import kotlinx.android.synthetic.main.photo_row.view.*

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {

    var photoList: MutableList<Photo> = mutableListOf()
        set(photolist) {
            field = photolist
            notifyDataSetChanged()
        }
    class MyViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview)

    var onItemClick : (Photo)->Unit = {}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.photo_row, parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(holder.itemView.context).load(photoList.get(position).url)
            .into(holder.itemView.photo_imgv)
        holder.itemView.setOnClickListener {
            onItemClick(photoList.get(position))
        }
    }

    override fun getItemCount(): Int {
        return photoList.size
    }
}

