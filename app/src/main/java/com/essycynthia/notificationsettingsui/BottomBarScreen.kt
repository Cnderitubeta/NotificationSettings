package com.essycynthia.notificationsettingsui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(val route: String, val icon: ImageVector, val title: String) {
    object AppMainScreen : BottomBarScreen("main_screen", Icons.Default.Home, "Home")
    object NotificationScreen :
        BottomBarScreen("notification_screen", Icons.Default.Notifications, "Notifications")
}
