package com.example.indigofera

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(
    val photo_url: String,
    val title: String,
    val text: String,
    val author: String?
): Parcelable
