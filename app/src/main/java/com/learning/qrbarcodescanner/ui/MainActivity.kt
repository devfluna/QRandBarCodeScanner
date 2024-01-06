package com.learning.qrbarcodescanner.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.learning.qrbarcodescanner.ui.model.DeliveryScreenEvent
import com.learning.qrbarcodescanner.ui.model.DeliveryStatus
import com.learning.qrbarcodescanner.ui.model.PackageDelivery
import com.learning.qrbarcodescanner.ui.screen.PackagesScreen
import com.learning.qrbarcodescanner.ui.theme.QRBarCodeScannerTheme
import com.learning.qrbarcodescanner.ui.viewmodel.PackagesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object {
        const val RESULT_KEY = "RESULT"
    }

    private val viewModel: PackagesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkForChanges()

        setContent {
            QRBarCodeScannerTheme {
                val list by viewModel.allPackagesList.collectAsState(initial = emptyList())
                PackagesScreen(
                    packageList = list,
                    modifier = Modifier.fillMaxSize(),
                    onStatusChanged = { delivery -> viewModel.update(delivery) },
                    onEvent = ::onEvent
                )
            }
        }
    }

    private fun launchScanActivity() {
        val intent = Intent(applicationContext, ScanActivity::class.java)
        startActivity(intent)
    }

    private fun onEvent(event: DeliveryScreenEvent) {
        when (event) {
            DeliveryScreenEvent.ADD -> {}
            DeliveryScreenEvent.SCAN -> launchScanActivity()
            is DeliveryScreenEvent.DELETE -> {
                viewModel.delete(event.delivery)
            }
        }
    }

    private fun checkForChanges() {
        val resultIntent = intent.getStringExtra(RESULT_KEY)

        if (resultIntent != null) {
            val delivery = PackageDelivery(
                itemName = resultIntent,
                trackingNumber = resultIntent,
                status = DeliveryStatus.Shipped()
            )
            viewModel.insertNew(delivery)
            intent.removeExtra(RESULT_KEY)
        }
    }
}