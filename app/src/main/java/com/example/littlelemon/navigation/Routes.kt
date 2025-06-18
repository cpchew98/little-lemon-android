package com.example.littlelemon.navigation

interface Routes{
    val route: String
}

object Onboarding: Routes{
    override val route = "Onboarding"
}

object Home: Routes{
    override val route = "Home"
}

object Profile: Routes{
    override val route = "Profile"
}