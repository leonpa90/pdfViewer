package com.example.gallery.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gallery.R
import com.example.gallery.adapter.HomeAdapter
import com.example.gallery.viewModel.HomeViewModel
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment: Fragment()

{
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter: HomeAdapter= HomeAdapter()
        recycle_view.layoutManager=GridLayoutManager(context,3)
        var modelView:HomeViewModel= ViewModelProvider(this).get(HomeViewModel::class.java)
        recycle_view.adapter=adapter

        modelView.photoData.observe(viewLifecycleOwner, Observer { it?.let {  adapter.photoList=it }})

        modelView.searchPhoto("disney")

    }

}