package com.rivaldofez.vogames.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.navArgs
import com.bumptech.glide.Glide
import com.rivaldofez.vogames.R
import com.rivaldofez.vogames.core.data.source.Resource
import com.rivaldofez.vogames.databinding.ActivityDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val detailGameViewModel: DetailGameViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gameId = DetailActivityArgs.fromBundle(intent.extras as Bundle).gameId
        if(gameId != null){
            detailGameViewModel.setCurrentGame(gameId)
            detailGameViewModel.detailGame?.observe(this, { detailGame ->
                when (detailGame) {
                    is Resource.Success -> {
                        Toast.makeText(this, detailGame.data?.name, Toast.LENGTH_SHORT).show()
                    }
                }
            })
            Toast.makeText(this,gameId, Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this,"Id Kosong", Toast.LENGTH_SHORT).show()
        }


    }
}