package com.rivaldofez.vogames.detail

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
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

    @ExperimentalStdlibApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gameId = DetailActivityArgs.fromBundle(intent.extras as Bundle).gameId
        val screenshoots = DetailActivityArgs.fromBundle(intent.extras as Bundle).screenshots

        if(gameId!= null && screenshoots != null){
            val sliderAdapter = SliderAdapter()
            sliderAdapter.setImages(screenshoots.split(',').map { it.trim() })
            binding.imgSlider.setSliderAdapter(sliderAdapter)
            binding.imgSlider.setIndicatorAnimation(IndicatorAnimationType.WORM)
            binding.imgSlider.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION)
            binding.imgSlider.startAutoCycle()

            detailGameViewModel.getDetailGame(gameId).observe(this, { detailGame ->
                when (detailGame) {
                    is Resource.Success -> {
                        detailGame.data?.let { setViewContent(it) }
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

    @ExperimentalStdlibApi
    private fun setViewContent(detailGame: DetailGame){
        with(binding){
            detailGame.platforms.split(",").map{
                val itemPlatform = ViewHelper.generatePlatform(it.lowercase().trim(), this@DetailActivity,20)
                if(itemPlatform != null){
                    cgPlatform.addView(itemPlatform)
                }
            }
            tvGameName.text = detailGame.name
            tvPublisher.text = detailGame.publishers
            tvMetacritic.text = detailGame.metacritic.toString()
            tvRelease.text = detailGame.released
            chartPopularity.setProgress((detailGame.rating.toFloat()/5F) * 100, true)
            tvAbout.text = detailGame.descriptionRaw.replace("\n", "").trim()
            addGenreChild()
        }
    }

    private fun addGenreChild() {
        val chipItem = Chip(this, null, )
        chipItem.setChipDrawable(ChipDrawable.createFromAttributes(this,null,0, R.style.ChipItem))
//        chipItem.isCheckable = false
        chipItem.setTextColor(ContextCompat.getColor(this,R.color.white))
        chipItem.text = "Testimoni"
        binding.cgGenres.addView(chipItem)
    }
}