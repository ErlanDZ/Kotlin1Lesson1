package com.example.kotlin1lesson6.ui.adapters.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin1lesson6.databinding.ItemLoadStateFooterViewBindingBinding

class LoadStateViewHolder(
    private val binding: ItemLoadStateFooterViewBindingBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.retryButton.setOnClickListener {
            retry.invoke()
        }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            binding.errorMsg.text = loadState.error.localizedMessage
        }
        binding.progressBar.isVisible = loadState is LoadState.Loading
        binding.retryButton.isVisible = loadState is LoadState.Error
        binding.errorMsg.isVisible = loadState is LoadState.Error
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): LoadStateViewHolder {
            return LoadStateViewHolder(
                ItemLoadStateFooterViewBindingBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                ),
                retry
            )
        }
    }
}