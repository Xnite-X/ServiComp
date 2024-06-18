package com.example.servicomp.fragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.servicomp.databinding.FragmentCheckserisBinding

class SeriesCheckFragment : AppCompatActivity() {

    private lateinit var binding: FragmentCheckserisBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentCheckserisBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
