package com.example.gallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import com.aghajari.zoomhelper.ZoomHelper

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return ZoomHelper.getInstance().dispatchTouchEvent(ev!!,this) || super.dispatchTouchEvent(ev)
    }

    }
