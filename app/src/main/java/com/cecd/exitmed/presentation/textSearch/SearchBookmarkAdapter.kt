package com.cecd.exitmed.presentation.textSearch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cecd.exitmed.databinding.ItemSearchBookmarkBinding

class SearchBookmarkAdapter(
    private val moveToPillDetail: () -> Unit
) : RecyclerView.Adapter<SearchBookmarkAdapter.BookmarkViewHolder>() {
    private var bookmarkList: MutableList<String> = mutableListOf()

    class BookmarkViewHolder(
        private val binding: ItemSearchBookmarkBinding
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        val binding =
            ItemSearchBookmarkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookmarkViewHolder(binding)
    }

    override fun getItemCount(): Int = bookmarkList.size

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        holder.onBind(bookmarkList[position], moveToPillDetail)
    }

    fun setBookmarkList(bookmarkList: MutableList<String>) {
        this.bookmarkList = bookmarkList.toMutableList()
        notifyDataSetChanged()
    }
}
