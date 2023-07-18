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

@Composable
fun SplashView(
    activityViewModel : TopLevelViewModel,
    viewModel : SplashViewModel = hiltViewModel(),
    navHostController: NavHostController
) {

}

@Composable
private fun SplashScreen() {
    Box(
        modifier = Modifier
            .background(MainPurple)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .wrapContentWidth()
                .align(Alignment.Center)
        ) {
            Image(
                painter = painterResource(id = R.drawable.app_logo),
                contentDescription = "application logo",
                modifier = Modifier
                    .padding(bottom = 11.dp)
                    .width(137.dp)
                    .height(138.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.app_logo_text),
                contentDescription = "application logo text",
                modifier = Modifier
                    .width(137.dp)
                    .height(49.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewSplash() {
    SplashScreen()
}