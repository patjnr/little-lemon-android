package com.example.littlelemon.navigation

import android.content.Context
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.littlelemon.AppDatabase
import com.example.littlelemon.data.local.SharedPreferencesManager
import com.example.littlelemon.ui.home.HomeScreen
import com.example.littlelemon.ui.onboarding.OnboardingScreen
import com.example.littlelemon.ui.profile.ProfileScreen

@Composable
fun NavigationComposable(
    context: Context,
    navController: NavHostController = rememberNavController()
) {
    val sharedPreferencesManager = SharedPreferencesManager(context)
    val isLoggedIn by rememberUpdatedState(sharedPreferencesManager.isUserLoggedIn())
    val startDestination = if (isLoggedIn) Home.route else Onboarding.route

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Onboarding.route) {
            OnboardingScreen(navController = navController)
        }
        composable(Home.route) {
            val database by lazy {
                Room.databaseBuilder(context, AppDatabase::class.java, "database").build()
            }
            HomeScreen(navController = navController, appDataBase =database)
        }

        composable(Profile.route) {
            ProfileScreen(navController = navController, sharedPreferencesManager = sharedPreferencesManager)
            /*
                onLogout = {
                    sharedPreferencesManager.clearUserData()
                    sharedPreferencesManager.setLoggedIn(false)
                    navController.navigate(Onboarding.route) {
                        popUpTo(0)
                    }
                }
            )

             */
        }
    }
}
