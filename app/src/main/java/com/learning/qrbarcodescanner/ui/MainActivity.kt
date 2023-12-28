package com.learning.qrbarcodescanner.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.learning.qrbarcodescanner.databinding.ActivityMainBinding
import com.learning.qrbarcodescanner.ui.theme.QRBarCodeScannerTheme
import com.learning.qrbarcodescanner.ui.viewmodel.PackagesViewModel
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

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    var resultState by remember { mutableStateOf("") }
                    Text(text = "HOLA MUNDO")

                    val resultIntent = intent.getStringExtra(RESULT_KEY)

                    if (resultIntent != null) {
                        Log.v("SEEKER", resultIntent.toString())
                        resultState = resultIntent.toString()
                    }
                }
            }


        }
    }

    private fun launchScanActivity() {
        val intent = Intent(applicationContext, ScanActivity::class.java)
        startActivity(intent)
    }
}