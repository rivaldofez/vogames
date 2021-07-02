package com.rivaldofez.vogames.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.bumptech.glide.Glide
import com.github.ybq.android.spinkit.style.DoubleBounce
import com.rivaldofez.vogames.HomeActivity
import com.rivaldofez.vogames.R
import com.rivaldofez.vogames.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(this).load(R.drawable.logo_full).into(binding.imgLogoSplash)

        val sprite = DoubleBounce()
        binding.loadingSplash.visibility = View.VISIBLE
        binding.loadingSplash.setIndeterminateDrawable(sprite)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
            binding.loadingSplash.visibility = View.INVISIBLE
        }, SPLASH_TIME)
    }

    companion object {
        private const val SPLASH_TIME: Long = 3000
    }
}