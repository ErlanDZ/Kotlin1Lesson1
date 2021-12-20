package com.example.kotlin1lesson1.ui.fragments.location.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin1lesson1.R
import com.example.kotlin1lesson1.common.resource.Resource
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

    private fun getData() = with(binding) {
        viewModel.fetchLocation().observe(requireActivity(),{
            loaderLocationDetail.isVisible = it is Resource.Loading
            groupLocation.isVisible = it !is Resource.Loading
            when(it){
                is Resource.Error -> {
                    Toast.makeText(requireActivity(), it.massage, Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {}
                is Resource.Success -> {
                    it.data?. let { data ->
                        txtIdLocationDetail.text = data.id.toString()
                        txtNameLocationDetail.text = data.name
                        txtTypeLocationDetail.text = data.type
                        txtDimensionLocationDetail.text = data.dimension
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