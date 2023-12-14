package com.cecd.exitmed.presentation.dose

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cecd.exitmed.R
import com.cecd.exitmed.databinding.ItemMyPillDrawerBinding
import com.cecd.exitmed.domain.type.PillDrawerData
import com.cecd.exitmed.util.ItemDiffCallback
import com.cecd.exitmed.util.binding.setImage

class PillDrawerListAdapter(
    private val onOffDoseAlarm: (Int) -> Unit,
    private val moveToPillDrawerDetail: (Int) -> Unit,
) : ListAdapter<PillDrawerData, PillDrawerListAdapter.PillDrawerListViewHolder>(
    ItemDiffCallback<PillDrawerData>(
        onItemTheSame = { old, new -> old.pillItemSequence == new.pillItemSequence },
        onContentsTheSame = { old, new -> old == new }
    )
) {

    class PillDrawerListViewHolder(
        private val binding: ItemMyPillDrawerBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(
            pill: PillDrawerData,
            moveToPillDrawerDetail: (Int) -> Unit,
            onOffDoseAlarm: (Int) -> Unit
        ) {
            binding.ivPill.setImage(pill.imageLink)
            binding.tvPillName.text = pill.pillName
            binding.tvPillCategory.text = pill.classification
            val isAlarmTurnedImageRes = when (pill.isAlarmTurned) {
                true -> {
                    R.drawable.ic_alarm_on
                }

                false -> {
                    R.drawable.ic_alarm_off
                }
            }
            binding.ivPillDrawerAlarm.setImageResource(isAlarmTurnedImageRes)
            binding.layoutPillDrawerItem.setOnClickListener {
                moveToPillDrawerDetail(pill.pillItemSequence)
            }
            binding.ivPillDrawerAlarm.setOnClickListener {
                onOffDoseAlarm(pill.pillItemSequence)
            }
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PillDrawerListViewHolder {
        val binding =
            ItemMyPillDrawerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PillDrawerListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PillDrawerListViewHolder, position: Int) {
        holder.onBind(getItem(position), moveToPillDrawerDetail, onOffDoseAlarm)
    }
}
