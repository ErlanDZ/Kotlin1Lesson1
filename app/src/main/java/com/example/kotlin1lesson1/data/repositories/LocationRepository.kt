package com.example.kotlin1lesson1.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.*
import com.example.kotlin1lesson1.data.network.apiservices.LocationApiService
import com.example.kotlin1lesson1.data.network.dtos.RickAndMortyResponse
import com.example.kotlin1lesson1.data.network.dtos.location.LocationModel
import com.example.kotlin1lesson1.data.network.pagingsource.LocationPagingSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class LocationRepository @Inject constructor(
    private val service: LocationApiService
) {

    fun locationRepository(): LiveData<PagingData<LocationModel>>{
        return Pager(
            config = PagingConfig(
                pageSize = 2
            ),
            pagingSourceFactory = {
                LocationPagingSource(service)
            }
        ).liveData
    }



    fun locationRepository(id: Int): MutableLiveData<LocationModel>{
        val data: MutableLiveData<LocationModel> = MutableLiveData()
        service.fetchLocationApiService(id).enqueue(object : Callback<LocationModel>{
            override fun onResponse(call: Call<LocationModel>, response: Response<LocationModel>) {
                if(response.isSuccessful){
                    data.value = response.body()
                }
            }

            override fun onFailure(call: Call<LocationModel>, t: Throwable) {
                data.value = null
            }

        })
        return data
    }
}