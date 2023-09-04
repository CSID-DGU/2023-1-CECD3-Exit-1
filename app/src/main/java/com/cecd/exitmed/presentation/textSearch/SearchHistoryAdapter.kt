package com.cecd.exitmed.presentation.textSearch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cecd.exitmed.databinding.ItemSearchHistoryBinding

class SearchHistoryAdapter(
    private val moveToPillDetail: () -> Unit
) : RecyclerView.Adapter<SearchHistoryAdapter.HistoryViewHolder>() {
    private var historyList: MutableList<String> = mutableListOf()

    class HistoryViewHolder(
        private val binding: ItemSearchHistoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(
            pillName: String,
            moveToPillDetail: () -> Unit
        ) {
            binding.pillName = pillName
            binding.tvSearchTitle.setOnClickListener {
                moveToPillDetail()
            }
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
        holder.onBind(historyList[position], moveToPillDetail)
    }

    fun setHistoryList(historyList: MutableList<String>) {
        this.historyList = historyList.toMutableList()
        notifyDataSetChanged()
    }
}
