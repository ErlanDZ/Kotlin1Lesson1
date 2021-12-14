package com.example.kotlin1lesson1.ui.fragments.episode.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
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

    private fun getData() {
        viewModel.fetchEpisode(EpisodeDetailFragmentArgs.fromBundle(requireArguments()).id)
            .observe(viewLifecycleOwner){
                binding.txtIdDetail.text = it.id.toString()
                binding.txtNameEpisodeDetail.text = it.name
                binding.txtAirDateEpisodeDetail.text = it.air_date
                binding.txtEpisodeEpisodeDetail.text = it.episode
                binding.txtCreatedEpisodeDetail.text = it.created
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}