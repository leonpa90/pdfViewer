package com.example.gallery.model


import com.google.gson.annotations.SerializedName

data class Children(
    @SerializedName("data")
    var photo: Photo,
    @SerializedName("kind")
    var kind: String
)