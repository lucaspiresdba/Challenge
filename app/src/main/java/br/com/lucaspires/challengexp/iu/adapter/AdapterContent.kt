package br.com.lucaspires.challengexp.iu.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.lucaspires.challengexp.R
import br.com.lucaspires.challengexp.loadImage
import br.com.lucaspires.domain.model.ItemContentModel

class AdapterContent : RecyclerView.Adapter<AdapterContent.ItemViewHolder>() {

    private val arrayItems = ArrayList<ItemContentModel>()

    fun addItems(list: List<ItemContentModel>) {
        arrayItems.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = ItemViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_content, parent, false)
    )

    override fun getItemCount() = arrayItems.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindView(arrayItems[position])
    }

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView? = view.findViewById(R.id.text_name_content)
        var image: ImageView? = view.findViewById(R.id.image_content)
        fun bindView(item: ItemContentModel) {
            title?.text = item.title
            item.thumbnail?.let { image?.loadImage(it) }
        }
    }
}