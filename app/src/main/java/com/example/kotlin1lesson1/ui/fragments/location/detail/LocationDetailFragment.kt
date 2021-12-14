package com.example.kotlin1lesson1.ui.fragments.location.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin1lesson1.R
import com.example.kotlin1lesson1.databinding.FragmentLocatioinDetailBinding
import com.example.kotlin1lesson1.ui.fragments.episode.detail.EpisodeDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel

@AndroidEntryPoint
class LocationDetailFragment : Fragment() {
    private val viewModel: LocationDetailViewModel by viewModels()
    private var _binding: FragmentLocatioinDetailBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLocatioinDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
    }

    private fun getData() {
        viewModel.fetchLocation(LocationDetailFragmentArgs.fromBundle(requireArguments()).id)
            .observe(viewLifecycleOwner){
                binding.txtIdLocationDetail.text = it.id.toString()
                binding.txtNameLocationDetail.text = it.name
                binding.txtTypeLocationDetail.text = it.type
                binding.txtDimensionLocationDetail.text = it.dimension

            }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}