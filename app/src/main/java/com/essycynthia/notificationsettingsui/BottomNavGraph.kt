package com.essycynthia.notificationsettingsui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun BottomNavGraph(navContoller: NavHostController) {

    NavHost(navController = navContoller, startDestination = BottomBarScreen.AppMainScreen.route) {
        composable(BottomBarScreen.AppMainScreen.route) {
            AppMainScreen()
        }
        composable(BottomBarScreen.NotificationScreen.route) {
            NotificationScreen()
        }

    }


}