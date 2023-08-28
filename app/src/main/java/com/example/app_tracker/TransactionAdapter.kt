package com.example.app_tracker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app_tracker.data.Transaction
import com.example.app_tracker.databinding.ItemTransactionBinding

class TransactionAdapter(
    private val transactions: List<Transaction>
): RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>(){

    inner class TransactionViewHolder(val binding: ItemTransactionBinding):RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemTransactionBinding.inflate(layoutInflater, parent, false)

        return  TransactionViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return transactions.size
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.binding.apply {
            tvItem.text = transactions[position].title
            amountItem.text = transactions[position].amount.toString()
        }
    }


}
