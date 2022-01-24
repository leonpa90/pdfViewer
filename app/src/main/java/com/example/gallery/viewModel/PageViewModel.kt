package com.example.gallery.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.gallery.database.DatabseImp
import com.example.gallery.model.Photo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PageViewModel:ViewModel()
{
    fun inserisciPref(context:Context,photo:Photo)
    {
    //creo l'istanza del database
    val databases: DatabseImp = DatabseImp.createDatabase(context)
    CoroutineScope(Dispatchers.IO).launch {
        databases.photoDao().inserPref(photo.also { it.preferiti=true })
    }
}
}