package com.example.saludappble.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.saludappble.R
import com.example.saludappble.ui.login.LoginActivity

class SplashActivity : AppCompatActivity() {
    private val splashTimeout : Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            abrirActivityLogin()
        }, splashTimeout)
    }

    private fun abrirActivityLogin(){
        startActivity(Intent(this, LoginActivity::class.java))
    }
}