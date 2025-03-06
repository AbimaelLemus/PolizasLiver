package com.example.polizasliver.ui.data_personal

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.polizasliver.R
import com.example.polizasliver.databinding.ActivityDataPersonalBinding
import com.example.polizasliver.databinding.ActivityHomeInsuranceBinding
import com.example.polizasliver.ui.type_insurance.TypeInsurance

class DataPersonalActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDataPersonalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataPersonalBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnDataPersonal.setOnClickListener {
            startActivity(Intent(this@DataPersonalActivity, TypeInsurance::class.java))
        }
    }
}