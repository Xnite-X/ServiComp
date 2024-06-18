package com.example.servicomp.fragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.servicomp.databinding.FragmentRamcheckBinding

class RamCheckFragment : AppCompatActivity() {

    private lateinit var binding: FragmentRamcheckBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentRamcheckBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
