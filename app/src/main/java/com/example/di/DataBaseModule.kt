package com.example.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.database.PokemonDao
import com.example.database.PokemonDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    @Singleton
    fun provideDataBase(context:Application):PokemonDataBase
    {
        return Room.databaseBuilder(context ,PokemonDataBase::class.java ,"fav_database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideDao(pokemonDataBase: PokemonDataBase):PokemonDao
    {
        return pokemonDataBase.pokemonDao()
    }

}