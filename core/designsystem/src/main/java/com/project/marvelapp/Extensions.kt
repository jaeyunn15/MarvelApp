package com.project.marvelapp

import android.content.Context
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import coil.request.ImageRequest
import coil.size.Size

fun loadImageData(context: Context, url: String): ImageRequest = ImageRequest
    .Builder(context)
    .data(url)
    .crossfade(true)
    .size(Size.ORIGINAL)
    .build()

@Composable
fun LazyGridState.OnBottomReached(
    buffer: Int = 0,
    onLoadMore: () -> Unit,
) {
    val shouldLoadMore = remember {
        derivedStateOf {
            val lastVisibleItem =
                layoutInfo.visibleItemsInfo.lastOrNull() ?: return@derivedStateOf false
            lastVisibleItem.index >= layoutInfo.totalItemsCount - 1 - buffer
        }
    }

    LaunchedEffect(shouldLoadMore) {
        snapshotFlow {
            shouldLoadMore.value
        }.collect {
            if (it) onLoadMore()
        }
    }
}