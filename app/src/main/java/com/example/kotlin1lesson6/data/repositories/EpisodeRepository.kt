package com.example.kotlin1lesson6.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.kotlin1lesson6.common.base.BaseRepository
import com.example.kotlin1lesson6.data.network.apiservices.EpisodeApiService
import com.example.kotlin1lesson6.data.network.dtos.episode.EpisodeModel
import com.example.kotlin1lesson6.data.network.pagingsource.EpisodePagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EpisodeRepository (
    private val service: EpisodeApiService
) : BaseRepository() {

    fun episodeRepository(): Flow<PagingData<EpisodeModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 2
            ),
            pagingSourceFactory = {
                EpisodePagingSource(service)
            }
        ).flow
    }

    fun episodeRepository(id: Int) = doRequest {
        service.fetchEpisodeApiService(id)
    }
}