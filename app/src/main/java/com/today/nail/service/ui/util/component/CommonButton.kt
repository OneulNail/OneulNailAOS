package com.today.nail.service.ui.util.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.today.nail.service.ui.theme.Color7A00C5
import com.today.nail.service.ui.util.dpToSp
import com.today.nail.service.ui.util.noRippleClickable

@Composable
fun CommonButton(
    title : String,
    modifier : Modifier = Modifier,
    onClick : () -> Unit,
) {
    Box(
        modifier = modifier
            .border(
                width = 1.dp,
                color = Color7A00C5,
                shape = RoundedCornerShape(10.dp)
            )
            .fillMaxWidth()
            .height(45.dp)
            .noRippleClickable {
                onClick()
            }
    ){
        Text(
            title,
            color = Color.Black,
            fontSize = 16.dpToSp(),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun ShowPreview() {
    CommonButton(
        "이거슨 테스트 버튼이요"
    ) {
    }
}