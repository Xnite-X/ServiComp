package com.example.servicomp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import com.example.servicomp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set OnClickListener for the login button
        binding.btnLogin.setOnClickListener {
            // Start the login activity
            startActivity(Intent(this, LoginActivity::class.java))
        }

        // Set OnClickListener for the register button
        binding.btnRegist.setOnClickListener {
            // Start the register activity
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}
