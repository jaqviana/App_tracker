package com.example.app_tracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.app_tracker.Presentation.TransactionViewModel
import com.example.app_tracker.data.AppDataBase
import com.example.app_tracker.data.Transaction
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers.IO

class MainActivity : AppCompatActivity() {

    private val viewModel: TransactionViewModel by lazy {
        TransactionViewModel.create(application)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val dataBase = (application as TransactionApplication).getAppDataBase().transactionDao()



        val adapter: TransactionAdapter = TransactionAdapter(listOf())
        viewModel.taskListLiveData.observe(this) {
            adapter.submitList(it)
        }


        val rvTransactions: RecyclerView = findViewById(R.id.rvList)
        rvTransactions.adapter = adapter

        val fab = findViewById<FloatingActionButton>(R.id.fab_add)

        fab.setOnClickListener{
            openAddTransaction()
        }


    }
    private fun openAddTransaction(transaction: Transaction? = null){
        val intent = AddTransaction.start(this)
        startActivity(intent)


}
    }

