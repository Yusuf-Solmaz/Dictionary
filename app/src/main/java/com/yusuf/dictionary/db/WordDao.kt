package com.yusuf.dictionary.db

import android.content.Context

class WordDao(context: Context) {
/*
    private val dbHelper = DbHelper(context)

    fun getAllWords(): List<Word>{

        val wordList = ArrayList<Word>()
        val db = dbHelper.writableDatabase

        val cursor = db.rawQuery("Select * From kelimeler",null)

        while (cursor.moveToNext()){
            val word = Word(cursor.getInt(cursor.getColumnIndex("kelime_id")),cursor.getString(cursor.getColumnIndex("ingilizce")),cursor.getString(cursor.getColumnIndex("turkce")))
            wordList.add(word)
        }

        return wordList
    }

    fun searchWords(query: String): List<Word>{

        val wordList = ArrayList<Word>()
        val db = dbHelper.writableDatabase

        val cursor = db.rawQuery("Select * From kelimeler where ingilizce like '%$query%'",null)

        while (cursor.moveToNext()){
            val word = Word(cursor.getInt(cursor.getColumnIndex("kelime_id")),cursor.getString(cursor.getColumnIndex("ingilizce")),cursor.getString(cursor.getColumnIndex("turkce")))
            wordList.add(word)
        }

        return wordList
    }

 */
}