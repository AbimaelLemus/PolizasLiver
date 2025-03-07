package com.example.polizasliver.ui.insurance

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.polizasliver.R
import com.example.polizasliver.databinding.ActivityInsuranceBinding
import com.example.polizasliver.domain.model.InfoInsuranceItem
import com.example.polizasliver.ui.adapter.TakeOutInsuranceAdapter
import com.example.polizasliver.ui.adapter.TypesInsuranceActives
import com.example.polizasliver.ui.dialogs_alerts.DialogsAlerts
import com.example.polizasliver.ui.home.HomeInsuranceActivity
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@AndroidEntryPoint
class TakeOutInsuranceActivity : AppCompatActivity(), TypesInsuranceActives {
    private lateinit var binding: ActivityInsuranceBinding
    private val TAG = TakeOutInsuranceActivity::class.java.simpleName
    private val calendar = Calendar.getInstance()
    private val viewModel: TakeOutInsuranceViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityInsuranceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val mPosition = intent.getStringExtra("KEY_TYPE_POSITION").orEmpty()
        viewModel.inicialize(this, mPosition.toInt())
        val mBeneficiary = intent.getStringExtra("KEY_BENEFICIARY").orEmpty()
        binding.tvTakeOutInsuranceName.text = mBeneficiary

        Log.e(TAG, "onCreate: mPosition:$mPosition mBeneficiary:$mBeneficiary")

        binding.rvTakeOurInsurance.layoutManager =
            LinearLayoutManager(this@TakeOutInsuranceActivity)
        myObservers()

        onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                DialogsAlerts().warningAlet(
                    this@TakeOutInsuranceActivity,
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

        binding.btnTakeOunInsuranceDateStart.setOnClickListener {
            showDatePickerStart()
        }
        binding.btnTakeOunInsuranceDateEnd.setOnClickListener {
            showDatePickerEnd()
        }
        binding.btnTakeOutInsurance.setOnClickListener {
            val mPosition = intent.getStringExtra("KEY_TYPE_POSITION").orEmpty()
            val mBeneficiary = intent.getStringExtra("KEY_BENEFICIARY").orEmpty()
            val mDirection = intent.getStringExtra("KEY_DIRECTION").orEmpty()
            val mCP = intent.getStringExtra("KEY_CP").orEmpty()
            val mPhone = intent.getStringExtra("KEY_PHONE").orEmpty()
            val mEName = intent.getStringExtra("KEY_ENAME").orEmpty()
            val mEPhone = intent.getStringExtra("KEY_EPHONE").orEmpty()


            val sdf = SimpleDateFormat("HHmmss", Locale("es", "ES"))
            sdf.timeZone = TimeZone.getTimeZone("UTC+1")
            val currentdate = sdf.format(Date())
            Log.d("fechaactual:", currentdate)
            val nameInsurance = viewModel.getEnum(mPosition.toInt())

            val info = InfoInsuranceItem(
                currentdate,
                nameInsurance,
                mBeneficiary,
                mDirection,
                mCP,
                mPhone,
                mEName,
                mEPhone
            )

            viewModel.validate(info)
        }
    }

    private fun showDatePickerStart() {
        val datePickerDialog = DatePickerDialog(
            this, { DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, monthOfYear, dayOfMonth)
                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val formattedDate = dateFormat.format(selectedDate.time)
                binding.tvInsuranceProtectedSince.text = "$formattedDate"
                binding.ivInsuranceStartDate.setColorFilter(
                    resources.getColor(R.color.green),
                    android.graphics.PorterDuff.Mode.MULTIPLY
                )
                viewModel.isDateStart = true
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    private fun showDatePickerEnd() {
        val datePickerDialog = DatePickerDialog(
            this, { DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, monthOfYear, dayOfMonth)
                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val formattedDate = dateFormat.format(selectedDate.time)
                binding.tvInsuranceProtectedUpTo.text = "$formattedDate"
                binding.ivInsuranceEndDate.setColorFilter(
                    resources.getColor(R.color.green),
                    android.graphics.PorterDuff.Mode.MULTIPLY
                )
                viewModel.isDateEnd = true
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    fun myObservers() {
        viewModel.listTerms.observe(this) { termsModel ->
            binding.tvInsuranceDescription.text = termsModel[0].description
            binding.rvTakeOurInsurance.adapter =
                TakeOutInsuranceAdapter(this@TakeOutInsuranceActivity, termsModel, this)
        }
        viewModel.typeInsurance.observe(this) { typeInsurance ->
            binding.tvInsuranceTittle.text = typeInsurance
        }
        viewModel.isLoading.observe(this) { isLoading ->
            if (isLoading) {
                binding.pbTakeOutInsurance.visibility = View.VISIBLE
            } else {

                binding.pbTakeOutInsurance.visibility = View.INVISIBLE
            }
        }
        viewModel.showCongratulation.observe(this) { showCongratulation ->
            if (showCongratulation) {
                DialogsAlerts().succesDialog(this@TakeOutInsuranceActivity) {
                    val intent =
                        Intent(this@TakeOutInsuranceActivity, HomeInsuranceActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    override fun ItemTypeActives(
        coverage: Boolean,
        exclusions: Boolean,
        deductibles: Boolean,
        payments: Boolean
    ) {
        if (coverage) {
            binding.ivInsuranceHealthSafety.setColorFilter(
                resources.getColor(R.color.green),
                android.graphics.PorterDuff.Mode.MULTIPLY
            )
            viewModel.isCoverage = true
        }
        if (exclusions) {
            binding.ivInsuranceGppBad.setColorFilter(
                resources.getColor(R.color.green),
                android.graphics.PorterDuff.Mode.MULTIPLY
            )
            viewModel.isExclusions = true
        }
        if (deductibles) {
            binding.ivInsuranceCalculate.setColorFilter(
                resources.getColor(R.color.green),
                android.graphics.PorterDuff.Mode.MULTIPLY
            )
            viewModel.isDeductibles = true
        }
        if (payments) {
            binding.ivInsurancePayments.setColorFilter(
                resources.getColor(R.color.green),
                android.graphics.PorterDuff.Mode.MULTIPLY
            )
            viewModel.isPayments = true
        }
    }
}

