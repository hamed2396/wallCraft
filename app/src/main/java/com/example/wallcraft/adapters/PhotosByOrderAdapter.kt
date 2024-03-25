package com.example.wallcraft.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.wallcraft.data.models.explore.ResponsePhotosByOrder.ResponsePhotosByOrderItem
import com.example.wallcraft.databinding.ItemPhotosStaggeredBinding
import com.example.wallcraft.utils.blurHash.BlurHashDecoder
import com.example.wallcraft.utils.loadImageByBlurHash
import javax.inject.Inject

class PhotosByOrderAdapter @Inject constructor() :
    PagingDataAdapter<ResponsePhotosByOrderItem, PhotosByOrderAdapter.ViewHolder>(differCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemPhotosStaggeredBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
        holder.binding.imgCover.transitionName = "item $position"

    }

    inner class ViewHolder(val binding: ItemPhotosStaggeredBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ResponsePhotosByOrderItem) {
            binding.apply {
                //Image

                if (item.urls?.regular != null && item.blurHash != null) {
                val blurHash = BlurHashDecoder.blurHashBitmap(binding.root.resources, item.blurHash)
                imgCover.loadImageByBlurHash(item.urls.regular, blurHash)


                }

                //Click
                root.setOnClickListener {
                    onItemClickListener?.let {
                        it(item.id!!,binding)
                    }
                }

            }
        }
    }

    private var onItemClickListener: ((String,ItemPhotosStaggeredBinding) -> Unit)? = null

    fun setOnItemClickListener(listener: (String,ItemPhotosStaggeredBinding) -> Unit) {
        onItemClickListener = listener
    }

    companion object {
        private val differCallback = object : DiffUtil.ItemCallback<ResponsePhotosByOrderItem>() {
            override fun areItemsTheSame(
                oldItem:ResponsePhotosByOrderItem,
                newItem:ResponsePhotosByOrderItem
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ResponsePhotosByOrderItem,
                newItem: ResponsePhotosByOrderItem
            ): Boolean {
                return oldItem == newItem

            }
        }
    }
}