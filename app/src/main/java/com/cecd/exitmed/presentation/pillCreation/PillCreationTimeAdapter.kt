package com.cecd.exitmed.presentation.pillCreation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cecd.exitmed.databinding.ItemPillCreationTimeBinding

class PillCreationTimeAdapter(
    private val showTimePickerDialog: (Int) -> Unit
) :
    RecyclerView.Adapter<PillCreationTimeAdapter.TimeViewHolder>() {
    var timeList = mutableListOf<String?>(null)

    class TimeViewHolder(
        private val binding: ItemPillCreationTimeBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(
            time: String?,
            showTimePickerDialog: (Int) -> Unit
        ) {
            binding.tvPillCreationTime.text = time
            binding.ivPillCreationArrowDown.setOnClickListener {
                showTimePickerDialog(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeViewHolder {
        val binding =
            ItemPillCreationTimeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TimeViewHolder(binding)
    }

    override fun getItemCount(): Int = timeList.size

    override fun onBindViewHolder(holder: TimeViewHolder, position: Int) {
        holder.onBind(timeList[position], showTimePickerDialog)
    }

    fun addItem() {
        timeList.add(null)
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        if (position > 0) {
            timeList.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun setItem(time: String, position: Int) {
        timeList[position] = time
        notifyDataSetChanged()
    }
}