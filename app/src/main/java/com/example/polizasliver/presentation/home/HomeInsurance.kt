package com.example.polizasliver.presentation.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.polizasliver.R
import com.example.polizasliver.databinding.ActivityHomeInsuranceBinding
import com.example.polizasliver.presentation.detail.Detail
import com.example.polizasliver.presentation.insurance.Insurance
import com.example.polizasliver.presentation.type_insurance.TypeInsurance

class HomeInsurance : AppCompatActivity() {
    private lateinit var binding: ActivityHomeInsuranceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeInsuranceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.fabHome.setOnClickListener {
            startActivity(Intent(this@HomeInsurance, TypeInsurance::class.java))
        }

        binding.iHome.cvHome.setOnClickListener {
            startActivity(Intent(this@HomeInsurance, Detail::class.java))
        }

    }
}