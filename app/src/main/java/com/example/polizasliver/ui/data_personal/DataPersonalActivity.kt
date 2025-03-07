package com.example.polizasliver.ui.data_personal

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.polizasliver.R
import com.example.polizasliver.data.model.Utils
import com.example.polizasliver.databinding.ActivityDataPersonalBinding
import com.example.polizasliver.ui.insurance.TakeOutInsuranceActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DataPersonalActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDataPersonalBinding
    val viewModel: DataPersonViewModel by viewModels()
    private val TAG = DataPersonalActivity::class.java.simpleName
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

        if (intent.hasExtra(Utils.KEY_TYPE_POSITION)) {
            val mPosition = intent.getStringExtra(Utils.KEY_TYPE_POSITION)
            if (mPosition != null) {
                Log.i(TAG, "onCreate: $mPosition")
            }

        }

        binding.btnDataPersonal.setOnClickListener {
            startActivity(Intent(this@DataPersonalActivity, TakeOutInsuranceActivity::class.java))
        }
    }
}