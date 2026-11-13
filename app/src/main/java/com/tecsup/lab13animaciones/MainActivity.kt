package com.tecsup.lab13animaciones

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tecsup.lab13animaciones.ui.theme.Lab13AnimacionesTheme
import kotlin.math.abs
import kotlin.math.roundToInt
import kotlinx.coroutines.launch

private val Ink = Color(0xFF152137)
private val Mist = Color(0xFFF2F5FA)
private val Blue = Color(0xFF4B72F2)
private val Mint = Color(0xFF45C7A1)
private val Coral = Color(0xFFFF766F)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab13AnimacionesTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = Mist) {
                    LabScreen()
                }
            }
        }
    }
}

@Composable
private fun LabScreen() {
    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(18.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Column(modifier = Modifier.padding(top = 12.dp, bottom = 2.dp)) {
            Text("LABORATORIO 13", color = Blue, fontSize = 12.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.4.sp)
            Text("Animaciones", color = Ink, fontSize = 30.sp, fontWeight = FontWeight.ExtraBold)
            Text("Jetpack Compose · Interacciones con movimiento", color = Color(0xFF627087), fontSize = 14.sp)
        }
        VisibilityExercise()
        ColorExercise()
        SizePositionExercise()
        ContentExercise()
        GamePrototype()
        CodelabExercise()
        Text("Desarrollado con Kotlin y Jetpack Compose", color = Color(0xFF7D8797), fontSize = 12.sp, modifier = Modifier.fillMaxWidth().padding(8.dp), textAlign = TextAlign.Center)
        Spacer(Modifier.height(12.dp))
    }
}

@Composable
private fun ExerciseCard(number: String, title: String, subtitle: String, content: @Composable () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Box(modifier = Modifier.size(34.dp).clip(CircleShape).background(Blue), contentAlignment = Alignment.Center) {
                    Text(number, color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                }
                Column {
                    Text(title, color = Ink, fontSize = 17.sp, fontWeight = FontWeight.Bold)
                    Text(subtitle, color = Color(0xFF768195), fontSize = 12.sp)
                }
            }
            content()
        }
    }
}

