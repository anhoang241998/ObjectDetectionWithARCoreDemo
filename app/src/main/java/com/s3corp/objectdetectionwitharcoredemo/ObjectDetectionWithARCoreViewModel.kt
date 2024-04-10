package com.s3corp.objectdetectionwitharcoredemo

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.s3corp.objectdetectionwitharcoredemo.detail.navigation.DetailNavigation
import com.s3corp.objectdetectionwitharcoredemo.home.navigation.HomeNavigation
import com.s3corp.objectdetectionwitharcoredemo.home.presentation.HomeScreen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ObjectDetectionWithARCoreViewModel @Inject constructor() : ViewModel() {

    private lateinit var mainNavController: NavHostController

    private var destinationScreen: String? by mutableStateOf(null)
    private var currentScreen: String? by mutableStateOf(HomeNavigation.HomeScreen.route)

    private var currentExit: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
        ExitTransition.None
    }
    private var destinationEnter: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
        EnterTransition.None
    }

    // Transition
    private val enterPush: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
        fadeIn(
            animationSpec = tween(ENTER_DURATION, easing = LinearEasing)
        ) + slideIntoContainer(
            animationSpec = tween(ENTER_DURATION, easing = LinearEasing),
            towards = AnimatedContentTransitionScope.SlideDirection.Start,
            initialOffset = { it }
        )
    }

    private val exitPush: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
        fadeOut(
            animationSpec = tween(EXIT_DURATION, easing = LinearEasing)
        ) + slideOutOfContainer(
            animationSpec = tween(EXIT_DURATION, easing = LinearEasing),
            towards = AnimatedContentTransitionScope.SlideDirection.End,
            targetOffset = { -it / 4 }
        )
    }

    private val enterPop: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
        fadeIn(
            animationSpec = tween(EXIT_DURATION, easing = LinearEasing)
        ) + slideIntoContainer(
            animationSpec = tween(EXIT_DURATION, easing = LinearEasing),
            towards = AnimatedContentTransitionScope.SlideDirection.Start,
            initialOffset = { -it / 4 }
        )
    }

    private val exitPop: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
        fadeOut(
            animationSpec = tween(ENTER_DURATION, easing = LinearEasing)
        ) + slideOutOfContainer(
            animationSpec = tween(ENTER_DURATION, easing = LinearEasing),
            towards = AnimatedContentTransitionScope.SlideDirection.End,
            targetOffset = { it }
        )
    }

    @Composable
    fun ObjectDetectionWithARCoreDemoNavHost() {
        mainNavController = rememberNavController()

        NavHost(
            navController = mainNavController,
            startDestination = HomeNavigation.HomeScreen.route,
            enterTransition = { EnterTransition.None },
            exitTransition = { ExitTransition.None }
        ) {
            composable(
                HomeNavigation.HomeScreen.route,
                enterTransition = {
                    if (destinationScreen == HomeNavigation.HomeScreen.route) {
                        destinationEnter()
                    } else {
                        EnterTransition.None
                    }
                },
                exitTransition = {
                    if (currentScreen == HomeNavigation.HomeScreen.route) {
                        currentExit()
                    } else {
                        ExitTransition.None
                    }
                }
            ) {
                HomeScreen(
                    onMoveToDetailTapped = {
                        push(DetailNavigation.DetailScreen.route)
                    }
                )
            }

            composable(
                DetailNavigation.DetailScreen.route,
                enterTransition = {
                    if (destinationScreen == DetailNavigation.DetailScreen.route) {
                        destinationEnter()
                    } else {
                        EnterTransition.None
                    }
                },
                exitTransition = {
                    if (currentScreen == DetailNavigation.DetailScreen.route) {
                        currentExit()
                    } else {
                        ExitTransition.None
                    }
                }
            ) {
                HomeScreen(
                    onMoveToDetailTapped = {
                        pop()
                    }
                )
            }
        }
    }

    private fun push(routeName: String, animated: Boolean = true) {
        if (animated) {
            currentExit = exitPush
            destinationEnter = enterPush
        } else {
            currentExit = { ExitTransition.None }
            destinationEnter = { EnterTransition.None }
        }

        val currentBackStackEntry = mainNavController.currentBackStackEntry
        currentScreen = currentBackStackEntry?.destination?.route ?: HomeNavigation.HomeScreen.route
        destinationScreen = routeName

        mainNavController.navigate(routeName)
    }

    private fun pop(animated: Boolean = true) {
        if (animated) {
            currentExit = exitPop
            destinationEnter = enterPop
        } else {
            currentExit = { ExitTransition.None }
            destinationEnter = { EnterTransition.None }
        }

        val currentBackStackEntry = mainNavController.currentBackStackEntry
        currentScreen = currentBackStackEntry?.destination?.route ?: HomeNavigation.HomeScreen.route

        val previousBackStackEntry = mainNavController.previousBackStackEntry
        destinationScreen = previousBackStackEntry?.destination?.route ?: HomeNavigation.HomeScreen.route
        mainNavController.navigateUp()

    }

    companion object {
        private const val ENTER_DURATION = 200
        private const val EXIT_DURATION = 300
    }
}