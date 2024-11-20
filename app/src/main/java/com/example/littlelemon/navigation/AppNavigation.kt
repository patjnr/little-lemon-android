package com.example.littlelemon.navigation

import android.content.Context
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.data.local.SharedPreferencesManager
import com.example.littlelemon.ui.home.HomeScreen
import com.example.littlelemon.ui.onboarding.OnboardingScreen
import com.example.littlelemon.ui.profile.ProfileScreen

@Composable
fun AppNavigation(context: Context, navController: NavHostController = rememberNavController()) {
    val sharedPreferencesManager = SharedPreferencesManager(context)
    val isLoggedIn by rememberUpdatedState(sharedPreferencesManager.isUserLoggedIn())

    val startDestination = if (isLoggedIn) Home.route else Onboarding.route

    LaunchedEffect(isLoggedIn) {
        if (!isLoggedIn) {
            navController.navigate(Onboarding.route) {
                popUpTo(0)
            }
        }
    }

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Onboarding.route) {
            OnboardingScreen(navController = navController)
        }
        composable(Home.route) {
            if (isLoggedIn) {
                HomeScreen(onNavigateToProfile = { navController.navigate(Profile.route) })
            } else {
                navController.navigate(Onboarding.route) {
                    popUpTo(0)
                }
            }
        }
        composable(Profile.route) {
            if (isLoggedIn) {
                ProfileScreen(
                    onLogout = {
                        sharedPreferencesManager.clearUserData()
                        sharedPreferencesManager.setLoggedIn(false)  // Set login status to false
                        navController.navigate(Onboarding.route) {
                            popUpTo(0)
                        }
                    }
                )
            } else {
                navController.navigate(Onboarding.route) {
                    popUpTo(0)
                }
            }
        }
    }
}
