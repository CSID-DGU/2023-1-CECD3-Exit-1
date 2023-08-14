package com.cecd.exitmed.presentation.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cecd.exitmed.databinding.ItemSearchHistoryBinding

class SearchHistoryAdapter : RecyclerView.Adapter<SearchHistoryAdapter.HistoryViewHolder>() {
    private var historyList: MutableList<String> = mutableListOf()

    class HistoryViewHolder(
        private val binding: ItemSearchHistoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(pillName: String) {
            binding.pillName = pillName
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding =
            ItemSearchHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(binding)
    }

    override fun getItemCount(): Int = historyList.size

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.onBind(historyList[position])
    }

    fun setHistoryList(historyList: MutableList<String>) {
        this.historyList = historyList.toMutableList()
        notifyDataSetChanged()
    }
}
