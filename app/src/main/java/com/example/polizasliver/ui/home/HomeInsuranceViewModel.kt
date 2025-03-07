package com.example.polizasliver.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.polizasliver.domain.GetAllInfoInsuranceUseCase
import com.example.polizasliver.domain.model.InfoInsuranceItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeInsuranceViewModel @Inject constructor(
    private val getAllInfoInsurance: GetAllInfoInsuranceUseCase
) : ViewModel() {

    val infoInsurances = MutableLiveData<List<InfoInsuranceItem>?>()
    val isLoading = MutableLiveData<Boolean>()
    val insuranceNull = MutableLiveData<Boolean>()
    private val TAG = HomeInsuranceViewModel::class.java.simpleName

    fun onCreate() {
        isLoading.postValue(true)
        viewModelScope.launch {
            val result = getAllInfoInsurance()
            Log.e(TAG, "onCreate: size:${result?.size}")
            if (result != null) {
                infoInsurances.postValue(result)
                insuranceNull.postValue(false)
            } else {
                insuranceNull.postValue(true)
            }
            isLoading.postValue(false)
        }
    }
}