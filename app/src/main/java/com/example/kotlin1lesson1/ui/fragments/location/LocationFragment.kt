package com.example.kotlin1lesson1.ui.fragments.location

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
import com.example.kotlin1lesson1.databinding.FragmentLocationBinding
import com.example.kotlin1lesson1.ui.adapters.LocationAdapter
import com.example.kotlin1lesson1.ui.adapters.paging.LoadStateAdapter
import com.example.kotlin1lesson1.ui.fragments.location.detail.LocationDetailFragmentArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LocationFragment :
    BaseFragment<LocationViewModel, FragmentLocationBinding>(R.layout.fragment_location) {

    override val binding by viewBinding(FragmentLocationBinding::bind)
    override val viewModel: LocationViewModel by viewModels()
    private val locationAdapter = LocationAdapter(this::setupListeners)


    override fun setUpObservers() {
        viewModel.fetchLocationsViewModel().observe(viewLifecycleOwner){
            lifecycleScope.launch {
                locationAdapter.submitData(it) }

        }
    }

    override fun initialization() = with(binding) {
        recyclerLocation.layoutManager = LinearLayoutManager(context)
        recyclerLocation.adapter = locationAdapter.withLoadStateFooter(
            LoadStateAdapter { locationAdapter.retry() })

        locationAdapter.addLoadStateListener { loadStates ->
            recyclerLocation.isVisible = loadStates.refresh is LoadState.NotLoading
            progressBar.isVisible = loadStates.refresh is LoadState.Loading
        }
    }

    private fun setupListeners(id: Int){
        findNavController().navigate(
            LocationFragmentDirections.actionLocationFragmentToLocatioinDetailFragment().setId(id))
    }
    override fun swiperefresh() {
        binding.locationSwiperefreshLayout.setOnRefreshListener {
            locationAdapter.refresh()
            binding.locationSwiperefreshLayout.isRefreshing = false
        }
    }
}