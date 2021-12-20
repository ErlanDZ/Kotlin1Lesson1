package com.example.kotlin1lesson1.data.repositories

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.kotlin1lesson1.common.base.BaseRepository
import com.example.kotlin1lesson1.data.network.apiservices.LocationApiService
import com.example.kotlin1lesson1.data.network.dtos.location.LocationModel
import com.example.kotlin1lesson1.data.network.pagingsource.LocationPagingSource
import javax.inject.Inject

class LocationRepository @Inject constructor(
    private val service: LocationApiService
) : BaseRepository() {

    fun locationRepository(): LiveData<PagingData<LocationModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 2
            ),
            pagingSourceFactory = {
                LocationPagingSource(service)
            }
        ).liveData
    }


    fun locationRepository(id: Int) = doRequest {
        service.fetchLocationApiService(1)
    }
}