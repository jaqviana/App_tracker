package com.example.app_tracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val itemList = listOf<Transaction>(
            Transaction("Title0",100 ),
            Transaction("Title1",200 ),
            Transaction("Title2",300 ),
            Transaction("Title3",450 ),
            Transaction("Title4",500 ),
            Transaction("Title5",600 ),
            Transaction("Title6",750 ),
            Transaction("Title7",800 ),
            Transaction("Title8",900 ),
            Transaction("Title3",450 ),
            Transaction("Title4",500 ),
            Transaction("Title5",600 ),
            Transaction("Title6",750 ),
            Transaction("Title7",800 ),
            Transaction("Title8",900 ),
        )
        val adapter: TransactionAdapter = TransactionAdapter(itemList)

        val rvTransactions: RecyclerView = findViewById(R.id.rvList)
        rvTransactions.adapter = adapter
    }


}