package com.learning.qrbarcodescanner.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
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

    private val viewModel: PackagesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QRBarCodeScannerTheme {
                val list by viewModel.allPackagesList.collectAsState(initial = emptyList())
                PackagesScreen(
                    packageList = list,
                    modifier = Modifier.fillMaxSize(),
                    onStatusChanged = { viewModel.update(it) }
                )
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