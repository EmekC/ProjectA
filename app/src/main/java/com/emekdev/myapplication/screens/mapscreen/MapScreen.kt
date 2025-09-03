package com.emekdev.myapplication.screens.mapscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.emekdev.myapplication.R
import com.emekdev.myapplication.data.ItemHeader
import com.emekdev.myapplication.navigation.ListRoute
import com.emekdev.myapplication.screens.listscreen.ListScreen
import com.utsman.osmandcompose.Marker
import com.utsman.osmandcompose.OpenStreetMap
import com.utsman.osmandcompose.rememberCameraState
import com.utsman.osmandcompose.rememberMarkerState
import org.osmdroid.util.GeoPoint


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(navController: NavController, test: String?) {
    val markers = listOf<ItemHeader>(
        ItemHeader("A", 31.6127, 34.7809),
        ItemHeader("B", 31.7232, 34.7502),
        ItemHeader("C", 31.4232, 34.7001),
    )
    val cameraState = rememberCameraState {
        geoPoint = GeoPoint(31.6105, 34.7723)
        zoom = 18.0
    }

//    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) {
//        Log.d("EMEK", "is permission granted: $it")
//    }

    Scaffold(
        floatingActionButton = {
            AddItemFab {
                // onClick
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            OpenStreetMap(
                modifier = Modifier.fillMaxSize(),
                cameraState = cameraState
            ) {
                markers.forEach { item ->
                    Marker(
                        title = item.title,
                        state = rememberMarkerState(
                            geoPoint = GeoPoint(
                                item.longitude,
                                item.latitude
                            )
                        ),
                    ) { marker ->
                    }
                }
            }
        }

        CustomTopBar(
            onUserClicked = {},
            onSearchClicked = {
                navController.navigate(ListRoute)
            }
        )
    }
}

@Composable
private fun CustomTopBar(
    onUserClicked: () -> Unit,
    onSearchClicked: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .height(128.dp)
            .padding(start = 16.dp, end = 16.dp)
    ) {
        // User
        FilledIconButton(
            modifier = Modifier
                .size(42.dp)
                .shadow(16.dp, shape = CircleShape),
            onClick = onUserClicked,
            colors = IconButtonDefaults.filledIconButtonColors().copy(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
        ) { Icon(Icons.Default.Person, "", Modifier.size(32.dp)) }

        // Search
        IconButton(onClick = onSearchClicked) { Icon(Icons.Default.Search, "", Modifier.size(32.dp)) }
    }
}

@Composable
fun AddItemFab(onClick: () -> Unit) {
    ExtendedFloatingActionButton(
        onClick = onClick,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(Icons.Default.Add, stringResource(R.string.add_item))
            Text(stringResource(R.string.add_item))
        }
    }
}

//@SuppressLint("MissingPermission")
//private fun getCurrentLocation(
//    context: Context,
//    onLocationReceived: (Location) -> Unit,
//    onError: (String) -> Unit
//) {
//    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
//
//    // Check permissions
//    if (ContextCompat.checkSelfPermission(
//            context,
//            Manifest.permission.ACCESS_COARSE_LOCATION
//        ) != PackageManager.PERMISSION_GRANTED &&
//        ContextCompat.checkSelfPermission(
//            context,
//            Manifest.permission.ACCESS_FINE_LOCATION
//        ) != PackageManager.PERMISSION_GRANTED
//    ) {
//        onError("Location permission not granted")
//        return
//    }
//
//    // Create location request with coarse accuracy
//    val locationRequest = LocationRequest.Builder(
//        Priority.PRIORITY_BALANCED_POWER_ACCURACY, // Coarse location priority
//        10000 // 10 seconds interval
//    ).apply {
//        setMinUpdateDistanceMeters(100f) // Update when moved 100 meters
//        setMaxUpdateDelayMillis(15000) // Maximum delay of 15 seconds
//    }.build()
//
//    // Try to get last known location first (faster)
//    fusedLocationClient.lastLocation
//        .addOnSuccessListener { location ->
//            if (location != null) {
//                onLocationReceived(location)
//            } else {
//                // If no last known location, request current location
//                requestCurrentLocation(fusedLocationClient, locationRequest, onLocationReceived, onError)
//            }
//        }
//        .addOnFailureListener { exception ->
//            // Fallback to current location request
//            requestCurrentLocation(fusedLocationClient, locationRequest, onLocationReceived, onError)
//        }
//}
//
//private fun requestCurrentLocation(
//    fusedLocationClient: FusedLocationProviderClient,
//    locationRequest: LocationRequest,
//    onLocationReceived: (Location) -> Unit,
//    onError: (String) -> Unit
//) {
//    val locationCallback = object : LocationCallback() {
//        override fun onLocationResult(locationResult: LocationResult) {
//            locationResult.lastLocation?.let { location ->
//                onLocationReceived(location)
//                fusedLocationClient.removeLocationUpdates(this)
//            }
//        }
//    }
//
//    try {
//        fusedLocationClient.requestLocationUpdates(
//            locationRequest,
//            locationCallback,
//            null
//        )
//
//        // Remove updates after 30 seconds to prevent battery drain
//        android.os.Handler(android.os.Looper.getMainLooper()).postDelayed({
//            fusedLocationClient.removeLocationUpdates(locationCallback)
//        }, 30000)
//
//    } catch (securityException: SecurityException) {
//        onError("Location permission denied: ${securityException.message}")
//    }
//}