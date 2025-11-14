package com.example.mooby.ui.theme.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mooby.navigation.Routes
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    var start by remember { mutableStateOf(false) }
    val scale = animateFloatAsState(targetValue = if (start) 1.05f else 0.9f, animationSpec = tween(800))
    LaunchedEffect(Unit) {
        start = true
        delay(1400)
        navController.navigate(Routes.INITIAL) { popUpTo(Routes.SPLASH) { inclusive = true } }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Mooby", fontSize = 36.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Seu assistente financeiro", fontSize = 14.sp)
        }
    }
}
