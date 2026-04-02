package com.kang.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kang.myapplication.ui.home.HomeScreen
import com.kang.myapplication.ui.navigation.LangTubeDestinations
import com.kang.myapplication.ui.player.PlayerScreen
import com.kang.myapplication.ui.search.SearchScreen
import com.kang.myapplication.ui.settings.SettingsScreen
import com.kang.myapplication.ui.theme.LangTubeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LangTubeTheme {
                LangTubeApp()
            }
        }
    }
}

@Composable
fun LangTubeApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = LangTubeDestinations.Home.route,
        modifier = Modifier.fillMaxSize(),
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(300)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(300)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(300)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(300)
            )
        }
    ) {
        composable(LangTubeDestinations.Home.route) {
            HomeScreen(
                onVideoClick = { videoId ->
                    navController.navigate(LangTubeDestinations.Player.createRoute(videoId))
                },
                onSearchClick = {
                    navController.navigate(LangTubeDestinations.Search.route)
                },
                onSettingsClick = {
                    navController.navigate(LangTubeDestinations.Settings.route)
                }
            )
        }
        composable(LangTubeDestinations.Search.route) {
            SearchScreen(
                onBackClick = { navController.popBackStack() },
                onVideoClick = { videoId ->
                    navController.navigate(LangTubeDestinations.Player.createRoute(videoId))
                }
            )
        }
        composable(LangTubeDestinations.Settings.route) {
            SettingsScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
        composable(LangTubeDestinations.Player.route) { backStackEntry ->
            val videoId = backStackEntry.arguments?.getString("videoId") ?: ""
            PlayerScreen(
                videoId = videoId,
                onBackClick = { navController.popBackStack() },
                onVideoClick = { newVideoId ->
                    navController.navigate(LangTubeDestinations.Player.createRoute(newVideoId))
                }
            )
        }
    }
}
