package com.rivaldofez.vogames

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import com.rivaldofez.vogames.databinding.ActivityHomeBinding
import com.rivaldofez.vogames.favorite.FavoriteFragment

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_host_fragment)

        binding.navFavorite.visibility = View.GONE
        binding.navAbout.visibility = View.GONE

        binding.bnavMain.setNavigationChangeListener{_, position ->
            when(position){
                0 -> {
                    navController.navigate(R.id.gamesFragment)
                }
                1 -> {
                    navController.navigate(R.id.favoriteFragment)
                }
                2 -> {
                    navController.navigate(R.id.aboutFragment)
                }
            }
        }
    }
}