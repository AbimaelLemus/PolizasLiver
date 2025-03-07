package com.example.polizasliver.ui.type_insurance

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.polizasliver.R
import com.example.polizasliver.data.model.Utils
import com.example.polizasliver.databinding.ActivityTypeInsuranceBinding
import com.example.polizasliver.ui.adapter.TypeInsuranceAdapter
import com.example.polizasliver.ui.data_personal.DataPersonalActivity
import com.example.polizasliver.ui.dialogs_alerts.DialogsAlerts
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TypeInsuranceActivity : AppCompatActivity(), CardTypeInterface {
    private lateinit var binding: ActivityTypeInsuranceBinding
    val viewModel: TypeInsuranceViewModel by viewModels()
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
        viewModel.onCreate(this@TypeInsuranceActivity)
        binding.rvTypeInsurance.layoutManager = GridLayoutManager(this, 2)
        myObservers()

    }

    override fun itemCardType(position: Int) {
        viewModel.evaluateType(this@TypeInsuranceActivity, position)
    }

    private fun myObservers() {
        viewModel.listTypeInsurance.observe(this) { listTypeInsurance ->
            binding.rvTypeInsurance.adapter = TypeInsuranceAdapter(listTypeInsurance, this)
        }

        viewModel.informationAlertModel.observe(this) { informationAlert ->
            DialogsAlerts().infoAlert(
                context = this@TypeInsuranceActivity,
                tittle = informationAlert.tittle,
                message = informationAlert.message,
                cancelable = true,
                buttomPositive = getString(R.string.accept),
                buttomNegative = getString(R.string.cancel)
            ) {
                val intent = Intent(this@TypeInsuranceActivity, DataPersonalActivity::class.java)
                val mBundle = Bundle()
                mBundle.putString(Utils.KEY_TYPE_POSITION, informationAlert.position.toString())
                intent.putExtras(mBundle)
                startActivity(intent)
            }
        }
    }
}