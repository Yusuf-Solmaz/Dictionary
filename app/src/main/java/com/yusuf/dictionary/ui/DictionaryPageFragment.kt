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
import com.yusuf.dictionary.db.WordDao

class DictionaryPageFragment : Fragment() {

    lateinit var binding: FragmentDictionaryPageBinding
    lateinit var wordList: ArrayList<Word>
    lateinit var adapter: WordAdapter

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
        wordList = dao.getAllWords() as ArrayList<Word>

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = WordAdapter(requireContext(),wordList)
        binding.recyclerView.adapter = adapter


    }



}