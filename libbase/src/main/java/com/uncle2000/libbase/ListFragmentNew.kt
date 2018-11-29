//package com.uncle2000.libbase
//
//
//import android.content.Context
//import android.os.Bundle
//import android.support.v7.widget.AppCompatButton
//import android.support.v7.widget.LinearLayoutManager
//import android.support.v7.widget.RecyclerView
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import com.jcodecraeer.xrecyclerview.XRecyclerView
//import com.uncle2000.libviews.StateRecyclerView
//import com.uncle2000.libviews.StateView
//import com.uncle2000.libviews.TitleView
//import java.util.*
//
///**
// * 吊炸天的军哥
// * 公元前3000年
// */
//abstract class ListFragmentNew<T> : BaseFragment(), StateRecyclerView.StateChangeListener {
//
//    protected val dataList: MutableList<T> = ArrayList()
//
//    var holder: ViewHolder? = null
//
//    var adapter: BaseRecyclerViewAdapterNew<T>? = null
//    private var loadDataSuccess: Boolean? = null
//
//    open fun getLayoutId(): Int {
//        return R.layout.fragment_stateview_with_title
//    }
//
//    /* override这个方法无意义 */
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        holder = ViewHolder(inflater, getLayoutId())
//        instantiateContentView(holder?.stateView)
//        if (dataList.isEmpty()) {
//            if (loadDataSuccess == null) {
//                holder?.stateView?.state = StateView.STATE_LOADING
//                loadData(true, 0) // 自动加载数据
//            } else if (loadDataSuccess!!) {
//                holder?.stateView?.state = StateView.STATE_EMPTY
//            } else {
//                holder?.stateView?.state = StateView.STATE_FAIL
//            }
//        } else {
//            holder?.stateView?.state = StateView.STATE_CONTENT
//            adapter?.data = dataList
//        }
//
//
//        holder?.stateView?.otherView?.setOnClickListener(holder)
//        if (holder?.stateView?.ivView != null)
//            holder?.stateView?.ivView?.setOnClickListener(holder)
//        onCreateViewFinish()
//        return holder?.rootView
//    }
//
//    protected open fun onCreateViewFinish() {
//
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        adapter = null
//        holder = null
//    }
//
//    override fun onClick(v: View) {
//
//    }
//
//    override fun onStateChange(state: Int) {
//        adapter?.setShowHeaderAndFooter(state == StateView.STATE_CONTENT)
//        when (state) {
//            StateView.STATE_CONTENT -> {
//            }
//            StateView.STATE_FAIL -> instantiateFailView(holder?.stateView)
//            StateView.STATE_EMPTY -> instantiateEmptyView(holder?.stateView)
//            StateView.STATE_LOADING -> instantiateLoadingView(holder?.stateView)
//        }// instantiateContentView(holder.placeholderView);
//    }
//
//    override fun onStateChangeFinish(state: Int) {
//
//    }
//
//    protected open fun getRootView(inflater: LayoutInflater): View? {
//        return null
//    }
//
//    protected abstract fun doLoadData(isRefresh: Boolean, currentSize: Int)
//
//    protected fun loadData(isRefresh: Boolean, currentSize: Int) {
//        if (holder?.recyclerView?.isRefreshing != true && (adapter == null || adapter?.itemCount == 0)) {
//            holder?.stateView?.showLoadingView(0, 0)
//        }
//
//        doLoadData(isRefresh, currentSize)
//    }
//
//    /**
//     * 如果loadFinished为true,禁用底部加载功能
//     */
//    protected fun loadDataFinish(isRefresh: Boolean, dataList: List<T>?, loadFinished: Boolean) {
//        if (isRefresh) {
//            this.dataList.clear()
//        }
//        if (dataList != null) {
//            this.dataList.addAll(dataList)
//        }
//
//        loadDataSuccess = true
//
//        if (holder?.recyclerView?.isRefreshing == true) {
//            holder?.recyclerView?.refreshComplete()
//        }
//
//        if (holder?.recyclerView?.isLoadingMore == true) {
//            holder?.recyclerView?.loadMoreComplete()
//        }
//
//        if (this.dataList.size > 0) {
//            holder?.stateView?.state = StateView.STATE_CONTENT
//            adapter?.data = this.dataList
//
//            holder?.recyclerView?.setLoadingMoreEnabled(!loadFinished)
//        } else {
//            adapter?.data = null
//            holder?.stateView?.state = StateView.STATE_EMPTY
//        }
//        adapter?.notifyDataSetChanged()
//    }
//
//    abstract fun createAdapter(): BaseRecyclerViewAdapterNew<T>
//
//    fun clearData() {
//        this.dataList.clear()
//        adapter?.data = null
//        holder?.stateView?.state = StateView.STATE_EMPTY
//    }
//
//    fun removeData(position: Int) {
//        if (position >= 0 && position < dataList.size) {
//            this.dataList.removeAt(position)
//        }
//
//        if (dataList.isEmpty()) {
//            holder?.stateView?.state = StateView.STATE_EMPTY
//        }
//        adapter?.data = this.dataList
//        adapter?.notifyDataSetChanged()
//    }
//
//    protected fun loadDataFailed() {
//
//        loadDataSuccess = false
//
//        if (holder?.recyclerView?.isRefreshing == true) {
//            holder?.recyclerView?.refreshComplete()
//        }
//
//        if (holder?.recyclerView?.isLoadingMore == true) {
//            holder?.recyclerView?.loadMoreComplete()
//        }
//
//        if (dataList.isEmpty()) {
//            adapter?.data = null
//            holder?.stateView?.state = StateView.STATE_FAIL
//            //            holder.placeholderView.showFailView(R.string.net_error, R.drawable.net_error);
//        } else {
//            holder?.stateView?.state = StateView.STATE_CONTENT
//        }
//
//    }
//
//    open fun onClickEmptyView(view: View) {
//        //        loadData(true, 0);
//    }
//
//    fun onClickFailView(view: View) {
//        loadData(true, 0)
//    }
//
//    protected fun instantiateLoadingView(stateView: StateRecyclerView?) {
//
//    }
//
//    protected open fun instantiateEmptyView(stateView: StateRecyclerView?) {
//        holder?.recyclerView?.apply {
//            if (itemDecorationCount > 0) {
//                for (i in 0 until itemDecorationCount) {
//                    holder?.recyclerView?.removeItemDecorationAt(i)
//                }
//            }
//        }
//        stateView?.showEmptyView(R.string.nothing, R.drawable.no_data)
//    }
//
//    protected open fun instantiateFailView(stateView: StateRecyclerView?) {
//        if (null != stateView) {
//            if (State.netState == 1) {
//                stateView.showFailView(getString(R.string.no_network), R.drawable.no_net)
//            } else {
//                stateView.showFailView(getString(R.string.net_error), R.drawable.no_data)
//            }
//        }
//    }
//
//    protected fun instantiateContentView(stateView: StateRecyclerView?) {
//        holder?.recyclerView?.layoutManager = getLayoutManager(context)
//        if (adapter == null) {
//            adapter = createAdapter()
//        }
//        holder?.recyclerView?.adapter = adapter
//    }
//
//    private fun getLayoutManager(context: Context?): RecyclerView.LayoutManager {
//        return LinearLayoutManager(getContext())
//    }
//
//    fun scrollToBottom() {
//        adapter?.apply {
//            if (itemCount > 0) {
//                holder?.stateView?.scrollToPosition(itemCount)
//            }
//        }
//    }
//
//    inner class ViewHolder(inflater: LayoutInflater, layoutId: Int) : XRecyclerView.LoadingListener, StateRecyclerView.StateChangeListener, View.OnClickListener {
//        var rootView: View
//        var titleView: TitleView? = null
//        var recyclerBtn: AppCompatButton?
//        var stateView: StateRecyclerView
//        var recyclerView: XRecyclerView
//
//        init {
//            if (getRootView(inflater) == null) {
//                rootView = inflater.inflate(layoutId, null)
//            } else {
//                rootView = getRootView(inflater)!!
//            }
//
//
//            if (rootView?.findViewById<View>(R.id.title_view) != null)
//                titleView = rootView?.findViewById<View>(R.id.title_view) as TitleView
//            stateView = rootView?.findViewById<View>(R.id.state_view) as StateRecyclerView
//            recyclerBtn = rootView.findViewById(R.id.recycler_btn)
//            recyclerView = stateView
//            recyclerView?.setLoadingListener(this)
//            stateView?.setStateChangeListener(this)
//        }
//
//        override fun onRefresh() {
//            adapter?.apply {
//                loadData(true, itemCount)
//            }
//        }
//
//        override fun onLoadMore() {
//            adapter?.apply {
//                loadData(false, itemCount)
//            }
//        }
//
//        override fun onStateChange(state: Int) {
//            if (state == StateView.STATE_FAIL) {
//            } else if (state == StateView.STATE_EMPTY) {
//            }
//            this@ListFragmentNew.onStateChange(state)
//        }
//
//        override fun onStateChangeFinish(state: Int) {
//            this@ListFragmentNew.onStateChangeFinish(state)
//        }
//
//        override fun onClick(v: View) {
//            if (holder?.stateView?.otherView !== v && holder?.stateView?.ivView !== v) {
//                return
//            }
//            when (stateView?.state) {
//                StateView.STATE_EMPTY -> onClickEmptyView(v)
//                StateView.STATE_FAIL -> onClickFailView(v)
//            }
//        }
//    }
//
//    fun refresh() {
//        holder?.recyclerView?.isRefreshing = true
//    }
//}
