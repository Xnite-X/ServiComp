package com.example.servicomp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.servicomp.databinding.FragmentActiveOrderBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class ActiveOrderActivity : AppCompatActivity(), ActiveOrderAdapter.OnItemClickListener {

    private lateinit var binding: FragmentActiveOrderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentActiveOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userId = FirebaseHelper.getCurrentUserId()
        if (userId != null) {
            val ordersRef = FirebaseHelper.getUserDataReference(userId).child("orders")
            val possibleOrderTypes = listOf("Laptop", "Smartphone", "Computer")
            val orders = mutableListOf<OrderData>()

            ordersRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (orderType in possibleOrderTypes) {
                        if (dataSnapshot.hasChild(orderType)) {
                            val specificOrderRef = ordersRef.child(orderType)
                            specificOrderRef.addListenerForSingleValueEvent(object : ValueEventListener {
                                override fun onDataChange(orderDataSnapshot: DataSnapshot) {
                                    for (child in orderDataSnapshot.children) {
                                        val orderData = child.getValue(OrderData::class.java)
                                        orderData?.let {
                                            if (it.progress?.get("selesai") == 0) {
                                                it.orderId = child.key
                                                it.orderType = orderType
                                                orders.add(it)
                                            }
                                        }
                                    }
                                    setupRecyclerView(orders)
                                }

                                override fun onCancelled(databaseError: DatabaseError) {
                                    Log.w("ActiveOrderActivity", "Failed to read order data.", databaseError.toException())
                                }
                            })
                        }
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Log.w("ActiveOrderActivity", "Failed to read orders.", databaseError.toException())
                }
            })
        } else {
            Log.d("ActiveOrderActivity", "User ID is null")
            Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show()
        }

        setupBottomNavigationView()
    }

    data class OrderData(
        var brand: String? = null,
        var progress: Map<String, Int>? = null,
        var damageDescription: String? = null,
        var harga: Int? = null,
        var tanggal: String? = null,
        var orderId: String? = null,
        var orderType: String? = null
    )

    private fun setupRecyclerView(orders: List<OrderData>) {
        val adapter = ActiveOrderAdapter(this, orders)
        adapter.setOnItemClickListener(this) // Set listener
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun setupBottomNavigationView() {
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.itemBackgroundResource = R.color.bottom_nav_color
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.active -> true
                R.id.history -> {
                    val intent = Intent(this, TransactionHistoryActivity::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.profile -> {
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                else -> false
            }
        }
        bottomNavigationView.selectedItemId = R.id.active
    }

    override fun onItemClick(order: OrderData) {
        val userId = FirebaseHelper.getCurrentUserId()
        if (userId != null && order.orderType != null && order.orderId != null) {
            val paymentStatusRef = FirebaseHelper.getUserDataReference(userId)
                .child("orders")
                .child(order.orderType!!)
                .child(order.orderId!!)
                .child("Status Pembayaran")

            paymentStatusRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val paymentStatus = dataSnapshot.getValue(String::class.java)
                    if (paymentStatus != null) {
                        Toast.makeText(this@ActiveOrderActivity, "Sudah di bayar", Toast.LENGTH_SHORT).show()
                    } else if (order.progress?.get("review") == 1) {
                        val intent = Intent(this@ActiveOrderActivity, PembayaranActivity::class.java)
                        intent.putExtra("brand", order.brand)
                        intent.putExtra("tanggal", order.tanggal)
                        intent.putExtra("orderType", order.orderType)
                        intent.putExtra("orderId", order.orderId)
                        startActivity(intent)
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Log.w("ActiveOrderActivity", "Failed to read payment status.", databaseError.toException())
                }
            })
        }
    }
}
