package com.example.kotlin1lesson1.ui.fragments.character

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kotlin1lesson1.R
import com.example.kotlin1lesson1.common.base.BaseFragment
import com.example.kotlin1lesson1.databinding.FragmentCharacterBinding
import com.example.kotlin1lesson1.ui.adapters.CharacterAdapter
import com.example.kotlin1lesson1.ui.adapters.paging.LoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterFragment :
    BaseFragment<CharacterViewModel, FragmentCharacterBinding>(R.layout.fragment_character) {

    override val binding by viewBinding(FragmentCharacterBinding::bind)
    override val viewModel: CharacterViewModel by viewModels()

    private val characterAdapter =
        CharacterAdapter(this::setOnItemClickListener, this::setOnItemLongClickListener)

    override fun setUpObservers() {
        viewModel.fetchCharacters().observe(viewLifecycleOwner, {
            lifecycleScope.launch {
                characterAdapter.submitData(it)
            }
        })
    }

    override fun initialization() = with(binding) {
        recyclerCharacter.layoutManager = LinearLayoutManager(context)
        recyclerCharacter.adapter = characterAdapter.withLoadStateFooter(
            LoadStateAdapter { characterAdapter.retry() })

        characterAdapter.addLoadStateListener { loadStates ->
            recyclerCharacter.isVisible = loadStates.refresh is LoadState.NotLoading
            progressBar.isVisible = loadStates.refresh is LoadState.Loading
            characterSwiperefreshLayout.isRefreshing = false


        }
    }

    override fun swiperefresh() {
        binding.characterSwiperefreshLayout.setOnRefreshListener {
            characterAdapter.refresh()
        }

    }

    private fun setOnItemClickListener(name: String, id: Int) {
        findNavController().navigate(
            CharacterFragmentDirections.actionCharacterFragmentToCharacterDetailFragment(
                "${getString(R.string.fragment_label_detail_character)} $name"
            ).setId(id)
        )
    }

    private fun setOnItemLongClickListener(image: String) {
        findNavController().navigate(
            CharacterFragmentDirections.actionCharacterFragmentToCharacterDialogFragment()
                .setImage(image)
        )
    }
}