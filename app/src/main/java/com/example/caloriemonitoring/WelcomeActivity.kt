package com.example.caloriemonitoring

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.caloriemonitoring.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val start = binding.btnWelcome

        start.setOnClickListener{
            getStarted()
        }
    }

    private fun getStarted() {
        val intent = Intent(this@WelcomeActivity, GetStartedActivity::class.java)
        startActivity(intent)
    }
}