//package com.uncle2000.lib.base
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import com.scwang.smartrefresh.layout.api.RefreshLayout
//import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
//import com.scwang.smartrefresh.layout.listener.OnRefreshListener
//import com.uncle2000.lib.R
//import com.uncle2000.lib.views.TitleView
//
//abstract class ListFragment<T> : BaseFragment() {
//    private var loading: Boolean = false
//    var lFHolder: LFHolder? = null
//
//    var noneLayoutId = 0
//    var emptyLayoutId = 0
//    var failLayoutId = 0
//    var noNetLayoutId = 0
//    protected var page = 1
//    protected var size = 10
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        lFHolder = LFHolder(inflater, getLayoutId())
//        lFHolder?.placeholderView?.setAdapter(createAdapter())
//
//        loadData(true, 0) // 自动加载数据
//
//        return lFHolder?.rootView
//    }
//
//    protected fun loadDataFinish(dataList: List<T>?) {
//        loadDataFinish(true, dataList)
//    }
//
//    protected fun loadDataFinish(isRefresh: Boolean, dataList: List<T>?) {
//        var loadFinish = true
//        if (dataList != null) {
//            loadFinish = dataList.size < size
//        }
//
//        loadDataFinish(isRefresh, dataList, loadFinish, true)
//    }
//
//    protected fun loadDataFinish(isRefresh: Boolean, dataList: List<T>?, loadFinish: Boolean, loadFromBottom: Boolean) {
//        loading = false
//        if (dataList == null)
//            lFHolder?.placeholderView?.loadDataFinish(isRefresh, dataList, true, loadFromBottom)
//        else
//            lFHolder?.placeholderView?.loadDataFinish(isRefresh, dataList, loadFinish, loadFromBottom)
//    }
//
//    fun getDataList() = lFHolder?.placeholderView?.dataList!!
//
//
//    protected fun loadDataFailed() {
//        loading = false
//        lFHolder?.placeholderView?.loadDataFailed()
//    }
//
//    open fun getLayoutId() = R.layout.list_fragment_default
//
//    protected abstract fun doLoadData(isRefresh: Boolean, currentSize: Int)
//
//    protected fun loadData(isRefresh: Boolean, currentSize: Int) {
//        if (isRefresh)
//            page = 1
//        doLoadData(isRefresh, currentSize)
//    }
//
//    abstract fun createAdapter(): RecyclerViewAdapter<T>
//
//    protected fun notifyDataSetChanged() {
//        lFHolder?.placeholderView?.phAdapter?.notifyDataSetChanged()
//    }
//
//    protected fun getRecyclerView() = lFHolder?.placeholderView?.recyclerView
//
//    protected fun <V : View> findViewById(int: Int) = lFHolder?.rootView?.findViewById<V>(int)
//
//    protected fun setNeedNotify(b: Boolean) {
//        lFHolder?.placeholderView?.ifNeedNotify = b
//    }
//
//    inner class LFHolder(inflater: LayoutInflater, layoutId: Int) : OnRefreshListener, OnLoadMoreListener {
//        var rootView: View = inflater.inflate(layoutId, null)
//        var titleView: TitleView? = null
//        var placeholderView: PlaceholderView<T>
//
//        init {
//            if (rootView.findViewById<View>(R.id.title_view) != null)
//                titleView = rootView.findViewById<View>(R.id.title_view) as TitleView
//            placeholderView = rootView.findViewById<View>(R.id.placeholder_view) as PlaceholderView<T>
//            setOnRefreshListener()
//            setOnLoadMoreListener()
//            if (noneLayoutId > 0) {
//                placeholderView.noneLayoutId = noneLayoutId
//            }
//            if (emptyLayoutId > 0) {
//                placeholderView.emptyLayoutId = emptyLayoutId
//            }
//            if (failLayoutId > 0) {
//                placeholderView.failLayoutId = failLayoutId
//            }
//            if (noNetLayoutId > 0) {
//                placeholderView.noNetLayoutId = noNetLayoutId
//            }
//        }
//
//        fun setCanLoadMore(b: Boolean) {
//            placeholderView.canLoadMore(b)
//        }
//
//        fun setCanRefresh(b: Boolean) {
//            placeholderView.smartRefreshLayout.isEnableRefresh = b
//        }
//
//        fun setOnRefreshListener() {
//            placeholderView.setOnRefreshListener(this)
//        }
//
//        fun setOnRefreshListener(l: OnRefreshListener) {
//            placeholderView.setOnRefreshListener(l)
//        }
//
//
//        fun setOnLoadMoreListener() {
//            placeholderView.setOnLoadMoreListener(this)
//        }
//
//        fun setOnRefreshListener(l: OnLoadMoreListener) {
//            placeholderView.setOnLoadMoreListener(l)
//        }
//
//        override fun onRefresh(refreshLayout: RefreshLayout) {
//            if (!loading) {
//                loading = true
//                loadData(true, placeholderView.dataList.size)
//            }
//        }
//
//        override fun onLoadMore(refreshLayout: RefreshLayout) {
//            if (!loading) {
//                loading = true
//                loadData(false, placeholderView.dataList.size)
//            }
//        }
//    }
//}