@Composable
private fun VisibilityExercise() {
    var visible by remember { mutableStateOf(false) }
    ExerciseCard("01", "Visibilidad suave", "AnimatedVisibility · fadeIn + fadeOut") {
        Button(onClick = { visible = !visible }) { Text(if (visible) "Ocultar destello" else "Mostrar destello") }
        AnimatedVisibility(visible, enter = fadeIn(tween(420)) + expandVertically(tween(420)), exit = fadeOut(tween(300)) + shrinkVertically(tween(300))) {
            Box(modifier = Modifier.fillMaxWidth().height(76.dp).clip(RoundedCornerShape(16.dp)).background(Color(0xFFE7F8F1)), contentAlignment = Alignment.Center) {
                Text("✦  ¡Objeto encontrado!  ✦", color = Color(0xFF197C60), fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
private fun ColorExercise() {
    var green by remember { mutableStateOf(false) }
    val color by animateColorAsState(if (green) Mint else Blue, animationSpec = spring(stiffness = Spring.StiffnessLow), label = "color del cubo")
    ExerciseCard("02", "Cambio de color", "animateColorAsState · spring") {
        Box(modifier = Modifier.fillMaxWidth().height(94.dp), contentAlignment = Alignment.Center) {
            Box(Modifier.size(68.dp).clip(RoundedCornerShape(18.dp)).background(color))
        }
        Button(onClick = { green = !green }) { Text("Cambiar a ${if (green) "azul" else "verde"}") }
    }
}

@Composable
private fun SizePositionExercise() {
    var expanded by remember { mutableStateOf(false) }
    val boxSize by animateDpAsState(if (expanded) 82.dp else 54.dp, animationSpec = spring(stiffness = Spring.StiffnessMediumLow), label = "tamaño")
    val x by animateDpAsState(if (expanded) 78.dp else (-78).dp, animationSpec = spring(stiffness = Spring.StiffnessMediumLow), label = "posición")
    ExerciseCard("03", "Tamaño y posición", "animateDpAsState · offset") {
        Box(Modifier.fillMaxWidth().height(100.dp).clip(RoundedCornerShape(16.dp)).background(Color(0xFFF0F3FA)), contentAlignment = Alignment.Center) {
            Box(Modifier.offset(x = x).size(boxSize).clip(RoundedCornerShape(18.dp)).background(Coral))
        }
        Button(onClick = { expanded = !expanded }) { Text(if (expanded) "Volver al inicio" else "Mover y ampliar") }
    }
}

@Composable
private fun ContentExercise() {
    val states = listOf("Cargando", "Contenido", "Error")
    val messages = listOf("Buscando datos de la misión…", "¡La misión está lista para comenzar!", "No se pudo conectar. Intenta otra vez.")
    val colors = listOf(Color(0xFF607DAD), Mint, Coral)
    var index by remember { mutableIntStateOf(0) }
    ExerciseCard("04", "Estados de contenido", "AnimatedContent · entrada y salida") {
        AnimatedContent(targetState = index, label = "estado del contenido") { target ->
            Box(Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp)).background(colors[target].copy(alpha = 0.13f)).padding(16.dp)) {
                Column {
                    Text(states[target], color = colors[target], fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Text(messages[target], color = Ink, fontSize = 13.sp)
                }
            }
        }
        Button(onClick = { index = (index + 1) % states.size }) { Text("Cambiar estado") }
    }
}

@Composable
private fun GamePrototype() {
    var attack by remember { mutableStateOf(false) }
    var score by remember { mutableIntStateOf(0) }
    val transition = updateTransition(targetState = attack, label = "ataque")
    val heroOffset by transition.animateDp(label = "posición del héroe") { if (it) 74.dp else (-74).dp }
    val heroRotation by transition.animateFloat(label = "giro del héroe") { if (it) 18f else -8f }
    val fieldColor by animateColorAsState(if (attack) Color(0xFF33264F) else Color(0xFF172542), tween(500), label = "campo de batalla")
    val pulse = rememberInfiniteTransition(label = "pulso del enemigo")
    val enemyScale by pulse.animateFloat(0.92f, 1.08f, infiniteRepeatable(tween(850, easing = FastOutSlowInEasing), RepeatMode.Reverse), label = "escala")
    ExerciseCard("★", "Nebula Quest", "Prototipo · color + posición + giro + pulso") {
        Box(Modifier.fillMaxWidth().height(200.dp).clip(RoundedCornerShape(20.dp)).background(fieldColor)) {
            Text("PUNTAJE  $score", color = Color(0xFFB8C8E9), fontSize = 12.sp, fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.TopStart).padding(14.dp))
            Text("✦", color = Color(0xFFFFDA6E), fontSize = 39.sp, modifier = Modifier.align(Alignment.Center).alpha(if (attack) 1f else 0.72f))
            Text("👾", fontSize = 48.sp, modifier = Modifier.align(Alignment.TopCenter).padding(top = 46.dp).graphicsScale(enemyScale))
            Text("🚀", fontSize = 44.sp, modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 22.dp).offset(x = heroOffset).rotate(heroRotation))
            AnimatedVisibility(attack, modifier = Modifier.align(Alignment.Center), enter = fadeIn(tween(180)), exit = fadeOut(tween(350))) {
                Text("¡IMPACTO!", color = Color(0xFFFFDA6E), fontSize = 24.sp, fontWeight = FontWeight.Black)
            }
        }
        Button(onClick = { attack = !attack; if (attack) score += 10 }) { Text(if (attack) "Otro ataque" else "Atacar al enemigo") }
    }
}

private fun Modifier.graphicsScale(scale: Float): Modifier = this.scale(scale)

@Composable
private fun CodelabExercise() {
    var expanded by remember { mutableStateOf(false) }
    var visible by remember { mutableStateOf(true) }
    var dismissed by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val swipe = remember { Animatable(0f) }
    val infinite = rememberInfiniteTransition(label = "carga infinita")
    val alpha by infinite.animateFloat(
        initialValue = 0.35f, targetValue = 1f,
        animationSpec = infiniteRepeatable(animation = keyframes { durationMillis = 1000; 0.7f at 500 }, repeatMode = RepeatMode.Reverse),
        label = "alfa del indicador"
    )
    ExerciseCard("+", "Codelab de animaciones", "color · expansión · repetición · gesto") {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(9.dp)) {
            Box(Modifier.size(12.dp).alpha(alpha).clip(CircleShape).background(Mint))
            Text("Sincronizando misión", color = Ink, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
            Spacer(Modifier.weight(1f))
            Button(onClick = { visible = !visible }) { Text(if (visible) "Ocultar" else "Mostrar") }
        }
        AnimatedVisibility(visible, enter = slideInVertically(initialOffsetY = { -it }, animationSpec = tween(240)), exit = slideOutVertically(targetOffsetY = { -it }, animationSpec = tween(260))) {
            Card(colors = CardDefaults.cardColors(containerColor = Color(0xFFEAF1FF))) {
                Column(Modifier.fillMaxWidth().padding(14.dp).animateContentSize()) {
                    Text("Entrada desde arriba", color = Blue, fontWeight = FontWeight.Bold)
                    Text("AnimatedVisibility con duración personalizada", color = Ink, fontSize = 12.sp)
                }
            }
        }
        Card(
            modifier = Modifier.fillMaxWidth().offset { IntOffset(swipe.value.roundToInt(), 0) }
                .pointerInput(dismissed) {
                    detectHorizontalDragGestures(
                        onHorizontalDrag = { change, amount ->
                            change.consume()
                            scope.launch { swipe.snapTo((swipe.value + amount).coerceIn(-size.width.toFloat(), size.width.toFloat())) }
                        },
                        onDragEnd = {
                            scope.launch {
                                if (abs(swipe.value) > 110f) {
                                    swipe.animateTo(if (swipe.value > 0) size.width.toFloat() else -size.width.toFloat(), tween(260))
                                    dismissed = true
                                } else swipe.animateTo(0f, spring(stiffness = Spring.StiffnessMedium))
                            }
                        }
                    )
                },
            colors = CardDefaults.cardColors(containerColor = if (dismissed) Color(0xFFE9F7F2) else Color(0xFFFFF6DF))
        ) {
            Text(if (dismissed) "✓ Tarea descartada" else "↔ Desliza esta tarea para descartarla", color = Ink, fontWeight = FontWeight.SemiBold, modifier = Modifier.fillMaxWidth().padding(16.dp))
        }
        Card(modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp)).background(Color(0xFFF0F3FA))) {
            Column(Modifier.fillMaxWidth().padding(14.dp).animateContentSize()) {
                Text("Tema: Animatable y gestos", color = Ink, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(6.dp))
                AnimatedVisibility(expanded, enter = expandVertically(), exit = shrinkVertically()) {
                    Text("El gesto actualiza el desplazamiento; Animatable devuelve la tarjeta a su lugar o la descarta al superar el umbral.", color = Color(0xFF5F6C80), fontSize = 13.sp)
                }
                Button(onClick = { expanded = !expanded }) { Text(if (expanded) "Contraer tema" else "Leer tema · animateContentSize") }
            }
        }
    }
}




