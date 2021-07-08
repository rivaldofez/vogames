package com.rivaldofez.vogames.games

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.rivaldofez.vogames.R
import com.rivaldofez.vogames.core.data.source.Resource
import com.rivaldofez.vogames.core.domain.model.Game
import com.rivaldofez.vogames.core.ui.GameAdapter
import com.rivaldofez.vogames.core.ui.GameFragmentCallback
import com.rivaldofez.vogames.databinding.FragmentGamesBinding
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel


class GamesFragment : Fragment(), GameFragmentCallback {

    private val gamesViewModel: GamesViewModel by viewModel()

    private var _binding: FragmentGamesBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGamesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(activity != null){
//            val searchManager = requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager
//            binding.searchField.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
//            binding.searchField.queryHint = "Masukan Kata Kunci"
//            binding.searchField.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//                override fun onQueryTextSubmit(query: String?): Boolean {
//
//                    return true
//                }
//                override fun onQueryTextChange(newText: String?): Boolean {
//                    return false
//                }
//            })

            binding.searchField.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    Log.d("Teston", "Submitt")
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    lifecycleScope.launch{
                        newText?.let { gamesViewModel.queryChannel.send(it) }
                    }
                    return false
                }
            })

            gamesViewModel.searchResult.observe(viewLifecycleOwner, Observer { games ->
                Log.d("Teston", games.toString())
            })

            val gameAdapter = GameAdapter(this)
            gamesViewModel.recentlyGames.observe(viewLifecycleOwner, { games ->
                if(games != null){
                    when(games){
                        is Resource.Loading -> showLoading(true)
                        is Resource.Success -> {
                            gameAdapter.setGames(games.data)
                            showLoading(false)
                        }
                        is Resource.Error -> {
                            showLoading(false)
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
        val gotoDetailActivity = GamesFragmentDirections.actionGamesFragmentToDetailActivity(
                game.id.toString(), game.shortScreenshots
        )
        findNavController().navigate(gotoDetailActivity)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showLoading(state: Boolean){
        if(state){
            binding.rvGame.visibility = View.GONE
            binding.loading.visibility = View.VISIBLE
        }else{
            binding.loading.visibility = View.GONE
            binding.rvGame.visibility = View.VISIBLE
        }
    }
}