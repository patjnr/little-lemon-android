package com.example.littlelemon

import android.content.Context
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
import com.example.littlelemon.data.local.LocalDataSource
import com.example.littlelemon.data.local.SharedPreferencesManager
import com.example.littlelemon.data.repository.UserRepository
import com.example.littlelemon.navigation.AppNavigation
import com.example.littlelemon.navigation.Home
import com.example.littlelemon.navigation.Onboarding
import com.example.littlelemon.navigation.Profile
import com.example.littlelemon.ui.home.HomeScreen
import com.example.littlelemon.ui.onboarding.OnboardingScreen
import com.example.littlelemon.ui.onboarding.OnboardingViewModel
import com.example.littlelemon.ui.profile.ProfileScreen
import com.example.littlelemon.ui.theme.LittleLemonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()




        setContent {
            LittleLemonTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   // AppNavigation(userRepository = userRepository)
                    AppNavigation(context = this)
              //   OnboardingScreen(viewModel = onboardingViewModel)
                }
            }
        }
    }
}

