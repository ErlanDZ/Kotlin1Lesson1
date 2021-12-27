package com.example.kotlin1lesson6.ui.fragments.episode.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kotlin1lesson6.R
import com.example.kotlin1lesson6.common.base.BaseFragment
import com.example.kotlin1lesson6.databinding.FragmentEpisodeDetailBinding
import com.example.kotlin1lesson6.presentation.state.UIState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

@InternalCoroutinesApi
class EpisodeDetailFragment : BaseFragment<EpisodeDetailViewModel, FragmentEpisodeDetailBinding>(
    R.layout.fragment_episode_detail
) {

    override val viewModel: EpisodeDetailViewModel by viewModel()
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