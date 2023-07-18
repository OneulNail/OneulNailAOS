package com.today.nail.service.ui.scenario.onBoarding.view.signIn

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.today.nail.service.ui.TopLevelViewModel
import com.today.nail.service.ui.util.dpToSp

@Composable
fun OnBoardingSignView(
    activityViewModel: TopLevelViewModel,
    navHostController: NavHostController,
    onBoardingSignViewModel : OnBoardingSingInViewModel = hiltViewModel(),
) {
    Screen()
}

@Composable
private fun Screen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "로그인 화면 입니다. TODO..",
            fontSize = 32.dpToSp(),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewScreen() {
    Screen()
}