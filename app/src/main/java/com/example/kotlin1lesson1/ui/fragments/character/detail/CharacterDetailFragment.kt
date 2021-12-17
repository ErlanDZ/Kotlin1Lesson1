package com.example.kotlin1lesson1.ui.fragments.character.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.kotlin1lesson1.R
import com.example.kotlin1lesson1.databinding.FragmentCharacterDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {
    private val viewModel: CharacterDetailViewModel by viewModels()
    private var _binding: FragmentCharacterDetailBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            getData()

    }

    private fun getData() = with(binding) {
        lifecycleScope.launch {
            viewModel.fetchCharacter(CharacterDetailFragmentArgs.fromBundle(requireArguments()).id)
                .observe(viewLifecycleOwner) {
                    Glide.with(imageCharacterDetail)
                        .load(it.image)
                        .into(imageCharacterDetail)
                    txtIdCharacterDetail.text = it.id.toString()
                    txtNameCharacterDetail.text = it.name
                    txtGenderCharacterDetail.text = it.gender
                    txtSpeciesCharacterDetail.text = it.gender
                    txtStatusCharacterDetail.text = it.status

                    if (it.status == "Alive") {
                        viewStatus.setBackgroundResource(R.drawable.oval)
                    } else if (it.status == "Dead") {
                        viewStatus.setBackgroundResource(R.drawable.oval1)
                    }
                }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}