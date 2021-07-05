package com.rivaldofez.vogames.games

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.rivaldofez.vogames.R
import com.rivaldofez.vogames.core.data.source.Resource
import com.rivaldofez.vogames.core.domain.model.Game
import com.rivaldofez.vogames.core.ui.GameAdapter
import com.rivaldofez.vogames.core.ui.GameFragmentCallback
import com.rivaldofez.vogames.databinding.FragmentGamesBinding
import com.rivaldofez.vogames.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel


class GamesFragment : Fragment(), GameFragmentCallback {

    private val gamesViewModel: GamesViewModel by viewModel()

    private lateinit var binding: FragmentGamesBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGamesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(activity != null){
            val gameAdapter = GameAdapter(this)

            gamesViewModel.recentlyGames.observe(viewLifecycleOwner, { games ->
                if(games != null){
                    when(games){
                        is Resource.Success -> {
                            gameAdapter.setGames(games.data)
                        }
                    }
                }
            })

            with(binding.rvGame){
                layoutManager = GridLayoutManager(context, 2)
                adapter = gameAdapter
            }

        }
    }

    override fun onGameClick(game: Game) {
        Toast.makeText(requireContext(), game.name, Toast.LENGTH_SHORT).show()
        val gotoDetailActivity = GamesFragmentDirections.actionGamesFragmentToDetailActivity(game.id.toString())
        findNavController().navigate(gotoDetailActivity)
    }
}