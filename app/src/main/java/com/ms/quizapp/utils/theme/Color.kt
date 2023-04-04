package com.ms.quizapp.utils.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.ms.quizapp.R

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)

val colorAppNameText = Color(0xFF1976D2)
val colorPrimary = Color(0xFF1976D2)
val colorPrimaryDark = Color(0xFF004C97)
val colorWhite = Color(0xFFFFFFFF)
val colorBlack = Color(0xFF000000)
val colorStrokeLine = Color(0xFFC9C9C9)
val colorGrey = Color(0xFFA2A2A2)


@Composable
fun light(): Color {
    return colorResource(id = R.color.light)
}

@Composable
fun dark(): Color {
    return colorResource(id = R.color.dark)
}

@Composable
fun switchTrack(): Color {
    return colorResource(id = R.color.default_switch_track_background)
}

