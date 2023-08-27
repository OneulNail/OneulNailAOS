package com.today.nail.service.ui.scenario.onBoarding.view.phoneVerify

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.today.nail.service.ui.TopLevelViewModel
import com.today.nail.service.ui.scenario.onBoarding.navigationGraph.OnBoardingRoutes
import com.today.nail.service.ui.scenario.onBoarding.view.register.OnBoardingRegisterViewModel
import com.today.nail.service.ui.theme.Color696969
import com.today.nail.service.ui.theme.Color7A00C5
import com.today.nail.service.ui.theme.ColorA4A4A4
import com.today.nail.service.ui.theme.ColorBEA3EA
import com.today.nail.service.ui.theme.ColorF1E4F9
import com.today.nail.service.ui.util.InputTextFieldWithPrimaryDesign
import com.today.nail.service.ui.util.ToastHelper
import com.today.nail.service.ui.util.component.BackButtonWithSlogan
import com.today.nail.service.ui.util.component.StateButton
import com.today.nail.service.ui.util.dpToSp
import com.today.nail.service.ui.util.noRippleClickable

@Composable
fun OnBoardingPhoneVerifyView(
    activityViewModel : TopLevelViewModel,
    viewModel : OnBoardingPhoneVerifyViewModel = hiltViewModel(),
    registerViewModel: OnBoardingRegisterViewModel = hiltViewModel(),
    navController: NavController
) {

    val codeSendState = viewModel.verifyCodeSendState.collectAsState().value
    val canMoveRegisterView = viewModel.canMoveToRegisterView.collectAsState().value

    val phoneStringValue = activityViewModel.phoneNumFieldValue.collectAsState().value
    val verifyStringValue = viewModel.verifyFieldValue.collectAsState().value

    Screen(
        isVerifyCodeSend = codeSendState,
        isVerifyCodeFilled = canMoveRegisterView,
        phoneString = phoneStringValue,
        verifyString = verifyStringValue,
        onChangePhoneField = {
            activityViewModel.updatePhoneNumField(it)
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
        },
        onClickBackHeader = {
            navController.popBackStack()
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
    onClickConfirm : () -> Unit,
    onClickBackHeader : () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        BackButtonWithSlogan(
            modifier = Modifier.padding(bottom = 54.dp)
        ) {
            onClickBackHeader()
        }

        Text(
            "안녕하세요!\n휴대폰 번호로 가입해주세요.",
            color = Color.Black,
            fontSize = 14.dpToSp(),
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .padding(bottom = 30.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            InputTextFieldWithPrimaryDesign(
                modifier = Modifier
                    .weight(2f)
                    .padding(3.dp),
                hintText = "휴대폰 번호(- 없이 숫자만 입력)",
                value = phoneString,
                onValueChange = onChangePhoneField,
            )
            VerifyButton(
                isClicked = isVerifyCodeSend,
                onClick = onClickSendVerifyCode
            )
        }

        if(isVerifyCodeSend) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                InputTextFieldWithPrimaryDesign(
                    modifier = Modifier
                        .weight(2f)
                        .padding(3.dp),
                    hintText = "인증번호 입력",
                    value = verifyString,
                    onValueChange = onChangeVerifyField,
                )
                Spacer(modifier = Modifier.width(119.dp))
            }


            Text(
                "절대 타인에게 공유하지 마세요.",
                color = ColorA4A4A4,
                fontSize = 13.dpToSp(),
                modifier = Modifier
                    .padding(horizontal = 21.dp)
                    .padding(bottom = 30.dp)
            )

            StateButton(
                modifier = Modifier.padding(horizontal = 16.dp),
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
            .width(119.dp)
            .background(
                if (isClicked) {
                    ColorF1E4F9
                } else {
                    ColorBEA3EA
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
            fontWeight = FontWeight.Bold,
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
            {},
            {}
        )
    }
}