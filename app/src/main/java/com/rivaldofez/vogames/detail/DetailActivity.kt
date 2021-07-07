package com.rivaldofez.vogames.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.navigation.navArgs
import com.bumptech.glide.Glide
import com.rivaldofez.vogames.R
import com.rivaldofez.vogames.core.data.source.Resource
import com.rivaldofez.vogames.databinding.ActivityDetailBinding
import com.rivaldofez.vogames.games.SliderAdapter
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val detailGameViewModel: DetailGameViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val images = listOf<String>(
            "https://media.rawg.io/media/screenshots/5f8/5f85fc16026af9e9cbbbe77e87f2d98d.jpg",
            "https://media.rawg.io/media/screenshots/d66/d66628e709dbb8e07f96fd1f82a55561.jpg",
            "https://media.rawg.io/media/screenshots/1a1/1a15003ce30e5d6eb7b61a734bc0eb3f.jpg",
            "https://media.rawg.io/media/screenshots/5f8/5f85fc16026af9e9cbbbe77e87f2d98d.jpg"
        )

        val sliderAdapter = SliderAdapter()
        sliderAdapter.setImages(images)
        binding.imgSlider.setSliderAdapter(sliderAdapter)
        binding.imgSlider.setIndicatorAnimation(IndicatorAnimationType.WORM)
        binding.imgSlider.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION)
        binding.imgSlider.startAutoCycle()

        val gameId = DetailActivityArgs.fromBundle(intent.extras as Bundle).gameId
        if(gameId != null){
            detailGameViewModel.getDetailGame(gameId).observe(this, { detailGame ->
                when (detailGame) {
                    is Resource.Success -> {
                        Log.d("Teston","sukses")
                        Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
                        binding.tvGameName.text = detailGame.data?.name
                        binding.tvAbout.text = detailGame.data?.rating.toString()
                        binding.tvPublisher.text = detailGame.data?.backgroundImage
                        binding.chartPopularity.setProgress(55F, true)

                        detailGame.data?.backgroundImage?.let { Log.d("Teston", it) }
                        detailGame.data?.let { Log.d("Teston", it.name) }
                        Toast.makeText(this, detailGame.data?.backgroundImage, Toast.LENGTH_SHORT).show()
                        Toast.makeText(this, detailGame.data?.rating.toString(), Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Error -> {
                        Log.d("Teston", "error")
                    }

                    is Resource.Loading -> {
                        Log.d("Teston", "loading")
                    }
                }
                Log.d("Teston", detailGame.toString())
            })
        }else{
            Log.d("Teston", "id kosong")
        }


    }
}