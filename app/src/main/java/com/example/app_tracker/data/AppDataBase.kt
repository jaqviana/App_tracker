package com.example.app_tracker.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Transaction::class], version = 1) //entity q eh nossa transaction, q vai ser um array
abstract class AppDataBase: RoomDatabase() {
    abstract fun taskDao(): TransactionDao //to expondo meu taskDao q vai ser meu acesso ao objeto/acesso a tabela/base de dados
}
