package com.example.gallery.utility

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.setImage(url: String?){
    if(url!=null)
    Glide.with(context).load(url).centerCrop().override(640).into(this)
}


