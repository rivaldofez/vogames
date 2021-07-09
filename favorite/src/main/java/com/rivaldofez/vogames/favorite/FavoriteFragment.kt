package com.rivaldofez.vogames.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.rivaldofez.vogames.core.domain.model.DetailGame
import com.rivaldofez.vogames.di.FavoriteModule
import com.rivaldofez.vogames.core.R
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
        val gotoDetailActivity = FavoriteFragmentDirections.actionFavoriteFragmentToDetailActivity(
                favoriteItem.id.toString(), favoriteItem.screenshots
        )
        findNavController().navigate(gotoDetailActivity)
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
                    layoutMessage.imgMessage.visibility = View.GONE
                    layoutMessage.tvMessage.visibility = View.GONE
                    rvFavoriteGame.visibility = View.VISIBLE
                    layoutSearch.searchField.visibility = View.VISIBLE
                }
            }
            "empty" -> {
                with(binding){
                    rvFavoriteGame.visibility = View.GONE
                    layoutSearch.searchField.visibility = View.VISIBLE
                    Glide.with(requireContext()).load(R.drawable.img_no_result).into(layoutMessage.imgMessage)
                    layoutMessage.tvMessage.text = getString(R.string.empty_message)
                    layoutMessage.imgMessage.visibility = View.VISIBLE
                    layoutMessage.tvMessage.visibility = View.VISIBLE
                }
            }
            "nofavorite" -> {
                with(binding){
                    rvFavoriteGame.visibility = View.GONE
                    layoutSearch.searchField.visibility = View.GONE
                    Glide.with(requireContext()).load(R.drawable.img_no_result).into(layoutMessage.imgMessage)
                    layoutMessage.tvMessage.text = getString(R.string.nofavorite_message)
                    layoutMessage.imgMessage.visibility = View.VISIBLE
                    layoutMessage.tvMessage.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        favoriteViewModel.searchResult.removeObservers(viewLifecycleOwner)
        favoriteViewModel.favoriteGames.removeObservers(viewLifecycleOwner)
    }
}