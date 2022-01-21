package com.example.gallery.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gallery.R
import com.example.gallery.model.Photo
import com.example.gallery.utility.setImage
import kotlinx.android.synthetic.main.photo_row.view.*

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {

    var photoList: MutableList<Photo?> = mutableListOf()
        set(photolist) {
            field = photolist
            notifyDataSetChanged()
        }


    var onItemClick : (Int)->Unit = {}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.photo_row, parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        photoList.get(position)?.url?.let { holder.itemView.photo_imgv.setImage(it) }
        holder.itemView.setOnClickListener {
             onItemClick(position)


        }
    }

    override fun getItemCount(): Int {
        return photoList.size
    }
    class MyViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview)
}

