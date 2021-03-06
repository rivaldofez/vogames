package com.rivaldofez.vogames.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.rivaldofez.vogames.core.R
import com.rivaldofez.vogames.core.domain.model.DetailGame
import com.rivaldofez.vogames.di.FavoriteModule
import com.rivaldofez.vogames.favorite.databinding.FragmentFavoriteBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

@ExperimentalCoroutinesApi
@FlowPreview
class FavoriteFragment : Fragment(), FavoriteFragmentCallback, SearchView.OnQueryTextListener {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private val favoriteAdapter = FavoriteAdapter(this)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadKoinModules(FavoriteModule)
        callObserveGames()
        binding.layoutSearch.searchField.setOnQueryTextListener(this)

        if (activity != null){
            with(binding.rvFavoriteGame){
                layoutManager = LinearLayoutManager(context)
                adapter = favoriteAdapter
                itemTouchHelper.attachToRecyclerView(this)
            }

        }
    }

    private fun callObserveGames(){
        favoriteViewModel.favoriteGames.observe(viewLifecycleOwner, { favoriteGames ->
            if(favoriteGames != null && favoriteGames.isNotEmpty()){
                favoriteAdapter.setFavoriteGames(favoriteGames)
                showMessage("success")
            }else{
                showMessage("nofavorite")
            }
        })
    }

    private fun callObserveSearch(){
        favoriteViewModel.searchResult.observe(viewLifecycleOwner, { favoriteGames ->
            if(favoriteGames != null && favoriteGames.isNotEmpty()){
                favoriteAdapter.setFavoriteGames(favoriteGames)
            }else{
                showMessage("empty")
            }
        })
    }

    override fun onFavoriteItemClick(favoriteItem: DetailGame) {
        val gotoDetailFragment = FavoriteFragmentDirections.actionFavoriteFragmentToDetailGameFragment(
                favoriteItem.id.toString(), favoriteItem.screenshots
        )
        findNavController().navigate(gotoDetailFragment)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (!newText.isNullOrEmpty()) {
            lifecycleScope.launch{
                newText.let { favoriteViewModel.queryChannel.send(it) }
            }
            callObserveSearch()
        }else{
            favoriteViewModel.searchResult.removeObservers(viewLifecycleOwner)
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
                    rvFavoriteGame.visibility = View.VISIBLE
                    layoutSearch.searchField.visibility = View.VISIBLE
                }
            }
            "empty" -> {
                with(binding){
                    layoutError.lottieError.visibility = View.GONE
                    layoutError.tvMessage.visibility = View.GONE
                    layoutEmpty.lottieEmpty.visibility = View.VISIBLE
                    layoutEmpty.tvMessage.visibility = View.VISIBLE
                    rvFavoriteGame.visibility = View.GONE
                    layoutSearch.searchField.visibility = View.VISIBLE
                }
            }
            "nofavorite" -> {
                with(binding){
                    rvFavoriteGame.visibility = View.GONE
                    layoutSearch.searchField.visibility = View.GONE
                    layoutError.lottieError.visibility = View.GONE
                    layoutError.tvMessage.visibility = View.GONE
                    layoutEmpty.lottieEmpty.visibility = View.VISIBLE
                    layoutEmpty.tvMessage.text = getString(R.string.nofavorite_message)
                    layoutEmpty.tvMessage.visibility = View.VISIBLE
                }
            }
        }
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback(){
        override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int =
                makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean =
                true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if(view != null){
                val swipedPosition = viewHolder.layoutPosition
                val swipedItem = favoriteAdapter.getSwipedItem(swipedPosition)
                swipedItem.let { favoriteViewModel.setFavoriteGame(swipedItem, !swipedItem.isFavorite) }

                val snackbar = Snackbar.make(view as View, "Batalkan menghapus item sebelumnya?", Snackbar.LENGTH_LONG)

                snackbar.setAction("OK"){
                    swipedItem.let { favoriteViewModel.setFavoriteGame(swipedItem, swipedItem.isFavorite) }
                }
                snackbar.show()
            }
        }
    })

    override fun onStop() {
        super.onStop()
        binding.rvFavoriteGame.adapter = null
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}