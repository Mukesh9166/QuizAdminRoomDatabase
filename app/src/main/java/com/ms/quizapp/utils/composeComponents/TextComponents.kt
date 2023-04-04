package com.ms.quizapp.utils.composeComponents

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ms.quizapp.R


@Composable
fun BodyText(
    text: String?,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.onPrimary,
    maxLines: Int = Int.MAX_VALUE,
    fontSize: Int = 17,
    overflow: TextOverflow = TextOverflow.Clip,
    textAlign: TextAlign? = null,
    fontFamily: FontFamily = FontFamily(Font(R.font.causten_regular))
) {
    Text(
        text = text ?: "",
        modifier = Modifier.then(modifier),
        maxLines = maxLines,
        overflow = overflow,
        textAlign = textAlign,

        style = TextStyle(
            color = color,
            fontSize = fontSize.sp,
            fontFamily = fontFamily
        )
    )
}

@Composable
fun BodyText(
    @StringRes textResource: Int,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.onPrimary,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip,
    textAlign: TextAlign? = null,
    fontFamily: FontFamily = FontFamily(Font(R.font.causten_regular))
) {
    BodyText(
        text = stringResource(id = textResource),
        modifier = modifier,
        color = color,
        maxLines = maxLines,
        overflow = overflow,
        textAlign = textAlign,
        fontFamily = fontFamily
    )
}

@Composable
fun BodyBoldText(
    @StringRes textResource: Int,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.onPrimary,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip,
    textAlign: TextAlign? = null,
    fontFamily: FontFamily = FontFamily(Font(R.font.causten_bold))
) {
    BodyText(
        text = stringResource(id = textResource),
        modifier = modifier,
        color = color,
        maxLines = maxLines,
        overflow = overflow,
        textAlign = textAlign,
        fontFamily = fontFamily
    )
}

@Composable
fun BodyBoldText(
    text: String?,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.onPrimary,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip,
    textAlign: TextAlign? = null,
    fontSize: Int = 17,
    fontFamily: FontFamily = FontFamily(Font(R.font.causten_bold))
) {
    BodyText(
        text = text,
        modifier = modifier,
        color = color,
        maxLines = maxLines,
        overflow = overflow,
        textAlign = textAlign,
        fontSize = fontSize,
        fontFamily = fontFamily
    )
}

@Composable
fun NanoText(
    text: String?,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.onPrimary,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip,
    textAlign: TextAlign? = null,
    fontFamily: FontFamily = FontFamily(Font(R.font.causten_regular))
) {
    Text(
        text = text ?: "",
        modifier = Modifier.then(modifier),
        maxLines = maxLines,
        overflow = overflow,
        textAlign = textAlign,
        style = TextStyle(
            color = color,
            fontSize = 13.sp,
            fontFamily = fontFamily
        )
    )
}

@Composable
fun NanoText(
    @StringRes textResource: Int,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.onPrimary,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip,
    textAlign: TextAlign? = null,
    fontFamily: FontFamily = FontFamily(Font(R.font.causten_regular))
) {
    NanoText(
        text = stringResource(id = textResource),
        modifier = modifier,
        color = color,
        maxLines = maxLines,
        overflow = overflow,
        textAlign = textAlign,
        fontFamily = fontFamily
    )
}

@Composable
fun MiniText(
    @StringRes textResource: Int,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.onPrimary,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip,
    textAlign: TextAlign? = null,
    fontFamily: FontFamily = FontFamily(Font(R.font.causten_regular))
) {
    MiniText(
        text = stringResource(id = textResource),
        modifier = Modifier.then(modifier),
        color = color,
        maxLines = maxLines,
        overflow = overflow,
        textAlign = textAlign,
        fontFamily = fontFamily
    )
}

@Composable
fun MiniText(
    text: String?,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.onPrimary,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip,
    textAlign: TextAlign? = null,
    fontFamily: FontFamily = FontFamily(Font(R.font.causten_regular))
) {
    Text(
        text = text ?: "",
        modifier = Modifier.then(modifier),
        maxLines = maxLines,
        overflow = overflow,
        textAlign = textAlign,
        style = TextStyle(
            color = color,
            fontSize = 14.sp,
            fontFamily = fontFamily
        )
    )
}

@Composable
fun MiniMediumText(
    text: String?,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.onPrimary,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip,
    textAlign: TextAlign? = null,
    fontFamily: FontFamily = FontFamily(Font(R.font.causten_medium))
) {
    MiniText(
        text = text,
        modifier = modifier,
        color = color,
        maxLines = maxLines,
        overflow = overflow,
        textAlign = textAlign,
        fontFamily = fontFamily
    )
}

