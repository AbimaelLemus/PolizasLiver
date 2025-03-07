package com.example.polizasliver.ui.take_out_insurance

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.polizasliver.R
import com.example.polizasliver.data.model.TermsModel
import com.example.polizasliver.data.model.TypeInsuranceEnum
import com.example.polizasliver.domain.InsertInfoInsuranceUseCase
import com.example.polizasliver.domain.model.InfoInsuranceItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TakeOutInsuranceViewModel @Inject constructor(
    private val insertInfo: InsertInfoInsuranceUseCase
) : ViewModel() {

    private val TAG = TakeOutInsuranceViewModel::class.java.simpleName

    val listTerms = MutableLiveData<List<TermsModel>>()
    val typeInsurance = MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>()
    val showCongratulation = MutableLiveData<Boolean>()

    var isDateStart: Boolean = false
    var isDateEnd: Boolean = false
    var isCoverage: Boolean = false
    var isExclusions: Boolean = false
    var isDeductibles: Boolean = false
    var isPayments: Boolean = false


    fun inicialize(context: Context, position: Int) {
        val clausesCoverage: String
        val clausesExclusions: String
        val clausesDeductibles: String
        val clausesPayments: String
        val desc: String
        when (position) {
            1 -> {
                clausesCoverage = context.getString(R.string.clauses_auto_coverage)
                clausesExclusions = context.getString(R.string.clauses_auto_exclusions)
                clausesDeductibles = context.getString(R.string.clauses_auto_deductibles)
                clausesPayments = context.getString(R.string.clauses_auto_payments)
                desc = context.getString(R.string.desc_auto_insurance)
                typeInsurance.postValue(TypeInsuranceEnum.MASCOTAS.name)
            }

            2 -> {
                clausesCoverage = context.getString(R.string.clauses_pets_coverage)
                clausesExclusions = context.getString(R.string.clauses_pets_exclusions)
                clausesDeductibles = context.getString(R.string.clauses_pets_deductibles)
                clausesPayments = context.getString(R.string.clauses_pets_payments)
                desc = context.getString(R.string.desc_pets_insurance)
                typeInsurance.postValue(TypeInsuranceEnum.TELEFONOS.name)
            }

            3 -> {
                clausesCoverage = context.getString(R.string.clauses_phone_coverage)
                clausesExclusions = context.getString(R.string.clauses_phone_exclusions)
                clausesDeductibles = context.getString(R.string.clauses_phone_deductibles)
                clausesPayments = context.getString(R.string.clauses_phone_payments)
                desc = context.getString(R.string.desc_phone_insurance)
                typeInsurance.postValue(TypeInsuranceEnum.VIDA.name)
            }

            else -> {
                clausesCoverage = context.getString(R.string.clauses_life_coverage)
                clausesExclusions = context.getString(R.string.clauses_life_exclusions)
                clausesDeductibles = context.getString(R.string.clauses_life_deductibles)
                clausesPayments = context.getString(R.string.clauses_life_payments)
                desc = context.getString(R.string.desc_life_insurance)
                typeInsurance.postValue(TypeInsuranceEnum.AUTO.name)
            }
        }

        listTerms.postValue(
            listOf(
                TermsModel(
                    context.getString(R.string.coverage),
                    R.drawable.health_and_safety,
                    clausesCoverage,
                    desc
                ),
                TermsModel(
                    context.getString(R.string.exclusions),
                    R.drawable.gpp_bad,
                    clausesExclusions,
                    desc
                ),
                TermsModel(
                    context.getString(R.string.deductibles),
                    R.drawable.calculate,
                    clausesDeductibles,
                    desc
                ),
                TermsModel(
                    context.getString(R.string.payments),
                    R.drawable.payments,
                    clausesPayments,
                    desc
                ),
            )
        )
    }

    fun validate(info: InfoInsuranceItem) {
        isLoading.postValue(true)
        viewModelScope.launch {
            if (isDateStart && isDateEnd && isCoverage && isExclusions && isDeductibles && isPayments) {

                val insert = insertInfo.invoke(info)
                Log.e(TAG, "validate: $insert")
                isLoading.postValue(false)
                showCongratulation.postValue(true)
            } else {
                showCongratulation.postValue(false)
            }
        }
    }

    fun getEnum(mPosition: Int): String {
        return when (mPosition) {
            1 -> {
                TypeInsuranceEnum.MASCOTAS.name
            }

            2 -> {
                TypeInsuranceEnum.TELEFONOS.name
            }

            3 -> {
                TypeInsuranceEnum.VIDA.name
            }

            else -> {
                TypeInsuranceEnum.AUTO.name
            }
        }
    }

}