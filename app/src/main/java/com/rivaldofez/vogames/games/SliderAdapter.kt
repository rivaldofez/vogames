package com.rivaldofez.vogames.games

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rivaldofez.vogames.core.R
import com.rivaldofez.vogames.databinding.ItemImageSliderBinding
import com.smarteist.autoimageslider.SliderViewAdapter

class SliderAdapter: SliderViewAdapter<SliderAdapter.Holder>() {
    private val listImages = ArrayList<String>()

    fun setImages(images: List<String>){
        this.listImages.clear()
        this.listImages.addAll(images)
        notifyDataSetChanged()
    }

    override fun getCount(): Int = listImages.size

    override fun onCreateViewHolder(parent: ViewGroup?): Holder {
        val itemImageSliderBinding = ItemImageSliderBinding.inflate(LayoutInflater.from(parent?.context), parent, false)
        return Holder(itemImageSliderBinding)
    }

    override fun onBindViewHolder(viewHolder: Holder?, position: Int) {
        val image = listImages[position]
        viewHolder?.bind(image)
    }

    inner class Holder(private val binding: ItemImageSliderBinding): SliderViewAdapter.ViewHolder(binding.root) {
        fun bind(image: String){
            with(binding){
                Glide.with(itemView.context).load(image)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error)).into(imgSliderItem)
            }
        }

    }
}