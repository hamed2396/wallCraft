package com.example.wallcraft.adapters

import android.annotation.SuppressLint
import android.graphics.drawable.BitmapDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.crazylegend.kotlinextensions.root.logError
import com.crazylegend.kotlinextensions.views.snackbar
import com.example.wallcraft.R
import com.example.wallcraft.data.db.entity.FavoriteEntity
import com.example.wallcraft.databinding.FragmentFavoriteBinding
import com.example.wallcraft.databinding.ItemFavoriteBinding
import com.example.wallcraft.utils.base.BaseDiffUtil
import com.example.wallcraft.utils.blurHash.BlurHashDecoder
import javax.inject.Inject

class FavoriteAdapter @Inject constructor(private val baseDiffUtil: BaseDiffUtil<Any>) :
    RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    private var items = emptyList<FavoriteEntity>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding =
            ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
        holder.binding.itemImage.transitionName = "item $position"


    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int) = position

    override fun getItemId(position: Int) = position.toLong()

    inner class ViewHolder( val binding: ItemFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: FavoriteEntity) {
            binding.apply {
                //Color
                itemImage.load(item.image) { crossfade(500) }



                //Click
                root.setOnClickListener {
                    onItemClickListener?.let {
                        it(item.id!!,binding.itemImage)
                    }
                }
            }
        }
    }

    private var onItemClickListener: ((String,ImageView) -> Unit)? = null

    fun setOnItemClickListener(listener: (String,ImageView) -> Unit) {
        onItemClickListener = listener
    }

    fun setData(data: List<FavoriteEntity>) {
        baseDiffUtil.setNewList(data)
        baseDiffUtil.setOldList(items)
        val result = DiffUtil.calculateDiff(baseDiffUtil)
        items = data
        result.dispatchUpdatesTo(this)
    }
}