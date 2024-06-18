package com.example.servicomp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.servicomp.databinding.FragmentProfileBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = FragmentProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Get current user ID
        val userId = FirebaseHelper.getCurrentUserId()

        // Get reference to the node containing user data in Firebase Realtime Database
        val userRef = FirebaseHelper.getUserDataReference(userId)

        // fetch data from firebase
        userRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Retrieve the value of "name" from the snapshot
                val userName = dataSnapshot.child("name").getValue(String::class.java)
                val alamat = dataSnapshot.child("alamat").getValue(String::class.java)
                val email = dataSnapshot.child("email").getValue(String::class.java)
                binding.namaProfile.text = userName ?: "User not found"
                binding.alamatProfile.text = alamat ?: "Alamat not found"
                binding.emailProfile.text = email ?: "Email not found"
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle errors that occur during the listener registration or data fetching
                Log.e("Firebase", "Error fetching user data: ${databaseError.message}")
            }
        })

        // Set up BottomNavigationView
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.itemBackgroundResource = R.color.bottom_nav_color;
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.active -> {
                    val intent = Intent(this, ActiveOrderActivity::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.history -> {
                    val intent = Intent(this, TransactionHistoryActivity::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.profile -> {
                    true
                }
                else -> false
            }
        }
        bottomNavigationView.selectedItemId = R.id.profile
    }

}
