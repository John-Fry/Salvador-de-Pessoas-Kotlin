package com.example.salvadordepessoas

import android.content.ContentValues
import android.content.Context

class PessoaDAO(var context: Context) {
    var banco: BancoHelper;

    init {
        this.banco = BancoHelper(this.context);
    }

    fun insert(pessoa: Pessoa) {
        val cv = ContentValues();
        cv.put("nome", pessoa.nome);
        cv.put("idade", pessoa.idade);
        this.banco.writableDatabase.insert("pessoa", null, cv);
    }

    fun select(): ArrayList<Pessoa> {
        var lista = arrayListOf<Pessoa>();
        val colunas = arrayOf("id", "nome", "idade");
        val cursor = this.banco.readableDatabase.query("pessoa", colunas, null, null, null, null, null);
        cursor.moveToFirst();

        for (i in 1 .. cursor.count) {
            var id = cursor.getInt(colunas.indexOf("id"));
            var nome = cursor.getString(colunas.indexOf("nome"));
            var idade = cursor.getInt(colunas.indexOf("idade"));
            lista.add(Pessoa(id, nome, idade));
            cursor.moveToNext();
        }
        return lista;
    }

    fun find(id: Int): Pessoa? {
        val colunas = arrayOf("id", "nome", "idade")
        val where = "id = ?"
        val pWhere = arrayOf(id.toString())
        val cursor = this.banco.readableDatabase.query("pessoa", colunas, where, pWhere, null, null, null)

        cursor.moveToFirst()

        if (cursor.count == 1) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
            val nome = cursor.getString(cursor.getColumnIndexOrThrow("nome"))
            val idade = cursor.getInt(cursor.getColumnIndexOrThrow("idade"))

            return Pessoa(id, nome, idade)
        }

        return null;
    }

    fun count(): Int {
        val sql = "select count(id) from pessoa"
        val cursor = this.banco.readableDatabase.rawQuery(sql, null)

        cursor.moveToFirst()

        return 0;
    }

    fun update(pessoa: Pessoa) {
        val where = "id = ?"
        val pWhere = arrayOf(pessoa.id.toString())

        val cv = ContentValues()
        cv.put("nome", pessoa.nome)
        cv.put("idade", pessoa.idade)

        this.banco.writableDatabase.update("pessoa", cv, where, pWhere)
    }

    fun delete(id: Int){
        val where = "id = ?"
        val pWhere = arrayOf(id.toString())

        this.banco.writableDatabase.delete("pessoa", where, pWhere)
    }

}