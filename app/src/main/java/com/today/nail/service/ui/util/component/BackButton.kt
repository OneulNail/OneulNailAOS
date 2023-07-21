package com.today.nail.service.ui.util.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.today.nail.service.R
import com.today.nail.service.ui.theme.ColorA4A4A4
import com.today.nail.service.ui.util.dpToSp
import com.today.nail.service.ui.util.noRippleClickable

@Composable
fun BackButtonWithSlogan(
    onClick :() -> Unit
) {
    Column(modifier = Modifier.wrapContentSize()) {
        Box(modifier = Modifier
            .wrapContentSize()
            .padding(bottom = 6.dp)
            .noRippleClickable {
                onClick()
            }
        ) {
            Image(
                painter = painterResource(id = R.drawable.button_back),
                contentDescription ="back button",
                modifier = Modifier
                    .padding(14.dp)
                    .size(30.dp)
                    .align(alignment = Alignment.Center)
            )
        }

        val defaultModifier = Modifier.padding(horizontal = 24.dp)
        Text(
            "네일을 찾아 떠나는 설레는 시간",
            color = ColorA4A4A4,
            fontSize = 14.dpToSp(),
            modifier = defaultModifier.padding(bottom = 3.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.app_logo_text),
            contentDescription = "app logo text",
            modifier = defaultModifier
                .padding(bottom = 43.dp)
                .width(87.dp)
                .height(31.dp)
        )
    }

}