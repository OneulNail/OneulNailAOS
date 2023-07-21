package com.today.nail.service.ui.scenario.onBoarding.view.phoneVerify

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.today.nail.service.ui.TopLevelViewModel
import com.today.nail.service.ui.scenario.onBoarding.navigationGraph.OnBoardingRoutes
import com.today.nail.service.ui.theme.Color696969
import com.today.nail.service.ui.theme.Color7A00C5
import com.today.nail.service.ui.theme.ColorBEA3EA
import com.today.nail.service.ui.theme.ColorF1E4F9
import com.today.nail.service.ui.util.ToastHelper
import com.today.nail.service.ui.util.component.BackButtonWithSlogan
import com.today.nail.service.ui.util.component.StateButton
import com.today.nail.service.ui.util.dpToSp
import com.today.nail.service.ui.util.noRippleClickable

@Composable
fun OnBoardingPhoneVerifyView(
    activityViewModel : TopLevelViewModel,
    viewModel : OnBoardingPhoneVerifyViewModel = hiltViewModel(),
    navController: NavController
) {

    val codeSendState = viewModel.verifyCodeSendState.collectAsState().value
    val canMoveRegisterView = viewModel.canMoveToRegisterView.collectAsState().value

    val phoneStringValue = viewModel.phoneNumFieldValue.collectAsState().value
    val verifyStringValue = viewModel.verifyFieldValue.collectAsState().value

    Screen(
        isVerifyCodeSend = codeSendState,
        isVerifyCodeFilled = canMoveRegisterView,
        phoneString = phoneStringValue,
        verifyString = verifyStringValue,
        onChangePhoneField = {
            viewModel.updatePhoneNumField(it)
        },
        onChangeVerifyField = {
            viewModel.updateVerifyField(it)
        },
        onClickSendVerifyCode = {
            viewModel.requestVerifyCode(
                onSuccess = {
                    ToastHelper.showToast("인증번호가 전송되었습니다.")
                },
                onFail = {
                    ToastHelper.showToast("휴대폰 번호를 정확하게 입력해주세요.")
                },
            )
        },
        onClickConfirm = {
            navController.navigate(OnBoardingRoutes.Register.routes)
        }
    )
}

@Composable
private fun Screen(
    isVerifyCodeSend : Boolean,
    isVerifyCodeFilled : Boolean,
    phoneString : String,
    verifyString : String,
    onChangePhoneField : (String) -> Unit,
    onChangeVerifyField : (String) -> Unit,
    onClickSendVerifyCode : () -> Unit,
    onClickConfirm : () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        BackButtonWithSlogan {

        }

        Text(
            "안녕하세요!\n휴대폰 번호로 가입해주세요."
        )

        Row() {
            TextField(
                value = phoneString,
                onValueChange = onChangePhoneField,
                placeholder = {
                    Text(
                        text = "휴대폰 번호(-없이 숫자만 입력)"
                    )
                }
            )

            VerifyButton(
                isClicked = isVerifyCodeSend,
                onClick = onClickSendVerifyCode
            )
        }

        if(isVerifyCodeSend) {
            TextField(
                value = verifyString,
                onValueChange = onChangeVerifyField,
                placeholder = {
                    Text(
                        text = "인증번호 입력"
                    )
                }
            )

            Text(
                "절대 타인에게 공유하지 마세요."
            )

            StateButton(
                title = "동의하고 시작하기",
                enable = isVerifyCodeFilled,
                onClickButton = onClickConfirm
            )
        }
    }
}

@Composable
private fun VerifyButton(
    isClicked : Boolean,
    onClick : () -> Unit,
) {
    Box(
        modifier= Modifier
            .height(45.dp)
            .background(
                if (isClicked) {
                    ColorBEA3EA
                } else {
                    ColorF1E4F9
                },
                shape = RoundedCornerShape(10.dp)
            )
            .noRippleClickable {
                if (!isClicked) {
                    onClick()
                }
            }
    ) {
        Text(
            "인증번호 요청",
            color = if (isClicked) {
                Color696969
            } else {
                Color.White
            },
            fontSize = 14.dpToSp(),
            fontWeight = FontWeight.Normal,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun PreviewScreen() {
    Column {
        Screen(
            true,
            false,
            "",
            "",
            {},
            {},
            {},
            {}
        )
    }
}