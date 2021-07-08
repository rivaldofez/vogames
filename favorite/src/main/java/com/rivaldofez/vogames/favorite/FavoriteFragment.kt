package com.rivaldofez.vogames.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rivaldofez.vogames.core.domain.model.DetailGame
import com.rivaldofez.vogames.di.FavoriteModule
import com.rivaldofez.vogames.favorite.databinding.FragmentFavoriteBinding
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment(), FavoriteFragmentCallback {

    private lateinit var binding : FragmentFavoriteBinding
    private val favoriteViewModel: FavoriteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadKoinModules(FavoriteModule)

        if (activity != null){
            val favoriteAdapter = FavoriteAdapter(this)

            with(binding.rvFavoriteGame){
                layoutManager = LinearLayoutManager(context)
                adapter = favoriteAdapter
            }
            favoriteViewModel.favoriteGames.observe(viewLifecycleOwner, { favoriteGames ->
                favoriteAdapter.setFavoriteGames(favoriteGames)
            })
        }
    }

    override fun onFavoriteItemClick(favoriteItem: DetailGame) {
        val gotoDetailActivity = FavoriteFragmentDirections.actionFavoriteFragmentToDetailActivity(
                favoriteItem.id.toString(), favoriteItem.screenshots
        )
        findNavController().navigate(gotoDetailActivity)
    }
}