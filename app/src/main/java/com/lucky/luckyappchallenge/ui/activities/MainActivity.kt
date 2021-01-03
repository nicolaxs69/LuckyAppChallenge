package com.lucky.luckyappchallenge.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.lucky.luckyappchallenge.R
import com.lucky.luckyappchallenge.utils.SPLASH_TIME_OUT

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        goToHomeActivity()
    }

    private fun goToHomeActivity() {
        Handler().postDelayed({
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            this.finish()

        }, SPLASH_TIME_OUT.toLong())
    }
}