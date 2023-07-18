package com.today.nail.service.ui

import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.today.nail.service.R
import com.today.nail.service.ui.theme.MainPurple
import com.today.nail.service.ui.util.component.ApplicationLogo

@Composable
fun SplashView(
    activityViewModel : TopLevelViewModel,
    viewModel : SplashViewModel = hiltViewModel(),
    navHostController: NavHostController
) {
    SplashScreen()
}

@Composable
private fun SplashScreen() {
    Box(
        modifier = Modifier
            .background(MainPurple)
            .fillMaxSize()
    ) {
       ApplicationLogo(
           modifier = Modifier.align(Alignment.Center)
       )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewSplash() {
    SplashScreen()
}