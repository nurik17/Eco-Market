package com.example.ecomarket.presentation.history

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.ecomarket.data.entity.OrderedItem
import com.example.ecomarket.databinding.HistoryItemDataBinding
import com.example.ecomarket.databinding.HistoryItemDateBinding

class HistoryAdapter :
    ListAdapter<OrderedItem, BaseHistoryViewHolder<*, OrderedItem>>(HistoryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHistoryViewHolder<*, OrderedItem> {
        return if(viewType == HistoryListType.VIEW_ORDER_TYPE.ordinal){
            OrderViewHolder(
                HistoryItemDataBinding.inflate(
                    LayoutInflater.from(parent.context),parent,false
                )
            )
        } else {
            DateViewHolder(
                HistoryItemDateBinding.inflate(
                    LayoutInflater.from(parent.context),parent,false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: BaseHistoryViewHolder<*, OrderedItem>, position: Int) {
        holder.bindView(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).viewType?.ordinal ?: super.getItemViewType(position)
    }

}


class OrderViewHolder(override val binding: HistoryItemDataBinding) :
    BaseHistoryViewHolder<HistoryItemDataBinding, OrderedItem>(binding) {

    @SuppressLint("SetTextI18n")
    override fun bindView(item: OrderedItem) {
        binding.apply {
            orderId.text = "Заказ № " + item.order_number.toString()
            orderPrice.text = item.total_amount
            orderIdTime.text = takeSplitTime(item)
        }
    }

    private fun takeSplitTime(order: OrderedItem): String {
        val dateTimeString = order.created_at
        val splitString = dateTimeString.split(" ")
        return splitString[1]
    }
}


class DateViewHolder(override val binding: HistoryItemDateBinding) :
    BaseHistoryViewHolder<HistoryItemDateBinding, OrderedItem>(binding) {

    override fun bindView(item: OrderedItem) {
        binding.dateText.text = item.created_at
    }
}

abstract class BaseHistoryViewHolder<VB : ViewBinding, T: Any>(protected open val binding: VB) :
    RecyclerView.ViewHolder(binding.root) {
    abstract fun bindView(item: OrderedItem)
}

enum class HistoryListType {
    VIEW_ORDER_TYPE, VIEW_DATE_TYPE
}
