package com.example.kotlin1lesson6.ui.fragments.character.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.kotlin1lesson6.R
import com.example.kotlin1lesson6.common.base.BaseFragment
import com.example.kotlin1lesson6.databinding.FragmentCharacterDetailBinding
import com.example.kotlin1lesson6.presentation.state.UIState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

@InternalCoroutinesApi
class CharacterDetailFragment :
    BaseFragment<CharacterDetailViewModel, FragmentCharacterDetailBinding>(
        R.layout.fragment_character_detail
    ) {
    override val viewModel: CharacterDetailViewModel by viewModel()
    override val binding by viewBinding(FragmentCharacterDetailBinding::bind)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
        viewModel.fetchCharacter(CharacterDetailFragmentArgs.fromBundle(requireArguments()).id)

    }


    private fun getData() = with(binding) {

        viewModel.characterState.subscribe {
            loaderCharacterDetail.isVisible = it is UIState.Loading
            groupCharacter.isVisible = it !is UIState.Loading
            when (it) {
                is UIState.Error -> {
                    Toast.makeText(requireActivity(), it.massage, Toast.LENGTH_SHORT).show()
                }
                is UIState.Loading -> {}
                is UIState.Success -> {
                    txtIdCharacterDetail.text = it.data.id.toString()
                    txtNameCharacterDetail.text = it.data.name
                    txtStatusCharacterDetail.text = it.data.status
                    txtSpeciesCharacterDetail.text = it.data.species
                    txtGenderCharacterDetail.text = it.data.gender
                    Glide.with(imageCharacterDetail)
                        .load(it.data.image)
                        .into(imageCharacterDetail)
                    if (it.data.status == "Alive") {
                        viewStatus.setBackgroundResource(R.drawable.oval)
                    } else if (it.data.status == "Dead") {
                        viewStatus.setBackgroundResource(R.drawable.oval1)
                    }
                }
            }
        }
    }


}