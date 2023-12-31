package com.firstapp.www.exampleapp

import android.os.Bundle
import android.view.animation.OvershootInterpolator
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.firstapp.www.exampleapp.ui.theme.ExampleAppTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(color = Color.White, modifier = Modifier.fillMaxSize()) {
                com.firstapp.www.exampleapp.Navigation()
            }
        }
    }
}

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash_screen"){
        composable("splash_screen"){
            SplashScreen(navController = navController)
        }
        composable("main_screen"){
            Box(modifier = Modifier.fillMaxSize(),contentAlignment = Alignment.Center) {
                Text(text = "Main Screen", color = Color.Black, fontSize = 24.sp)
            }
        }
    }
}


@Composable
fun SplashScreen(navController:NavController){
        val scale = remember{
            androidx.compose.animation.core.Animatable(0f)
        }
    
    LaunchedEffect(key1 = true){
        scale.animateTo(
            targetValue = 0.7f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                })
        )
        delay(2000)
        navController.navigate("main_screen")
    }

    Box(contentAlignment = Alignment.Center,
    modifier = Modifier.fillMaxSize()
    ) {
        Image(painter = painterResource(id = R.drawable.youtube), contentDescription = "Logo"
        , modifier = Modifier.scale(scale.value))
    }
}
