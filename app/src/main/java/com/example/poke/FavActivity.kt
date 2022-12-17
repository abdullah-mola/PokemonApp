package com.example.poke

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.Adapters.AdapterFav
import com.example.Adapters.AdapterPokemons
import com.example.pojo.Pokemon
import com.example.viewmodels.PokemonsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavActivity : AppCompatActivity() {

    lateinit var adapter: AdapterFav

    lateinit var viewModel: PokemonsViewModel

    lateinit var rv: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fav)

        rv = findViewById<RecyclerView>(R.id.rv_fav)

        adapter = AdapterFav(this)

        viewModel = ViewModelProvider(this)[PokemonsViewModel::class.java]

        rv.adapter = adapter

        setupSwipe()

        rv.layoutManager = LinearLayoutManager(this)

        viewModel.getAllPokemons()

        viewModel.favList.observe(this ,object : Observer<List<Pokemon>> {
            override fun onChanged(t: List<Pokemon>?) {
                adapter.setList(t!! as ArrayList<Pokemon>)
            }

        })
    }
    fun setupSwipe()
    {
        val callback: ItemTouchHelper.SimpleCallback = object :
            ItemTouchHelper.SimpleCallback(0 , ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Not yet implemented")
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val swipedPokemon = adapter.getPokemonAt(position)
                viewModel.DeletePokemon(swipedPokemon.name)
                adapter.notifyDataSetChanged()
                Toast.makeText(this@FavActivity ,"Pokemon deleted" , Toast.LENGTH_LONG).show()
            }
        }

        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rv)
    }
}