package com.example.app_tracker.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(transaction: Transaction)

    @Query("Select * from `transaction`")
    fun getAll(): LiveData<List<Transaction>>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(transaction: Transaction)

    //Delete all
    @Query("Delete from `transaction`")
    fun deleteALl()

    //Delete pelo id
    @Query("Delete from `transaction` WHERE id =:id")
    fun deleteById(id: Int)

}
