package com.example.gallery.model


import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("id")
    var id: String? = "",
    @SerializedName("resolutions")
    var resolutions: List<Resolution>? = listOf(),
    @SerializedName("source")
    var source: Source? = Source(),

)