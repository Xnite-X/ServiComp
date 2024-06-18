package com.example.servicomp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.servicomp.databinding.CardTransactionHistoryBinding

class TransactionHistoryAdapter(private val context: Context, private val orders: List<TransactionHistoryActivity.OrderData>) :
    RecyclerView.Adapter<TransactionHistoryAdapter.OrderViewHolder>() {

    inner class OrderViewHolder(val binding: CardTransactionHistoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val binding = CardTransactionHistoryBinding.inflate(LayoutInflater.from(context), parent, false)
        return OrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orders[position]
        holder.binding.HistoryMerk.text = order.brand
        holder.binding.TxtKerusakanMerk.text = order.damageDescription
        holder.binding.HargaHistory.text = order.harga.toString()
        holder.binding.TglOrderHistory.text = order.tanggal
    }

    override fun getItemCount() = orders.size
}

