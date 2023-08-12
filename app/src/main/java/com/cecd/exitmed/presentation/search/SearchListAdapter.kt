package com.cecd.exitmed.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cecd.exitmed.databinding.ItemSearchResultBinding
import com.cecd.exitmed.domain.type.SearchPill
import com.cecd.exitmed.util.ItemDiffCallback

class SearchListAdapter : ListAdapter<SearchPill, SearchListAdapter.SearchResultViewHolder>(
    ItemDiffCallback<SearchPill>(
        onItemTheSame = { old, new -> old.pillId == new.pillId },
        onContentsTheSame = { old, new -> old == new }
    )
) {

    class SearchResultViewHolder(
        private val binding: ItemSearchResultBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(pill: SearchPill) {
            binding.searchPill = pill
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        val binding =
            ItemSearchResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchResultViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}
