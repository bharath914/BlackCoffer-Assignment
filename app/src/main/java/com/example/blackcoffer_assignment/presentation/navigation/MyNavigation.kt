package com.example.blackcoffer_assignment.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.blackcoffer_assignment.presentation.screens.ExploreScreen
import com.example.blackcoffer_assignment.presentation.screens.RefineScreen

@Composable
fun MyNavHost(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = NavConst.explore,
        builder = {

            composable(NavConst.explore) {
                ExploreScreen(navHostController)
            }
            composable(NavConst.refine) {
                RefineScreen(navHostController)
            }
        })
}



