package com.essycynthia.notificationsettingsui

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NavigationTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    lateinit var navController: TestNavHostController

    @Before
    fun setupNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            BottomNavGraph(navContoller = navController)
        }
    }

    @Test

    fun verify_startDestinationIsAppMainScreen() {
        composeTestRule.onNodeWithText("Welcome to Software Update Notifications")
            .assertIsDisplayed()
    }

    @Test
    fun isBottomNavigationDisplayed() {
        composeTestRule.onNodeWithText("Home")
            .assertIsDisplayed()


    }
    @Test
    fun ensureNavigationIsWorkingAsExpected(){

       composeTestRule. onNodeWithText("Home")
            .assertIsDisplayed()
           .performClick()
        val route = navController.currentBackStackEntry?.destination?.route
        Assert.assertEquals(route,"main_screen")
    }
}