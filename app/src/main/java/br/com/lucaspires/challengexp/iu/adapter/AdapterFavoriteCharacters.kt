package br.com.lucaspires.challengexp.iu.adapter

import android.content.Intent
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.lucaspires.challengexp.R
import br.com.lucaspires.challengexp.iu.activity.CharacterDetailsActivity
import br.com.lucaspires.challengexp.loadImage
import br.com.lucaspires.domain.model.CharacterModel

class AdapterFavoriteCharacters : RecyclerView.Adapter<AdapterFavoriteCharacters.ItemViewHolder>() {

    private val arrayItems = ArrayList<CharacterModel>()

    fun addItems(list: List<CharacterModel>) {
        arrayItems.clear()
        arrayItems.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_character_list, parent, false)
        )

    override fun getItemCount() = arrayItems.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindView(arrayItems[position])
    }

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView? = view.findViewById(R.id.text_hero_name)
        var favorite: ImageView? = view.findViewById(R.id.star_favorite)
        var image: ImageView? = view.findViewById(R.id.hero_image)
        fun bindView(item: CharacterModel) {
            name?.text = item.name
            item.thumbnail?.let { image?.loadImage(it) }
            item.isFavorite?.let {
                if (it) {
                    favorite?.setColorFilter(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.yellow
                        ), PorterDuff.Mode.SRC_IN
                    )
                }
            }
            image?.setOnClickListener {
                image?.context?.startActivity(
                    Intent(
                        image?.context,
                        CharacterDetailsActivity::class.java
                    ).apply {
                        putExtra(CharacterDetailsActivity.CHARACTER_DATE, item)
                    })
            }
        }
    }
}