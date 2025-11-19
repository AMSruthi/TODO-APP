package com.example.flows.ui

sealed class Screen(val route: String) {

    object Main : Screen("main")

    object Daily : Screen("daily")
    object Weekly : Screen("weekly")
    object Monthly : Screen("monthly")

    object TaskDetails : Screen("task/{taskId}") {
        fun createRoute(taskId: Long) = "task/$taskId"
    }

    object Completed : Screen("completed")
}