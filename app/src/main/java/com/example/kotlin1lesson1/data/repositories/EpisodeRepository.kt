package com.example.kotlin1lesson1.data.repositories

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.kotlin1lesson1.common.base.BaseRepository
import com.example.kotlin1lesson1.data.network.apiservices.EpisodeApiService
import com.example.kotlin1lesson1.data.network.dtos.episode.EpisodeModel
import com.example.kotlin1lesson1.data.network.pagingsource.EpisodePagingSource
import javax.inject.Inject

class EpisodeRepository @Inject constructor(
    private val service: EpisodeApiService
) : BaseRepository() {

    fun episodeRepository(): LiveData<PagingData<EpisodeModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 2
            ),
            pagingSourceFactory = {
                EpisodePagingSource(service)
            }
        ).liveData
    }

    fun episodeRepository(id: Int) = doRequest {
        service.fetchEpisodeApiService(id)
    }
}