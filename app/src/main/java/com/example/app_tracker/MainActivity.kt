package com.example.app_tracker

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.RecyclerView
import com.example.app_tracker.Presentation.TransactionViewModel
import com.example.app_tracker.data.Transaction
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import java.io.Serializable
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {

    private val adapter: TransactionAdapter by lazy {
        TransactionAdapter(::onListItemClicked)
    }

    //criando o viewmodel na activity "create" application que esta dentro do viewmodel dentro do companion object, assim nao preciso criar nada mais aqui a view fica bem burrinha a funcao ta la no viewmodel
    //by lazy so vai chamar o codigo qdo eu chamar o adapter
    //ESSA PARTE DO CODIGO QUE ESTA PEGANDO AS INFO DO DATA BASE ATRAVES DO CODIGO DO VIEWMODEL?
    private val viewModel: TransactionViewModel by lazy {
        TransactionViewModel.create(application)
    }

    //VER QUE PARTE DA AULA QUE ELE FEZ ESSE CODIGO - O Q SIGNIFICA?
    private val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            val transactionAction = data?.getSerializableExtra(TRANSACTION_ACTION_RESULT) as TransactionAction

            viewModel.execute(transactionAction)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //POSSO DELETAR A LINHA ABAIXO PQ MINHA VIEW VAI SER ATUALIZADA POR OBESERVAR O LIVEDATA
        val dataBase = (application as TransactionApplication).getAppDataBase().transactionDao()


        //A PARTE DO CODIGO DO MEU ADAPTER TA DIFERENTE
        //TaskBeats
        val adapter: TransactionAdapter by lazy {
          TransactionAdapter(::onListItemClicked)
        }
        //MEU ADAPTER
        /*val adapter: TransactionAdapter = TransactionAdapter(listOf())
        viewModel.transactionListLiveData.observe(this) {
            adapter.submitList(it)
        }*/


        val rvTransactions: RecyclerView = findViewById(R.id.rvList)
        rvTransactions.adapter = adapter

        val fab = findViewById<FloatingActionButton>(R.id.fab_add)
        fab.setOnClickListener{
            openAddTransaction(null)
        }


    }
    //VER ESSA PARTE DA AULA
    //inicializando o codigo
    override fun onStart() {
        super.onStart()
        listFromDataBase()
    }

    private fun deleteAll() {
        val transactionAction = TransactionAction( null, ActionType.DELETE_ALL.name)
        viewModel.execute(transactionAction)
    }


    //VER COMO FICA ESSA PARTE SE VOU DEIXAR AQUI
    private fun listFromDataBase() {
        //Observer
        val listObserver = Observer<List<Transaction>>{ listTasks ->
            /*if(listTasks.isEmpty()) {
                ctnContent.visibility = View.VISIBLE
            } else {
                ctnContent.visibility = View.GONE
            }*/

            adapter.submitList(listTasks)
        }

        //Preciso linkar com o Live data
        viewModel.transactionListLiveData.observe(this@MainActivity, listObserver)
    }

    private fun showMassage(view: View, message: String){
        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
            .setAction("Action", null)
            .show()
    }
    private fun onListItemClicked (transaction: Transaction){
        openAddTransaction(transaction)
    }

    private fun openAddTransaction(transaction: Transaction? = null){
        val intent = AddTransaction.start(this, transaction)
        startForResult.launch(intent)

    }


}

//CRUD (Create, Read, Update, Delete)
enum class ActionType {
    DELETE,
    DELETE_ALL,
    UPDATE,
    CREATE,
}

//enum acima nao serializar ele, passar de uma tela pra outra por isso q passo por uma string e comparo name
//VER ESSA PARTE DA AULA
data class TransactionAction(
    val transaction: Transaction?,
    val actionType: String
) : Serializable

//VER ESSA PARTE DA AULA
const val TRANSACTION_ACTION_RESULT = "TRANSACTION_ACTION_RESULT"


