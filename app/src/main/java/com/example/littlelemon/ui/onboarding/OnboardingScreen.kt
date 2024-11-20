package com.example.littlelemon.ui.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.littlelemon.data.local.LocalDataSource
import com.example.littlelemon.data.local.SharedPreferencesManager
import com.example.littlelemon.data.repository.UserRepository
import com.example.littlelemon.ui.onboarding.components.Header
import com.example.littlelemon.ui.theme.LittleLemonColor
import com.example.littlelemon.ui.theme.Strings

@Composable
fun OnboardingScreen(navController: NavHostController) {
    var firstName by remember { mutableStateOf("Paul") }
    var lastName by remember { mutableStateOf("Chito") }
    var email by remember { mutableStateOf("paul.chito@chitosystems.com") }
    var errorMessage by remember { mutableStateOf("") }

    val sharedPreferencesManager = SharedPreferencesManager(navController.context)
    val localDataSource = LocalDataSource(sharedPreferencesManager)
    val userRepository = UserRepository(localDataSource, sharedPreferencesManager)
    val viewModel: OnboardingViewModel = viewModel(
        factory = OnboardingViewModelFactory(userRepository)
    )

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header()

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(LittleLemonColor.green)
                .padding(vertical = 16.dp)
        ) {
            Text(
                text = Strings.REGISTER_SUBTITLE,
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                color = LittleLemonColor.white,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = Strings.PERSONAL_INFORMATION,
            fontSize = 20.sp,
            color = LittleLemonColor.green,
            modifier = Modifier.padding(vertical = 50.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                value = firstName,
                onValueChange = { firstName = it },
                label = { Text("First Name") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = lastName,
                onValueChange = { lastName = it },
                label = { Text("Last Name") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = email,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Done
                ),
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )

            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    fontSize = 16.sp,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(
                onClick = {
                    if (firstName.isBlank() || lastName.isBlank() || email.isBlank()) {
                        errorMessage = "Registration unsuccessful. Please enter all data."
                    } else {
                        if (viewModel.saveUser(firstName, lastName, email)) {
                            navController.navigate("home")
                        } else {
                            errorMessage = "Registration unsuccessful. Please enter all data."
                        }
                    }
                },
                modifier = Modifier
                    .background(LittleLemonColor.yellow)
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Register",
                    color = Color.Black
                )
            }
        }
    }
}
