package com.example.littlelemon.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.composables.Home
import com.example.littlelemon.composables.Onboarding
import com.example.littlelemon.composables.Profile

@Composable
fun Navigation(context: Context) {
    val navController = rememberNavController()
    val destination = determineStartDestination(context)

    NavHost(navController = navController,
        startDestination = destination
    ) {
        composable(Home.route) {
            Home(onNavProfile = {
                navController.navigate(Profile.route)
            })
        }
        composable(Profile.route) {
            Profile(navController)
        }
        composable(Onboarding.route) {
            Onboarding(onNavHome = {
                navController.navigate(Home.route)
            })
        }
    }
}

private fun determineStartDestination(context: Context): String {
    //check if user data is stored in shared preferences and return the appropriate start destination
    // For example, if user data exists, returns Onboarding
    // Otherwise, returns Home as the default start destination
    val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)


    if (sharedPreferences.getBoolean("userRegistered", false)) {
        return Home.route

    }

    return Onboarding.route
}