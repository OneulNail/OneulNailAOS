package com.today.nail.service.ui.util

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.today.nail.service.ui.theme.Color696969
import com.today.nail.service.ui.theme.Color7A00C5
import com.today.nail.service.ui.theme.Color898989

@Composable
fun TodayNailCheckBox(
    modifier : Modifier = Modifier,
    buttonSize : Int = 15,
    isChecked : Boolean,
    onClick : () -> Unit,
) {

    val buttonModifier = if(isChecked) {
        modifier.background(
            color = Color7A00C5,
            shape = RoundedCornerShape(2.dp)
        )
    } else {
        modifier.border(
            width = 2.dp,
            color = Color898989,
            shape = RoundedCornerShape(2.dp)
        )
    }
    Box(
        modifier = buttonModifier
            .size(buttonSize.dp)
            .noRippleClickable { onClick() }
    ) {

    }
}


@Preview(showBackground = true)
@Composable
fun PreviewComponentUnChecked() {
    TodayNailCheckBox(
        isChecked = false
    ) {

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewComponentChecked() {
    TodayNailCheckBox(
        isChecked = true
    ) {

    }
}