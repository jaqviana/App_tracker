package com.example.app_tracker.Presentation

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_tracker.ActionType
import com.example.app_tracker.TransactionAction
import com.example.app_tracker.TransactionApplication
import com.example.app_tracker.data.Transaction
import com.example.app_tracker.data.TransactionDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class TransactionViewModel (private val transactionDao: TransactionDao):ViewModel() { //criando dependencia com o Dao, para ter acesso as informacoes de dados

    //desse jeito tenho meu liveData no viewmodel - O liveData vai receber as atualizacoes do viewmodel q pediu as info para o banco de dados
    val transactionListLiveData: LiveData<List<Transaction>> = transactionDao.getAll()

    fun execute(transactionAction: TransactionAction) {
        when (transactionAction.actionType) {
            ActionType.DELETE.name -> deleteByID(transactionAction.transaction!!.id)
            ActionType.CREATE.name -> insertIntoDataBase(transactionAction.transaction!!)
            ActionType.UPDATE.name -> updateIntoDataBase(transactionAction.transaction!!)
            ActionType.DELETE_ALL.name -> deleteAll()
        }
    }
    //Dispatchers.IO para mudar de thread
    private fun deleteByID(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            transactionDao.deleteById(id)
        }

    }


    private fun insertIntoDataBase(transaction: Transaction) {
        viewModelScope.launch(Dispatchers.IO) {
            transactionDao.insert(transaction) //insiro a nova tarefa

        }

    }

    private fun updateIntoDataBase(transaction: Transaction) {
        viewModelScope.launch(Dispatchers.IO) {
            transactionDao.update(transaction) //update da tarefa

        }

    }

    private fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            transactionDao.deleteALl()

        }
    }

    //fazer a criacao do viewmodel
    companion object { //significa que consigo chamar meu viewmodel na activity/ter uma instancia do viewmodel sem ter q criar um novo viewmodel e nao precisa passar o dao so o application
        fun create(application: Application): TransactionViewModel {
            //assim consigo pegar o dataBase
            val dataBaseInstance = (application as TransactionApplication).getAppDataBase()
            val dao = dataBaseInstance.transactionDao()
            return TransactionViewModel(dao) //para criar um viewmodel eu preciso passar um dao como parametro de construcao
        }
    }

}







//O meu viewmodel nao conhece minha view, atraves do livedata vou observar as mudancas que tiver no meu banco de dados ai vou conseguir atualizar a view
//dao me traz um LiveData .getAll (vem com o live data do dao) e esse getAll e pra nao ter que chamar o list novamente qdo atualizar meu banco de dados