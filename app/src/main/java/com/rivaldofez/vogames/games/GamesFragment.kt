package com.rivaldofez.vogames.games

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.rivaldofez.vogames.core.data.source.Resource
import com.rivaldofez.vogames.core.domain.model.Game
import com.rivaldofez.vogames.core.ui.GameAdapter
import com.rivaldofez.vogames.core.ui.GameFragmentCallback
import com.rivaldofez.vogames.databinding.FragmentGamesBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
@FlowPreview
class GamesFragment : Fragment(), GameFragmentCallback, SearchView.OnQueryTextListener {

    private val gamesViewModel: GamesViewModel by viewModel()
    private var _binding: FragmentGamesBinding? = null
    private val binding get() = _binding!!
    private val gameAdapter = GameAdapter(this)

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
            callObserveGames()
            binding.layoutSearch.searchField.setOnQueryTextListener(this)

            with(binding.rvGame){
                layoutManager = GridLayoutManager(context, 2)
                adapter = gameAdapter
            }
        }
    }

    private fun callObserveGames(){
        gamesViewModel.recentlyGames.observe(viewLifecycleOwner, { games ->
            Log.d("Teston", "call games")
            if(games != null){
                when(games){
                    is Resource.Loading -> showLoading(true)
                    is Resource.Success -> {
                        gameAdapter.setGames(games.data)
                        binding.rvGame.scheduleLayoutAnimation()
                        showLoading(false)
                    }
                    is Resource.Error -> {
                        showLoading(false)
                    }
                }
            }
        })

    }

    private fun callObserveSearch(){
        gamesViewModel.searchResult.observe(viewLifecycleOwner, { games ->
            if(games != null){
                gameAdapter.setGames(games)
                binding.rvGame.scheduleLayoutAnimation()
            }
        })
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
            binding.layoutLoading.loading.visibility = View.VISIBLE
        }else{
            binding.layoutLoading.loading.visibility = View.GONE
            binding.rvGame.visibility = View.VISIBLE
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (!newText.isNullOrEmpty()) {
            lifecycleScope.launch{
                newText.let { gamesViewModel.queryChannel.send(it) }
            }
            callObserveSearch()
        }else{
            gamesViewModel.searchResult.removeObservers(viewLifecycleOwner)
            callObserveGames()
        }
        return false
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        binding.layoutSearch.searchField.setOnQueryTextListener(this)
    }

}