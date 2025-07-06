package com.sebasdroid98.app3_casas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sebasdroid98.app3_casas.ui.pantallas.PantallaInicio
import com.sebasdroid98.app3_casas.ui.theme.App3_CasasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CasasApp()
        }
    }
}

@Composable()
fun CasasApp(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "inicio"){
        composable("inicio"){ PantallaInicio(navController) }
    }
}

/* @Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    App3_CasasTheme {
        Greeting("Hola Sebas")
    }
} */