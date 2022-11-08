package com.makhalibagas.chucknorris.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.makhalibagas.chucknorris.R
import com.makhalibagas.chucknorris.databinding.ItemChucknorrisBinding
import com.makhalibagas.chucknorris.domain.domain.Chucknorris

class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    private val listData = ArrayList<Chucknorris>()

    fun setData(newListData: List<Chucknorris>) {
        val previousContentSize = listData.size
        listData.clear()
        listData.addAll(newListData)
        notifyItemRangeRemoved(0, previousContentSize)
        notifyItemRangeInserted(0, newListData.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemChucknorrisBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount() = listData.size

    inner class ViewHolder(private val binding: ItemChucknorrisBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Chucknorris) {
            initView(data)
        }

        private fun initView(data: Chucknorris) {
            binding.apply {
                tvTitle.text = data.value
                imgChucknorris.load(data.iconUrl) {
                    error(R.drawable.ic_launcher_background)
                }
            }
        }
    }
}