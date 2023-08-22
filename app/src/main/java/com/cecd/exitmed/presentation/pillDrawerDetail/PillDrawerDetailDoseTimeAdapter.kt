package com.cecd.exitmed.presentation.pillDrawerDetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cecd.exitmed.databinding.ItemPillDrawerDetailDoseTimeBinding
import com.cecd.exitmed.domain.type.DoseTime

class PillDrawerDetailDoseTimeAdapter :
    RecyclerView.Adapter<PillDrawerDetailDoseTimeAdapter.DoseTimeViewHolder>() {
    private var doseTimeList = emptyList<DoseTime>()

    class DoseTimeViewHolder(
        private val binding: ItemPillDrawerDetailDoseTimeBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(doseTime: DoseTime) {
            binding.doseTime = doseTime
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoseTimeViewHolder {
        val binding = ItemPillDrawerDetailDoseTimeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DoseTimeViewHolder(binding)
    }

    override fun getItemCount(): Int = doseTimeList.size

    override fun onBindViewHolder(holder: DoseTimeViewHolder, position: Int) {
        holder.onBind(doseTimeList[position])
    }

    fun setDoseTimeList(doseTimeList: List<DoseTime>) {
        this.doseTimeList = doseTimeList
        notifyDataSetChanged()
    }
}
