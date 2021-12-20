package com.example.kotlin1lesson1.data.repositories

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.kotlin1lesson1.common.base.BaseRepository
import com.example.kotlin1lesson1.data.network.apiservices.CharacterApiService
import com.example.kotlin1lesson1.data.network.dtos.character.CharacterModel
import com.example.kotlin1lesson1.data.network.pagingsource.CharacterPagingSource
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val service: CharacterApiService
) : BaseRepository() {

    fun charactersRepository(): LiveData<PagingData<CharacterModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 2,
            ),
            pagingSourceFactory = {
                CharacterPagingSource(service)
            }
        ).liveData
    }

    fun characterRepository(id: Int) = doRequest {
        service.fetchCharacterApiService(id)
    }
}