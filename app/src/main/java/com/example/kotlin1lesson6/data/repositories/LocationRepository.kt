package com.example.kotlin1lesson6.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.kotlin1lesson6.common.base.BaseRepository
import com.example.kotlin1lesson6.data.network.apiservices.LocationApiService
import com.example.kotlin1lesson6.data.network.dtos.location.LocationModel
import com.example.kotlin1lesson6.data.network.pagingsource.LocationPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocationRepository (
    private val service: LocationApiService
) : BaseRepository() {

    fun locationRepository(): Flow<PagingData<LocationModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10
            ),
            pagingSourceFactory = {
                LocationPagingSource(service)
            }
        ).flow
    }


    fun locationRepository(id: Int) = doRequest {
        service.fetchLocationApiService(id)
    }
}