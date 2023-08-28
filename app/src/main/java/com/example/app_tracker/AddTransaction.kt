package com.example.app_tracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app_tracker.data.Transaction

class AddTransaction : AppCompatActivity() {

    //passando criando a chave pra passar de uma activity pra outra
    companion object{
        private const val TRANSACTION_DETAIL_EXTRA = "transaction.extra.detail"
        //Contexto eh quem ta me abrindo, quem quiser abrir minha AddTransaction vai precisar chamar a funcao abaixo passando uma transaction
        fun start (context: Context, transaction: Transaction): Intent{
            val intent = Intent(context, AddTransaction::class.java)

            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_transaction)

        //checar essa linha
        val transaction: Transaction? = intent.getSerializableExtra(TRANSACTION_DETAIL_EXTRA) as Trasaction?
        requireNotNull(transaction)//isso para dar um crash na hora de abrir a tela se nao passar uam transaction qdo quiser abrir a tela
        val tvValue: TextView = findViewById(R.id.editTitle1)
        tvValue.text = transaction?.title
    }
}

//nao finalizei a parte da aula da "intent" pq o Roque usa o apply, mas eu nao tenho que passar nada para a outra activity, so quero abrir a outra activity
//nao importei o serializable no "modelo". Nesta parte do video 5:15' ele fala q a tarefa antes era um string agora nao eh mais por isso do serialzable para corrigir isso (agora e do tipo task - um objeto inteiro)
//checar linha 28 a 31
//ver tempo 17:46' para finalizar a implemnetacao se for seguir como a aula "como ver o resultado de uma actividade"
