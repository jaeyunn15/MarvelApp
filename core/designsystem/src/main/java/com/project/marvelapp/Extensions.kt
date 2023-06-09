package com.project.marvelapp

import android.content.Context
import coil.request.ImageRequest
import coil.size.Size

fun loadImageData(context: Context, url: String): ImageRequest = ImageRequest
    .Builder(context)
    .data(url)
    .crossfade(true)
    .size(Size.ORIGINAL)
    .build()