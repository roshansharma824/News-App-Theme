package com.example.newsapptheme.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.newsapptheme.ConsCentNewsApp
import com.example.newsapptheme.navigation.graph.RootNavigationGraph
import com.roshan.themebuilder.theme.LocalAppViewModel
import com.roshan.themebuilder.theme.ThemeBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    // Get a reference to the singleton ViewModel from the Application instance
    private val themeViewModel by lazy { (application as ConsCentNewsApp).themeViewModel }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompositionLocalProvider(LocalAppViewModel provides themeViewModel){
                ThemeBuilder(themeViewModel = themeViewModel) {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        RootNavigationGraph(navController = rememberNavController())
                    }
                }
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

}


