package com.example.proyectofinalopentech.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.proyectofinalopentech.ui.theme.ProyectoFinalOpenTechTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity(): ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProyectoFinalOpenTechTheme() {
                // A surface container using the 'background' color from the theme
                TestView()
            }
        }
    }
}


@Composable
fun TestView(viewModel: ViewModelTest = koinViewModel()){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
       viewModel.test()
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
    ProyectoFinalOpenTechTheme {
        Greeting("Android")
    }
}