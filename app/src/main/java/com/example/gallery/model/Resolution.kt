package com.example.gallery.model


import com.google.gson.annotations.SerializedName

data class Resolution(
    @SerializedName("height")
    var height: Int,
    @SerializedName("url")
    var url: String,
    @SerializedName("width")
    var width: Int
)