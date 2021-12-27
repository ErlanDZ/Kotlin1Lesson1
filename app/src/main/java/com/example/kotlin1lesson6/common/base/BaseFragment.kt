package com.example.kotlin1lesson6.common.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.example.kotlin1lesson6.presentation.state.UIState
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


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


    @InternalCoroutinesApi
    protected fun <T> StateFlow<UIState<T>>.subscribe(
        state: Lifecycle.State = Lifecycle.State.STARTED,
        action: (UIState<T>) -> Unit
    ) {
      viewLifecycleOwner.lifecycleScope.launch {
          viewLifecycleOwner.repeatOnLifecycle(state){
              this@subscribe.collect{
                  action(it)
              }
          }
      }
    }

}