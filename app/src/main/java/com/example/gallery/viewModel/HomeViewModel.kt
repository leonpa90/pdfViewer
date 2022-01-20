package com.example.gallery.viewModel

import androidx.core.text.htmlEncode
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gallery.api.RetrofitClient
import com.example.gallery.model.Photo
import com.example.gallery.model.PhotoResponse
import com.example.gallery.utility.getPhotos
import okio.ByteString.Companion.encodeUtf8
import retrofit2.Call
import retrofit2.Response

class HomeViewModel:ViewModel()
{
    var photoData : MutableLiveData<MutableList<Photo?>> = MutableLiveData()


    fun searchPhoto(query:String)
    {
        RetrofitClient.apiServices.searchPhotos(query).enqueue(object : retrofit2.Callback<PhotoResponse>{
            override fun onResponse(call: Call<PhotoResponse>, response: Response<PhotoResponse>) {
// uso le extension per mappare la lista delle photo e faccio la lettura della response
              photoData.value=  response.body()?.content?.children?.getPhotos()
            }

            override fun onFailure(call: Call<PhotoResponse>, t: Throwable) {
            }

        })

    }
}