package com.today.nail.service.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.today.nail.service.ui.scenario.onBoarding.navigationGraph.OnBoardingRoutes
import com.today.nail.service.ui.theme.ColorMainF1E4F9
import com.today.nail.service.ui.util.component.ApplicationLogo
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SplashView(
    activityViewModel : TopLevelViewModel,
    viewModel : SplashViewModel = hiltViewModel(),
    navHostController: NavHostController
) {
    SplashScreen()

    LaunchedEffect(Unit) {
        viewModel.canMoveToOnBoard.collectLatest { value ->
            if(value) {
                navHostController.navigate(OnBoardingRoutes.Start.routes) {
                    popUpTo(TopLevelNavigationRoutes.SplashGraph.routes)
                }
            }
        }
    }
}

@Composable
private fun SplashScreen() {
    Box(
        modifier = Modifier
            .background(ColorMainF1E4F9)
            .fillMaxSize()
    ) {
       ApplicationLogo(
           modifier = Modifier.align(Alignment.Center)
       )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewScreen() {
    SplashScreen()
}