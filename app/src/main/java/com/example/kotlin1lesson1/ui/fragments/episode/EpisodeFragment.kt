package com.example.kotlin1lesson1.ui.fragments.episode

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kotlin1lesson1.R
import com.example.kotlin1lesson1.common.base.BaseFragment
import com.example.kotlin1lesson1.data.network.dtos.RickAndMortyResponse
import com.example.kotlin1lesson1.data.network.dtos.episode.EpisodeModel
import com.example.kotlin1lesson1.databinding.FragmentEpisodeBinding
import com.example.kotlin1lesson1.ui.adapters.EpisodeAdapter
import com.example.kotlin1lesson1.ui.adapters.paging.LoadStateAdapter
import com.example.kotlin1lesson1.ui.fragments.character.CharacterFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.security.auth.callback.Callback

@AndroidEntryPoint
class EpisodeFragment :
    BaseFragment<EpisodeViewModel, FragmentEpisodeBinding>(R.layout.fragment_episode){

    override val binding by viewBinding(FragmentEpisodeBinding::bind)
    override val viewModel: EpisodeViewModel by viewModels()
    private val episodeAdapter = EpisodeAdapter(this::setupListeners)



    private fun setupListeners(id: Int) {
        findNavController().navigate(
            EpisodeFragmentDirections.actionEpisodeFragmentToEpisodeDetailFragment().setId(id)
        )
    }

    override fun setUpObservers() {
        viewModel.fetchLocationsViewModel().observe(viewLifecycleOwner){
            lifecycleScope.launch {
                episodeAdapter.submitData(it) }

        }
    }

    override fun initialization() = with(binding) {
        recyclerEpisode.layoutManager = LinearLayoutManager(context)
        recyclerEpisode.adapter = episodeAdapter.withLoadStateFooter(
            LoadStateAdapter { episodeAdapter.retry() })

        episodeAdapter.addLoadStateListener { loadStates ->
            recyclerEpisode.isVisible = loadStates.refresh is LoadState.NotLoading
            progressBar.isVisible = loadStates.refresh is LoadState.Loading
        }


    }
    override fun swiperefresh() {
        binding.episodeSwiperefreshLayout.setOnRefreshListener {
            episodeAdapter.refresh()
            binding.episodeSwiperefreshLayout.isRefreshing = false
        }
    }

}
