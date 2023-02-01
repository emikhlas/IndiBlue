package com.example.indigofera

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        showHome()
    }

    private fun showHome() {
        Executors.newSingleThreadScheduledExecutor().schedule( {
            startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
            finish()
        }, 2, TimeUnit.SECONDS)
    }
}