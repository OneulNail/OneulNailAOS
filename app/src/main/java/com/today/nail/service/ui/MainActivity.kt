package com.today.nail.service.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.today.nail.service.ui.theme.MyApplicationTheme
import com.today.nail.service.ui.util.ToastHelper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
@OptIn(ExperimentalAnimationApi::class)
class MainActivity : ComponentActivity() {

    private val viewModel : TopLevelViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ToastHelper.initContext(this)

        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme

                val animateNavHostController = rememberAnimatedNavController()

               TopLevelNavHost(
                   modifier = Modifier,
                   navHostController = animateNavHostController,
                   topLevelViewModel = viewModel
               ) {

               }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        ToastHelper.disposeContext()
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}