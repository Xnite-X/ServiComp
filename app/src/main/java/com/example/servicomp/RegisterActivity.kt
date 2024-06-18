package com.example.servicomp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.servicomp.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import at.favre.lib.crypto.bcrypt.BCrypt

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        val backBtn: ImageView = binding.backButtonRegist

        binding.Register.setOnClickListener {
            val name = binding.editTextTextNama.text.toString().trim()
            val email = binding.editTextTextEmail.text.toString().trim()
            val alamat = binding.editTextTextPostalAddress.text.toString().trim()
            val noHp = binding.editTextTextNoHP.text.toString().trim()
            val password = binding.rgstPassword.text.toString().trim()
            val confirmPassword = binding.EditTextConfirmPassword.text.toString().trim()

            if (password == confirmPassword) {
                registerUser(name, email, alamat, noHp, password)
            } else {
                Toast.makeText(this, "Password and Confirm Password do not match", Toast.LENGTH_SHORT).show()
            }
        }

        backBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun hashPassword(password: String): String {
        return BCrypt.withDefaults().hashToString(12, password.toCharArray())
    }

    private fun registerUser(name: String, email: String, alamat: String, noHp: String, password: String) {
        val hashedPassword = hashPassword(password)

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    val userId = user?.uid

                    if (userId != null) {
                        val userMap = hashMapOf(
                            "name" to name,
                            "email" to email,
                            "alamat" to alamat,
                            "no_hp" to noHp,
                            "password" to hashedPassword
                        )

                        FirebaseHelper.getUserDataReference().setValue(userMap)
                            .addOnCompleteListener { databaseTask ->
                                if (databaseTask.isSuccessful) {
                                    Toast.makeText(this, "User registered successfully", Toast.LENGTH_SHORT).show()
                                    startActivity(Intent(this, HomeActivity::class.java))
                                    finish()
                                } else {
                                    Toast.makeText(this, "Database Error: ${databaseTask.exception?.message}", Toast.LENGTH_SHORT).show()
                                    Log.e("RegisterActivity", "Database Error: ${databaseTask.exception?.message}")
                                }
                            }
                    } else {
                        Toast.makeText(this, "User ID is null", Toast.LENGTH_SHORT).show()
                        Log.e("RegisterActivity", "User ID is null")
                    }
                } else {
                    Toast.makeText(this, "Authentication Error: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    Log.e("RegisterActivity", "Authentication Error: ${task.exception?.message}")
                }
            }
    }
}
