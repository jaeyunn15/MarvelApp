package com.project.marvelapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.project.marvelapp.navGraph.Graph
import com.project.marvelapp.navGraph.RootNavGraph
import com.project.marvelapp.theme.MarvelsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            MarvelsTheme {
                RootNavGraph(
                    navController = navController,
                    startDestination = Graph.MAIN
                )
            }
        }
    }
}