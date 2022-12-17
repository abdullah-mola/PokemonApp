package com.example.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.pojo.Pokemon

@Dao
interface PokemonDao {

    @Insert
    fun InsertPokemon(pokemon: Pokemon)

    @Query("delete from fav_table where name =:pokemonname")
    fun DeletePokemon(pokemonname: String)

    @Query("select * from fav_table")
    fun getAllPokemons():LiveData<List<Pokemon>>

}