package com.example.polizasliver.ui.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor() : ViewModel() {
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