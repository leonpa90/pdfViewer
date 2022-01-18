package com.example.gallery.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gallery.api.RetrofitClient
import com.example.gallery.model.Photo
import com.example.gallery.model.PhotoResponse
import retrofit2.Call
import retrofit2.Response

class HomeViewModel:ViewModel()
{
    var photoData : MutableLiveData<MutableList<Photo>> = MutableLiveData()


    fun searchPhoto(query:String)
    {
        RetrofitClient.apiServices.searchPhotos(query).enqueue(object : retrofit2.Callback<PhotoResponse>{
            override fun onResponse(call: Call<PhotoResponse>, response: Response<PhotoResponse>) {
// uso lo stream per evitare di fare controlli superflui e piu lenti
                photoData.value=response.body()?.content?.children?.map { it.photo }?.filter { !it.url.isNullOrEmpty() }?.filter { it.url.contains(".jpg") }?.toMutableList()

            }

            override fun onFailure(call: Call<PhotoResponse>, t: Throwable) {
            }

        })

    }
}