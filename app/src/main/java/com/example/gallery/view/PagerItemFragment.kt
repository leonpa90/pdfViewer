package com.example.gallery.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.aghajari.zoomhelper.ZoomHelper
import com.example.gallery.R
import com.example.gallery.model.Photo
import com.example.gallery.utility.setImage
import com.example.gallery.viewModel.HomeViewModel
import com.example.gallery.viewModel.PageViewModel
import kotlinx.android.synthetic.main.page_layout.*
import kotlinx.coroutines.CoroutineScope

class PagerItemFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.page_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (arguments?.getSerializable("photo") as? Photo)?.let {
            zoomImage.setImage(it.url)
            infoImage.text = "${it.title}\n ${it.authorFullname}"
            ZoomHelper.addZoomableView(zoomImage)

        }
    }
}
