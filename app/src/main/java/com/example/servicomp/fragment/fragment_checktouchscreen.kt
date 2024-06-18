package com.example.servicomp.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.example.servicomp.databinding.FragmentTouchcheckBinding

class TouchScreenFragment : AppCompatActivity() {

    private lateinit var binding: FragmentTouchcheckBinding
    private val colors = arrayOf(Color.RED, Color.GREEN, Color.BLUE, Color.BLACK, Color.WHITE)
    private var colorIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentTouchcheckBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set a click listener on a button to start the pixel test
        binding.btnStartTest.setOnClickListener {
            startPixelTest()
        }
    }

    private fun startPixelTest() {
        binding.root.setBackgroundColor(colors[colorIndex])
        colorIndex = (colorIndex + 1) % colors.size
    }
}
