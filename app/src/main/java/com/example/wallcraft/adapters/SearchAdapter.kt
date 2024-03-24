package com.example.wallcraft.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.wallcraft.data.models.home.ResponsePhotos.responsePhotosItem
import com.example.wallcraft.data.models.search.ResponseSearch
import com.example.wallcraft.databinding.ItemPhotosBinding
import com.example.wallcraft.utils.blurHash.BlurHashDecoder
import com.example.wallcraft.utils.loadImageByBlurHash
import javax.inject.Inject

class SearchAdapter @Inject constructor() :
    PagingDataAdapter<ResponseSearch.Result, SearchAdapter.ViewHolder>(differCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemPhotosBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
        holder.binding.imgCover.transitionName="item$position"



    }

    inner class ViewHolder(val binding: ItemPhotosBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ResponseSearch.Result) {
            binding.apply {
                //Image

                if (item.urls?.regular != null && item.blurHash != null) {
                    val blurHash =
                        BlurHashDecoder.blurHashBitmap(binding.root.resources, item.blurHash)
                    imgCover.loadImageByBlurHash(item.urls.regular, blurHash)

                }

                //Click
                root.setOnClickListener {
                    onItemClickListener?.let {
                        it(item.id!!,binding.imgCover)
                    }
                }

            }
        }
    }

    private var onItemClickListener: ((String,ImageView) -> Unit)? = null

    fun setOnItemClickListener(listener: (String,ImageView) -> Unit) {
        onItemClickListener = listener
    }

    companion object {
        private val differCallback = object : DiffUtil.ItemCallback<ResponseSearch.Result>() {
            override fun areItemsTheSame(
                oldItem: ResponseSearch.Result,
                newItem: ResponseSearch.Result
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ResponseSearch.Result,
                newItem: ResponseSearch.Result
            ): Boolean {
                return oldItem == newItem

            }
        }
    }
}