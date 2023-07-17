package com.autocrypt.move.kcallpax.extension

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.*
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.slideComposable(
    duration : Int,
    route : String,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    content: @Composable() (AnimatedVisibilityScope.(NavBackStackEntry) -> Unit)
) = composable(
    route = route,
    arguments = arguments,
    deepLinks = deepLinks,
    enterTransition = {
        slideIntoContainer(
            AnimatedContentScope.SlideDirection.Right,
            animationSpec = tween(duration)
        )
    },
    exitTransition = {
        slideOutOfContainer(
            AnimatedContentScope.SlideDirection.Right,
            animationSpec = tween(duration)
        )
    },
    popEnterTransition = {
        slideIntoContainer(AnimatedContentScope.SlideDirection.Left, tween(duration))
    },
    popExitTransition = {
        slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, tween(duration))
    },
    content = content
)