package com.project.marvelapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.project.marvelapp.component.CustomProgressBar
import com.project.marvelapp.component.SearchTopBar
import com.project.marvelapp.entity.CharacterEntity

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier),
        verticalArrangement = Arrangement.Center
    ) {
        SearchTopBar(
            viewModel.searchKeyword.collectAsStateWithLifecycle().value.getOrNull(),
            viewModel::setSearchString,
            viewModel::clearSearchString
        )
        Box(modifier = Modifier.weight(1f)) {
            SearchResultScreen(
                searchKeyWord = viewModel.searchKeyword.collectAsStateWithLifecycle().value.getOrNull().orEmpty()
            )
        }
    }
}

@Composable
fun SearchResultScreen(
    viewModel: SearchResultViewModel = hiltViewModel(),
    searchKeyWord: String
) {
    LaunchedEffect(searchKeyWord) {
        viewModel.updateKeyword(searchKeyWord)
    }
    val viewState = viewModel.viewState.collectAsStateWithLifecycle().value

    CharacterScreen(
        viewState = viewState,
        onLoadMore = viewModel::onLoadMoreCharacters,
        onClickEvent = {},
        onErrorToast = {}
    )
}

@Composable
fun CharacterScreen(
    viewState: SearchUiState,
    modifier: Modifier = Modifier,
    onLoadMore: () -> Unit,
    onClickEvent: (CharacterEntity) -> Unit,
    onErrorToast: (message: String) -> Unit
) {
    when(viewState) {
        is SearchUiState.Error -> {
            viewState.msg?.let {
                ErrorMessageHolder(it)
            }
        }
        is SearchUiState.Loading -> {
            CustomProgressBar()
        }
        is SearchUiState.Success -> {
            val lazyListState = rememberLazyGridState().apply {
                OnBottomReached(
                    onLoadMore = onLoadMore,
                    buffer = 0,
                )
            }
            LazyVerticalGrid(
                state = lazyListState,
                columns = GridCells.Fixed(2)
            ) {
                items(
                    count = viewState.characters.size
                ) { index ->
                    ImageWithFavorite(
                        item = viewState.characters[index],
                        onClickEvent = onClickEvent,
                        isFavorite = false,
                    )
                }
            }
        }
        SearchUiState.Wait -> Unit
    }
}

@Composable
fun ErrorMessageHolder(
    msg: String
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = msg, textAlign = TextAlign.Center,
            modifier = Modifier,
            fontSize = TextUnit.Unspecified
        )
    }
}

@Composable
fun ImageWithFavorite(
    item: CharacterEntity,
    onClickEvent: (CharacterEntity) -> Unit,
    isFavorite: Boolean,
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