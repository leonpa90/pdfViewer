package com.example.gallery.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Photo(
    @SerializedName("author_fullname")
    var authorFullname: String?="",
    @SerializedName("url")
    var url: String?=null,
    @SerializedName("title")
    var title: String?="",
    @SerializedName("id")
    var id: String?=""
            ):Serializable




