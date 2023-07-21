package com.today.nail.service.ui.util

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.today.nail.service.ui.theme.ColorA4A4A4

@Composable
fun InputTextFieldNormal(
    modifier: Modifier = Modifier,
    value : String,
    hintText : String,
    onValueChange : (String) -> Unit
) {
    val focusReq = remember { FocusRequester() }
    var focusColor by remember {
        mutableStateOf(ColorA4A4A4)
    }
    var hintTextDisable by remember {
        mutableStateOf(false)
    }
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        textStyle = TextStyle(
            color = Color.Black,
            fontSize = 16.dpToSp()
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(45.dp)
            .focusRequester(focusReq)
            .onFocusChanged {
                if (it.hasFocus) {
                    focusColor = Color.Black
                    hintTextDisable = true
                } else {
                    focusColor = ColorA4A4A4
                    hintTextDisable = false
                }
            }
            .bottomBorder(
                strokeWidth = 1.dp,
                color = focusColor
            ),
        decorationBox = { innerTextField ->
            Box(modifier = Modifier.fillMaxSize()) {
                if(value.isNotEmpty() || hintTextDisable) {
                    Box(modifier = Modifier.wrapContentSize().align(Alignment.CenterStart)) {
                        innerTextField()
                    }
                } else {
                    Text(
                        hintText,
                        color = ColorA4A4A4,
                        fontSize = 16.dpToSp(),
                        modifier = Modifier.align(Alignment.CenterStart)
                    )
                }
            }
        }
    )
}
