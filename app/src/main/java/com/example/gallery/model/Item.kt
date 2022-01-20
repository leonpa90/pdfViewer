package com.example.gallery.model

import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("author_fullname")
    var authorFullname: String?,
    @SerializedName("url")
    var url: String?,
    @SerializedName("preview")
    var preview:Preview? = Preview(),
    @SerializedName("title")
    var title: String?,
    @SerializedName("id")
    var id: String?
            )




