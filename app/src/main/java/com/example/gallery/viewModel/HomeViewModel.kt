package com.example.gallery.viewModel

import android.content.Context
import androidx.core.text.htmlEncode
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gallery.api.RetrofitClient
import com.example.gallery.database.DatabseImp
import com.example.gallery.model.Photo
import com.example.gallery.model.PhotoResponse
import com.example.gallery.utility.getPhotos
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okio.ByteString.Companion.encodeUtf8
import retrofit2.Call
import retrofit2.Response

class HomeViewModel:ViewModel()
{
    //ho usato la mutablelivedata anziche livedata soltanto, perche altrimenti avrei dovuto vrare
    // sia un oggetto livedqta che un oggetto mutable ed esporre alla view il livedata e settare il valore nella mutable
    var photoData : MutableLiveData<MutableList<Photo?>> = MutableLiveData()
    var errodata:   MutableLiveData<Throwable> = MutableLiveData()

    fun searchPhoto(query:String,context: Context)
    {
        //creo l'istanza del database
        val databases:DatabseImp= DatabseImp.createDatabase(context)
        CoroutineScope(Dispatchers.IO).launch { databases.photoDao().getDati(query).
        let {
            CoroutineScope(Dispatchers.Main).launch { photoData.value=it }}

        }
        RetrofitClient.apiServices.searchPhotos(query).enqueue(object : retrofit2.Callback<PhotoResponse>{
            override fun onResponse(call: Call<PhotoResponse>, response: Response<PhotoResponse>) {
// uso le extension per mappare la lista delle photo e faccio la lettura della response
              photoData.value=  response.body()?.content?.children?.getPhotos()
                CoroutineScope(Dispatchers.IO).launch {databases.photoDao().
                insertDati(photoData.value?.
                map {
                    it?.also { it.query = query } }?.toMutableList())   }

            }

            override fun onFailure(call: Call<PhotoResponse>, t: Throwable) {
                //perche in futuro potrei usare qesta eccezione per mostrare un errore specifico
                errodata.value= t.cause

            }

        })

    }
}