package com.example.kotlin1lesson1.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.kotlin1lesson1.data.network.apiservices.EpisodeApiService
import com.example.kotlin1lesson1.data.network.dtos.episode.EpisodeModel
import com.example.kotlin1lesson1.data.network.pagingsource.EpisodePagingSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class EpisodeRepository @Inject constructor(
    private val service: EpisodeApiService
)  {

    fun episodeRepository(): LiveData<PagingData<EpisodeModel>>{
        return Pager(
            config = PagingConfig(
                pageSize = 2
            ),
            pagingSourceFactory = {
                EpisodePagingSource(service)
            }
        ).liveData
    }
//    fun fetchEpisodesRepository(page: Int): MutableLiveData<RickAndMortyResponse<EpisodeModel>?>{
//        val data = MutableLiveData<RickAndMortyResponse<EpisodeModel>?>()
//        service.episodeApiService(page)
//            .enqueue(object : Callback<RickAndMortyResponse<EpisodeModel>>{
//            override fun onResponse(
//                call: Call<RickAndMortyResponse<EpisodeModel>>,
//                response: Response<RickAndMortyResponse<EpisodeModel>>
//            ) {
//                if(response.isSuccessful){
//                    data.value = response.body()
//                }
//            }
//
//            override fun onFailure(
//                call: Call<RickAndMortyResponse<EpisodeModel>>, t: Throwable) {
//                data.value = null
//            }
//        })
//        return data
//    }

    fun episodeRepository(id: Int): MutableLiveData<EpisodeModel>{
        val data: MutableLiveData<EpisodeModel> = MutableLiveData()
        service.fetchEpisodeApiService(id).enqueue(object : Callback<EpisodeModel>{
            override fun onResponse(call: Call<EpisodeModel>, response: Response<EpisodeModel>) {
                if(response.isSuccessful){
                    data.value = response.body()
                }
            }

            override fun onFailure(call: Call<EpisodeModel>, t: Throwable) {
                data.value = null
            }
        })
        return data
    }
}