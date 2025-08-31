package com.emekdev.myapplication.navigation

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.emekdev.myapplication.screens.mapscreen.MapScreen
import kotlinx.serialization.Serializable

// Navigation Routes
@Serializable
data class MapRoute(val test: String? = null)
@Serializable
object ListRoute

@Composable
fun SetupNavigation() {
    val navController = rememberNavController()

    // Navigation Host,
    NavHost(
        navController = navController,
        startDestination = MapRoute(test = "Test Argument"),
    ) {
        // create the NavGraph and pass all destinations

        composable<MapRoute> { backStackEntry ->
            val mapRoute: MapRoute = backStackEntry.toRoute()
            // call Screen composable and pass mapRoute.test
            MapScreen(navController)
        }

        composable<ListRoute> {
            Text("List Route")
            Button(onClick = {
                navController.popBackStack()
            }) {}
        }
    }
}