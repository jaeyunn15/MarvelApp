package com.project.marvelapp

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.project.marvelapp.common.CharacterUiModel
import com.project.marvelapp.component.CustomProgressBar
import com.project.marvelapp.component.MessageBoxLayout
import com.project.marvelapp.component.loadImageData
import com.project.marvelapp.feature.favorite.R

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun FavoriteScreen(
    viewModel: FavoriteViewModel = hiltViewModel()
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow)
    ) {
        val viewState = viewModel.uiState.collectAsStateWithLifecycle().value

        when (viewState) {
            FavoriteUiState.Wait -> Unit
            FavoriteUiState.Loading -> CustomProgressBar()
            is FavoriteUiState.Error -> {
                viewState.msg?.let {
                    MessageBoxLayout(it)
                }
            }
            is FavoriteUiState.Success -> {
                if (viewState.characters.isEmpty()){
                    MessageBoxLayout(
                        message = stringResource(R.string.empty_favorite_item_message)
                    )
                } else {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2)
                    ) {
                        items(
                            count = viewState.characters.size,
                            key = { viewState.characters[it].id }
                        ) { index ->
                            ImageWithFavorite(
                                item = viewState.characters[index],
                                onClickEvent = {
                                    if (it.isFavorite) {
                                        viewModel.removeFavorite(it)
                                    } else {
                                        viewModel.addFavorite(it)
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ImageWithFavorite(
    item: CharacterUiModel,
    onClickEvent: (CharacterUiModel) -> Unit
) {
    val painter = rememberAsyncImagePainter(
        model = loadImageData(
            LocalContext.current,
            item.thumbnail
        )
    )

    val aspectRatio = remember { (5f / 5f) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable {
                onClickEvent(item)
            },
    ) {
        Spacer(modifier = Modifier.height(5.dp))
        Box(contentAlignment = Alignment.Center) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(aspectRatio),
                painter = painter,
                contentDescription = "Character Image",
                contentScale = ContentScale.Fit
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = item.name,
            textAlign = TextAlign.Center,
            modifier = Modifier,
            fontSize = TextUnit.Unspecified
        )
        Spacer(modifier = Modifier.height(5.dp))
    }
}