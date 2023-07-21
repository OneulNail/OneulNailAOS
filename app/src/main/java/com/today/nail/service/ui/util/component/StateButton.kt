package com.today.nail.service.ui.util.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.today.nail.service.ui.theme.Color7A00C5
import com.today.nail.service.ui.theme.ColorA4A4A4
import com.today.nail.service.ui.util.dpToSp
import com.today.nail.service.ui.util.noRippleClickable

@Composable
fun StateButton(
    title : String,
    enable : Boolean,
    onClickButton : () -> Unit
) {
    Box(
       modifier = Modifier
           .background(
               if (enable) {
                   Color7A00C5
               } else {
                   ColorA4A4A4
               }
           )
           .fillMaxWidth()
           .height(45.dp)
           .noRippleClickable {
               if (enable) {
                   onClickButton()
               }
           }
    ) {
        Text(
            title,
            color = Color.White,
            fontSize = 16.dpToSp(),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun PreviewComponent() {
    Column {
        StateButton(
            title  = "테스트 버튼",
            enable = true) {

        }
        StateButton(
            title = "테스트 버튼",
            enable = false) {

        }
    }
}