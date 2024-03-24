package com.example.wallcraft.utils.paging.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.wallcraft.databinding.LoadMoreBinding

class HomeLoadMoreAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<HomeLoadMoreAdapter.ViewHolder>() {
    private lateinit var binding: LoadMoreBinding
    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): ViewHolder {
        binding = LoadMoreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    inner class ViewHolder() : RecyclerView.ViewHolder(binding.root) {

        fun bind(state: LoadState) {
            binding.apply {
                loadMoreProgress.isVisible = state is LoadState.Loading
                loadMoreRetry.apply {
                    isVisible = state is LoadState.Error
                    setOnClickListener { retry.invoke() }
                }
            }

        }

    }
}