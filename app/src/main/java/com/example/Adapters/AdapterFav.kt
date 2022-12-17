package com.example.Adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pojo.Pokemon
import com.example.poke.R

class AdapterFav(val context: Context): RecyclerView.Adapter<AdapterFav.PokemonViewHolder>() {

    var arrayofPokemons = arrayListOf<Pokemon>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterFav.PokemonViewHolder {
        return PokemonViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_pokemon,parent ,false))
    }
    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.name.text = arrayofPokemons[position].name
        Glide.with(context).load(arrayofPokemons[position].img).placeholder(R.drawable.ic_launcher_background).into(holder.image)
    }

    override fun getItemCount(): Int {
        return arrayofPokemons.size
    }

    fun getPokemonAt(position: Int): Pokemon
    {
        return arrayofPokemons[position]
    }

    class PokemonViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val image = itemView.findViewById<ImageView>(R.id.imgeview_pokemon)
        val name = itemView.findViewById<TextView>(R.id.tv_name_pokemon)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list:ArrayList<Pokemon>)
    {
        this.arrayofPokemons = list
        notifyDataSetChanged()
    }

}