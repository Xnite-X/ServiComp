package com.example.servicomp.fragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.servicomp.databinding.FragmentHardiskcheckBinding

class HardiskCheckFragment : AppCompatActivity() {

    private lateinit var binding: FragmentHardiskcheckBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentHardiskcheckBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
