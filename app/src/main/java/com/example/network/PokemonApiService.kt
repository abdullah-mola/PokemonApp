package com.example.network

import com.example.pojo.PokemonResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface PokemonApiService {
    @GET("pokemon")
    fun getPokemons():Observable<PokemonResponse>
}