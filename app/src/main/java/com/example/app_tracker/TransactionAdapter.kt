package com.example.app_tracker

import android.view.LayoutInflater
import android.view.SurfaceControl.Transaction
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TransactionAdapter(
 var transactions: List<Transaction>
): RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>(){

    inner class TransactionViewHolder(itemView: View):RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_transaction,parent,false)
        return  TransactionViewHolder(view)
    }

    override fun getItemCount(): Int {
        return transactions.size
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.itemView.apply {
            tvItem.text
        }
    }


}
