package com.project.marvelapp.navGraph

import androidx.annotation.DrawableRes
import com.project.marvelapp.component.BottomBar.BOTTOM_FAVORITE
import com.project.marvelapp.component.BottomBar.BOTTOM_HOME
import com.project.marvelapp.component.BottomBar.SELECT_FAVORITE
import com.project.marvelapp.component.BottomBar.SELECT_HOME
import com.project.marvelapp.favoriteRoute
import com.project.marvelapp.navigation.homeRoute

sealed class BottomNavItem(
    val title: Int,
    @DrawableRes val icon: Int,
    val screenRoute: String
) {
    object Home : BottomNavItem(BOTTOM_HOME, SELECT_HOME, homeRoute)
    object Favorite : BottomNavItem(BOTTOM_FAVORITE, SELECT_FAVORITE, favoriteRoute)
}