package com.example.repository

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.Query
import com.example.database.PokemonDao
import com.example.network.PokemonApiService
import com.example.pojo.Pokemon
import com.example.pojo.PokemonResponse
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class Repository {
     var pokemonApiService:PokemonApiService
     var pokemonDao:PokemonDao

     @Inject
     constructor(pokemonApiService: PokemonApiService ,pokemonDao: PokemonDao)
     {
         this.pokemonApiService = pokemonApiService
         this.pokemonDao = pokemonDao
     }

    @Inject
    fun getPokemons():Observable<PokemonResponse>
    {
        return pokemonApiService.getPokemons()
    }


    fun InsertPokemon(pokemon: Pokemon)
    {
        pokemonDao.InsertPokemon(pokemon)
    }

    fun DeletePokemon(pokemonname: String)
    {
        pokemonDao.DeletePokemon(pokemonname)
    }

    fun getAllPokemons(): LiveData<List<Pokemon>>
    {
        return pokemonDao.getAllPokemons()
    }

}