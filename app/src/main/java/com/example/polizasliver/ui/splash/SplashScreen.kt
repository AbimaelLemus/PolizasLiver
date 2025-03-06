package com.example.polizasliver.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.polizasliver.databinding.ActivitySplashScreenBinding
import com.example.polizasliver.ui.home.HomeInsuranceActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashScreen : AppCompatActivity(), SplashInterface {
    private lateinit var binding: ActivitySplashScreenBinding
    val viewModel: SplashScreenViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.onCreate()

        viewModel.goHome.observe(this) { go ->
            if (go) {
                //FIXME: componer esto cuando ya se implemente dagger y se pueda injectar la interfaz en el MV
                goHome()
            }
        }

    }

    override fun goHome() {
        var intent = Intent(this@SplashScreen, HomeInsuranceActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
    }
}