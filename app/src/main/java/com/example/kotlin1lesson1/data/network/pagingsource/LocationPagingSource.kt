package com.example.kotlin1lesson1.data.network.pagingsource

import com.example.kotlin1lesson1.common.base.BasePagingSource
import com.example.kotlin1lesson1.data.network.apiservices.LocationApiService
import com.example.kotlin1lesson1.data.network.dtos.location.LocationModel

class LocationPagingSource(
    private val service: LocationApiService
) : BasePagingSource<LocationModel>({ position ->
    service.fetchLocations(position)
})