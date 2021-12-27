package com.example.kotlin1lesson6.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlin1lesson6.common.base.BaseComparator
import com.example.kotlin1lesson6.data.network.dtos.character.CharacterModel
import com.example.kotlin1lesson6.databinding.ItemCharacterBinding

class CharacterAdapter(
    private val onItemClick: (name: String, id: Int) -> Unit,
    private val onLongClick: (image: String) ->Unit
) : PagingDataAdapter<CharacterModel, CharacterAdapter.CharacterViewHolder>(
    BaseComparator()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        getItem(position)?.let { holder.onBind(it) }
    }

    inner class CharacterViewHolder(
        private val binding: ItemCharacterBinding
    ) : RecyclerView.ViewHolder(binding.root){

        init {
            binding.root.setOnClickListener{
                getItem(bindingAdapterPosition)?.let {
                    onItemClick(it.name, it.id)
                }
            }
            binding.root.setOnLongClickListener{
                getItem(absoluteAdapterPosition)?.let {
                    onLongClick(it.image)
                }
                return@setOnLongClickListener false
            }

        }

        fun onBind(item: CharacterModel) = with(binding) {
            txtNameCharacter.text = item.name
            Glide.with(imageCharacter)
                .load(item.image)
                .into(imageCharacter)
        }
    }
}