package com.example.app_tracker.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Transaction (

@PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val amount: Int
)
