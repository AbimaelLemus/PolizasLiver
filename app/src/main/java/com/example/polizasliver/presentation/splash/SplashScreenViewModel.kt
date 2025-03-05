package com.example.polizasliver.presentation.splash

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashScreenViewModel(private val splashInterface: SplashInterface) : ViewModel(){

    fun onCreate() {
        CoroutineScope(Dispatchers.Default).launch {
            delay(java.util.concurrent.TimeUnit.SECONDS.toMillis(2))
            withContext(Dispatchers.Main) {
                //splashInterface.goHome()
            }
        }
    }

}