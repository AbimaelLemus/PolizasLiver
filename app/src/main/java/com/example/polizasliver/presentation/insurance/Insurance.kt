package com.example.polizasliver.presentation.insurance

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.polizasliver.databinding.ActivityInsuranceBinding

class Insurance : AppCompatActivity() {
    private lateinit var binding: ActivityInsuranceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        binding = ActivityInsuranceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/
    }
}