package com.styni.simplecarbase.ui.global

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.styni.simplecarbase.R
import com.styni.simplecarbase.data.Item
import com.styni.simplecarbase.databinding.ItemBrandBinding

class SelectItemAdapter(
    private var data: ArrayList<Item>?,
    private val itemClick: (Item) -> Unit
    ) : RecyclerView.Adapter<SelectItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_brand, parent, false)
        val binding = ItemBrandBinding.bind(view)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        data?.get(position)?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int = data?.size ?: 0

    fun setData(data: ArrayList<Item>?) {
        this.data = data
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemBrandBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            binding.root.setOnClickListener {
                itemClick(item)
            }
            binding.name.text = item.name

            //TODO logo
        }
    }

}