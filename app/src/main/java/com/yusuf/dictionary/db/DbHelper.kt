package com.yusuf.dictionary.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(context: Context): SQLiteOpenHelper(context,"sozluk.sqlite",null,1) {
    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        sqLiteDatabase.execSQL(
            "CREATE TABLE IF NOT EXISTS kelimeler (kelime_id INTEGER PRIMARY KEY AUTOINCREMENT,ingilizce TEXT,turkce TEXT);"
        )
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS kelimeler")
        onCreate(sqLiteDatabase)
    }
}
