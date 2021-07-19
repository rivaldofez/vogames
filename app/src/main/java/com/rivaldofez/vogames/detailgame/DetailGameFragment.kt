package com.rivaldofez.vogames.detailgame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.github.ybq.android.spinkit.style.DoubleBounce
import com.google.android.material.snackbar.Snackbar
import com.rivaldofez.vogames.R
import com.rivaldofez.vogames.core.data.source.Resource
import com.rivaldofez.vogames.core.domain.model.DetailGame
import com.rivaldofez.vogames.core.utils.DataMapper
import com.rivaldofez.vogames.core.utils.ViewHelper
import com.rivaldofez.vogames.databinding.FragmentDetailGameBinding
import com.rivaldofez.vogames.games.SliderAdapter
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import org.koin.android.viewmodel.ext.android.viewModel

class DetailGameFragment : Fragment() {
    private var _binding: FragmentDetailGameBinding? = null
    private val binding get() = _binding!!
    private val detailGameViewModel: DetailGameViewModel by viewModel()
    private val sliderAdapter = SliderAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.layoutLoading.loading.setIndeterminateDrawable(DoubleBounce())

        val gameId = DetailGameFragmentArgs.fromBundle(arguments as Bundle).gameId
        val screenshoots = DetailGameFragmentArgs.fromBundle(arguments as Bundle).screenshots

        if(screenshoots != null && gameId != null){
            attachImageSlider(screenshoots)

            detailGameViewModel.getDetailGame(gameId).observe(viewLifecycleOwner, { detailGame ->
                when (detailGame) {
                    is Resource.Success -> {
                        detailGame.data?.let {
                            if(it.screenshots.isEmpty())
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
                val itemPlatform = ViewHelper.generatePlatform(it.trim(), requireContext(),20)
                if(itemPlatform != null){
                    cgPlatform.addView(itemPlatform)
                }
            }
            cgGenres.removeAllViews()
            detailGame.genres.split(",").map {
                cgGenres.addView(
                    ViewHelper.generateChipItem(it, requireContext())
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

            btnWebview.setOnClickListener {
                val gotoWebView = DetailGameFragmentDirections.actionDetailGameFragmentToWebviewFragment(
                    detailGame.website
                )
                findNavController().navigate(gotoWebView)
            }
        }
    }

    private fun setStateFavoriteIcon(isFavorite: Boolean){
        if(isFavorite)
            binding.btnFavorite.setImageDrawable(
                ContextCompat.getDrawable(requireContext(), R.drawable.ic_favorite)
            )
        else
            binding.btnFavorite.setImageDrawable(
                ContextCompat.getDrawable(requireContext(), R.drawable.ic_favorite_unfilled)
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
                    layoutError.lottieError.visibility = View.GONE
                    layoutError.tvMessage.visibility = View.GONE
                    imgSlider.visibility = View.VISIBLE
                    scItem.visibility = View.VISIBLE
                    btnFavorite.visibility = View.VISIBLE
                }
            }
            "error" -> {
                with(binding){
                    scItem.visibility = View.GONE
                    btnFavorite.visibility = View.GONE
                    layoutError.lottieError.visibility = View.VISIBLE
                    layoutError.tvMessage.visibility = View.VISIBLE
                }
            }
            "loading" -> {
                with(binding){
                    btnFavorite.visibility = View.GONE
                    layoutError.lottieError.visibility = View.GONE
                    layoutError.tvMessage.visibility = View.GONE
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

    override fun onStop() {
        super.onStop()
        detachedImageSlider()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}