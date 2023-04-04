package com.ms.quizapp.ui.splash.view


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.OvershootInterpolator
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.ms.quizapp.BuildConfig
import com.ms.quizapp.R
import com.ms.quizapp.ui.homePage.view.HomePageActivity
import com.ms.quizapp.utils.composeComponents.BodyBoldText
import com.ms.quizapp.utils.theme.OrderAppTheme
import com.ms.quizapp.utils.theme.colorGrey
import kotlinx.coroutines.delay

class SplashActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            OrderAppTheme {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()


                ) {
                    ShowAppNameCard()
                }
            }
        }


        Handler(Looper.getMainLooper())
            .postDelayed({

                startActivity(Intent(this@SplashActivity, HomePageActivity::class.java))
                finish()

            }, 1500)


    }


}

@Composable
fun ShowAppNameCard() {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        val scale = remember {
            androidx.compose.animation.core.Animatable(0.0f)
        }

        LaunchedEffect(key1 = true) {
            scale.animateTo(
                targetValue = 0.7f,
                animationSpec = tween(800, easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                })
            )
            delay(1000)
           /* navController.navigate(Screens.Dashboard) {
                popUpTo(Screens.Splash) {
                    inclusive = true
                }
            }*/
        }

        Image(
            painter = painterResource(id = R.drawable.app_logo),
            contentDescription = "",
            alignment = Alignment.Center, modifier = Modifier
                .fillMaxSize()
                .padding(40.dp)
                .scale(scale.value)
        )

        BodyBoldText(
            text = "Version - ${BuildConfig.VERSION_NAME}",
            textAlign = TextAlign.Center,
            color = colorGrey,
            fontSize = 20,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        )
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    OrderAppTheme {
        ShowAppNameCard()
    }
}