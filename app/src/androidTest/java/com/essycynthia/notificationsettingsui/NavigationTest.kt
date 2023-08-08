package com.essycynthia.notificationsettingsui

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NavigationTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    lateinit var navController : TestNavHostController
    @Before
    fun setupNavHost(){
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            BottomNavGraph(navContoller = navController)
        }
    }
    @Test

    fun verify_startDestinationIsAppMainScreen(){
        composeTestRule.onNodeWithText("Welcome to Software Update Notifications")
            .assertIsDisplayed()
    }


}