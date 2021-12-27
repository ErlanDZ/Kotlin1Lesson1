package com.example.kotlin1lesson6.servicelocator

import com.example.kotlin1lesson6.data.network.RetrofitClient
import com.example.kotlin1lesson6.data.repositories.CharacterRepository
import com.example.kotlin1lesson6.data.repositories.EpisodeRepository
import com.example.kotlin1lesson6.data.repositories.LocationRepository
import com.example.kotlin1lesson6.ui.fragments.character.CharacterViewModel
import com.example.kotlin1lesson6.ui.fragments.character.detail.CharacterDetailViewModel
import com.example.kotlin1lesson6.ui.fragments.episode.EpisodeViewModel
import com.example.kotlin1lesson6.ui.fragments.episode.detail.EpisodeDetailViewModel
import com.example.kotlin1lesson6.ui.fragments.location.LocationViewModel
import com.example.kotlin1lesson6.ui.fragments.location.detail.LocationDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val networkModule = module {
    single { RetrofitClient() }
    single { get<RetrofitClient>().characterRetrofitClient() }
    single { get<RetrofitClient>().episodeRetrofitClient() }
    single { get<RetrofitClient>().locationRetrofitClient() }
}
val repositoriesModel = module {
    factory { CharacterRepository(get ()) }
    factory { EpisodeRepository(get ()) }
    factory { LocationRepository(get()) }
}
val viewModelsModule = module {
    viewModel{CharacterViewModel(get())}
    viewModel { EpisodeViewModel(get()) }
    viewModel { LocationViewModel(get()) }
    viewModel { CharacterDetailViewModel(get()) }
    viewModel { EpisodeDetailViewModel(get()) }
    viewModel { LocationDetailViewModel(get()) }

}