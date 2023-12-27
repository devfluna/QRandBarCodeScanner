package com.learning.qrbarcodescanner.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.learning.qrbarcodescanner.databinding.ActivityMainBinding
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
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnScan.setOnClickListener {
            val intent = Intent(applicationContext, ScanActivity::class.java)
            startActivity(intent)
        }

        val result = intent.getStringExtra(RESULT_KEY)

        if (result != null){
            Log.v("SEEKER", result.toString())
            binding.rvResult.text = result.toString()
        }
    }
}