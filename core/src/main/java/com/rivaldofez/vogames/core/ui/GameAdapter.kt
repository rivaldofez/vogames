package com.rivaldofez.vogames.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rivaldofez.vogames.core.R
import com.rivaldofez.vogames.core.databinding.ItemGamesBinding
import com.rivaldofez.vogames.core.domain.model.Game


class GameAdapter(private val callback: GameFragmentCallback): RecyclerView.Adapter<GameAdapter.GameViewHolder>() {
    private val listGame = ArrayList<Game>()

    fun setGames(games: List<Game>?){
        if(games == null) return
        this.listGame.clear()
        this.listGame.addAll(games)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val itemGamesBinding = ItemGamesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameViewHolder(itemGamesBinding)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val game = listGame[position]
        holder.bind(game)
    }

    inner class GameViewHolder(private val binding: ItemGamesBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(game: Game){
            with(binding){
                tvTitle.text = game.name
                if(game.rating != null){
                    ratingGame.rating = game.rating.toFloat()
                }


                cvItemGame.setOnClickListener{callback.onGameClick(game)}

                Glide.with(itemView.context).load(game.backgroundImage)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)).into(imgPoster)
            }
        }

    }

    override fun getItemCount(): Int = listGame.size

}