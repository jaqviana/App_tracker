package com.example.app_tracker.data

import androidx.room.Database
import androidx.room.RoomDatabase
//AppDataBase serve como ponto de acesso ao banco de dados SQLite e metodo "transactionDao()" permite a manipilacao dos dados na base de dados
@Database(entities = [Transaction::class], version = 1) //Transaction::class e a classe que representa a entidade a ser armazenada no banco de dados
abstract class AppDataBase: RoomDatabase() {            //A versao 1 indica que e a primeira versao do banco de dados, caso tenha uma mudanca na estrutura das tabelas o Room vai ter q atualizar
    abstract fun transactionDao(): TransactionDao //Atraves do Dao podera realizar operacoes de leitura, escrita e updates na tabela que esta no banco de dados.
}
