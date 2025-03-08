package com.example.polizasliver.ui.detail

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.polizasliver.R
import com.example.polizasliver.data.model.TermsModel
import com.example.polizasliver.data.model.TypeInsuranceEnum
import com.example.polizasliver.domain.DeleteInsuranceUseCase
import com.example.polizasliver.domain.GetDetailInsuranceUseCase
import com.example.polizasliver.domain.model.InfoInsuranceItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getDetailUseCase: GetDetailInsuranceUseCase,
    private val deleteInsuranceUseCase: DeleteInsuranceUseCase
) : ViewModel() {

    val infoInsurance = MutableLiveData<InfoInsuranceItem>()
    val iconInsurance = MutableLiveData<Int>()
    val listTerms = MutableLiveData<List<TermsModel>>()
    val deleteInsurance = MutableLiveData<String>()

    fun inicialized(context: Context, noInsurance: String) {
        viewModelScope.launch {
            val result = getDetailUseCase.invoke(noInsurance)
            infoInsurance.postValue(result)

            val icon = if (result.nameInsurance == TypeInsuranceEnum.AUTO.name) {
                R.drawable.car
            } else if (result.nameInsurance == TypeInsuranceEnum.TELEFONOS.name) {
                R.drawable.phone
            } else if (result.nameInsurance == TypeInsuranceEnum.MASCOTAS.name) {
                R.drawable.pets
            } else {
                R.drawable.person
            }
            iconInsurance.postValue(icon)
            termsCondition(context, result)

        }
    }


    fun termsCondition(context: Context, result: InfoInsuranceItem) {
        val clausesCoverage: String
        val clausesExclusions: String
        val clausesDeductibles: String
        when (result.nameInsurance) {
            TypeInsuranceEnum.AUTO.name -> {
                clausesCoverage = context.getString(R.string.clauses_auto_coverage)
                clausesExclusions = context.getString(R.string.clauses_auto_exclusions)
                clausesDeductibles = context.getString(R.string.clauses_auto_deductibles)
            }

            TypeInsuranceEnum.MASCOTAS.name -> {
                clausesCoverage = context.getString(R.string.clauses_pets_coverage)
                clausesExclusions = context.getString(R.string.clauses_pets_exclusions)
                clausesDeductibles = context.getString(R.string.clauses_pets_deductibles)
            }

            TypeInsuranceEnum.TELEFONOS.name -> {
                clausesCoverage = context.getString(R.string.clauses_phone_coverage)
                clausesExclusions = context.getString(R.string.clauses_phone_exclusions)
                clausesDeductibles = context.getString(R.string.clauses_phone_deductibles)
            }

            else -> {
                clausesCoverage = context.getString(R.string.clauses_life_coverage)
                clausesExclusions = context.getString(R.string.clauses_life_exclusions)
                clausesDeductibles = context.getString(R.string.clauses_life_deductibles)
            }
        }
        val dataPersonal = "Póliza de:   ${result.nameInsurance}\n\n" +
                "Beneficiario:\n${result.clientsName}\n\n" +
                "Dirección:\n${result.direction}\n\n" +
                "C.P.:\n${result.cp}\n\n" +
                "Teléfono:\n${result.phone}\n\n" +
                "Contacto de emergencia:\n${result.emergencyContact}\n\n" +
                "Teléfono del contaco de emergencia:\n${result.emergencyPhone}"

        listTerms.postValue(
            listOf(
                TermsModel(
                    context.getString(R.string.coverage),
                    R.drawable.health_and_safety,
                    clausesCoverage,
                    ""
                ),
                TermsModel(
                    context.getString(R.string.exclusions),
                    R.drawable.gpp_bad,
                    clausesExclusions,
                    ""
                ),
                TermsModel(
                    context.getString(R.string.deductibles),
                    R.drawable.calculate,
                    clausesDeductibles,
                    ""
                ),
                TermsModel(
                    context.getString(R.string.data_personal),
                    R.drawable.person_add,
                    dataPersonal,
                    ""
                ),
            )
        )
    }

    fun cancelInsurance(context: Context, noInsurance: String) {
        viewModelScope.launch {
            val isDelete = deleteInsuranceUseCase.invoke(noInsurance)
            val result = if (isDelete != 0) {
                context.getString(R.string.cancelInsuranceSuccess)
            } else {
                context.getString(R.string.cancelInsuranceError)
            }
            deleteInsurance.postValue(result)
        }
    }
}