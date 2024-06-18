package com.example.servicomp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.servicomp.databinding.FragmentTransactionHistoryBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class TransactionHistoryActivity : AppCompatActivity() {

    private lateinit var binding: FragmentTransactionHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentTransactionHistoryBinding.inflate(layoutInflater)
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
                                            if (it.progress?.get("selesai") == 1) {
                                                it.orderId = child.key
                                                it.orderType = orderType
                                                orders.add(it)
                                            }
                                        }
                                    }
                                    setupRecyclerView(orders)
                                }

                                override fun onCancelled(databaseError: DatabaseError) {
                                    // Handle error
                                }
                            })
                        }
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Handle error
                }
            })
        } else {
            // Handle user not logged in
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
        val adapter = TransactionHistoryAdapter(this, orders)
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
                R.id.active -> {
                    val intent = Intent(this, ActiveOrderActivity::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.history -> {
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
        bottomNavigationView.selectedItemId = R.id.history
    }
}
