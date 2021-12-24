package com.example.kotlin1lesson1.ui.fragments.episode.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kotlin1lesson1.R
import com.example.kotlin1lesson1.common.base.BaseFragment
import com.example.kotlin1lesson1.databinding.FragmentEpisodeDetailBinding
import com.example.kotlin1lesson1.presentation.state.UIState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@AndroidEntryPoint
class EpisodeDetailFragment : BaseFragment<EpisodeDetailViewModel, FragmentEpisodeDetailBinding>(
    R.layout.fragment_episode_detail
) {

    override val viewModel: EpisodeDetailViewModel by viewModels()
    override val binding by viewBinding(FragmentEpisodeDetailBinding::bind)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchEpisode(EpisodeDetailFragmentArgs.fromBundle(requireArguments()).id)
        getData()
    }

    private fun getData() = with(binding) {
        viewModel.episodeState.subscribe {
            loaderEpisodeDetail.isVisible = it is UIState.Loading
            groupEpisode.isVisible = it !is UIState.Loading
            when (it) {
                is UIState.Error -> {
                    Toast.makeText(requireActivity(), it.massage, Toast.LENGTH_SHORT).show()
                }
                is UIState.Loading -> {}
                is UIState.Success -> {
                    txtIdDetail.text = it.data.id.toString()
                    txtNameEpisodeDetail.text = it.data.name
                    txtAirDateEpisodeDetail.text = it.data.air_date
                    txtEpisodeEpisodeDetail.text = it.data.episode
                    txtCreatedEpisodeDetail.text = it.data.created
                }
            }

        }

    }
}