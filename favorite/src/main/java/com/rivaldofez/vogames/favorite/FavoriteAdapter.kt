package com.rivaldofez.vogames.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rivaldofez.vogames.core.R
import com.rivaldofez.vogames.core.domain.model.DetailGame
import com.rivaldofez.vogames.favorite.databinding.ItemGameFavoriteBinding

class FavoriteAdapter(private val callback: FavoriteFragmentCallback): RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {
    private val listFavoriteGames = ArrayList<DetailGame>()

    fun setFavoriteGames(favoriteGames: List<DetailGame>?){
        if(favoriteGames == null) return
        this.listFavoriteGames.clear()
        this.listFavoriteGames.addAll(favoriteGames)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val itemFavoriteBinding = ItemGameFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(itemFavoriteBinding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val favoriteGame = listFavoriteGames[position]
        holder.bind(favoriteGame)
    }

    override fun getItemCount(): Int = listFavoriteGames.size

    inner class FavoriteViewHolder(private val binding: ItemGameFavoriteBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(favoriteGame: DetailGame){
            with(binding){

                cvItemFavorite.setOnClickListener { callback.onFavoriteItemClick(favoriteGame) }
                Glide.with(itemView.context).load(favoriteGame.backgroundImage)
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                                .error(R.drawable.ic_error)).into(imgPoster)
            }
        }
    }

}