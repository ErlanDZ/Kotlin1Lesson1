package com.example.kotlin1lesson6.data.network.pagingsource

import com.example.kotlin1lesson6.common.base.BasePagingSource
import com.example.kotlin1lesson6.data.network.apiservices.LocationApiService
import com.example.kotlin1lesson6.data.network.dtos.location.LocationModel

class LocationPagingSource(
    private val service: LocationApiService
) : BasePagingSource<LocationModel>({ position ->
    service.fetchLocations(position)
})