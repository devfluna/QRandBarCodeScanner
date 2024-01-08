package com.learning.qrbarcodescanner.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.learning.qrbarcodescanner.ui.model.DeliveryStatus
import com.learning.qrbarcodescanner.ui.model.PackageDelivery
import com.learning.qrbarcodescanner.ui.navigation.NavComponent
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
            QRBarCodeScannerTheme { NavComponent() }
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