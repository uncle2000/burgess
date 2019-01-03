//package com.uncle2000.lib.base
//
//import android.content.Context
//import android.support.v7.widget.LinearLayoutManager
//import android.support.v7.widget.RecyclerView
//import android.util.AttributeSet
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.FrameLayout
//import android.widget.ImageView
//import android.widget.TextView
//import com.scwang.smartrefresh.layout.SmartRefreshLayout
//import com.scwang.smartrefresh.layout.constant.RefreshState
//import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
//import com.scwang.smartrefresh.layout.listener.OnRefreshListener
//import com.uncle2000.lib.R
//
//class PlaceholderView<T> @JvmOverloads constructor(
//        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
//) : FrameLayout(context, attrs, defStyleAttr) {
//    private var currentState = STATE_NONE
//    var phAdapter: RecyclerViewAdapter<T>? = null
//    val recyclerView: RecyclerView by lazy {
//        return@lazy findViewById(R.id.recycler_view) as RecyclerView
//    }
//    val smartRefreshLayout: SmartRefreshLayout by lazy {
//        return@lazy findViewById<SmartRefreshLayout>(R.id.smart_refresh_layout)
//    }
//    var phView: ViewGroup
//    val dataList: MutableList<T> = ArrayList<T>()
//    var ifNeedNotify = true
//    var noneLayoutId = R.layout.placeholder_state_container
//        set(value) {
//            phView = View.inflate(context, value, null) as ViewGroup
//        }
//    var emptyLayoutId = R.layout.placeholder_state_empty
//    var failLayoutId = R.layout.placeholder_state_fail
//    var noNetLayoutId = R.layout.placeholder_state_no_net
//
//    var emptyText: String = "没有内容"
//    var failText: String = "数据获取失败"
//    var noNetText: String = "数据获取失败"
//    var emptyImage: Int = R.drawable.no_data
//    var failImage: Int = R.drawable.no_data
//    var noNetImage: Int = R.drawable.no_net
//
//    init {
//        LayoutInflater.from(context).inflate(getPhLayoutId(), this, true)
//        phView = View.inflate(context, noneLayoutId, null) as ViewGroup
//        recyclerView.layoutManager = LinearLayoutManager(context)
//        phView.layoutParams = RecyclerView.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
//    }
//
//    fun setAdapter(adapter: RecyclerViewAdapter<T>) {
//        phAdapter = adapter
//        phAdapter?.phView = phView
//        recyclerView.adapter = phAdapter
//    }
//
//    fun changeState(state: Int) {
//        when (state) {
//            STATE_CONTENT -> {
//            }
//            STATE_NONE -> phView.removeAllViews()
//            STATE_LOADING -> {
//                phView.addView(View.inflate(context, emptyLayoutId, null))
//            }
//            STATE_EMPTY -> {
//                phView.addView(View.inflate(context, emptyLayoutId, null))
//                phView.findViewById<TextView>(R.id.text).text = emptyText
//                phView.findViewById<ImageView>(R.id.image).setImageResource(emptyImage)
//            }
//            STATE_FAIL -> {
//                phView.addView(View.inflate(context, failLayoutId, null))
//                phView.findViewById<TextView>(R.id.text).text = failText
//                phView.findViewById<ImageView>(R.id.image).setImageResource(failImage)
//            }
//            STATE_NO_NETWORK -> {
//                phView.addView(View.inflate(context, noNetLayoutId, null))
//                phView.findViewById<TextView>(R.id.text).text = noNetText
//                phView.findViewById<ImageView>(R.id.image).setImageResource(noNetImage)
//            }
//        }
//        if (state != STATE_CONTENT) {
//            dataList.clear()
//            canLoadMore(false)
//        }
//        phAdapter?.changeState(state)
//        currentState = state
//    }
//
//    open fun getPhLayoutId() = R.layout.placeholder_shell
//
//    //状态------------------------------------------------------------------------------
//    fun loadDataFinish(isRefresh: Boolean, dataList: List<T>?, loadFinished: Boolean, loadFromBottom: Boolean) {
//        if (isRefresh) {
//            this.dataList.clear()
//        }
//        if (dataList != null) {
//            if (loadFromBottom)
//                this.dataList.addAll(dataList)
//            else
//                this.dataList.addAll(0, dataList)
//        }
//
//        phAdapter?.data = this.dataList
//        if (ifNeedNotify) {
//            if (isRefresh) {
//                phAdapter?.notifyDataSetChanged()
//            } else if (dataList != null) {
//                if (loadFromBottom) {
//                    phAdapter?.notifyItemRangeChanged(this.dataList.size - dataList.size, dataList.size)
//                } else {
//                    phAdapter?.notifyItemRangeChanged(0, dataList.size)
//                }
//            }
//        }
//
////        phAdapter?.data = this.dataList
////        phAdapter?.notifyDataSetChanged()
//
//        loadFinish()
//        canLoadMore(smartRefreshLayout.isEnableLoadMore && !loadFinished)
//        if (this.dataList.isEmpty())
//            changeState(STATE_EMPTY)
//        else
//            changeState(STATE_CONTENT)
//    }
//
//    fun loadDataFailed() {
//        loadFinish()
//        canLoadMore(false)
//        if (this.dataList.isEmpty())
//            changeState(STATE_FAIL)
//        else
//            changeState(STATE_CONTENT)
//    }
//
//    //计算高度 最大值不计算软键盘------------------------------------------------------------------------------
//    private var heightWithoutKeyBoard = 0;
//
//    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
//        super.onSizeChanged(w, h, oldw, oldh)
//        if (h > heightWithoutKeyBoard) {
//            heightWithoutKeyBoard = h
//            computeOtherViewHeight(h)
//        }
//    }
//
//    private fun computeOtherViewHeight(height: Int) {
//        phAdapter?.apply {
//            phView?.layoutParams?.height = height - headHeight - footHeight
//        }
//    }
//
//    private fun loadFinish() {
//        if (smartRefreshLayout.state == RefreshState.Refreshing) {
//            smartRefreshLayout.finishRefresh(100)
//        }
//
//        if (smartRefreshLayout.state == RefreshState.Loading) {
//            smartRefreshLayout.finishLoadMore(100)
//        }
//    }
//
//    public fun canLoadMore(canLoadMore: Boolean) {
//        smartRefreshLayout.isEnableLoadMore = canLoadMore
//    }
//
//    fun setOnRefreshListener(listener: OnRefreshListener) {
//        smartRefreshLayout.setOnRefreshListener(listener)
//    }
//
//    fun setOnLoadMoreListener(listener: OnLoadMoreListener) {
//        smartRefreshLayout.setOnLoadMoreListener(listener)
//    }
//
//    fun setEmpty(text: String, drawable: Int = 0) {
//        if (text.isNotEmpty())
//            emptyText = text
//        if (drawable > 0)
//            emptyImage = drawable
//    }
//
//    fun setFail(text: String, drawable: Int = 0) {
//        if (text.isNotEmpty())
//            failText = text
//        if (drawable > 0)
//            failImage = drawable
//    }
//
//    fun setNoNet(text: String, drawable: Int = 0) {
//        if (text.isNotEmpty())
//            noNetText = text
//        if (drawable > 0)
//            noNetImage = drawable
//    }
//
//}
