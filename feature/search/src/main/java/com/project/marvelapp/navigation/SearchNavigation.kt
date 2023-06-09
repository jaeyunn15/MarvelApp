package com.project.marvelapp.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.project.marvelapp.SearchScreen

const val homeRoute = "home_route"

fun NavGraphBuilder.homeNavGraph(navHostController: NavHostController, modifier: Modifier) {
    composable(
        route = homeRoute
    ) {
        SearchScreen(modifier = modifier)
    }
}