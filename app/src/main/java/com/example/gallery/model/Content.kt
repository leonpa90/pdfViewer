package com.example.gallery.model

import java.io.Serializable


data class Content(
    val mediaDate: MediaDate,
    val mediaId: Int,
    val mediaTitleCustom: String,
    val mediaType: String,
    val mediaUrl: String,
    val mediaUrlBig: String
):Serializable