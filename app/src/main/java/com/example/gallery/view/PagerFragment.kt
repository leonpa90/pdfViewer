package com.example.gallery.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.gallery.R
import com.example.gallery.adapter.PageDapater
import com.example.gallery.model.Photo
import kotlinx.android.synthetic.main.pager_fragment.*

class PagerFragment:Fragment()
{
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.pager_fragment,container,false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       var list:MutableList<Photo>? = (arguments?.getSerializable("photos")as? MutableList<Photo>)
            list?.let {
           val pageAdapter= PageDapater(this, it)
                pager_view.adapter=pageAdapter
                arguments?.getInt("photoposition")?.let {
                    view.post { pager_view.currentItem =it }
                }
        }
    }
}