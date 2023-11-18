package com.cecd.exitmed.presentation.dose

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cecd.exitmed.databinding.ItemDoseTimetableBinding
import com.cecd.exitmed.domain.type.DoseTimeTable
import com.cecd.exitmed.util.ItemDiffCallback

class DoseTimeTableAdapter :
    ListAdapter<DoseTimeTable, DoseTimeTableAdapter.DoseTimeTableViewHolder>(
        ItemDiffCallback(
            onItemTheSame = { old, new -> old.pillName == new.pillName },
            onContentsTheSame = { old, new -> old == new }
        )
    ) {
    class DoseTimeTableViewHolder(
        val binding: ItemDoseTimetableBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(doseTimeTable: DoseTimeTable) {
            binding.doseTimeTable = doseTimeTable
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoseTimeTableViewHolder {
        val binding =
            ItemDoseTimetableBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DoseTimeTableViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DoseTimeTableViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}
