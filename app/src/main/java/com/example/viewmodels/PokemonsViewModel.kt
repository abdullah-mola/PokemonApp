package com.example.viewmodels

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pojo.Pokemon
import com.example.pojo.PokemonResponse
import com.example.repository.Repository
import dagger.hilt.android.internal.modules.ApplicationContextModule
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.functions.Function
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class PokemonsViewModel @Inject constructor(var repository: Repository) : ViewModel() {

    private val pokemonList: MutableLiveData<ArrayList<Pokemon>> = MutableLiveData()
    lateinit var favList: LiveData<List<Pokemon>>


    fun getPokemonList(): MutableLiveData<ArrayList<Pokemon>> {
        return pokemonList
    }

    @SuppressLint("CheckResult")
    fun getPokemons() {
        repository.getPokemons()
            .subscribeOn(Schedulers.io())
            .map(object : Function<PokemonResponse, ArrayList<Pokemon>> {
                override fun apply(t: PokemonResponse): ArrayList<Pokemon> {
                    val list = t.results
                    for (pokemon in list) {
                        val url = pokemon.url
                        val pokemonIndex = url.split("/")
                        pokemon.url =
                            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemonIndex[pokemonIndex.size - 2]}.png"
                    }
                    return list
                }

            }).observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Consumer<ArrayList<Pokemon>> {
                override fun accept(t: ArrayList<Pokemon>) {
                    pokemonList.value = t
                }
            }, object : Consumer<Throwable> {
                override fun accept(t: Throwable) {
                    Log.d("ViewModel", t.message.toString())
                }
            })

    }

    fun InsertPokemon(pokemon: Pokemon)
    {
        repository.InsertPokemon(pokemon)
    }

    fun DeletePokemon(pokemonname: String)
    {
        repository.DeletePokemon(pokemonname)
    }

    fun getAllPokemons()
    {
        favList = repository.getAllPokemons()
    }
}