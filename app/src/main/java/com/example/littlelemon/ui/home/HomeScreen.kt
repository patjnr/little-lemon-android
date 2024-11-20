package com.example.littlelemon.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.TextButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.littlelemon.ui.theme.LittleLemonColor

@Composable
fun HomeScreen(onNavigateToProfile: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        TextButton(
            onClick = onNavigateToProfile,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Go to Profile",
                color = LittleLemonColor.green
            )
        }
    }
}
