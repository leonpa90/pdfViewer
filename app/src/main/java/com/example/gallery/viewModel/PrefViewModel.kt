package com.example.gallery.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gallery.database.DatabseImp
import com.example.gallery.model.Photo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PrefViewModel : ViewModel() {
    var photoData: MutableLiveData<MutableList<Photo?>> = MutableLiveData()
    fun getPreferiti(context: Context) {

        val databases: DatabseImp = DatabseImp.createDatabase(context)
        CoroutineScope(Dispatchers.IO).launch {
            databases.photoDao().getPreferiti().let {
                photoData.postValue(it)
            }
        }
    }
}
