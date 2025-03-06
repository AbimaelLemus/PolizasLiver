package com.example.polizasliver.ui.type_insurance

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.polizasliver.R
import com.example.polizasliver.databinding.ActivityTypeInsuranceBinding
import com.example.polizasliver.ui.data_personal.DataPersonalActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TypeInsuranceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTypeInsuranceBinding
    private val TAG = TypeInsuranceActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityTypeInsuranceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.llTypeInsurance.setOnClickListener {
            startActivity(Intent(this@TypeInsuranceActivity, DataPersonalActivity::class.java))
        }


    }
}