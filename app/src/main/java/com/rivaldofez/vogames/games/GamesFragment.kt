package com.rivaldofez.vogames.games

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.github.ybq.android.spinkit.style.DoubleBounce
import com.rivaldofez.vogames.core.data.source.Resource
import com.rivaldofez.vogames.core.domain.model.Game
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
            binding.layoutLoading.loading.setIndeterminateDrawable(DoubleBounce())
            binding.layoutSearch.searchField.setOnQueryTextListener(this)

            with(binding.rvGame){
                layoutManager = GridLayoutManager(context, 2)
                adapter = gameAdapter
            }
        }
    }

    private fun callObserveGames(){
        gamesViewModel.recentlyGames.observe(viewLifecycleOwner, { games ->
            if(games != null){
                when(games){
                    is Resource.Loading -> showLoading(true)
                    is Resource.Success -> {
                        gameAdapter.setGames(games.data)
                        showLoading(false)
                        showMessage("success")
                    }
                    is Resource.Error -> {
                        showLoading(false)
                        showMessage("error")
                    }
                }
            }
        })
    }

    private fun callObserveSearch(){
        gamesViewModel.searchResult.observe(viewLifecycleOwner, { games ->
            if(games != null && games.isNotEmpty()){
                gameAdapter.setGames(games)
            }else{
                showMessage("empty")
            }
        })
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

    private fun showMessage(status: String){
        when(status){
            "success" -> {
                with(binding){
                    layoutEmpty.lottieEmpty.visibility = View.GONE
                    layoutEmpty.tvMessage.visibility = View.GONE
                    layoutError.lottieError.visibility = View.GONE
                    layoutError.tvMessage.visibility = View.GONE
                    rvGame.visibility = View.VISIBLE
                }
            }
            "error" -> {
                with(binding){
                    rvGame.visibility = View.GONE
                    layoutError.lottieError.visibility = View.VISIBLE
                    layoutError.tvMessage.visibility = View.VISIBLE
                    layoutEmpty.lottieEmpty.visibility = View.GONE
                    layoutEmpty.tvMessage.visibility = View.GONE
                }
            }
            "empty" -> {
                with(binding){
                    rvGame.visibility = View.GONE
                    layoutError.lottieError.visibility = View.GONE
                    layoutError.tvMessage.visibility = View.GONE
                    layoutEmpty.lottieEmpty.visibility = View.VISIBLE
                    layoutEmpty.tvMessage.visibility = View.VISIBLE
                }
            }
            "loading" -> {
                with(binding){
                    rvGame.visibility = View.GONE
                    layoutEmpty.lottieEmpty.visibility = View.GONE
                    layoutEmpty.tvMessage.visibility = View.GONE
                    layoutError.lottieError.visibility = View.GONE
                    layoutError.tvMessage.visibility = View.GONE
                }
            }
        }
    }

    private fun showLoading(state: Boolean){
        if(state){
            showMessage("loading")
            binding.layoutLoading.loading.visibility = View.VISIBLE
        }else{
            binding.layoutLoading.loading.visibility = View.GONE
        }
    }

    override fun onGameClick(game: Game) {
        val gotoDetailFragment = GamesFragmentDirections.actionGamesFragmentToDetailGameFragment(
                game.id.toString(), game.shortScreenshots
        )
        findNavController().navigate(gotoDetailFragment)
    }

    override fun onStop() {
        super.onStop()
        binding.rvGame.adapter = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}