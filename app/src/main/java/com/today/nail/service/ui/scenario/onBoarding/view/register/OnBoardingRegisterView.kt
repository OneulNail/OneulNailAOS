package com.today.nail.service.ui.scenario.onBoarding.view.register

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.today.nail.service.ui.TopLevelNavigationRoutes
import com.today.nail.service.ui.TopLevelViewModel
import com.today.nail.service.ui.theme.Color0A7BE4
import com.today.nail.service.ui.theme.Color898989
import com.today.nail.service.ui.theme.ColorA4A4A4
import com.today.nail.service.ui.util.InputTextFieldWithPrimaryDesign
import com.today.nail.service.ui.util.TodayNailCheckBox
import com.today.nail.service.ui.util.component.BackButtonWithText
import com.today.nail.service.ui.util.component.StateButton
import com.today.nail.service.ui.util.dpToSp
import com.today.nail.service.ui.util.noRippleClickable

@Composable
fun OnBoardingRegisterView(
    activityViewModel : TopLevelViewModel,
    viewModel : OnBoardingRegisterViewModel = hiltViewModel(),
    navController: NavController
) {

    val nameStringValue = viewModel.nameFieldValue.collectAsState().value
    val nickNameStringValue = viewModel.nickNameFieldValue.collectAsState().value
    val passwordStringValue = viewModel.passwordFieldValue.collectAsState().value
    val passwordRecheckStringValue = viewModel.passwordRecheckFieldValue.collectAsState().value

    Screen(
        onNavigateToHome = {
            navController.navigate(TopLevelNavigationRoutes.HomeGraph.routes) {
                popUpTo(navController.graph.id) { inclusive = true }
            }
        },
        nameString = nameStringValue,
        nickNameString = nickNameStringValue,
        passwordStirng = passwordStringValue,
        passwordRecheckString = passwordRecheckStringValue,
        onChangeNameField = {
            viewModel.updateNameField(it)
        },
        onChangeNickNameField = {
            viewModel.updateNickNameField(it)
        },
        onChangePasswordField = {
            viewModel.updatePasswordField(it)
        },
        onChangePasswordRecheckField = {
            viewModel.updatePasswordRecheckField(it)
        },

    )
}

