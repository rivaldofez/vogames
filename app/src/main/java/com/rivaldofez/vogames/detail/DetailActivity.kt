package com.rivaldofez.vogames.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.github.ybq.android.spinkit.style.DoubleBounce
import com.google.android.material.snackbar.Snackbar
import com.rivaldofez.vogames.R
import com.rivaldofez.vogames.core.data.source.Resource
import com.rivaldofez.vogames.core.domain.model.DetailGame
import com.rivaldofez.vogames.core.utils.DataMapper
import com.rivaldofez.vogames.core.utils.ViewHelper
import com.rivaldofez.vogames.databinding.ActivityDetailBinding
import com.rivaldofez.vogames.games.SliderAdapter
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val detailGameViewModel: DetailGameViewModel by viewModel()
    private val sliderAdapter = SliderAdapter()
    private lateinit var gameId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.layoutLoading.loading.setIndeterminateDrawable(DoubleBounce())
        setActionBar()

        gameId = DetailActivityArgs.fromBundle(intent.extras as Bundle).gameId.toString()
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
                        showLoading(false)
                        showMessage("success")
                    }
                    is Resource.Error -> {
                        showLoading(false)
                        showMessage("error")
                    }
                    is Resource.Loading -> {
                        showLoading(true)
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
            tvRelease.text = DataMapper.formatDate(detailGame.released)
            chartPopularity.setProgress((detailGame.rating.toFloat()/5F) * 100, true)
            tvAbout.text = detailGame.descriptionRaw.replace("\n", "").trim()

            btnFavorite.apply {
                setStateFavoriteIcon(detailGame.isFavorite)
                setOnClickListener {
                    detailGameViewModel.setFavoriteGame(detailGame, !detailGame.isFavorite)
                    setStateFavoriteIcon(detailGame.isFavorite)
                    showSnackBarFavorite(!detailGame.isFavorite)
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

    private fun showSnackBarFavorite(isFavorite: Boolean){
        if(isFavorite){
            val snackbar = Snackbar.make(binding.root, "Added to Favorite List", Snackbar.LENGTH_SHORT)
            snackbar.show()
        }else{
            val snackbar = Snackbar.make(binding.root, "Removed from Favorite List", Snackbar.LENGTH_SHORT)
            snackbar.show()
        }
    }

    private fun attachImageSlider(screenshoot: String){
        sliderAdapter.setImages(screenshoot.split(',').map { it.trim() })
        binding.imgSlider.setSliderAdapter(sliderAdapter)
        binding.imgSlider.setIndicatorAnimation(IndicatorAnimationType.WORM)
        binding.imgSlider.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION)
        binding.imgSlider.startAutoCycle()
    }

    private fun detachedImageSlider(){
        sliderAdapter.removeImages()
        binding.imgSlider.stopAutoCycle()
        binding.imgSlider.removeAllViewsInLayout()
        binding.imgSlider.removeAllViews()
    }

    private fun showMessage(status: String){
        when(status){
            "success" -> {
                with(binding){
                    layoutMessage.imgMessage.visibility = View.GONE
                    layoutMessage.tvMessage.visibility = View.GONE
                    imgSlider.visibility = View.VISIBLE
                    scItem.visibility = View.VISIBLE
                    btnFavorite.visibility = View.VISIBLE
                }
            }
            "error" -> {
                with(binding){
                    scItem.visibility = View.GONE
                    btnFavorite.visibility = View.GONE
                    Glide.with(this@DetailActivity).load(com.rivaldofez.vogames.core.R.drawable.img_error).into(layoutMessage.imgMessage)
                    layoutMessage.tvMessage.text = getString(com.rivaldofez.vogames.core.R.string.error_message)
                    layoutMessage.imgMessage.visibility = View.VISIBLE
                    layoutMessage.tvMessage.visibility = View.VISIBLE
                }
            }
            "loading" -> {
                with(binding){
                    btnFavorite.visibility = View.GONE
                    layoutMessage.imgMessage.visibility = View.GONE
                    layoutMessage.tvMessage.visibility = View.GONE
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

    private fun setActionBar(){
        supportActionBar?.title = getString(R.string.detail_game)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }

    override fun onStop() {
        super.onStop()
        detailGameViewModel.getDetailGame(gameId).removeObservers(this)
        unregisterComponentCallbacks(this)
        detachedImageSlider()
    }
}