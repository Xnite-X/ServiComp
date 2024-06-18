package com.example.servicomp.fragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.servicomp.databinding.FragmentKeyboardcheckBinding

class KeyboardCheckFragment : AppCompatActivity() {

    private lateinit var binding: FragmentKeyboardcheckBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentKeyboardcheckBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
