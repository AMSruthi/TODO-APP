package com.example.flows.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.Main.route
) {
    NavHost(navController = navController, startDestination = startDestination) {

        composable(Screen.Main.route) {
            MainScreenWithBottomNav(navController = navController)
        }

        composable(
            route = Screen.TaskDetails.route, arguments = listOf(navArgument("taskId") {
                type = NavType.LongType
            })
        ) { backStackEntry ->
//            val taskId = backStackEntry.arguments?.getLong("taskId") ?: -1L
//            TaskDetailsScreen(taskId = taskId, onBack = {
//                navController.popBackStack()
//            })

        }

        composable(Screen.Completed.route) {
//            CompletedTaskScreen(onBack = {
//                navController.popBackStack()
//            })
        }
    }
}

@Composable
fun MainScreenWithBottomNav(navController: NavHostController) {

    val tabs = listOf(Screen.Daily, Screen.Weekly, Screen.Monthly)
    val innerNavController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by innerNavController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                tabs.forEach { screen ->
                    NavigationBarItem(
                        icon = { /* TODO add icon */ },
                        label = { Text(screen.route.replaceFirstChar { it.uppercase() }) },
                        selected = currentRoute == screen.route,
                        onClick = {
                            innerNavController.navigate(screen.route) {
                                popUpTo(innerNavController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->

        NavHost(
            navController = innerNavController,
            startDestination = Screen.Daily.route,
            modifier = Modifier.padding(innerPadding)
        ) {

            composable(Screen.Daily.route) {
                DailyListScreen()
            }

            composable(Screen.Weekly.route) {
                WeeklyListScreen()
            }

            composable(Screen.Monthly.route) {
                MonthlyListScreen()
            }
        }
    }
}

