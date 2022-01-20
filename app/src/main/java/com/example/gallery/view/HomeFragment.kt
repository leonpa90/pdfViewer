package com.example.gallery.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gallery.R
import com.example.gallery.adapter.HomeAdapter
import com.example.gallery.viewModel.HomeViewModel
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.coroutines.*
import okhttp3.Dispatcher

class HomeFragment: Fragment() {
    var job: Job? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = HomeAdapter()
        recycle_view.layoutManager = GridLayoutManager(context, 3)
        var modelView: HomeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        recycle_view.adapter = adapter

        modelView.photoData.observe(
            viewLifecycleOwner,
            Observer {
                 if(it.isNullOrEmpty() ) {
                    Toast.makeText(context, "Nessun risultato", Toast.LENGTH_SHORT).show()
                }
                    else
                    adapter.photoList = it
                  })




        searchView.addTextChangedListener {
            job?.cancel()
            job= CoroutineScope(Dispatchers.Main).launch {

            delay(500)
            if(!it.toString().isNullOrEmpty())
            {
                modelView.searchPhoto(it.toString())
            }
                else {
                adapter.photoList.clear()
                adapter.notifyDataSetChanged()
            }
        }
        }

                adapter.onItemClick={
                    view.findNavController().navigate(R.id.action_homeFragment_to_pagerFragment,
                        bundleOf("photos" to adapter.photoList,"photoposition" to it))
                }




}
}