package com.example.kotlin1lesson6.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.kotlin1lesson6.common.base.BaseRepository
import com.example.kotlin1lesson6.data.network.apiservices.CharacterApiService
import com.example.kotlin1lesson6.data.network.dtos.character.CharacterModel
import com.example.kotlin1lesson6.data.network.pagingsource.CharacterPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterRepository (
    private val service: CharacterApiService
) : BaseRepository() {

    fun charactersRepository(): Flow<PagingData<CharacterModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
            ),
            pagingSourceFactory = {
                CharacterPagingSource(service)
            }
        ).flow
    }

    fun characterRepository(id: Int) = doRequest {
        service.fetchCharacterApiService(id)
    }
}