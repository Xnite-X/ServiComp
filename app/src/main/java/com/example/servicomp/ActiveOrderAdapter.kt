package com.example.servicomp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.servicomp.databinding.CardActiveOrderBinding

class ActiveOrderAdapter(private val context: Context, private val orders: List<ActiveOrderActivity.OrderData>) :
    RecyclerView.Adapter<ActiveOrderAdapter.OrderViewHolder>() {

    private var itemClickListener: OnItemClickListener? = null

    inner class OrderViewHolder(val binding: CardActiveOrderBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val binding = CardActiveOrderBinding.inflate(LayoutInflater.from(context), parent, false)
        return OrderViewHolder(binding)
    }

    interface OnItemClickListener {
        fun onItemClick(order: ActiveOrderActivity.OrderData)
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val order = orders[position]
        holder.binding.ActiveOrderMerk.text = order.brand

        order.progress?.let {
            holder.binding.ProgressReview.setBackgroundColor(
                ResourcesCompat.getColor(context.resources, if (it["review"] == 1) R.color.green else R.color.grey, null)
            )
            holder.binding.ProgressPenjemputan.setBackgroundColor(
                ResourcesCompat.getColor(context.resources, if (it["penjemputan"] == 1) R.color.green else R.color.grey, null)
            )
            holder.binding.ProgressService.setBackgroundColor(
                ResourcesCompat.getColor(context.resources, if (it["service"] == 1) R.color.green else R.color.grey, null)
            )
            holder.binding.ProgressPengantaran.setBackgroundColor(
                ResourcesCompat.getColor(context.resources, if (it["pengantaran"] == 1) R.color.green else R.color.grey, null)
            )
            holder.binding.ProgressSelesai.setBackgroundColor(
                ResourcesCompat.getColor(context.resources, if (it["selesai"] == 1) R.color.green else R.color.grey, null)
            )
        }

        holder.binding.root.setOnClickListener {
            itemClickListener?.onItemClick(order)
        }
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }

    override fun getItemCount() = orders.size
}
