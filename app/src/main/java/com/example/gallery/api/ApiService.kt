package com.example.gallery.api

import com.example.gallery.model.ApiResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiService
{
    @GET("test.json")
    fun getPdf() : Call<ApiResponse>
}