package com.example.salvadordepessoas

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BancoHelper(var context: Context): SQLiteOpenHelper(context ,"pessoa.SQLite", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        var sql = "create table pesssoa( " +
                " id integer primary key autoincrement, " +
                "nome text, " +
                " idade integer)"
        db?.execSQL(sql);
    }

    override fun onUpgrade(db: SQLiteDatabase?, anterior: Int, atual: Int) {
        if (atual != anterior) {
            db?.execSQL("drop table pessoa");
            this.onCreate(db);
        }
    }



}