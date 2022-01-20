package com.example.gallery.utility

import com.example.gallery.model.Children
import com.example.gallery.model.Photo

fun List<Children>?.getPhotos(): MutableList<Photo?>? {
    val photo = this?.map { children ->
        Photo(
            children.item.authorFullname,
            children.item.preview?.images?.first()?.source?.url?.decode(),
            children.item.title,
            children.item.id
        )
    }?.filter { !it.url.isNullOrEmpty() }
    return photo?.toMutableList()
}

fun String.decode(): String {
    return replace("amp;", "")
}