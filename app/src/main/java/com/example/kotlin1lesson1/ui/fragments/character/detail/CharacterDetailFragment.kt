package com.example.kotlin1lesson1.ui.fragments.character.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.kotlin1lesson1.R
import com.example.kotlin1lesson1.common.resource.Resource
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
        viewModel.fetchCharacter().observe(requireActivity(),{
            loaderCharacterDetail.isVisible = it is Resource.Loading
            groupCharacter.isVisible = it !is Resource.Loading
            when(it){
                is Resource.Error -> {
                    Toast.makeText(requireActivity(), it.massage, Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    it.data?.let {data ->
                        txtIdCharacterDetail.text = data.id.toString()
                        txtNameCharacterDetail.text = data.name
                        txtStatusCharacterDetail.text = data.status
                        txtSpeciesCharacterDetail.text = data.species
                        txtGenderCharacterDetail.text = data.gender
                        Glide.with(imageCharacterDetail)
                            .load(data.image)
                            .into(imageCharacterDetail)
                        if (data.status == "Alive"){
                            viewStatus.setBackgroundResource(R.drawable.oval)
                        }else if(data.status == "Dead"){
                            viewStatus.setBackgroundResource(R.drawable.oval1)
                        }
                    }
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}