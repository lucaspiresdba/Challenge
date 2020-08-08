package br.com.lucaspires.challengexp

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(url:String){
    Glide.with(this)
        .load(url)
        .error(R.drawable.ic_baseline_thumb_down_alt_24)
        .fitCenter()
        .into(this)
}