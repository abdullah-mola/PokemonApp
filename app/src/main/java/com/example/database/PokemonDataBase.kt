package com.example.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.Tools.Converters
import com.example.pojo.Pokemon

@Database(entities = [Pokemon::class] , version = 2 , exportSchema = false)
@TypeConverters(Converters::class)
 abstract class PokemonDataBase:RoomDatabase() {
     abstract fun pokemonDao():PokemonDao
}