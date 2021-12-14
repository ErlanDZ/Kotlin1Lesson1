package com.example.kotlin1lesson1.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.*
import com.example.kotlin1lesson1.data.network.apiservices.CharacterApiService
import com.example.kotlin1lesson1.data.network.dtos.character.CharacterModel
import com.example.kotlin1lesson1.data.network.pagingsource.CharacterPagingSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val service: CharacterApiService
) {

     fun charactersRepository(): LiveData<PagingData<CharacterModel>>{
        return Pager(
            config = PagingConfig(
                pageSize = 2,
            ),
            pagingSourceFactory = {
                CharacterPagingSource(service)
            }
        ).liveData
    }
//    fun charactersRepository(page: Int): MutableLiveData<RickAndMortyResponse<CharacterModel>?> {
//        val data = MutableLiveData<RickAndMortyResponse<CharacterModel>?>()
//        service.fetchCharactersApiService(page).enqueue(object : Callback<RickAndMortyResponse<CharacterModel>>{
//            override fun onResponse(
//                call: Call<RickAndMortyResponse<CharacterModel>>,
//                response: Response<RickAndMortyResponse<CharacterModel>>
//            ) {
//                 if (response.isSuccessful){
//                     data.value = response.body()
//
//                 }
//            }
//
//            override fun onFailure(call: Call<RickAndMortyResponse<CharacterModel>>, t: Throwable) {
//                data.value = null
//            }
//        })
//        return data
//    }

    fun characterRepository(id: Int): MutableLiveData<CharacterModel>{
        val data: MutableLiveData<CharacterModel> = MutableLiveData()
        service.fetchCharacterApiService(id).enqueue(object : Callback<CharacterModel>{
            override fun onResponse(
                call: Call<CharacterModel>,
                response: Response<CharacterModel>
            ) {
                if (response.isSuccessful){
                    data.value = response.body()
                }
            }

            override fun onFailure(call: Call<CharacterModel>, t: Throwable) {
                data.value = null
            }
        })
        return data
    }
}