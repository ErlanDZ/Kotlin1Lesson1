package com.example.kotlin1lesson1.ui.fragments.episode

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kotlin1lesson1.R
import com.example.kotlin1lesson1.common.base.BaseFragment
import com.example.kotlin1lesson1.databinding.FragmentEpisodeBinding
import com.example.kotlin1lesson1.ui.adapters.EpisodeAdapter
import com.example.kotlin1lesson1.ui.adapters.paging.LoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@InternalCoroutinesApi
@AndroidEntryPoint
class EpisodeFragment :
    BaseFragment<EpisodeViewModel, FragmentEpisodeBinding>(R.layout.fragment_episode) {

    override val binding by viewBinding(FragmentEpisodeBinding::bind)
    override val viewModel: EpisodeViewModel by viewModels()
    private val episodeAdapter = EpisodeAdapter(this::setupListeners)


    private fun setupListeners(id: Int) {
        findNavController().navigate(
            EpisodeFragmentDirections.actionEpisodeFragmentToEpisodeDetailFragment().setId(id)
        )
    }

    override fun setUpObservers() {
        lifecycleScope.launch {
            viewModel.fetchLocations().collectLatest {
                episodeAdapter.submitData(it)
            }
        }
    }

    override fun initialization() = with(binding) {
        recyclerEpisode.layoutManager = LinearLayoutManager(context)
        recyclerEpisode.adapter = episodeAdapter.withLoadStateFooter(
            LoadStateAdapter { episodeAdapter.retry() })

        episodeAdapter.addLoadStateListener { loadStates ->
            recyclerEpisode.isVisible = loadStates.refresh is LoadState.NotLoading
            progressBar.isVisible = loadStates.refresh is LoadState.Loading
            episodeSwiperefreshLayout.isRefreshing = false
        }


    }

    override fun swiperefresh() {
        binding.episodeSwiperefreshLayout.setOnRefreshListener {
            episodeAdapter.refresh()
        }
    }

}
