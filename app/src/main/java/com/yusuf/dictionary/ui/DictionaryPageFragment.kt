package com.yusuf.dictionary.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.yusuf.dictionary.R
import com.yusuf.dictionary.adapter.WordAdapter
import com.yusuf.dictionary.databinding.FragmentDictionaryPageBinding
import com.yusuf.dictionary.db.Word
import com.yusuf.dictionary.db.WordDao

class DictionaryPageFragment : Fragment() {

    lateinit var binding: FragmentDictionaryPageBinding
    lateinit var wordList: ArrayList<Word>
    lateinit var adapter: WordAdapter

    private lateinit var wordReference : DatabaseReference

    private lateinit var dao: WordDao

    companion object {
        lateinit var instance: DictionaryPageFragment
            private set
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDictionaryPageBinding.inflate(inflater,container,false)
        binding.recyclerView.setHasFixedSize(true)

        instance = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        dao = WordDao(requireContext())
        //wordList = dao.getAllWords() as ArrayList<Word>

        wordList = ArrayList()

        val db = FirebaseDatabase.getInstance()
        wordReference = db.getReference("kelimeler")

        Log.e("datas",wordReference.toString())

        observeDatas()

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = WordAdapter(requireContext(),wordList)
        binding.recyclerView.adapter = adapter



        Log.e("datas",wordList.toString())

    }

    private fun observeDatas(){
        wordReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                wordList.clear()

                for (c in snapshot.children){
                    val word = c.getValue(Word::class.java)
                    if (word != null){
                        Log.e("datas",word.toString())
                        word.kelime_id = c.key
                        wordList.add(word)

                    }

                }

                adapter.notifyDataSetChanged()

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireContext(),"Something went wrong!",Toast.LENGTH_LONG).show()
            }

        })
    }


}