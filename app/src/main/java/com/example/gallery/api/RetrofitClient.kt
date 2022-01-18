package com.example.gallery.api


import com.example.gallery.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient
{
    // istanzio la baseurl basata sul flavour settato sul gradle
    val BASEURL= BuildConfig.SERVER_URL


    //faccio una funzione per istanziare il client retofit
    private fun getRetrofit():Retrofit
    {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASEURL).build()
    }

    // ho usato by lazy per istanziare solo una volta retorfit
    val apiServices:ApiService by lazy {
        getRetrofit().create(ApiService::class.java)
    }

}