package com.example.gallery.api

import androidx.room.Query
import retrofit2.http.GET

interface ApiService
{
    @GET("a/top.json")
    fun searchPhotos(@retrofit2.http.Query("a") photos:String)
}