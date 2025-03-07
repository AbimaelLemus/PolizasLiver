package com.example.polizasliver.ui.detail

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.polizasliver.R
import com.example.polizasliver.databinding.ActivityDetailBinding
import com.example.polizasliver.ui.adapter.DetailAdapter
import com.example.polizasliver.ui.dialogs_alerts.DialogsAlerts
import com.example.polizasliver.ui.home.HomeInsuranceActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    val viewModel: DetailViewModel by viewModels()
    private val TAG = DetailActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val noInsurance = intent.getStringExtra("KEY_NO_INSURANCE").orEmpty()
        viewModel.inicialized(this@DetailActivity, noInsurance)
        Log.e(TAG, "onCreate: $noInsurance")

        binding.rvDetailInsurance.layoutManager = LinearLayoutManager(this)

        myObservers()

        binding.btnDetailCancel.setOnClickListener {
            DialogsAlerts().infoAlert(
                this@DetailActivity,
                getString(R.string.alert_cancel_insurance_tittle),
                getString(R.string.alert_cancel_insurance_message),
                false,
                getString(R.string.confir_cancel),
                getString(R.string.cancel)
            ) {
                val noInsurance = intent.getStringExtra("KEY_NO_INSURANCE").orEmpty()
                viewModel.cancelInsurance(noInsurance)

            }
        }

    }

    private fun myObservers() {
        viewModel.isLoading.observe(this) { showLoading ->
            if (showLoading) {
                binding.pgDetailInsurance.visibility = View.VISIBLE
            } else {
                binding.pgDetailInsurance.visibility = View.INVISIBLE
            }
        }

        viewModel.infoInsurance.observe(this) { infoInsurance ->
            binding.tvDetailName.text = infoInsurance.clientsName
            binding.tvDetailNoInsurance.text = infoInsurance.noInsurance
            //binding.tvDetailProtectedSince.text = infoInsurance.

        }

        viewModel.iconInsurance.observe(this) { icon ->
            binding.ivDetailIconInsurance.setBackgroundResource(icon)
        }

        viewModel.listTerms.observe(this) { listTerms ->
            binding.rvDetailInsurance.adapter = DetailAdapter(this@DetailActivity, listTerms)
        }

        viewModel.deleteInsurance.observe(this) { message ->
            Toast.makeText(this@DetailActivity, message, Toast.LENGTH_SHORT).show()


            val intent =
                Intent(this@DetailActivity, HomeInsuranceActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
    }
}