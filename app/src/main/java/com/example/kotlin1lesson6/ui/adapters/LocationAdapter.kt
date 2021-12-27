package com.example.kotlin1lesson6.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin1lesson6.common.base.BaseComparator
import com.example.kotlin1lesson6.data.network.dtos.location.LocationModel
import com.example.kotlin1lesson6.databinding.ItemLocationBinding


class LocationAdapter(
    private val onItemClick: (id: Int) -> Unit
) : PagingDataAdapter<LocationModel, LocationAdapter.LocationViewHolder>(
    BaseComparator()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        return LocationViewHolder(
            ItemLocationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    inner class LocationViewHolder(
        private val binding: ItemLocationBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                getItem(absoluteAdapterPosition)?. let {
                    onItemClick(it.id)
                }
            }
        }

        fun onBind(item: LocationModel) = with(binding) {
            txtNameLocation.text = item.name
            txtTypeLocation.text = item.type
        }
    }
}

