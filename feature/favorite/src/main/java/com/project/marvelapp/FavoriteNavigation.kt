package com.project.marvelapp

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val favoriteRoute = "favorite_route"

fun NavGraphBuilder.favoriteNavGraph() {
    composable(
        route = favoriteRoute
    ) {
        FavoriteScreen()
    }
}