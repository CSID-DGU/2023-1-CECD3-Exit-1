package com.cecd.exitmed.presentation.pillDrawer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cecd.exitmed.databinding.ItemPillDrawerBinding
import com.cecd.exitmed.domain.type.DrawerPill
import com.cecd.exitmed.util.ItemDiffCallback

class PillDrawerListAdapter : ListAdapter<DrawerPill, PillDrawerListAdapter.PillDrawerViewHolder>(
    ItemDiffCallback<DrawerPill>(
        onItemTheSame = { old, new -> old.pillId == new.pillId },
        onContentsTheSame = { old, new -> old == new }
    )
) {

    class PillDrawerViewHolder(
        private val binding: ItemPillDrawerBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(drawerPill: DrawerPill) {
            binding.drawerPill = drawerPill
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PillDrawerViewHolder {
        val binding =
            ItemPillDrawerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PillDrawerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PillDrawerViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}
