package com.rivaldofez.vogames

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import com.rivaldofez.vogames.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.nav_host_fragment)

        binding.bnavMain.setNavigationChangeListener{_, position ->
            when(position){
                0 -> {
                    navController.popBackStack()
                    navController.navigate(R.id.gamesFragment)
                }
                1 -> {
                    navController.popBackStack()
                    navController.navigate(R.id.favoriteFragment)
                }
                2 -> {
                    navController.popBackStack()
                    navController.navigate(R.id.aboutFragment)
                }
            }
        }

        navController.addOnDestinationChangedListener { _, destination: NavDestination, _ ->
            if (destination.id == R.id.gamesFragment || destination.id == R.id.favoriteFragment || destination.id == R.id.aboutFragment) {
                binding.bnavMain.visibility = View.VISIBLE
                binding.toolbarMain.visibility = View.VISIBLE
            } else {
                binding.bnavMain.visibility = View.GONE
                binding.toolbarMain.visibility = View.GONE
            }
        }
    }
}