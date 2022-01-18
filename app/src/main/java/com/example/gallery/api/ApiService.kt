package com.example.gallery.api

import androidx.room.Query
import com.example.gallery.model.PhotoResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService
{
    @GET("{a}/top.json")
    fun searchPhotos(@retrofit2.http.Path("a") photos:String):Call<PhotoResponse>
}