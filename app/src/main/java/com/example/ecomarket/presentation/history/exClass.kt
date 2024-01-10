/*
package com.example.ecomarket.presentation.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding


class CountryListAdapter :
    ListAdapter<CountryListDto, BaseCountryViewHolder<*, String>>(CountryDiffCallback()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseCountryViewHolder<*, String> {
        return if (viewType == CountryListType.REGION_VIEW.ordinal) {
            RegionViewHolder(
                ItemRegisonBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        } else CountryViewHolder(
            ItemCountryBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(
        holder: BaseCountryViewHolder<*, String>,
        position: Int
    ) {
        holder.bindView(getItem(position).name)
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).viewType.ordinal
    }

    class CountryViewHolder(override val binding: ItemCountryBinding) :
        BaseCountryViewHolder<ItemCountryBinding, String>(binding) {

        override fun bindView(item: String) {
            binding.root.text = item
        }
    }

    class RegionViewHolder(override val binding: ItemRegisonBinding) :
        BaseCountryViewHolder<ItemRegisonBinding, String>(binding) {

        override fun bindView(item: String) {
            binding.root.text = item

            binding.root.setOnClickListener {
                // Handle click here
            }
        }
    }
}

abstract class BaseCountryViewHolder<VB : ViewBinding, T>(protected open val binding: VB) :
    RecyclerView.ViewHolder(binding.root) {
    abstract fun bindView(item: T)
}

enum class CountryListType {
    REGION_VIEW, COUNTRY_VIEW
}

data class CountryListDto(
    val viewType: CountryListType,
    val name: String
)

class CountryDiffCallback : DiffUtil.ItemCallback<CountryListDto>() {
    override fun areItemsTheSame(
        oldItem: CountryListDto,
        newItem: CountryListDto
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: CountryListDto,
        newItem: CountryListDto
    ): Boolean {
        return oldItem == newItem
    }
}
*/
