package com.yusuf.dictionary.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.yusuf.dictionary.R
import com.yusuf.dictionary.adapter.WordAdapter
import com.yusuf.dictionary.databinding.FragmentDictionaryPageBinding
import com.yusuf.dictionary.db.Word

class DictionaryPageFragment : Fragment() {

    private lateinit var binding: FragmentDictionaryPageBinding
    private lateinit var wordList: ArrayList<Word>
    private lateinit var adapter: WordAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDictionaryPageBinding.inflate(inflater,container,false)
        binding.recyclerView.setHasFixedSize(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)


        wordList = ArrayList<Word>()

        val dog = Word(1,"Dog","Köpek")
        val cat = Word(2,"Cat","Kedi")
        val rabbit = Word(3,"Rabbit","Tavşan")

        wordList.add(dog)
        wordList.add(cat)
        wordList.add(rabbit)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = WordAdapter(requireContext(),wordList)
        binding.recyclerView.adapter = adapter


    }



}