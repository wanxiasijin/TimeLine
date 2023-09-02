package com.example.timelinedemo

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class TimelineDecoration(
    private val context: Context,
    private val width: Int, //时间轴宽度
    private val top: Int, //圆距离item顶部高度
    private val dividerHeight: Int //线条粗细
) : ItemDecoration() {
    private val mPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val ovalRadius by lazy { //灰色圆的半径
        context.resources.getDimensionPixelOffset(R.dimen.time_line_oval_radius)
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect[width, 0, 0] = dividerHeight
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val top = child.top
            val bottom = child.bottom
            val tvState = child.findViewById<TextView>(R.id.tv_state)
            val linearLayout = child.findViewById<LinearLayout>(R.id.root_layout)
            val textHeightHalf =
                (tvState.paint.fontMetrics.descent - tvState.paint.fontMetrics.ascent) / 2.0f
            val left = parent.paddingLeft + width / 2
            //画竖直线，只不过这个竖直线宽度为1dp
            c.drawRect(
                left.toFloat(),

                (if (i != 0) top else top + this.top + textHeightHalf).toFloat(),
                (left + dividerHeight).toFloat(),
                (if (i != childCount - 1) bottom + dividerHeight else bottom + dividerHeight - linearLayout.height + this.top + textHeightHalf).toFloat(),
                mPaint
            )
            //小圆点
            val ovalCenterX = (top
                    + this.top
                    + textHeightHalf)
            c.drawCircle(left.toFloat(), ovalCenterX, ovalRadius.toFloat(), mPaint)
        }
    }
}