package com.example.servicomp

import FirebaseHelper
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.servicomp.databinding.FragmentHomeBinding
import com.example.servicomp.fragment.HardiskCheckFragment
import com.example.servicomp.fragment.KeyboardCheckFragment
import com.example.servicomp.fragment.RamCheckFragment
import com.example.servicomp.fragment.SeriesCheckFragment
import com.example.servicomp.fragment.SlowLaptopFragment
import com.example.servicomp.fragment.TouchScreenFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener

class HomeActivity : AppCompatActivity() {

    private lateinit var btn_smartphone: LinearLayout
    private lateinit var btn_laptop: LinearLayout
    private lateinit var btn_computer: LinearLayout
    private lateinit var btn_series: LinearLayout
    private lateinit var btn_slow: LinearLayout
    private lateinit var btn_ram: LinearLayout
    private lateinit var btn_touch: LinearLayout
    private lateinit var btn_keyboard: LinearLayout
    private lateinit var btn_hardisk: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = FragmentHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize views
        btn_smartphone = findViewById(R.id.click_smartphone)
        btn_laptop = findViewById(R.id.click_laptop)
        btn_computer = findViewById(R.id.click_computer)
        btn_series = findViewById(R.id.click_series)
        btn_slow = findViewById(R.id.click_slow)
        btn_ram = findViewById(R.id.click_ram)
        btn_touch = findViewById(R.id.click_touch)
        btn_keyboard = findViewById(R.id.click_keyboard)
        btn_hardisk = findViewById(R.id.click_hardisk)

        // Get current user ID
        val userId = FirebaseHelper.getCurrentUserId()

        // Get reference to the node containing user data in Firebase Realtime Database
        val userRef = FirebaseHelper.getUserDataReference(userId)

        //Untuk tampilkan nama pada text diatas Welcome/HI
        extracted(userRef, binding)

        //btn Service Smartphone
        btn_smartphone.setOnClickListener {
            val intent = Intent(this, OrderActivity::class.java)
            intent.putExtra("order_type", "Smartphone")
            startActivity(intent)
        }

        //btn Service Laptop
        btn_laptop.setOnClickListener {
            val intent = Intent(this, OrderActivity::class.java)
            intent.putExtra("order_type", "Laptop")
            startActivity(intent)
        }

        //btn Service Computer
        btn_computer.setOnClickListener {
            val intent = Intent(this, OrderActivity::class.java)
            intent.putExtra("order_type", "Computer")
            startActivity(intent)
        }

        //Tips and trick button
        btn_series.setOnClickListener {
            val intent = Intent(this, SeriesCheckFragment::class.java)
            startActivity(intent)
        }

        //btn Service Slow
        btn_slow.setOnClickListener {
            val intent = Intent(this, SlowLaptopFragment::class.java)
            startActivity(intent)
        }

        //btn Service RAM
        btn_ram.setOnClickListener {
            val intent = Intent(this, RamCheckFragment::class.java)
            startActivity(intent)
        }

        //btn Service Touch
        btn_touch.setOnClickListener {
            val intent = Intent(this, TouchScreenFragment::class.java)
            startActivity(intent)
        }

        //btn Service Keyboard
        btn_keyboard.setOnClickListener {
            val intent = Intent(this, KeyboardCheckFragment::class.java)
            startActivity(intent)
        }

        //btn Service Hardisk
        btn_hardisk.setOnClickListener {
            val intent = Intent(this, HardiskCheckFragment::class.java)
            startActivity(intent)
        }

        // Set up BottomNavigationView
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.itemBackgroundResource = R.color.bottom_nav_color;
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    true
                }
                R.id.active -> {
                    val intent = Intent(this, ActiveOrderActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.history -> {
                    val intent = Intent(this, TransactionHistoryActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.profile -> {
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
        bottomNavigationView.selectedItemId = R.id.home
    }

    private fun extracted(
        userRef: DatabaseReference,
        binding: FragmentHomeBinding
    ) {
        userRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Retrieve the value of "name" from the snapshot
                val userName = dataSnapshot.child("name").getValue(String::class.java)
                binding.wlcmUser.text = "Hi, ${userName ?: "User not found"}"
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle errors that occur during the listener registration or data fetching
                Log.e("Firebase", "Error fetching user data: ${databaseError.message}")
            }
        })
    }
}
