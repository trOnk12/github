package com.example.github.feature.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.github.databinding.ListItemBinding
import com.example.github.domain.model.Repository

class ListAdapter(
    private val onClick: (item: Repository, position: Int, imageView: ImageView) -> Unit
) : PagedListAdapter<Repository, ListAdapter.ViewHolder>(DIFF_CALLBACK) {

//    private var searchableList: ArrayList<Repository> = ArrayList()
//    private var originalDataSet: PagedList<List<Repository>> = PagedList<List<Repository>>()

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

//    fun setData(dataSet: PagedList<List<Repository>>) {
//        originalDataSet = dataSet
//        submitList(dataSet)
//    }
//
//    fun filterItems(searchedTitle: CharSequence) {
//        filter.filter(searchedTitle)
//    }

//    override fun getFilter(): Filter {
//        return object : Filter() {
//            private val filterResults = FilterResults()
//            override fun performFiltering(searchedTitle: CharSequence?): FilterResults {
//                searchableList.clear()
//                if (searchedTitle.isNullOrBlank()) {
//                    searchableList.addAll(originalDataSet)
//                } else {
////                    val searchResults =
////                        originalDataSet.filter {
////                            it.photoInfo.title.toLowerCase()
////                                .contains(searchedTitle.toString().toLowerCase())
////                        }
////                    searchableList.addAll(searchResults)
//                }
//                return filterResults.also {
//                    it.values = searchableList
//                }
//            }
//
//            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
//                onFilterResult(searchableList)
//            }
//        }
//
//    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Repository>() {
            override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean =
                oldItem.id == newItem.id
        }
    }

}
