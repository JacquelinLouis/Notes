package com.jac.notes

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainCompose() {

    val navController = rememberNavController()
    MainNavHost.Create(navController)

}