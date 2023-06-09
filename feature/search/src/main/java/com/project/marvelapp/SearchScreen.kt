package com.project.marvelapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.project.marvelapp.component.MessageBoxLayout
import com.project.marvelapp.component.OnBottomReached
import com.project.marvelapp.component.SearchTopBar
import com.project.marvelapp.component.loadImageData
import com.project.marvelapp.feature.search.R
import com.project.marvelapp.state.SearchResultUiState

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
                searchKeyWord = viewModel.searchKeyword.collectAsStateWithLifecycle().value.getOrNull()
                    .orEmpty()
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
    val viewState = viewModel.uiState.collectAsStateWithLifecycle().value

    CharacterScreen(
        viewState = viewState,
        onLoadMore = viewModel::onLoadMoreCharacters,
        onClickEvent = {
            if (it.isFavorite) {
                viewModel.removeFavorite(it)
            } else {
                viewModel.addFavorite(it)
            }
        }
    )
}

@Composable
fun CharacterScreen(
    viewState: SearchResultUiState,
    modifier: Modifier = Modifier,
    onLoadMore: () -> Unit,
    onClickEvent: (CharacterUiModel) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(), contentAlignment = Alignment.Center
        ) {
            when (viewState) {
                SearchResultUiState.EmptyQuery -> {
                    MessageBoxLayout(message = stringResource(R.string.min_two_word_warning_message))
                }

                is SearchResultUiState.LoadFailed -> {
                    MessageBoxLayout(message = viewState.errorMsg)
                }

                is SearchResultUiState.Success -> {
                    val lazyListState = rememberLazyGridState().apply {
                        OnBottomReached(
                            onLoadMore = onLoadMore,
                            buffer = 0,
                        )
                    }

                    if (viewState.isEmpty()) {
                        MessageBoxLayout(message = stringResource(R.string.empty_search_result_message))
                    } else {
                        Box(
                            modifier = Modifier.fillMaxHeight()
                        ) {
                            LazyVerticalGrid(
                                state = lazyListState,
                                columns = GridCells.Fixed(2)
                            ) {
                                items(
                                    count = viewState.characters.size,
                                    key = { viewState.characters[it].id }
                                ) { index ->
                                    ImageWithFavorite(
                                        item = viewState.characters[index],
                                        onClickEvent = onClickEvent
                                    )
                                }
                            }
                            if (viewState.isPaging) {
                                LinearProgressIndicator(
                                    modifier = Modifier
                                        .align(Alignment.BottomCenter)
                                        .fillMaxWidth()
                                )
                            }
                        }
                    }
                }

                SearchResultUiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                SearchResultUiState.Wait -> Unit
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

    val bg = if (item.isFavorite) {
        Color.Blue
    } else {
        Color.Transparent
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(bg)
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