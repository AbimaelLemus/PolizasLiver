package com.example.polizasliver.presentation.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashScreenViewModel() : ViewModel(){
    val goHome = MutableLiveData<Boolean>()

    fun onCreate() {
        goHome.postValue(false)
        CoroutineScope(Dispatchers.IO).launch {
            delay(java.util.concurrent.TimeUnit.SECONDS.toMillis(2))
            withContext(Dispatchers.IO) {
                goHome.postValue(true)
            }
        }
    }

}