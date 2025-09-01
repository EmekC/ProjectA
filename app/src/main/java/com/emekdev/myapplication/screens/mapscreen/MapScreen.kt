package com.emekdev.myapplication.screens.mapscreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.emekdev.myapplication.navigation.ListRoute
import com.utsman.osmandcompose.OpenStreetMap
import com.utsman.osmandcompose.rememberCameraState
import org.osmdroid.util.GeoPoint

@Composable
fun MapScreen(navController: NavController, test: String?) {
    val cameraState = rememberCameraState {
        geoPoint = GeoPoint(31.6105, 34.7723)
        zoom = 18.0
    }

    OpenStreetMap(
        modifier = Modifier.fillMaxSize(),
        cameraState = cameraState
    )

    Button(onClick = {
        navController.navigate(ListRoute)
    }) { }
}