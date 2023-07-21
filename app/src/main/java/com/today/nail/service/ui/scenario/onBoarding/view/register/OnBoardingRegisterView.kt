package com.today.nail.service.ui.scenario.onBoarding.view.register

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.today.nail.service.ui.TopLevelViewModel

@Composable
fun OnBoardingRegisterView(
    activityViewModel : TopLevelViewModel,
    viewModel : OnBoardingRegisterViewModel = hiltViewModel(),
    navController: NavController
) {
    Screen()
}

@Composable
private fun Screen() {
    Box(
       modifier = Modifier.fillMaxSize()
    ) {
        Text(
            "여기는 회원가입 정보입력 뷰입니다.",
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewScreen() {
    Screen()
}