@Composable
fun MiniMediumText(
    @StringRes textResource: Int,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.onPrimary,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip,
    textAlign: TextAlign? = null,
    fontFamily: FontFamily = FontFamily(Font(R.font.causten_medium))
) {
    MiniText(
        text = stringResource(id = textResource),
        modifier = modifier,
        color = color,
        maxLines = maxLines,
        overflow = overflow,
        textAlign = textAlign,
        fontFamily = fontFamily
    )
}

@Composable
fun MiniBoldText(
    @StringRes textResource: Int,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.onPrimary,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip,
    textAlign: TextAlign? = null,
    fontFamily: FontFamily = FontFamily(Font(R.font.causten_bold))
) {
    MiniText(
        text = stringResource(id = textResource),
        modifier = modifier,
        color = color,
        maxLines = maxLines,
        overflow = overflow,
        textAlign = textAlign,
        fontFamily = fontFamily
    )
}

@Composable
fun MiniBoldText(
    text: String?,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.onPrimary,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip,
    textAlign: TextAlign? = null,
    fontFamily: FontFamily = FontFamily(Font(R.font.causten_bold))
) {
    MiniText(
        text = text,
        modifier = modifier,
        color = color,
        maxLines = maxLines,
        overflow = overflow,
        textAlign = textAlign,
        fontFamily = fontFamily
    )
}

@Composable
fun SmallText(
    @StringRes textResource: Int,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.onPrimary,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip,
    textAlign: TextAlign? = null
) {
    SmallText(
        text = stringResource(id = textResource),
        modifier = modifier,
        color = color,
        maxLines = maxLines,
        overflow = overflow,
        textAlign = textAlign
    )
}

@Composable
fun SmallText(
    text: String?,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.onPrimary,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip,
    textAlign: TextAlign? = null,
    fontFamily: FontFamily = FontFamily(Font(R.font.causten_regular)),
    fontSize: Int = 16,
    textDecoration: TextDecoration = TextDecoration.None,

) {
    Text(
        text = text ?: "",
        modifier = Modifier.then(modifier),
        maxLines = maxLines,
        overflow = overflow,
        textAlign = textAlign,
        style = TextStyle(
            color = color,
            fontSize = fontSize.sp,
            fontFamily = fontFamily
        ),
        textDecoration = textDecoration
    )
}

@Composable
fun SmallMediumText(
    @StringRes textResource: Int,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.onPrimary,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip,
    textAlign: TextAlign? = null,
    fontFamily: FontFamily = FontFamily(Font(R.font.causten_medium))
) {
    SmallText(
        text = stringResource(id = textResource),
        modifier = modifier,
        color = color,
        maxLines = maxLines,
        overflow = overflow,
        textAlign = textAlign,
        fontFamily = fontFamily
    )
}


@Composable
fun SmallMediumText(
    text: String?,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.onPrimary,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip,
    textAlign: TextAlign? = null,
    fontFamily: FontFamily = FontFamily(Font(R.font.causten_medium)),
    fontSize: Int = 16
) {
    SmallText(
        text = text,
        modifier = modifier,
        color = color,
        maxLines = maxLines,
        overflow = overflow,
        textAlign = textAlign,
        fontFamily = fontFamily,
        fontSize = fontSize
    )
}

@Composable
fun SmallBoldText(
    @StringRes textResource: Int,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.onPrimary,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip,
    textAlign: TextAlign? = null,
    fontFamily: FontFamily = FontFamily(Font(R.font.causten_bold))
) {
    SmallText(
        text = stringResource(id = textResource),
        modifier = modifier,
        color = color,
        maxLines = maxLines,
        overflow = overflow,
        textAlign = textAlign,
        fontFamily = fontFamily
    )
}


@Composable
fun SmallBoldText(
    text: String?,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.onPrimary,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip,
    textAlign: TextAlign? = null,
    fontFamily: FontFamily = FontFamily(Font(R.font.causten_bold)),
    fontSize: Int = 16
) {
    SmallText(
        text = text,
        modifier = modifier,
        color = color,
        maxLines = maxLines,
        overflow = overflow,
        textAlign = textAlign,
        fontFamily = fontFamily,
        fontSize = fontSize
    )
}

@Composable
fun TextLabel(text: String, color: Color) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier
                .matchParentSize()
                .alpha(0.18f),
            shape = RoundedCornerShape(4.dp),
            color = color,
        ) {

        }
        MiniBoldText(
            text = text,
            color = color,
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
        )
    }
}

