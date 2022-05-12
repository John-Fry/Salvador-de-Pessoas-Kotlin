package com.example.salvadordepessoas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var name: EditText;
    private lateinit var idade: EditText;
    private lateinit var salvar: Button;
    private lateinit var listar: Button;
    private lateinit var dao: PessoaDAO;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.dao = PessoaDAO(this);
        this.name = findViewById(R.id.name);
        this.idade = findViewById(R.id.idade);
        this.salvar = findViewById(R.id.btnSalvar);
        this.listar = findViewById(R.id.btnListar)

        this.salvar.setOnClickListener {
            salvar()
        }

        this.listar.setOnClickListener {
            listar()
        }
    }

    fun salvar() {
        val nome = this.name.text.toString();
        val idade = this.idade.text.toString().toInt();
        val pessoa = Pessoa(nome, idade);
        this.dao.insert(pessoa);
        Toast.makeText(this, "Pessoa salva", Toast.LENGTH_SHORT).show()
    }

    fun listar() {
        Log.i("APP_SALVADOR", this.dao.select().toString())
    }
}