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

        when(platform){
            "PC" -> {
                item.setImageDrawable(AppCompatResources.getDrawable(context,R.drawable.ic_windows))
                return item
            }
            "Xbox" -> {
                item.setImageDrawable(AppCompatResources.getDrawable(context,R.drawable.ic_xbox))
                return item
            }
            "PlayStation" -> {
                item.setImageDrawable(AppCompatResources.getDrawable(context,R.drawable.ic_playstation))
                return item
            }
            "Linux" -> {
                item.setImageDrawable(AppCompatResources.getDrawable(context,R.drawable.ic_linux))
                return item
            }
            "Browser" -> {
                item.setImageDrawable(AppCompatResources.getDrawable(context,R.drawable.ic_browser))
                return item
            }
            "iOS" -> {
                item.setImageDrawable(AppCompatResources.getDrawable(context,R.drawable.ic_ios))
                return item
            }
            "Apple Macintosh" -> {
                item.setImageDrawable(AppCompatResources.getDrawable(context,R.drawable.ic_apple))
                return item
            }
            "Nintendo" -> {
                item.setImageDrawable(AppCompatResources.getDrawable(context, R.drawable.ic_nintendo))
                return item
            }
            "Android" -> {
                item.setImageDrawable(AppCompatResources.getDrawable(context, R.drawable.ic_android))
                return item
            }
            else -> return null
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