package com.example.gallery.model


import com.google.gson.annotations.SerializedName

data class Children(
    @SerializedName("data")
    var `data`: DataX,
    @SerializedName("kind")
    var kind: String
)