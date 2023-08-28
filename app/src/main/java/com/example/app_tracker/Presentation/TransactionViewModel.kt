package com.example.app_tracker.Presentation

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_tracker.TransactionApplication
import com.example.app_tracker.data.Transaction
import com.example.app_tracker.data.TransactionDao
import kotlinx.coroutines.Dispatchers

class TransactionViewModel (private val transactionDao: TransactionDao):ViewModel() {

    val taskListLiveData: LiveData<List<Transaction>> = transactionDao.getAll()

    fun execute(transactionAction: TransactionAction) {
        when (transactionAction.actionType) {
            ActionType.DELETE.name -> deleteByID(transactionAction.task!!.id)
            ActionType.CREATE.name -> insertIntoDataBase(transactionAction.task!!)
            ActionType.UPDATE.name -> updateIntoDataBase(transactionAction.task!!)
            ActionType.DELETE_ALL.name -> deleteAll()
        }
    }

    private fun deleteByID(id: Int){
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

    private fun deleteAll(){
        viewModelScope.launch(Dispatchers.IO) {
            transactionDao.deleteALl()

        }
    }
    companion object {
       fun create(application: Application):TransactionViewModel {
            val dataBaseInstance = (application as TransactionApplication).getAppDataBase()
            val dao = dataBaseInstance.taskDao()
            return TransactionViewModel(dao)
        }
    }
}   