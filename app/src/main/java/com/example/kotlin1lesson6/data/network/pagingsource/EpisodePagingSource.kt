package com.example.kotlin1lesson6.data.network.pagingsource

import com.example.kotlin1lesson6.common.base.BasePagingSource
import com.example.kotlin1lesson6.data.network.apiservices.EpisodeApiService
import com.example.kotlin1lesson6.data.network.dtos.episode.EpisodeModel

class EpisodePagingSource(
    private val service: EpisodeApiService
) : BasePagingSource<EpisodeModel>({ position ->
    service.episodeApiService(position)
})