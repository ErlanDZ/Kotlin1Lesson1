package com.example.kotlin1lesson1.common.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<ViewModel : BaseViewModel, viewBinding : ViewBinding>(
    @LayoutRes layoutId: Int
) : Fragment(layoutId) {

    protected abstract val binding: viewBinding
    protected abstract val viewModel: ViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialization()
        setupListeners()
        setupViews()
        setUpObservers()
        swiperefresh()
    }

    open fun setUpObservers() {}

    open fun setupViews() {}

    open fun setupListeners() {}

    open fun initialization() {}

    open fun swiperefresh() {}

}