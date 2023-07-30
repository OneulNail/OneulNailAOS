package com.today.nail.service.ui.util.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.today.nail.service.R

@Composable
fun ApplicationLogo(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .wrapContentHeight()
            .wrapContentWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.app_logo),
            contentDescription = "application logo",
            modifier = Modifier
                .padding(bottom = 11.dp)
                .width(137.dp)
                .height(138.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.app_logo_text),
            contentDescription = "application logo text",
            modifier = Modifier
                .width(137.dp)
                .height(49.dp)
        )
    }
}