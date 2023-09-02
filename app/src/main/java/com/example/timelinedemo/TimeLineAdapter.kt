package com.example.timelinedemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class TimeLineAdapter(private val dataList: MutableList<TimeBean>?) : RecyclerView.Adapter<TimeLineAdapter.TanLinViewHolder>() {

    companion object {
        const val NO_STATE = 0  //无状态
        const val PLACED_AN_ORDER = 0x1  //PlacedAnOrder 已下单
        const val SHIPPED = 0x2  //shipped 已发货
        const val JOB_BOUGHT = 0x4  //Job bought 已揽件
        const val IN_TRANSIT = 0x8  //In transit 运输中
        const val OUT_OF_DELIVERY = 0x10  //Out of delivery 派送中
        const val UNCLAIMED_ITEM = 0x20  //Unclaimed item 待取件
        const val HAVE_BEEN_SIGNED = 0x40  //Have been signed 已签收
    }


    // 创建ViewHolder
    class TanLinViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvState: TextView
        var tvTime: TextView
        var tvDescribe: TextView
        init {
            tvState = itemView.findViewById(R.id.tv_state)
            tvDescribe = itemView.findViewById(R.id.tv_describe)
            tvTime = itemView.findViewById(R.id.tv_time)
        }
    }

    // 创建ViewHolder，并关联item布局
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TanLinViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return TanLinViewHolder(view)
    }

    private fun stateConvertTo(state: Int): String {
        return when (state) {
            NO_STATE -> "无状态"
            PLACED_AN_ORDER -> "已下单"
            SHIPPED -> "已发货"
            JOB_BOUGHT -> "已揽件"
            IN_TRANSIT -> "运输中"
            OUT_OF_DELIVERY -> "派送中"
            UNCLAIMED_ITEM -> "待取件"
            HAVE_BEEN_SIGNED -> "已签收"
            else -> {
                "无状态"
            }
        }
    }

    override fun onBindViewHolder(holder: TanLinViewHolder, position: Int) {
        val data = dataList?.get(position)
        holder.tvTime.text = data?.time
        if (data?.state == 0) {
            holder.tvState.visibility = View.GONE
        } else {
            holder.tvState.visibility = View.VISIBLE
            holder.tvState.text = stateConvertTo(data?.state ?: 0)
        }
        holder.tvDescribe.text = data?.describe
    }

    // 返回列表项数量
    override fun getItemCount(): Int {
        return dataList?.size ?: 0
    }
}