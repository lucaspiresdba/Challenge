package br.com.lucaspires.challengexp

import android.widget.ImageView
import br.com.lucaspires.challengexp.iu.activity.CharacterDetailsActivity
import br.com.lucaspires.domain.model.CharacterModel
import com.bumptech.glide.Glide

fun ImageView.loadImage(url:String){
    Glide.with(this)
        .load(url)
        .error(R.drawable.ic_baseline_thumb_down_alt_24)
        .fitCenter()
        .into(this)
}

fun CharacterDetailsActivity.getHeroData(): CharacterModel {
    return this.intent?.extras?.getSerializable(CharacterDetailsActivity.CHARACTER_DATE) as CharacterModel
}