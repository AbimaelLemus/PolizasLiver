package com.example.polizasliver.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.transition.Visibility
import com.example.polizasliver.R
import com.example.polizasliver.databinding.ActivityHomeInsuranceBinding
import com.example.polizasliver.ui.detail.DetailActivity
import com.example.polizasliver.ui.type_insurance.TypeInsuranceActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeInsuranceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeInsuranceBinding
    val viewModel: HomeInsuranceViewModel by viewModels()
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

        viewModel.onCreate()
        observers()

        binding.fabHome.setOnClickListener {
            startActivity(Intent(this@HomeInsuranceActivity, TypeInsuranceActivity::class.java))
        }

        binding.iHome.cvHome.setOnClickListener {
            startActivity(Intent(this@HomeInsuranceActivity, DetailActivity::class.java))
        }

    }

    private fun observers() {
        viewModel.isLoading.observe(this) { visible ->
            if (visible) {
                binding.pbHome.visibility = View.VISIBLE
            } else {
                binding.pbHome.visibility = View.INVISIBLE
            }
        }

        viewModel.insuranceNull.observe(this) { isNull ->
            if (isNull) {
                binding.tvHomeNotInsurance.visibility = View.VISIBLE
                binding.iHome.root.visibility = View.INVISIBLE
            } else {
                binding.tvHomeNotInsurance.visibility = View.INVISIBLE
                binding.iHome.root.visibility = View.VISIBLE
            }
        }
    }
}