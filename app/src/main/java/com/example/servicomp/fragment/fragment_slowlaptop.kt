package com.example.servicomp.fragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.servicomp.databinding.FragmentSlowlaptopBinding

class SlowLaptopFragment : AppCompatActivity() {

    private lateinit var binding: FragmentSlowlaptopBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentSlowlaptopBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
