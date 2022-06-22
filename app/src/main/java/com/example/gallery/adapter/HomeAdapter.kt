package com.example.gallery.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gallery.R
import com.example.gallery.model.Content
import kotlinx.android.synthetic.main.pdf_row.view.*

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {

    var pdfList: MutableList<Content?> = mutableListOf()
        set(photolist) {
            field = photolist
            notifyDataSetChanged()
        }

    var onItemClick: (Int) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.pdf_row, parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.title.text = pdfList.get(position)?.mediaTitleCustom.toString()
        holder.itemView.setOnClickListener {
            onItemClick(position)
        }

    }


    override fun getItemCount(): Int {
        return pdfList.size
    }

    class MyViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview)

    fun removeItem(position: Int) {
        pdfList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, pdfList.size)
    }
}

