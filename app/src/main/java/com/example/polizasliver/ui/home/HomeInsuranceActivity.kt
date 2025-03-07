package com.example.polizasliver.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.polizasliver.R
import com.example.polizasliver.databinding.ActivityHomeInsuranceBinding
import com.example.polizasliver.domain.model.InfoInsuranceItem
import com.example.polizasliver.ui.adapter.HomeInsuranceAdapter
import com.example.polizasliver.ui.detail.DetailActivity
import com.example.polizasliver.ui.type_insurance.TypeInsuranceActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeInsuranceActivity : AppCompatActivity(), InfoInsuranceInterface {
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
        binding.rvHomeinsurance.layoutManager = LinearLayoutManager(this)

        binding.fabHome.setOnClickListener {
            startActivity(Intent(this@HomeInsuranceActivity, TypeInsuranceActivity::class.java))
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
            } else {
                binding.tvHomeNotInsurance.visibility = View.INVISIBLE
            }
        }
        viewModel.infoInsurances.observe(this) { listInfoInsurance ->
            if (listInfoInsurance != null) {
                binding.rvHomeinsurance.adapter =
                    HomeInsuranceAdapter(listInfoInsurance, this)
            }
        }
    }

    override fun infoInsurance(noInsurance: String) {
        val intent = Intent(this@HomeInsuranceActivity, DetailActivity::class.java)
        intent.putExtra("KEY_NO_INSURANCE", noInsurance)
        startActivity(intent)

    }
}