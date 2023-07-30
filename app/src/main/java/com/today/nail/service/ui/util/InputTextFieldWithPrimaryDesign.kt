package com.today.nail.service.ui.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.today.nail.service.ui.theme.ColorBEA3EA

@Composable
fun InputTextFieldWithPrimaryDesign(
    modifier: Modifier = Modifier,
    value : String,
    hintText : String? = null,
    onValueChange : (String) -> Unit,
) {

    val focusReq = remember { FocusRequester() }
    var hintTextDisable by remember {
        mutableStateOf(false)
    }

    BasicTextField(
        modifier = modifier
            .background(
                color = ColorBEA3EA,
                shape = RoundedCornerShape(10.dp)
            )
            .height(45.dp)
            .focusRequester(focusReq)
            .onFocusChanged {
                hintTextDisable = it.hasFocus
            },
        value = value,
        onValueChange = onValueChange,
        cursorBrush = SolidColor(Color.White),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        textStyle = TextStyle(
            color = Color.White,
            fontSize = 14.dpToSp(),
        ),
        decorationBox = { innerTextField ->
            Box(
                modifier = modifier
                    .fillMaxSize()
            ) {
                if(value.isNotEmpty() || hintTextDisable) {
                    Box(modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.CenterStart)
                        .padding(start = 3.dp)) {
                        innerTextField()
                    }
                } else {
                    if(hintText != null) {
                        Text(
                            hintText,
                            color = Color.White,
                            fontSize = 14.dpToSp(),
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                                .padding(start = 3.dp)
                        )
                    }
                }
            }

        }
    )
}

@Preview(showBackground = true)
@Composable
private fun ShowComponent() {
    InputTextFieldWithPrimaryDesign(
        modifier = Modifier.width(208.dp),
        value = "",
        hintText = "인증번호 입력",
        onValueChange =  {},
    )
}