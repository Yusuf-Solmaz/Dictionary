package com.yusuf.dictionary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.navigation.findNavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.info.sqlitekullanimihazirveritabani.DatabaseCopyHelper
import com.yusuf.dictionary.adapter.WordAdapter
import com.yusuf.dictionary.databinding.ActivityMainBinding
import com.yusuf.dictionary.db.Word
import com.yusuf.dictionary.db.WordDao
import com.yusuf.dictionary.ui.DictionaryPageFragment

class MainActivity : AppCompatActivity(),SearchView.OnQueryTextListener {
    private lateinit var  binding: ActivityMainBinding
    private lateinit var dao: WordDao

    private lateinit var wordReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createDb()
        dao = WordDao(this@MainActivity)

        val db = FirebaseDatabase.getInstance()
        wordReference = db.getReference("kelimeler")

        setSupportActionBar(binding.toolbar)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu,menu)

        val item = menu?.findItem(R.id.menuSearch)
        val searchView = item?.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            search(query)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null) {
            search(newText)
        }
        return true
    }

    private fun createDb(){
        val copyHelper = DatabaseCopyHelper(this@MainActivity)

        try {
            copyHelper.createDataBase()
            copyHelper.openDataBase()
        }
        catch (e:Exception){
            e.localizedMessage?.let { Log.e("DB Connection Error", it) }
        }
    }

    private fun search(query:String){
        //DictionaryPageFragment.instance.wordList = dao.searchWords(query) as ArrayList<Word>
        DictionaryPageFragment.instance.adapter = WordAdapter(this,DictionaryPageFragment.instance.wordList)
        DictionaryPageFragment.instance.binding.recyclerView.adapter = DictionaryPageFragment.instance.adapter
        observeDatas(query,DictionaryPageFragment.instance.wordList,DictionaryPageFragment.instance.adapter)
    }

    private fun observeDatas(query:String,wordList:ArrayList<Word>,adapter: WordAdapter){
        wordReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                wordList.clear()

                for (c in snapshot.children){
                    val word = c.getValue(Word::class.java)
                    if (word != null){

                        if (word.ingilizce!!.contains(query)){
                            word.kelime_id = c.key
                            wordList.add(word)
                        }



                    }

                }

                adapter.notifyDataSetChanged()

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MainActivity,"Something went wrong!", Toast.LENGTH_LONG).show()
            }

        })
    }
}