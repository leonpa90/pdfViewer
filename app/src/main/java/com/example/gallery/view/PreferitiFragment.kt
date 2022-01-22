package com.example.gallery.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gallery.R
import com.example.gallery.adapter.HomeAdapter
import com.example.gallery.viewModel.HomeViewModel
import com.example.gallery.viewModel.PrefViewModel
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.android.synthetic.main.preferiti_frag.*


class PreferitiFragment:Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.preferiti_frag, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = HomeAdapter()
        recycleviewpref.layoutManager = GridLayoutManager(context, 3)
        var modelView: PrefViewModel = ViewModelProvider(this).get(PrefViewModel::class.java)
        recycleviewpref.adapter = adapter
        modelView.photoData.observe(viewLifecycleOwner, Observer {
            adapter.photoList = it
        })
        modelView.getPreferiti(requireContext())

        adapter.onItemClick = {
            view.findNavController().navigate(
                R.id.action_preferitiFragment_to_pagerFragment,
                bundleOf("photos" to adapter.photoList, "photoposition" to it)
            )

        }
    }
}