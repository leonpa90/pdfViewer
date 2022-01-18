package com.example.gallery.model


import com.google.gson.annotations.SerializedName

data class PhotoResponse(
    @SerializedName("data")
    var content: Data,
    @SerializedName("kind")
    var kind: String
)