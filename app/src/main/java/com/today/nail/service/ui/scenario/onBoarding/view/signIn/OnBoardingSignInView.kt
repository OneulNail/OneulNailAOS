package com.today.nail.service.ui.scenario.onBoarding.view.signIn

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.today.nail.service.R
import com.today.nail.service.ui.TopLevelViewModel
import com.today.nail.service.ui.scenario.onBoarding.navigationGraph.OnBoardingRoutes
import com.today.nail.service.ui.theme.ColorA4A4A4
import com.today.nail.service.ui.theme.ColorD9D9D9
import com.today.nail.service.ui.util.InputTextFieldNormal
import com.today.nail.service.ui.util.ToastHelper
import com.today.nail.service.ui.util.bottomBorder
import com.today.nail.service.ui.util.component.BackButtonWithSlogan
import com.today.nail.service.ui.util.component.CommonButton
import com.today.nail.service.ui.util.dpToSp

@Composable
fun OnBoardingSignView(
    activityViewModel: TopLevelViewModel,
    navHostController: NavHostController,
    onBoardingSignViewModel : OnBoardingSingInViewModel = hiltViewModel(),
) {
    val userId = onBoardingSignViewModel.userId.collectAsState().value
    val userPw = onBoardingSignViewModel.password.collectAsState().value

    Screen(
        userId = userId,
        onIdChange = { value ->
            onBoardingSignViewModel.updateId(value)
        },
        userPassword = userPw,
        onPasswordChange = { value ->
            onBoardingSignViewModel.updatePassword(value)
        },
        onClickLogin = {
            ToastHelper.showToast("준비중인 기능입니다.")
        },
        onClickRegister = {
            navHostController.navigate(OnBoardingRoutes.PhoneVerify.routes)
        },
        onClickHeaderBack = {
            navHostController.popBackStack()
        }
    )
}

@Composable
private fun Screen(
    userId : String = "-",
    onIdChange : (String) -> Unit,
    userPassword : String = "-",
    onPasswordChange : (String) -> Unit,
    onClickLogin : () -> Unit,
    onClickRegister : () -> Unit,
    onClickHeaderBack : () -> Unit,

) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {

        BackButtonWithSlogan(
            modifier = Modifier.padding(bottom = 43.dp)
        ) {
            onClickHeaderBack()
        }

        InputTextFieldNormal(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 25.dp),
            value = userId,
            hintText = "아이디",
            onValueChange = onIdChange,
        )


        InputTextFieldNormal(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 25.dp),
            value = userPassword,
            hintText = "비밀번호",
            onValueChange = onPasswordChange,
        )

        CommonButton(
            title = "로그인",
            onClick = onClickLogin,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 19.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 16.dp)
                .padding(bottom = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "카카오/네이버로 오늘네일\n간편하게 시작하기",
                color = ColorA4A4A4,
                fontSize = 14.dpToSp()
            )
            Spacer(modifier = Modifier.weight(1f))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .height(44.dp)
                        .width(1.dp)
                        .background(ColorA4A4A4)
                )
                Spacer(modifier = Modifier.width(20.dp))
                Image(
                    painter = painterResource(id = R.drawable.sns_kakao_login),
                    contentDescription = "카카오",
                    modifier = Modifier.size(44.dp)
                )
                Spacer(modifier = Modifier.width(30.dp))
                Image(
                    painter = painterResource(id = R.drawable.sns_naver_login),
                    contentDescription = "네이버",
                    modifier = Modifier.size(44.dp)
                )
            }

        }
        
        CommonButton(
            title = "회원가입",
            onClick = onClickRegister,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewScreen() {
    Screen(
        onIdChange = {},
        onPasswordChange = {},
        onClickLogin = {},
        onClickRegister = {},
        onClickHeaderBack = {},
    )
}