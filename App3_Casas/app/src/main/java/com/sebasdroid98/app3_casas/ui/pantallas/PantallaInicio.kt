package com.sebasdroid98.app3_casas.ui.pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun PantallaInicio(navController: NavController){

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Bienvenido a la galeria de casas")

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = {/* Hola */}) {
            Text("Ver galeria")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {/* Hola */}) {
            Text("Sobre la App")
        }
    }

}