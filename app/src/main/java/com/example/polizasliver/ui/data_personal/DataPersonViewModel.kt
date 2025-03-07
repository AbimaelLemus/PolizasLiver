package com.example.polizasliver.ui.data_personal

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.polizasliver.R
import com.example.polizasliver.data.model.DataForTakeInsurance
import com.example.polizasliver.data.model.DataPersonalValid
import com.example.polizasliver.data.model.TypeInsuranceEnum
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DataPersonViewModel @Inject constructor() : ViewModel() {
    val tietBeneficiary = MutableLiveData<DataPersonalValid>()
    val tietDirection = MutableLiveData<DataPersonalValid>()
    val tietEName = MutableLiveData<DataPersonalValid>()

    val tietCP = MutableLiveData<DataPersonalValid>()
    val tietPhone = MutableLiveData<DataPersonalValid>()
    val tietEPhone = MutableLiveData<DataPersonalValid>()

    val typeInsurance = MutableLiveData<String>()
    private var mPosition: Int = 0

    val goTakeInsurance = MutableLiveData<DataForTakeInsurance>()


    fun onCreate(position: Int) {
        mPosition = position
        typeInsurance.postValue(
            when (position) {
                1 -> {
                    TypeInsuranceEnum.AUTO.name
                }

                2 -> {
                    TypeInsuranceEnum.PETS.name
                }

                3 -> {
                    TypeInsuranceEnum.PHONE.name
                }

                else -> {
                    TypeInsuranceEnum.PERSON.name
                }
            }
        )
    }

    fun validForm(
        context: Context,
        beneficiary: String,
        direction: String,
        cp: String,
        phone: String,
        eName: String,
        ePhone: String
    ) {
        val isValidBeneficiary: Boolean
        if (beneficiary.isEmpty()) {
            tietBeneficiary.postValue(
                DataPersonalValid(
                    true,
                    context.getString(R.string.adds_beneficiary)
                )
            )
            isValidBeneficiary = false
        } else {
            tietBeneficiary.postValue(DataPersonalValid(false, null))
            isValidBeneficiary = true
        }

        val isValidDirection: Boolean
        if (direction.isEmpty()) {
            tietDirection.postValue(
                DataPersonalValid(
                    true,
                    context.getString(R.string.adds_direction)
                )
            )
            isValidDirection = false
        } else {
            tietDirection.postValue(DataPersonalValid(false, null))
            isValidDirection = true
        }

        val isValidEname: Boolean
        if (eName.isEmpty()) {
            tietEName.postValue(
                DataPersonalValid(
                    true,
                    context.getString(R.string.adds_emergency_name)
                )
            )
            isValidEname = false
        } else {
            tietEName.postValue(DataPersonalValid(false, null))
            isValidEname = true
        }

        val isValidCP: Boolean
        if (cp.isEmpty()) {
            tietCP.postValue(
                DataPersonalValid(
                    true,
                    context.getString(R.string.adds_cp)
                )
            )
            isValidCP = false
        } else if (cp.length < 5) {
            tietCP.postValue(
                DataPersonalValid(
                    true,
                    context.getString(R.string.less_five)
                )
            )
            isValidCP = false
        } else {
            tietCP.postValue(DataPersonalValid(false, null))
            isValidCP = true
        }

        val isValidPhone: Boolean
        if (phone.isEmpty()) {
            tietPhone.postValue(
                DataPersonalValid(
                    true,
                    context.getString(R.string.adds_phone)
                )
            )
            isValidPhone = false
        } else if (phone.length < 10) {
            tietPhone.postValue(
                DataPersonalValid(
                    true,
                    context.getString(R.string.less_ten)
                )
            )
            isValidPhone = false
        } else {
            tietPhone.postValue(DataPersonalValid(false, null))
            isValidPhone = true
        }

        val isValidEPhone: Boolean
        if (ePhone.isEmpty()) {
            tietEPhone.postValue(
                DataPersonalValid(
                    true,
                    context.getString(R.string.adds_phone)
                )
            )
            isValidEPhone = false
        } else if (ePhone.length < 10) {
            tietEPhone.postValue(
                DataPersonalValid(
                    true,
                    context.getString(R.string.less_ten)
                )
            )
            isValidEPhone = false
        } else {
            tietEPhone.postValue(DataPersonalValid(false, null))
            isValidEPhone = true
        }

        if (isValidBeneficiary && isValidDirection && isValidCP
            && isValidPhone && isValidEname && isValidEPhone
        ) {
            goTakeInsurance.postValue(
                DataForTakeInsurance(
                    beneficiary,
                    direction,
                    cp,
                    phone,
                    eName,
                    ePhone
                )
            )
        }

    }
}