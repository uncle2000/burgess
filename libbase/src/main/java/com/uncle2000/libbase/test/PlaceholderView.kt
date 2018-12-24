package com.uncle2000.libbase.test

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.constant.RefreshState
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import com.uncle2000.libbase.R

class PlaceholderView<T> @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private var currentState = STATE_NONE
    var phAdapter: RecyclerViewAdapter<T>? = null
    val recyclerView: RecyclerView by lazy {
        return@lazy findViewById<RecyclerView>(R.id.recycler_view)
    }
    val smartRefreshLayout: SmartRefreshLayout by lazy {
        return@lazy findViewById<SmartRefreshLayout>(R.id.smart_refresh_layout)
    }
    var phView: ViewGroup
    val dataList: MutableList<T> = ArrayList()
    var noneLayoutId = R.layout.placeholder_state_container
        set(value) {
            phView = View.inflate(context, value, null) as ViewGroup
        }
    var emptyLayoutId = R.layout.placeholder_state_empty
    var failLayoutId = R.layout.placeholder_state_fail
    var noNetLayoutId = R.layout.placeholder_state_no_net

    init {
        LayoutInflater.from(context).inflate(getPhLayoutId(), this, true)
        phView = View.inflate(context, noneLayoutId, null) as ViewGroup
        recyclerView.layoutManager = LinearLayoutManager(context)
        phView.layoutParams = RecyclerView.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
    }

    fun setAdapter(adapter: RecyclerViewAdapter<T>) {
        phAdapter = adapter
        phAdapter?.phView = phView
        recyclerView.adapter = phAdapter
    }

    fun changeState(state: Int) {
        when (state) {
            STATE_CONTENT -> {
            }
            STATE_NONE -> phView.removeAllViews()
            STATE_LOADING -> phView.addView(View.inflate(context, emptyLayoutId, null))
            STATE_EMPTY -> phView.addView(View.inflate(context, emptyLayoutId, null))
            STATE_FAIL -> phView.addView(View.inflate(context, failLayoutId, null))
            STATE_NO_NETWORK -> phView.addView(View.inflate(context, noNetLayoutId, null))
        }
        if (state != STATE_CONTENT) {
            dataList.clear()
            canLoadMore(false)
        }
        phAdapter?.changeState(state)
        currentState = state
    }

    open fun getPhLayoutId() = R.layout.placeholder_shell

    //状态------------------------------------------------------------------------------
    fun loadDataFinish(isRefresh: Boolean, dataList: List<T>?, loadFinished: Boolean) {
        if (isRefresh) {
            this.dataList.clear()
        }

        if (dataList != null) {
            this.dataList.addAll(dataList)
        }

        phAdapter?.data = this.dataList
        phAdapter?.notifyDataSetChanged()

        loadFinish()
        canLoadMore(smartRefreshLayout.isEnableLoadMore && !loadFinished)
        if (this.dataList.isEmpty())
            changeState(STATE_EMPTY)
        else
            changeState(STATE_CONTENT)
    }

    fun loadDataFailed() {
        loadFinish()
        canLoadMore(false)
        if (this.dataList.isEmpty())
            changeState(STATE_FAIL)
        else
            changeState(STATE_CONTENT)
    }

    //计算高度 最大值不计算软键盘------------------------------------------------------------------------------
    private var heightWithoutKeyBoard = 0;

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (h > heightWithoutKeyBoard) {
            heightWithoutKeyBoard = h
            computeOtherViewHeight(h)
        }
    }

    private fun computeOtherViewHeight(height: Int) {
        phAdapter?.apply {
            phView?.layoutParams?.height = height - headHeight - footHeight
        }
    }

    private fun loadFinish() {
        if (smartRefreshLayout.state == RefreshState.Refreshing) {
            smartRefreshLayout.finishRefresh(100)
        }

        if (smartRefreshLayout.state == RefreshState.Loading) {
            smartRefreshLayout.finishLoadMore(100)
        }
    }

    public fun canLoadMore(canLoadMore: Boolean) {
        smartRefreshLayout.isEnableLoadMore = canLoadMore
    }

    fun setOnRefreshListener(listener: OnRefreshListener) {
        smartRefreshLayout.setOnRefreshListener(listener)
    }

    fun setOnLoadMoreListener(listener: OnLoadMoreListener) {
        smartRefreshLayout.setOnLoadMoreListener(listener)
    }

}
