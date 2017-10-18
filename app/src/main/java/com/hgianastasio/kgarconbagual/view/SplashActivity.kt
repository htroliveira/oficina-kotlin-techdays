package com.hgianastasio.kgarconbagual.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.hgianastasio.kgarconbagual.R

/**
 * Created by filipenunes on 10/18/17.
 */
class SplashActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val background = object : Thread() {
            override fun run() {
                try {
                    Thread.sleep((2000).toLong())
                    val i = Intent(baseContext, MainActivity::class.java)
                    startActivity(i)
                    finish()
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }
        // start thread
        background.start()
    }
}