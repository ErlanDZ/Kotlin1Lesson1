package com.example.kotlin1lesson1.ui.fragments.location.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.kotlin1lesson1.R
import com.example.kotlin1lesson1.common.base.BaseFragment
import com.example.kotlin1lesson1.databinding.FragmentLocatioinDetailBinding
import com.example.kotlin1lesson1.presentation.state.UIState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
@AndroidEntryPoint
class LocationDetailFragment :
    BaseFragment<LocationDetailViewModel, FragmentLocatioinDetailBinding>(
        R.layout.fragment_locatioin_detail
    ) {
    override val viewModel: LocationDetailViewModel by viewModels()
    override val binding by viewBinding(FragmentLocatioinDetailBinding::bind)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchLocation(LocationDetailFragmentArgs.fromBundle(requireArguments()).id)
        getData()
    }


    private fun getData() = with(binding) {
        viewModel.locationState.subscribe {
            loaderLocationDetail.isVisible = it is UIState.Loading
            groupLocation.isVisible = it !is UIState.Loading
            when (it) {
                is UIState.Error -> {
                    Toast.makeText(requireActivity(), it.massage, Toast.LENGTH_SHORT).show()
                }
                is UIState.Loading -> {}
                is UIState.Success -> {
                    txtIdLocationDetail.text = it.data.id.toString()
                    txtNameLocationDetail.text = it.data.name
                    txtTypeLocationDetail.text = it.data.type
                    txtDimensionLocationDetail.text = it.data.dimension
                }
            }
        }
    }
}