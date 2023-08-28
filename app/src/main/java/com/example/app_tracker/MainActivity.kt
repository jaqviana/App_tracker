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

        /*val dataBase = Room.databaseBuilder(
            applicationContext,
            AppDataBase::class.java, "transactions-database"
        ).build()*/

        val dao = dataBase.taskDao()

        val transaction = Transaction(title = "Title0", amount = 100)

        CoroutineScope(IO).launch {
        val myDataBaseList: LiveData<List<Transaction>> = dao.getAll()

       }


        val itemList = listOf<Transaction>(
            Transaction(0,"Title0",100 ),
            Transaction(1,"Title2",200 ),
            Transaction(2,"Title3",300 )

        )
        val adapter: TransactionAdapter = TransactionAdapter(itemList)

        val rvTransactions: RecyclerView = findViewById(R.id.rvList)
        rvTransactions.adapter = adapter

        val fab = findViewById<FloatingActionButton>(R.id.fab_add)

        fab.setOnClickListener{
            openAddTransaction()
        }


    }
    private fun openAddTransaction(transaction: Transaction? = null){
        val intent = AddTransaction.start(this, transaction) //precisa de uma trasacation para abrir a AddTransaction
        startActivity(intent)


}
    }

//ver linha 25 nao tinha q ser val dao = dataBase.transactionDao() e val dao = dataBase.taskDao()?