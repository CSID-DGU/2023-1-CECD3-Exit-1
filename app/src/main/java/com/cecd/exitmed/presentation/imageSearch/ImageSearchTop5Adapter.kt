package com.cecd.exitmed.presentation.imageSearch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.cecd.exitmed.R
import com.cecd.exitmed.databinding.ItemSearchTop5Binding
import com.cecd.exitmed.domain.type.ImageSearchInfo
import com.cecd.exitmed.util.ItemDiffCallback
import com.cecd.exitmed.util.binding.setImage

class ImageSearchTop5Adapter :
    ListAdapter<ImageSearchInfo, ImageSearchTop5Adapter.ImageSearchTop5ViewHolder>(
        ItemDiffCallback<ImageSearchInfo>(
            onItemTheSame = { old, new -> old.pillItemSequence == new.pillItemSequence },
            onContentsTheSame = { old, new -> old == new }
        )
    ) {
    class ImageSearchTop5ViewHolder(
        private val binding: ItemSearchTop5Binding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(
            imagePillInfo: ImageSearchInfo
        ) {
            with(binding) {
                this.ivTopAccuracyImage.setImage(imagePillInfo.imageLink)
                binding.tvTopAccuracyPillName.text =
                    binding.root.context.getString(
                        R.string.image_search_pill_name,
                        imagePillInfo.pillName
                    )
                binding.tvTopAccuracyPillShpae.text =
                    binding.root.context.getString(
                        R.string.image_search_pill_shape,
                        imagePillInfo.shape
                    )
            }
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageSearchTop5ViewHolder {
        val binding =
            ItemSearchTop5Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageSearchTop5ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageSearchTop5ViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}
