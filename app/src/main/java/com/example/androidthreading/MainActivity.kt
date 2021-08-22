package com.example.androidthreading

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //faz o handle do button
        button_load_data.setOnClickListener {
            launchAstrosTask()
        }

    }

    //função para exibir os dados carregados
    fun showData(list: List<AstroPeople>) {
        textview_data.text = ""
        list?.forEach { people ->
            textview_data.append("${people.name} - ${people.craft} \n\n")
        }
    }

    //função para exibir o ProgressBar
    fun showLoadingIndicator() {
        progressBar_load_indicator.visibility = View.VISIBLE
    }

    //função para esconder o ProgressBar
    fun hideLoadingIndicator() {
        progressBar_load_indicator.visibility = View.GONE
    }

    //função para lançar a task
    fun launchAstrosTask(){
        val task = TaskAstros()
        task.execute()
    }


    //classe interna criada para rodar tarefa assincrona
    inner class TaskAstros() : AsyncTask<Void, Int, List<AstroPeople>>() {
        private val repository = AstrosRepository()

        override fun onPreExecute() {
            super.onPreExecute()
            showLoadingIndicator()
        }

        override fun doInBackground(vararg params: Void?): List<AstroPeople> {
            // faz a chamada do repository
            return repository.loadData()
        }

        override fun onPostExecute(result: List<AstroPeople>?) {
            super.onPostExecute(result)
            hideLoadingIndicator()
            if (result != null) {
                showData(result)
            } else {
                Log.d("onPostExecute", "Erro no postExcute no mainActivity")
            }
        }

    }
}