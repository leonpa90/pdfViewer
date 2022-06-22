package com.example.gallery.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gallery.api.ApiService
import com.example.gallery.api.RetrofitClient
import com.example.gallery.model.ApiResponse
import com.example.gallery.model.Content
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class HomeViewModel : ViewModel() {

    var pdfData: MutableLiveData<ApiResponse> = MutableLiveData()
    var errodata: MutableLiveData<Throwable> = MutableLiveData()

    fun getPdfList() {
        RetrofitClient.apiServices.getPdf()
            .enqueue(object : retrofit2.Callback<ApiResponse> {
                override fun onResponse(
                    call: Call<ApiResponse>,
                    response: Response<ApiResponse>
                ) {

                    pdfData.value = response.body()


                }

                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    errodata.value = t.cause

                }
            })
    }
}