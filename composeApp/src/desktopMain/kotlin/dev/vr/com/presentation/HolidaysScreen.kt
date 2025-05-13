package dev.vr.com.presentation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.navigation.NavController

@Composable
fun HolidaysScreen(
    navController: NavController
) {
    Text("HOLIDAYS", color = White)
}