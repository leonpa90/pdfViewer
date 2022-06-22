package com.example.gallery.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.gallery.R
import com.example.gallery.model.Content
import kotlinx.android.synthetic.main.details_fragment.*

class DetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.details_fragment,container,false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = arguments?.getSerializable("pdf") as Content

        dateString.text = args.mediaDate.dateString
        yearString.text = args.mediaDate.year

        pdfViewer.getSettings().setJavaScriptEnabled(true)
        val pdf = args.mediaUrl
        pdfViewer.loadUrl("https://drive.google.com/viewerng/viewer?embedded=true&url=$pdf")
    }
}