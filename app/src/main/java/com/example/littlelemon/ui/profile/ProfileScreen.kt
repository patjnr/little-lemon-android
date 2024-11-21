package com.example.littlelemon.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.littlelemon.data.local.SharedPreferencesManager
import com.example.littlelemon.ui.components.Header
import com.example.littlelemon.ui.theme.LittleLemonColor

@Composable
fun ProfileScreen(
    navController: NavController,
    sharedPreferencesManager: SharedPreferencesManager
) {

    val firstName = sharedPreferencesManager.getFirstName()
    val lastName = sharedPreferencesManager.getLastName()
    val email = sharedPreferencesManager.getEmail()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .padding(26.dp)
    ) {
        Header()

        Text(
            text = "Profile information:",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "First Name: $firstName",
                modifier = Modifier.padding(vertical = 10.dp)
            )
            Text(
                text = "Last Name: $lastName",
                modifier = Modifier.padding(vertical = 10.dp)
            )
            Text(
                text = "Email: $email",
                modifier = Modifier.padding(vertical = 10.dp)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        TextButton(
            onClick = {
                sharedPreferencesManager.clearUserData()
                navController.navigate("onboarding") {
                    popUpTo("onboarding") { inclusive = true }
                }
            },
            modifier = Modifier
                .background(LittleLemonColor.yellow)
                .fillMaxWidth()
                .padding(2.dp)
        ) {
            Text(
                text = "Log out",
                fontSize = 20.sp,
                color = LittleLemonColor.green
            )
        }

    }
}