@Composable
private fun Screen(
    onNavigateToHome: () -> Unit,
    nameString: String,
    nickNameString: String,
    passwordStirng: String,
    passwordRecheckString: String,
    onChangeNameField : (String) -> Unit,
    onChangeNickNameField : (String) -> Unit,
    onChangePasswordField : (String) -> Unit,
    onChangePasswordRecheckField : (String) -> Unit,
) {

    val defaultModifier = Modifier.padding(horizontal = 16.dp)

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        state = rememberLazyListState()
    ) {
        item {
            Row(
                modifier = Modifier.padding(bottom = 28.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BackButtonWithText {

                }
                Text(
                    "회원가입",
                    color = Color.Black,
                    fontSize = 20.dpToSp(),
                    modifier = Modifier
                        .padding(bottom = 8.dp),
                )
            }
        }

        item {
            Column(
                modifier = defaultModifier
                    .fillMaxWidth()
                    .padding(bottom = 30.dp)
            ) {
                Text(
                    "이름",
                    color = Color.Black,
                    fontSize = 14.dpToSp(),
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(bottom = 10.dp)
                )

                InputTextFieldWithPrimaryDesign(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = nameString,
                    onValueChange = onChangeNameField,
                    hintText = "이름을 입력해주세요."
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    "네일 예약시 사용할 이름이므로, 꼭 실명을 기재해주세요.",
                    color = ColorA4A4A4,
                    fontSize = 12.dpToSp(),
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }

        }

        item {
            Column(
                modifier = defaultModifier
                    .fillMaxWidth()
                    .padding(bottom = 43.dp)
            ) {
                Text(
                    "닉네임(선택)",
                    color = Color.Black,
                    fontSize = 14.dpToSp(),
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(bottom = 10.dp)
                )

                InputTextFieldWithPrimaryDesign(
                    modifier = Modifier.fillMaxWidth(),
                    value = nickNameString,
                    onValueChange = onChangeNickNameField,
                    hintText = "닉네임을 입력해주세요."
                )
            }
        }

        item {
            Column(
                modifier = defaultModifier
                    .fillMaxWidth()
                    .padding(bottom = 30.dp)
            ) {
                Text(
                    "비밀번호",
                    color = Color.Black,
                    fontSize = 14.dpToSp(),
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(bottom = 10.dp)
                )

                InputTextFieldWithPrimaryDesign(
                    modifier = Modifier.fillMaxWidth(),
                    value = passwordStirng,
                    onValueChange = onChangePasswordField,
                    hintText = "비밀번호를 입력해주세요."
                )

                Spacer(modifier = Modifier.height(10.dp))

                InputTextFieldWithPrimaryDesign(
                    modifier = Modifier.fillMaxWidth(),
                    value = passwordRecheckString,
                    onValueChange = onChangePasswordRecheckField,
                    hintText = "비밀번호를 다시 한 번 입력해주세요."
                )

            }
        }

        item {
            val isChecked = false
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 23.dp)
                .border(1.dp, color = ColorA4A4A4)
            ) {
                val termModifier = Modifier.padding(start = 18.dp)
                AllAgreeTermsItem(
                    modifier = termModifier.padding(
                        top = 18.dp,
                        bottom = 41.dp
                    ),
                    isChecked = isChecked,
                ) {

                }

                TermItem(
                    modifier = termModifier.padding(bottom = 25.dp),
                    content = "이용약관에는 마케팅 정보 수신에 대한 동의와 관련된\n" +
                            "내용이 포함되어 있습니다.",
                    isChecked = false,
                    onClickCheckBox = {},
                    onClickTermLink = {}
                )

                TermItem(
                    modifier = termModifier,
                    content = "이용약관에는 개인정보 수집에 대한 동의와 관련된\n" +
                            "내용이 포함되어 있습니다.",
                    isChecked = false,
                    onClickCheckBox = {},
                    onClickTermLink = {}
                )

            }
        }
        item { Spacer(modifier = Modifier.height(150.dp))  }

    }

    Box(modifier = defaultModifier.fillMaxSize()) {
        StateButton(
            modifier = Modifier
                .padding(bottom = 67.dp)
                .align(Alignment.BottomCenter),
            title = "시작하기",
            enable = true
        ) {
            onNavigateToHome()
        }
    }
}

@Composable
private fun AllAgreeTermsItem(
    modifier: Modifier = Modifier,
    isChecked: Boolean,
    onClick: () -> Unit,
) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TodayNailCheckBox(
            modifier = Modifier.padding(end = 10.dp),
            isChecked = isChecked,
            onClick = onClick,
        )

        Text(
            "약관 전체동의",
            color = Color898989,
            fontSize = 13.dpToSp(),
            fontWeight = FontWeight.Bold
        )

    }

}
@Composable
private fun TermItem(
    modifier : Modifier = Modifier,
    content : String,
    isChecked : Boolean,
    onClickCheckBox :() ->  Unit,
    onClickTermLink : () -> Unit,
) {
    Row(
        modifier = modifier
    ) {
        TodayNailCheckBox(
            modifier = Modifier.padding(end = 10.dp),
            isChecked = isChecked,
            onClick = onClickCheckBox,
        )

        Column {
           Row(
               modifier = Modifier.padding(bottom = 17.dp),
               verticalAlignment = Alignment.CenterVertically
           ) {
               Text(
                   "이용약관에 동의합니다.",
                   color = Color898989,
                   fontSize = 11.dpToSp(),
                   fontWeight = FontWeight.Normal
               )
               Text(
                   "본문보기",
                   color = Color0A7BE4,
                   fontSize = 10.dpToSp(),
                   textDecoration = TextDecoration.Underline,
                   modifier = Modifier
                       .padding(start = 3.dp)
                       .noRippleClickable {
                           onClickTermLink()
                       }
               )
           }
            Text(
                content,
                color = Color898989,
                fontSize = 11.dpToSp(),
                fontWeight = FontWeight.Normal
            )
        }
    }


}
@Preview(showBackground = true)
@Composable
private fun PreviewScreen() {
    Screen(onNavigateToHome = {},
        "",
        "",
        "",
        "",
        {},
        {},
        {},
        {},
    )
}