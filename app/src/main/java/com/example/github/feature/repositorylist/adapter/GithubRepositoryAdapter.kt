package com.example.github.feature.repositorylist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.github.databinding.ListItemBinding
import com.example.github.domain.model.Repository

class GithubRepositoryAdapter(
    private val onClick: (item: Repository, position: Int, imageView: ImageView) -> Unit
) : PagedListAdapter<Repository, GithubRepositoryAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(layoutInflater, parent, false)

        return ViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class ViewHolder(
        private val binding: ListItemBinding,
        private val onClick: (item: Repository, position: Int, imageView: ImageView) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Repository) = with(itemView) {
            binding.repository = item
            setOnClickListener { onClick(item, adapterPosition, binding.ivThumb) }

            binding.executePendingBindings()
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Repository>() {
            override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean =
                oldItem.id == newItem.id
        }
    }

}
