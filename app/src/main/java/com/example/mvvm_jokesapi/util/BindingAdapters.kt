package com.example.mvvm_jokesapi.util

import com.bumptech.glide.Glide
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView,url : String){
    Glide.with(imageView.context).load(url).into(imageView)
}