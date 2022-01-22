package com.example.gallery.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable
@Entity
data class Photo(
    var query:String="",
    @SerializedName("author_fullname")
    var authorFullname: String?="",
    @SerializedName("url")
    var url: String?=null,
    @SerializedName("title")
    var title: String?="",
    var preferiti:Boolean=false,
    @SerializedName("id")
    @PrimaryKey
    var id: String=""
            ):Serializable




