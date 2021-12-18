package io.github.iampjeetsingh.assignment

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun ImageView.imageUrl(url: String){
    Glide.with(this.context).load(url).into(this)
}