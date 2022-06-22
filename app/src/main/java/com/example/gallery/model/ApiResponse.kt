package com.example.gallery.model

data class ApiResponse(
    val content: List<Content>,
    val lang: String,
    val status: Boolean
)