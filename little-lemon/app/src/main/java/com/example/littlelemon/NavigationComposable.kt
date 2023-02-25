package com.example.littlelemon

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost

@Composable
fun Navigation(navController: NavHostController) {

    // Code to read preferences. done without separate class-file to encapsulate it.
    val context = LocalContext.current
    val sharedPreferences =  context.getSharedPreferences(
        "LittleLemon",
        ComponentActivity.MODE_PRIVATE
    )

    val firstName: String? = sharedPreferences.getString("firstName", "")
    val lastName: String? = sharedPreferences.getString("lastName", "")
    val email: String? = sharedPreferences.getString("email", "")

    val startDestinationRoute =
        if (firstName != "" && lastName != "" && email != ""){
            Home.route
        } else {
            Onboarding.route
        }

    NavHost(
        navController = navController,
        startDestination = startDestinationRoute
    ){
       composable(Home.route) {
           Home(navController)
        }
        composable(Onboarding.route){
            Onboarding(navController)
        }
        composable(Profile.route){
            Profile(navController)
        }

    }


}