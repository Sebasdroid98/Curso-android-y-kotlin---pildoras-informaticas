package com.sebasdroid98.app2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sebasdroid98.app2.ui.theme.App2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiSegundoComposable()
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun MiSegundoComposable() {
    // Declaramos el color de fondo de la interfaz

    var colorFondo by remember { mutableStateOf( Color.White) }

    var posicionTexto by remember { mutableStateOf(Offset(0f,0f)) }

    var anchoPantalla by remember { mutableStateOf(0f) }

    var altoPantalla by remember { mutableStateOf(0f) }

    var anchoTexto by remember { mutableStateOf(0f) }

    var altoTexto by remember { mutableStateOf(0f) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(colorFondo)
            .onGloballyPositioned { coordinates ->
                anchoPantalla = coordinates.size.width.toFloat()
                altoPantalla = coordinates.size.height.toFloat()
            }
    ){
        /* Image(
            painter = painterResource(R.drawable.autoporche),
            contentDescription = "Auto Porche",
            modifier = Modifier.align(Alignment.Center).fillMaxSize()
        ) */

        imagenInteractiva()

        Text(
            text = "Vehiculo Porche",
            fontSize = 24.sp,
            color = Color.Yellow,
            textAlign = TextAlign.Center,
            // modifier = Modifier.align(Alignment.Center)
            modifier = Modifier
                .offset{
                    IntOffset(posicionTexto.x.toInt(), posicionTexto.y.toInt())
                }
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        change.consume()
                        posicionTexto += Offset(dragAmount.x, dragAmount.y)
                    }
                }
                .onGloballyPositioned { coordinates ->
                    anchoTexto = coordinates.size.width.toFloat()
                    altoTexto = coordinates.size.height.toFloat()

                    // Centrar elemento
                    if (posicionTexto == Offset(0f,0f)) {
                        posicionTexto = Offset(
                            (anchoPantalla - anchoTexto)/2,
                            (altoPantalla - altoTexto )/2
                        )
                    }
                }
        )

        // Botón en la parte superior izquierda

        Button(
            // onClick = {colorFondo=colorAteatorio()},
            onClick = {colorFondo=Color(colorAteatorio2())},
            modifier = Modifier.align(Alignment.TopStart)

        ) {
            Text(
                text = "Fondo"
            )
        }
    }

}

// Version 1 - Función para generar un color random
/* fun colorAteatorio(): Color{
    val rojo = (0..255).random()
    val verde = (0..255).random()
    val azul = (0..255).random()
    return Color(red = rojo, green = verde, blue = azul)
} */

// Version 2 - Función para generar un color random
fun colorAteatorio2(): Long{
    return (0xFFFFFF..0xFFFFFFFF).random()
}

@Composable
fun imagenInteractiva() {

    var escala by remember { mutableStateOf( 1f ) } // Escala de la imagen
    var posicion by remember { mutableStateOf(Offset(0f,0f)) } // Posición de la imagen
    var rotacion by remember { mutableStateOf(0f) } // Rotación de la imagen

    // Animaciones suaves hacia valores iniciales

    val escalaAnimada by animateFloatAsState(targetValue = escala, animationSpec = tween(durationMillis = 800, easing = FastOutSlowInEasing))
    val rotacionAnimada by animateFloatAsState(targetValue = rotacion, animationSpec = tween(durationMillis = 800, easing = FastOutSlowInEasing))
    val posicionAnimada by animateOffsetAsState(targetValue = posicion, animationSpec = tween(durationMillis = 800, easing = FastOutSlowInEasing))

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit){
                detectTransformGestures{ _, desplazamiento, zoom, anguloRotacion ->

                    escala *= zoom // Aplica zoom
                    posicion += desplazamiento // Aplica desplazamiento
                    rotacion += anguloRotacion // Aplica rotación

                }
            }.pointerInput(Unit){
                detectTapGestures(
                    onDoubleTap = {
                        escala = 1f // Reset de la escala
                        posicion = Offset(0f,0f) // Reset de la posición
                        rotacion = 0f // Reset de la rotación
                    }
                )
            },
        contentAlignment = Alignment.Center
    ){
        Image(
            painter = painterResource(R.drawable.autoporche),
            contentDescription = "Auto Porche",
            modifier = Modifier.graphicsLayer(
                scaleX = escalaAnimada.coerceIn(0.5f, 3f), // Limite zoom en X
                scaleY = escalaAnimada.coerceIn(0.5f, 3f), // Limite zoom en Y
                translationX = posicionAnimada.x, // Desplazamiento horizontal
                translationY = posicionAnimada.y, // Desplazamiento vertical
                rotationZ = rotacionAnimada // Rotación en el eje Z
            )
        )
    }
}

@Composable
fun GreetingPreview() {
    App2Theme {
        Greeting("Android")
    }
}