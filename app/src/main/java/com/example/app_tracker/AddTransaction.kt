package com.example.app_tracker

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app_tracker.data.Transaction

class AddTransaction : AppCompatActivity() {


   companion object{

        fun start (context: Context): Intent{
            val intent = Intent(context, AddTransaction::class.java)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_transaction)

    }
}

