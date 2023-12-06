package com.yusuf.dictionary.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.yusuf.dictionary.databinding.DictRowBinding
import com.yusuf.dictionary.databinding.DictionaryRecyclerviewRowBinding
import com.yusuf.dictionary.db.Word
import com.yusuf.dictionary.ui.DictionaryPageFragmentDirections

class WordAdapter(private val context:Context,private val wordList: List<Word>): RecyclerView.Adapter<WordAdapter.WordHolder>() {
    class WordHolder(val binding: DictRowBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordHolder {
        val itemBinding = DictRowBinding.inflate(LayoutInflater.from(context),parent,false)
        return WordHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return wordList.size
    }

    override fun onBindViewHolder(holder: WordHolder, position: Int) {
        holder.binding.wordEng.text = wordList[position].ingilizce
        holder.binding.wordTrk.text = wordList[position].turkce

        holder.binding.cardView.setOnClickListener {
            val action = DictionaryPageFragmentDirections.actionDictionaryPageFragmentToWordDetailPageFragment(wordList[position])
            Navigation.findNavController(it).navigate(action)

        }
    }
}