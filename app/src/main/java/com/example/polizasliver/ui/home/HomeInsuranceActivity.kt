package com.example.polizasliver.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.polizasliver.R
import com.example.polizasliver.databinding.ActivityHomeInsuranceBinding
import com.example.polizasliver.ui.detail.DetailActivity
import com.example.polizasliver.ui.type_insurance.TypeInsuranceActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeInsuranceActivity : AppCompatActivity() {
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
            startActivity(Intent(this@HomeInsuranceActivity, TypeInsuranceActivity::class.java))
        }

        binding.iHome.cvHome.setOnClickListener {
            startActivity(Intent(this@HomeInsuranceActivity, DetailActivity::class.java))
        }

    }
}