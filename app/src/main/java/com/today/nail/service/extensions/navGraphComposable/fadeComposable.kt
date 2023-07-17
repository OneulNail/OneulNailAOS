package com.autocrypt.move.kcallpax.extension

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.fadeComposable(
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
        fadeIn(tween(duration))
    },
    exitTransition = {
        fadeOut(tween(duration))
    },
    popEnterTransition = {
        fadeIn(tween(duration))
    },
    popExitTransition = {
        fadeOut(tween(duration))
    },
    content = content
)