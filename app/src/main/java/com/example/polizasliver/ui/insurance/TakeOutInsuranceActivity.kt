package com.example.polizasliver.ui.insurance

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.polizasliver.R
import com.example.polizasliver.databinding.ActivityInsuranceBinding
import com.example.polizasliver.ui.dialogs_alerts.DialogsAlerts
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TakeOutInsuranceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInsuranceBinding
    val viewModel: TakeOutInsuranceViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityInsuranceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                DialogsAlerts().warningAlet(
                    this@TakeOutInsuranceActivity,
                    getString(R.string.cancel_process),
                    getString(R.string.cancel_process_take_insurance),
                    true,
                    getString(R.string.accept),
                    getString(R.string.cancel)
                ) {
                    finish()
                }
            }
        })
    }
}