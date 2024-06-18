package com.example.servicomp

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import java.lang.Thread.sleep
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.servicomp.databinding.ActivityOrderBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.io.ByteArrayOutputStream
import android.util.Base64
import android.util.Log
import android.widget.ImageButton
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class OrderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOrderBinding
    private var currentImageViewIndex = 0
    private val imageViews by lazy {
        arrayOf(binding.kotakFoto1, binding.kotakFoto2, binding.kotakFoto3)
    }

    private val takePicture = registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
        bitmap?.let {
            imageViews[currentImageViewIndex].setImageBitmap(it)
            currentImageViewIndex = (currentImageViewIndex + 1) % imageViews.size
        }
    }

    private val REQUEST_CODE_PERMISSIONS = 101
    private val REQUIRED_PERMISSIONS = arrayOf(android.Manifest.permission.CAMERA)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS)
        }

        val backArrow = findViewById<ImageButton>(R.id.back_arrow_order)
        backArrow.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }

        binding.btnTakePicture.setOnClickListener {
            if (allPermissionsGranted()) {
                takePicture.launch(null)
            } else {
                ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS)
            }
        }

        // Set click listeners for image views to show full image and allow retaking
        imageViews.forEachIndexed { index, imageView ->
            imageView.setOnClickListener {
                val drawable = imageView.drawable
                if (drawable != null) {
                    binding.fullImageView.setImageDrawable(drawable)
                    binding.fullImageView.visibility = View.VISIBLE
                    binding.closeFullImageButton.visibility = View.VISIBLE
                }

                binding.closeFullImageButton.setOnClickListener {
                    binding.fullImageView.visibility = View.GONE
                    binding.closeFullImageButton.visibility = View.GONE
                }

                binding.fullImageView.setOnClickListener {
                    binding.fullImageView.visibility = View.GONE
                    binding.closeFullImageButton.visibility = View.GONE
                }

                binding.fullImageView.setOnLongClickListener {
                    currentImageViewIndex = index
                    if (allPermissionsGranted()) {
                        takePicture.launch(null)
                    } else {
                        ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS)
                    }
                    true
                }
            }
        }

        binding.button2.setOnClickListener {
            saveOrderToFirebase()
        }
    }

    // Check if all required permissions are granted
    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED
    }

    // Handle permission request result
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (grantResults.isNotEmpty() && grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                // Permission granted, start camera or other relevant operations
                takePicture.launch(null)
            } else {
                // Permission denied, handle accordingly (e.g., show a message to the user)
            }
        }
    }

    private fun saveOrderToFirebase() {
        val brand = binding.editTextTextBrand.text.toString()
        val damageDescription = binding.editText.text.toString()

        if (brand.isEmpty() || damageDescription.isEmpty()) {
            // Display a message to the user to fill in the details
            Toast.makeText(this, "Silahkan isi Brand, dan Deskripsi kerusakan", Toast.LENGTH_SHORT).show()
            return
        }
        val orderType = intent.getStringExtra("order_type") ?: return
        Log.d("saveOrderToFirebase", "orderType: $orderType")
        val userId = FirebaseHelper.getCurrentUserId()
        if (userId != null) {
            val orderRef = FirebaseHelper.getUserDataReference(userId).child("orders").child(orderType).push()
            val orderId = orderRef.key ?: return

            val photos = mutableListOf<String>()
            imageViews.forEach { imageView ->
                val drawable = imageView.drawable as? BitmapDrawable
                drawable?.let {
                    val bitmap = it.bitmap
                    val photoString = bitmapToBase64(bitmap)
                    photos.add(photoString)
                }
            }

            val currentDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(
                Date()
            )
            val orderData = OrderData(
                brand = brand,
                damageDescription = damageDescription,
                photos = photos,
                jenispembayaran = null, // Tambahkan nilai default null untuk jenispembayaran
                harga = null, // Tambahkan nilai default null untuk harga
                tanggal = currentDate,
                progress = mapOf(
                    "review" to 0,
                    "penjemputan" to 0,
                    "service" to 0,
                    "pengantaran" to 0,
                    "selesai" to 0
                )
            )

            orderRef.setValue(orderData).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Order saved successfully
                    Toast.makeText(this, "Suksess order", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, ReviewActivity::class.java))
                    finish()
                } else {
                    // Handle the error
                    Toast.makeText(this, "Order gagal, silahkan coba lagi dalam 2 menit", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                }
            }
        }
    }

    private fun bitmapToBase64(bitmap: Bitmap): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.DEFAULT)
    }

    data class OrderData(
        val brand: String,
        val damageDescription: String,
        val photos: List<String>,
        val jenispembayaran: String? = null, // Menambahkan jenis pembayaran dengan default null
        val harga: Int? = null, // Menambahkan harga dengan default null
        val tanggal: String? = null, // Menambahkan tanggal dengan default null
        val progress: Map<String, Int>
    )
}
