package com.example.servicomp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.servicomp.databinding.ActivityPembayaranBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class PembayaranActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPembayaranBinding
    private lateinit var selectedOrderId: String
    private lateinit var selectedOrderType: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPembayaranBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userId = FirebaseHelper.getCurrentUserId()
        if (userId != null) {
            val ordersRef = FirebaseHelper.getUserDataReference(userId).child("orders")
            val possibleOrderTypes = listOf("Laptop", "Smartphone", "Computer")
            val orders = mutableListOf<ActiveOrderActivity.OrderData>()

            ordersRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (orderType in possibleOrderTypes) {
                        if (dataSnapshot.hasChild(orderType)) {
                            val specificOrderRef = ordersRef.child(orderType)
                            specificOrderRef.addListenerForSingleValueEvent(object :
                                ValueEventListener {
                                override fun onDataChange(orderDataSnapshot: DataSnapshot) {
                                    for (child in orderDataSnapshot.children) {
                                        val orderData = child.getValue(ActiveOrderActivity.OrderData::class.java)
                                        orderData?.let {
                                            if (it.progress?.get("selesai") == 0) {
                                                orders.add(it)
                                                selectedOrderId = child.key ?: ""
                                                selectedOrderType = orderType
                                                updateUI(it)
                                            }
                                        }
                                    }
                                }

                                override fun onCancelled(databaseError: DatabaseError) {
                                    Log.w("PembayaranActivity", "Failed to read order data.", databaseError.toException())
                                }
                            })
                        }
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Log.w("PembayaranActivity", "Failed to read orders.", databaseError.toException())
                }
            })
        } else {
            Log.d("PembayaranActivity", "User ID is null")
            Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show()
        }

        binding.buttonBayar.setOnClickListener {
            val selectedPaymentMethod = when (binding.RadioGrupBayar.checkedRadioButtonId) {
                R.id.radioButtonCash -> "Cash"
                R.id.bayar_transfer -> "Bank Transfer"
                else -> ""
            }

            val selectedPickupMethod = when (binding.Pengambilan.checkedRadioButtonId) {
                R.id.radio_button_1 -> "Kirim ke Alamat"
                R.id.radio_button_2 -> "Ambil di Counter"
                else -> ""
            }

            if (selectedPaymentMethod.isNotEmpty() && selectedPickupMethod.isNotEmpty() && ::selectedOrderId.isInitialized && ::selectedOrderType.isInitialized) {
                saveMethodsToFirebase(selectedOrderType, selectedOrderId, selectedPaymentMethod, selectedPickupMethod)
            } else {
                Toast.makeText(this, "Please select both a payment and pickup method", Toast.LENGTH_SHORT).show()
            }

            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun updateUI(orderData: ActiveOrderActivity.OrderData) {
        // Perbarui UI dengan data order yang diambil dari Firebase
        binding.pembayaranMerk.text = orderData.brand
        binding.txtKerusakan.text = orderData.damageDescription
        binding.hargaPembayaran.text = orderData.harga?.toString() ?: "N/A"
        binding.tglOrder.text = orderData.tanggal
    }

    private fun saveMethodsToFirebase(orderType: String, orderId: String, paymentMethod: String, pickupMethod: String) {
        val userId = FirebaseHelper.getCurrentUserId()
        if (userId != null) {
            val orderRef = FirebaseHelper.getUserDataReference(userId).child("orders").child(orderType).child(orderId)
            orderRef.child("jenispembayaran").setValue(paymentMethod)
            orderRef.child("Status Pembayaran").setValue("Berhasil")
            orderRef.child("jenis_pengambilan").setValue(pickupMethod).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Pembayaran Berhasil", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Failed to save methods", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
