package com.yusuf.dictionary.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.yusuf.dictionary.R
import com.yusuf.dictionary.databinding.FragmentWordDetailPageBinding

class WordDetailPageFragment : Fragment() {

    private lateinit var binding: FragmentWordDetailPageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWordDetailPageBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bundle: WordDetailPageFragmentArgs by navArgs()
        val dictWord=bundle.word

        binding.WordEn.text = dictWord.ingilizce
        binding.wordTr.text = dictWord.turkce
    }


}