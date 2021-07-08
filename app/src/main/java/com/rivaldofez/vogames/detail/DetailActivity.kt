package com.rivaldofez.vogames.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import com.rivaldofez.vogames.R
import com.rivaldofez.vogames.core.data.source.Resource
import com.rivaldofez.vogames.core.domain.model.DetailGame
import com.rivaldofez.vogames.core.utils.ViewHelper
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

        val gameId = DetailActivityArgs.fromBundle(intent.extras as Bundle).gameId
        val screenshoots = DetailActivityArgs.fromBundle(intent.extras as Bundle).screenshots

        if(gameId!= null && screenshoots != null){
            attachImageSlider(screenshoots)

            detailGameViewModel.getDetailGame(gameId).observe(this, { detailGame ->
                when (detailGame) {
                    is Resource.Success -> {
                        detailGame.data?.let {
                            if(it.screenshots == null)
                                detailGameViewModel.setScreenshot(screenshoots, gameId)
                            setViewContent(it)
                        }
                    }
                    is Resource.Error -> {
                        Log.d("Teston", "error")
                    }

                    is Resource.Loading -> {
                        Log.d("Teston", "loading")
                    }
                }
            })
        }
    }

    private fun setViewContent(detailGame: DetailGame){
        with(binding){
            cgPlatform.removeAllViews()
            detailGame.platforms.split(",").map{
                val itemPlatform = ViewHelper.generatePlatform(it.trim(), this@DetailActivity,20)
                if(itemPlatform != null){
                    cgPlatform.addView(itemPlatform)
                }
            }
            cgGenres.removeAllViews()
            detailGame.genres.split(",").map {
                cgGenres.addView(
                        ViewHelper.generateChipItem(it, this@DetailActivity)
                )
            }
            tvGameName.text = detailGame.name
            tvPublisher.text = detailGame.publishers
            tvPlatform.text = detailGame.platforms
            tvMetacritic.text = detailGame.metacritic.toString()
            tvRelease.text = detailGame.released
            chartPopularity.setProgress((detailGame.rating.toFloat()/5F) * 100, true)
            tvAbout.text = detailGame.descriptionRaw.replace("\n", "").trim()

            btnFavorite.apply {
                setStateFavoriteIcon(detailGame.isFavorite)
                setOnClickListener {
                    detailGameViewModel.setFavoriteGame(detailGame, !detailGame.isFavorite)
                    setStateFavoriteIcon(detailGame.isFavorite)
                }
            }
        }
    }

    private fun setStateFavoriteIcon(isFavorite: Boolean){
        if(isFavorite)
            binding.btnFavorite.setImageDrawable(
                    ContextCompat.getDrawable(this, R.drawable.ic_favorite)
            )
        else
            binding.btnFavorite.setImageDrawable(
                    ContextCompat.getDrawable(this, R.drawable.ic_favorite_unfilled)
            )
    }

    private fun attachImageSlider(screenshoot: String){
        val sliderAdapter = SliderAdapter()
        sliderAdapter.setImages(screenshoot.split(',').map { it.trim() })
        binding.imgSlider.setSliderAdapter(sliderAdapter)
        binding.imgSlider.setIndicatorAnimation(IndicatorAnimationType.WORM)
        binding.imgSlider.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION)
        binding.imgSlider.startAutoCycle()
    }
}