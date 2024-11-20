package com.example.littlelemon.ui.onboarding.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.littlelemon.R

@Composable
fun Header() {
    Image(
        painter = painterResource(R.drawable.logo),
        contentDescription = "App Logo",
        modifier = Modifier.size(185.dp)
    )
}