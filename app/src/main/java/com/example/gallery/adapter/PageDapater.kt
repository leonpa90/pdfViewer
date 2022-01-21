package com.example.gallery.adapter

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.gallery.model.Photo
import com.example.gallery.view.PagerFragment
import com.example.gallery.view.PagerItemFragment

class PageDapater(fragmentActivity:Fragment,var photos:List<Photo>):FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return photos.size
    }

    override fun createFragment(position: Int): Fragment {
        return PagerItemFragment().apply { arguments= bundleOf("photo" to photos.get(position)) } }
    }
