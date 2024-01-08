package com.learning.qrbarcodescanner.ui.navigation

import android.content.Intent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.learning.qrbarcodescanner.ui.ScanActivity
import com.learning.qrbarcodescanner.ui.model.DeliveryScreenEvent
import com.learning.qrbarcodescanner.ui.screen.PackagesScreen
import com.learning.qrbarcodescanner.ui.viewmodel.PackagesViewModel

@Composable
fun NavComponent() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = DeliveryScreen.HOME.route) {

        composable(DeliveryScreen.HOME.route) {
            val context = LocalContext.current
            val viewModel: PackagesViewModel = hiltViewModel()

            val list by viewModel.allPackagesList.collectAsState(initial = emptyList())

            PackagesScreen(
                packageList = list,
                modifier = Modifier.fillMaxSize(),
                onStatusChanged = { delivery -> viewModel.update(delivery) },
                onEvent = { event ->
                    when (event) {
                        DeliveryScreenEvent.ADD -> TODO()
                        is DeliveryScreenEvent.DELETE -> viewModel.delete(event.delivery)
                        DeliveryScreenEvent.SCAN -> {
                            val intent = Intent(context, ScanActivity::class.java)
                            context.startActivity(intent)
                        }
                    }
                }
            )
        }


    }
}