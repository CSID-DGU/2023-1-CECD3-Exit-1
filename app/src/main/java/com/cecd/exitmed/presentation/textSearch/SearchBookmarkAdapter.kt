package com.cecd.exitmed.presentation.textSearch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cecd.exitmed.data.model.response.ResponseTextSearchBookmarkedList
import com.cecd.exitmed.databinding.ItemSearchBookmarkBinding

class SearchBookmarkAdapter(
    private val moveToPillDetail: (Int) -> Unit
) : RecyclerView.Adapter<SearchBookmarkAdapter.BookmarkViewHolder>() {
    private var bookmarkList: MutableList<ResponseTextSearchBookmarkedList.Data> = mutableListOf()

    class BookmarkViewHolder(
        private val binding: ItemSearchBookmarkBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(
            pillInfo: ResponseTextSearchBookmarkedList.Data,
            moveToPillDetail: (Int) -> Unit
        ) {
            binding.pillName = pillInfo.pillName
            binding.root.setOnClickListener {
                moveToPillDetail(pillInfo.pillItemSequence)
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

    fun setBookmarkList(bookmarkList: MutableList<ResponseTextSearchBookmarkedList.Data>) {
        this.bookmarkList = bookmarkList.toMutableList()
        notifyDataSetChanged()
    }
}
