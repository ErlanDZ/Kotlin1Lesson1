package com.example.kotlin1lesson1.ui.fragments.episode.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.example.kotlin1lesson1.common.resource.Resource
import com.example.kotlin1lesson1.databinding.FragmentEpisodeDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EpisodeDetailFragment : Fragment() {

    private val viewModel: EpisodeDetailViewModel by viewModels()
    private var _binding: FragmentEpisodeDetailBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEpisodeDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
    }

    private fun getData() = with(binding) {
        viewModel.fetchEpisode().observe(requireActivity(),{
            loaderEpisodeDetail.isVisible = it is Resource.Loading
            groupEpisode.isVisible = it !is Resource.Loading
            when(it){
                is Resource.Error -> {
                    Toast.makeText(requireActivity(), it.massage, Toast.LENGTH_SHORT).show()}
                is Resource.Loading -> {}
                is Resource.Success -> {
                    it.data?.let{data ->
                        txtIdDetail.text = data.id.toString()
                        txtNameEpisodeDetail.text = data.name
                        txtAirDateEpisodeDetail.text = data.air_date
                        txtEpisodeEpisodeDetail.text = data.episode
                        txtCreatedEpisodeDetail.text = data.created
                    }
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}