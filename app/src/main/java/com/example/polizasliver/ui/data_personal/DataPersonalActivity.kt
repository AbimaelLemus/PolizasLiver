package com.example.polizasliver.ui.data_personal

import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.polizasliver.R
import com.example.polizasliver.data.model.Utils
import com.example.polizasliver.databinding.ActivityDataPersonalBinding
import com.example.polizasliver.ui.dialogs_alerts.DialogsAlerts
import com.example.polizasliver.ui.take_out_insurance.TakeOutInsuranceActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DataPersonalActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDataPersonalBinding
    private val viewModel by lazy { DataPersonViewModel() }
    private val TAG = DataPersonalActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDataPersonalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if (intent.hasExtra(Utils.KEY_TYPE_POSITION)) {
            val mPosition = intent.getStringExtra(Utils.KEY_TYPE_POSITION)
            if (mPosition != null) {
                viewModel.onCreate(position = mPosition.toInt())
            }

        }
        myObservers()

        binding.btnDataPersonal.setOnClickListener {
            val beneficiary = binding.tietBeneficiary.text.toString()
            val direction: String = binding.tietDirection.text.toString()
            val cp: String = binding.tietCP.text.toString()
            val phone: String = binding.tietPhone.text.toString()
            val eName: String = binding.tietEmergencyName.text.toString()
            val ePhone: String = binding.tietEmergencyPhone.text.toString()
            viewModel.validForm(
                this@DataPersonalActivity,
                beneficiary,
                direction,
                cp,
                phone,
                eName,
                ePhone
            )
        }

        onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                DialogsAlerts().warningAlert(
                    this@DataPersonalActivity,
                    getString(R.string.cancel_process),
                    getString(R.string.cancel_process_take_insurance),
                    true,
                    getString(R.string.accept),
                    getString(R.string.cancel)
                ) {
                    finish()
                }
            }
        })

    }

    private fun myObservers() {
        viewModel.tietBeneficiary.observe(this) { dataPersonalValid ->
            binding.tietBeneficiary.error = null
            if (dataPersonalValid.isError) {
                binding.tietBeneficiary.error = dataPersonalValid.message
            }

        }
        viewModel.tietDirection.observe(this) { dataPersonalValid ->
            binding.tietDirection.error = null
            if (dataPersonalValid.isError) {
                binding.tietDirection.error = dataPersonalValid.message
            }
        }
        viewModel.tietCP.observe(this) { dataPersonalValid ->
            binding.tietCP.error = null
            if (dataPersonalValid.isError) {
                binding.tietCP.error = dataPersonalValid.message
            }
        }
        viewModel.tietPhone.observe(this) { dataPersonalValid ->
            binding.tietPhone.error = null
            if (dataPersonalValid.isError) {
                binding.tietPhone.error = dataPersonalValid.message
            }
        }
        viewModel.tietEName.observe(this) { dataPersonalValid ->
            binding.tietEmergencyName.error = null
            if (dataPersonalValid.isError) {
                binding.tietEmergencyName.error = dataPersonalValid.message
            }
        }
        viewModel.tietEPhone.observe(this) { dataPersonalValid ->
            binding.tietEmergencyPhone.error = null
            if (dataPersonalValid.isError) {
                binding.tietEmergencyPhone.error = dataPersonalValid.message
            }
        }
        viewModel.typeInsurance.observe(this) { typeInsurance ->
            binding.tvDataPersonalTypeInsurance.text = typeInsurance

        }
        viewModel.goTakeInsurance.observe(this) { dataForTakeInsurance ->
            val intent = Intent(this@DataPersonalActivity, TakeOutInsuranceActivity::class.java)
            intent.putExtra("KEY_TYPE_POSITION", dataForTakeInsurance.position.toString())
            intent.putExtra("KEY_BENEFICIARY", dataForTakeInsurance.beneficiary)
            intent.putExtra("KEY_DIRECTION", dataForTakeInsurance.direcion)
            intent.putExtra("KEY_CP", dataForTakeInsurance.cp)
            intent.putExtra("KEY_PHONE", dataForTakeInsurance.phone)
            intent.putExtra("KEY_ENAME", dataForTakeInsurance.EName)
            intent.putExtra("KEY_EPHONE", dataForTakeInsurance.EPhone)
            startActivity(intent)
        }
    }
}