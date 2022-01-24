package com.example.gallery.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.gallery.R
import com.example.gallery.adapter.PageDapater
import com.example.gallery.model.Photo
import com.example.gallery.viewModel.PageViewModel
import kotlinx.android.synthetic.main.pager_fragment.*

class PagerFragment : Fragment() {
    var list: MutableList<Photo>? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.pager_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
// recupero i dati serializzati passati tramite bundle
            list = (it.getSerializable("photos") as? MutableList<Photo>)
            list?.let { photos ->
                val pageAdapter = PageDapater(this, photos)
                pager_view.adapter = pageAdapter
                it.getInt("photoposition").let {
                    view.post { pager_view.currentItem = it }
                }
                val pageItem: PageViewModel = ViewModelProvider(this).get(
                    PageViewModel::class.java
                )
                addPrefBtn.setOnClickListener { view ->
                    pageItem.inserisciPref(requireContext(), photos.get(pager_view.currentItem))
                    Toast.makeText(context, "Aggiunto ai preferiti!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}