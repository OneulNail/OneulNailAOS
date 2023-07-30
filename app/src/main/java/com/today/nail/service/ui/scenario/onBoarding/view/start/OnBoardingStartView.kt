package com.today.nail.service.ui.scenario.onBoarding.view.start

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.today.nail.service.R
import com.today.nail.service.ui.TopLevelViewModel
import com.today.nail.service.ui.scenario.onBoarding.navigationGraph.OnBoardingRoutes
import com.today.nail.service.ui.theme.Color696969
import com.today.nail.service.ui.theme.ColorMainF1E4F9
import com.today.nail.service.ui.util.component.ApplicationLogo
import com.today.nail.service.ui.util.component.CommonButton
import com.today.nail.service.ui.util.dpToSp

@Composable
fun OnBoardingStartView(
    activityViewModel : TopLevelViewModel,
    navHostController: NavHostController,
) {
    OnBoardingStartScreen(
        onClickStart = {
            navHostController.navigate(OnBoardingRoutes.SignIn.routes)
        }
    )
}

@Composable
private fun OnBoardingStartScreen(
    onClickStart : () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorMainF1E4F9),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ApplicationLogo(
            modifier = Modifier
                .padding(bottom = 28.dp)
        )

        Text(
            "네일 디자인, 샵 찾기부터 예약까지",
            color = Color696969,
            fontSize = 14.dpToSp(),
            fontWeight = FontWeight.Normal
        )
        Row(
            modifier = Modifier.padding(bottom = 93.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.app_logo_text),
                contentDescription = "application logo",
                modifier = Modifier
                    .padding(end = 2.dp)
                    .width(48.dp)
                    .height(17.dp)
            )
            Text(
                "에서 한 번에 해결",
                color = Color696969,
                fontSize = 14.dpToSp(),
                fontWeight = FontWeight.Normal
            )
        }

        CommonButton(
            title = "시작하기",
            onClick = onClickStart,
            modifier = Modifier.padding(horizontal = 55.dp)
        )
    }

}

@Preview(showBackground = true)
@Composable
private fun PreviewScreen() {
    OnBoardingStartScreen(
        onClickStart = {}
    )
}