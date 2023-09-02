package com.example.timelinedemo

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableStringBuilder
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class RecyclerViewActivity : Activity() {

    private var recyclerView: RecyclerView? = null
    private var adapter: TimeLineAdapter? = null
    private var dataList: MutableList<TimeBean>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recyclerview)
        recyclerView = findViewById(R.id.recyclerView)
        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView?.layoutManager = layoutManager
        dataList = ArrayList()
        initData()
        adapter = TimeLineAdapter(dataList)
        recyclerView?.adapter = adapter
        recyclerView?.addItemDecoration(
            TimelineDecoration(
                this@RecyclerViewActivity,
                getDimen(R.dimen.time_line_width),
                getDimen(R.dimen.root_layout_padding),
                getDimen(R.dimen.time_line_stroke_width)
            )
        )
    }

    private fun initData() {
        dataList?.add(
            TimeBean(
                TimeLineAdapter.NO_STATE,
                "07-18 11:17",
                SpannableStringBuilder("商品已经下单")
            )
        )
        dataList?.add(
            TimeBean(
                TimeLineAdapter.PLACED_AN_ORDER,
                "07-18 16:11",
                SpannableStringBuilder("您的订单已经开始处理")
            )
        )
        dataList?.add(
            TimeBean(
                TimeLineAdapter.SHIPPED,
                "07-19 09:04",
                SpannableStringBuilder("包裹正在等待揽收")
            )
        )
        val textSection = "[合肥市]磨店的王兵("
        val spanBuilder =
            SpanUtil.create().addSection(textSection).addForeColorSection("18156537586", Color.RED)
        spanBuilder.addSection(")已揽收")
        dataList?.add(
            TimeBean(
                TimeLineAdapter.JOB_BOUGHT,
                "07-19 15:01",
                spanBuilder.spanStrBuilder
            )
        )
        dataList?.add(
            TimeBean(
                TimeLineAdapter.NO_STATE,
                "07-19 18:10",
                SpannableStringBuilder("[合肥市]快件离开合肥磨店已发往合肥中转部")
            )
        )
        dataList?.add(
            TimeBean(
                TimeLineAdapter.NO_STATE,
                "07-19 19:31",
                SpannableStringBuilder("[合肥市]快件已发到达合肥中转部")
            )
        )
        dataList?.add(
            TimeBean(
                TimeLineAdapter.NO_STATE,
                "07-21 00:01",
                SpannableStringBuilder("[深圳市]快件已到达深圳中心")
            )
        )
        dataList?.add(
            TimeBean(
                TimeLineAdapter.IN_TRANSIT,
                "07-21 06:04",
                SpannableStringBuilder("[深圳市]快件已到达深圳龙华二部")
            )
        )
        val text = "[深圳市]深圳龙华二部("
        val spannableStringBuilder =
            SpanUtil.create().addSection(text).addForeColorSection("0755-36553676", Color.RED)
        spannableStringBuilder.addSection(")正在派件")
        dataList?.add(
            TimeBean(
                TimeLineAdapter.OUT_OF_DELIVERY,
                "07-21 08:03",
                spannableStringBuilder.spanStrBuilder
            )
        )
        dataList?.add(
            TimeBean(
                TimeLineAdapter.UNCLAIMED_ITEM,
                "07-21 11:27",
                SpannableStringBuilder("[深圳市]快件已被[代收点]的[菜鸟驿站]代收，请及时取件")
            )
        )
        dataList?.add(
            TimeBean(
                TimeLineAdapter.HAVE_BEEN_SIGNED,
                "07-22 17:21",
                SpannableStringBuilder("[深圳市]您的快递已签收")
            )
        )
        dataList?.reverse()
    }

    private fun getDimen(dimeRes: Int): Int {
        return resources.getDimension(dimeRes).toInt()
    }


}