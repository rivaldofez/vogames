package com.rivaldofez.vogames.core.utils

import android.content.Context
import android.util.DisplayMetrics
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipDrawable
import com.rivaldofez.vogames.core.R

object ViewHelper {
    fun generatePlatform(platform: String, context: Context, size: Int ): ImageView?{
        val item = ImageView(context)
        item.layoutParams  = ViewGroup.LayoutParams(size.toPx(context), size.toPx(context))

        if(platform.contains("pc") || platform.contains("window")){
            item.setImageDrawable(AppCompatResources.getDrawable(context,R.drawable.ic_windows))
            return item
        }else if(platform.contains("xbox")){
            item.setImageDrawable(AppCompatResources.getDrawable(context,R.drawable.ic_xbox))
            return item
        }else if(platform.contains("playstation")){
            item.setImageDrawable(AppCompatResources.getDrawable(context,R.drawable.ic_playstation))
            return item
        }else if(platform.contains("linux")){
            item.setImageDrawable(AppCompatResources.getDrawable(context,R.drawable.ic_linux))
            return item
        }else if(platform.contains("browser")){
            item.setImageDrawable(AppCompatResources.getDrawable(context,R.drawable.ic_browser))
            return item
        }else if(platform.contains("ios")){
            item.setImageDrawable(AppCompatResources.getDrawable(context,R.drawable.ic_ios))
            return item
        }else if(platform.contains("apple")){
            item.setImageDrawable(AppCompatResources.getDrawable(context,R.drawable.ic_apple))
            return item
        }else if(platform.contains("nintendo")) {
            item.setImageDrawable(AppCompatResources.getDrawable(context, R.drawable.ic_nintendo))
            return item
        }else{
            return null
        }
    }

    fun generateChipItem(text: String, context: Context): Chip{
        val chipItem = Chip(context, null )
        chipItem.setChipDrawable(ChipDrawable.createFromAttributes(context,null,0, R.style.ChipItem))
        chipItem.isClickable = false
        chipItem.setTextColor(ContextCompat.getColor(context,R.color.white))
        chipItem.text = text

        return chipItem
    }

    private fun Int.toPx(context: Context) = this * context.resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT

}