package com.example.gallery.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.VectorDrawable
import androidx.core.content.ContextCompat

class BitmapUtils {
    companion object{
         fun getBitmap(context: Context, drawableId: Int): Bitmap? {
            val drawable = ContextCompat.getDrawable(context, drawableId)
            return if (drawable is BitmapDrawable) {
                BitmapFactory.decodeResource(context.getResources(), drawableId)
            } else if (drawable is VectorDrawable) {
                getBitmap(drawable)
            } else {
                throw IllegalArgumentException("unsupported drawable type")
            }
        }

         fun getBitmap(vectorDrawable: VectorDrawable): Bitmap? {
            val bitmap = Bitmap.createBitmap(
                vectorDrawable.intrinsicWidth,
                vectorDrawable.intrinsicHeight, Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(bitmap)
            vectorDrawable.setBounds(0, 0, canvas.width, canvas.height)
            vectorDrawable.draw(canvas)
            return bitmap
        }
    }
}