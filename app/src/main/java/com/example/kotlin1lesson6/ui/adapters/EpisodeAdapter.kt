package com.example.kotlin1lesson6.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin1lesson6.common.base.BaseComparator
import com.example.kotlin1lesson6.data.network.dtos.episode.EpisodeModel
import com.example.kotlin1lesson6.databinding.ItemEpisodeBinding

class EpisodeAdapter(
    private val onItemClick: (id: Int) -> Unit
) : PagingDataAdapter<EpisodeModel, EpisodeAdapter.EpisodeViewHolder>(
    BaseComparator()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        return EpisodeViewHolder(
            ItemEpisodeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    inner class EpisodeViewHolder(
        private val binding: ItemEpisodeBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener{
                getItem(bindingAdapterPosition)?.let {
                    onItemClick(it.id)
                }
            }
        }

        fun onBind(item: EpisodeModel) = with(binding) {
            txtNameEpisode.text = item.name
            txtAirDateEpisode.text = item.air_date
        }

    }
}

