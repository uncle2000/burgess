//package com.uncle2000.libbase.search.core
//
//import android.databinding.ViewDataBinding
//import android.os.Bundle
//import android.support.annotation.LayoutRes
//import android.support.v4.content.ContextCompat
//import android.text.Spannable
//import android.text.SpannableStringBuilder
//import android.text.style.ForegroundColorSpan
//import android.view.View
//import android.view.WindowManager
//import android.view.inputmethod.EditorInfo
//import android.widget.TextView
//import android.widget.Toast
//import com.uncle2000.libbase.App
//import com.uncle2000.libbase.OnItemClickListener
//import com.uncle2000.libbase.R
//import com.uncle2000.libbase.databinding.SearchFragmentBinding
//import com.uncle2000.libbase.test.ListFragment
//import com.uncle2000.libbase.test.RecyclerViewAdapter
//import com.uncle2000.libbase.test.Vh
//import kotlinx.android.synthetic.main.search_fragment.*
//import kotlinx.android.synthetic.main.view_search_fragment_title.view.*
//
///**
// * wangwei
// * 2018.4.8
// * 搜索通用界面
// * 该界面与搜索历史界面依赖度低
// * 理论上讲，日后再有其他类型的搜索不需要动这里的代码
// */
//abstract class SearchListFragment<T, D : ViewDataBinding> : ListFragment<T>(), OnItemClickListener<T> {
//    private var binding: SearchFragmentBinding? = null
//    /* 搜索类型：自动为子类的简化名称 不需要自己写 */
//    private val recordType = javaClass.simpleName
//    /* 搜索历史记录的类 */
////    private lateinit var searchRecord: SearchRecord
//
//    fun getKeyWord(): String {
//        return if (binding?.searchTitle?.search_et?.text == null) {
//            ""
//        } else {
//            binding?.searchTitle?.search_et?.text.toString().trim()
//        }
//    }
//
//    @LayoutRes
//    abstract fun getItemLayoutId(): Int
//
//    open fun bindItemData(iB: D, t: T, pos: Int) {
//
//    }
//
//    /* 如果界面不一样，可以重写下面这个方法，但是要注意不能缺省某些id */
//    override fun getLayoutId(): Int {
//        return R.layout.search_fragment
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
//        binding = SearchFragmentBinding.bind(view)
//        initRecord()
//    }
//
//    override fun createAdapter(): RecyclerViewAdapter<T> {
//        val adapterNew = object : RecyclerViewAdapter<T>(context!!, getItemLayoutId()) {
//            override fun bindData(vh: Vh<T>, t: T, pos: Int) {
//                super.bindData(vh, t, pos)
//                bindItemData(vh.getBinding() as D, t, pos)
//            }
//        }
//        adapterNew.onItemClickListener = this
//        return adapterNew
//    }
//
//    /* 初始化搜索记录 */
//    private fun initRecord() {
//        binding?.apply {
//            searchTitle?.apply {
//                cancel.setOnClickListener { finish() }
//                clean_search.setOnClickListener {
//                    search_et.setText("")
//                    openRecord()
//                }
//                search_et.setOnClickListener { openRecord() }
//                search_et.setOnEditorActionListener { _, i, _ ->
//                    if (i == EditorInfo.IME_ACTION_SEARCH) {
//                        if (getKeyWord().isEmpty()) {
//                            Toast.makeText(context, "请输入搜索关键字!", Toast.LENGTH_SHORT).show()
//                        } else {
//                            readyToSearch()
//                        }
//                    }
//                    true
//                }
//            }
////            stateView.setState(StateView.State.STATE_EMPTY)
////            stateView.state = StateView.STATE_EMPTY
////            searchRecord = SearchRecord(context!!, search_record_view, recordType, object : OnItemClickListener<SearchRecordModel> {
////                override fun onItemClick(v: View, t: SearchRecordModel, pos: Int) {
////                    binding?.searchTitle?.search_et?.setText(t.keyword)
////                    readyToSearch()
////                }
////
////            })
////            searchRecord.loadRecords()
////            openRecord()
//
//        }
//    }
//
//    /* 开始搜索，UI调用 */
//    private fun readyToSearch() {
////        binding?.stateView?.state = StateView.STATE_LOADING
//////        binding?.stateView?.setState(StateView.State.STATE_LOADING)
//        binding?.searchTitle?.search_et?.text.toString().trim().apply {
//            search(true, 0)
////            searchRecord.addRecord(this)
////            searchRecord.loadRecords()
////            closeRecord()
////            searchRecord.notifyDataSetChanged()
//        }
//    }
//
//    /* 搜索用来刷新数据，由子类实现 如无特殊需求，禁止override */
//    final override fun doLoadData(isRefresh: Boolean, currentSize: Int) {
//        if (getKeyWord().isNotEmpty()) {
//            search(isRefresh, currentSize)
//        } else {
//            loadDataFinish(true, null, true)
//        }
//    }
//
//    /* 空界面 如无特殊需求，禁止override */
////    final override fun instantiateEmptyView(stateView: StateRecyclerView?) {
//////        super.instantiateEmptyView(view)
//////        (view.findViewById(R.id.tv) as TextView).setText(R.string.search_no_data)
//////        (view.findViewById(R.id.iv) as ImageView).setImageResource(R.drawable.state_empty_search)
//////        /* 这里需要优化，因为点击会刷新 */
//////        view.setOnClickListener {}
////
////        stateView!!.showEmptyView(R.string.search_no_data, R.drawable.no_data)
////    }
//
//    /* 关闭搜索记录栏 */
//    private fun closeRecord() {
////        searchRecord.closeRecord()
//        binding?.searchTitle?.search_et?.isFocusable = false
//        hideInput()
//    }
//
//    /* 打开搜索记录栏 */
//    private fun openRecord() {
//        binding?.searchTitle?.search_et?.apply {
//            /* !!!下面四行代码 顺序不能错 也不能删除 */
//            isFocusable = true//1-设置输入框可聚集
//            isFocusableInTouchMode = true//2-设置触摸聚焦
//            requestFocus()//3-请求焦点
//            findFocus()//4-获取焦点
//            showInput(this)
//        }
////        searchRecord.openRecord()
//    }
//
//    /* 需要子类实现的点击搜索后要做的事情 */
//    abstract fun search(isRefresh: Boolean, currentSize: Int)
//
//    protected fun highLightItemKeyword(str: CharSequence, view: TextView) {
//        var index = 0
//        val b = SpannableStringBuilder(str)
//        do {
//            index = str.indexOf(getKeyWord(), index, true)
//            if (index == -1) break
//            val redSpan = ForegroundColorSpan(ContextCompat.getColor(App.getInstance(), R.color.widgetColorPrimary))
//            b.setSpan(redSpan, index, index + getKeyWord().length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//            index++
//        } while (index != -1)
//
//        view.text = b
//    }
//}