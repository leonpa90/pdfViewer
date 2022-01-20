package com.example.gallery.model


import com.google.gson.annotations.SerializedName

data class Children(
    @SerializedName("data")
    var item: Item,
    @SerializedName("kind")
    var kind: String
)