package com.learning.qrbarcodescanner.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import com.learning.qrbarcodescanner.databinding.ActivityMainBinding
import com.learning.qrbarcodescanner.ui.screen.PackagesScreen
import com.learning.qrbarcodescanner.ui.theme.QRBarCodeScannerTheme
import com.learning.qrbarcodescanner.ui.viewmodel.PackagesViewModel
import com.learning.qrbarcodescanner.utils.FakePackageList
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object {
        const val RESULT_KEY = "RESULT"
    }

    private lateinit var binding: ActivityMainBinding
    private val viewModel: PackagesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QRBarCodeScannerTheme {
                PackagesScreen(packageList = FakePackageList, onStatusChanged = {})
            }
        }
    }

    private fun launchScanActivity() {
        val intent = Intent(applicationContext, ScanActivity::class.java)
        startActivity(intent)
    }

    private fun checkForChanges() {
        val resultIntent = intent.getStringExtra(RESULT_KEY)

        if (resultIntent != null) {
            Log.v("SEEKER", resultIntent.toString())
//            resultState = resultIntent.toString()
        }
    }
}