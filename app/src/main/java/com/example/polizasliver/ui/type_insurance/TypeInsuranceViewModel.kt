package com.example.polizasliver.ui.type_insurance

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.polizasliver.R
import com.example.polizasliver.data.model.InformationAlertModel
import com.example.polizasliver.data.model.TypeInsuranceModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TypeInsuranceViewModel @Inject constructor() : ViewModel() {
    val listTypeInsurance = MutableLiveData<List<TypeInsuranceModel>>()
    val informationAlertModel = MutableLiveData<InformationAlertModel>()
    fun onCreate(context: Context) {
        listTypeInsurance.postValue(
            listOf(
                TypeInsuranceModel(context.getString(R.string.auto_insurance), R.drawable.car),
                TypeInsuranceModel(context.getString(R.string.pets_insurance), R.drawable.pets),
                TypeInsuranceModel(context.getString(R.string.phone_insurance), R.drawable.phone),
                TypeInsuranceModel(context.getString(R.string.life_insurance), R.drawable.person)
            )
        )
    }

    fun evaluateType(context: Context, position: Int) {
        val informationAlert = when (position) {
            1 -> {
                InformationAlertModel(
                    position,
                    tittle = context.getString(R.string.pets_insurance),
                    message = context.getString(R.string.desc_pets_insurance)
                )
            }

            2 -> {
                InformationAlertModel(
                    position,
                    tittle = context.getString(R.string.phone_insurance),
                    message = context.getString(R.string.desc_phone_insurance)
                )
            }

            3 -> {
                InformationAlertModel(
                    position,
                    tittle = context.getString(R.string.life_insurance),
                    message = context.getString(R.string.desc_life_insurance)
                )
            }

            else -> {
                InformationAlertModel(
                    position,
                    tittle = context.getString(R.string.auto_insurance),
                    message = context.getString(R.string.desc_auto_insurance)
                )
            }
        }

        informationAlertModel.postValue(
            informationAlert
        )
    }
}