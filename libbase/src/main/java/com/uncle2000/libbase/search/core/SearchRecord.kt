//package com.mll.verification.business.search.core
//
//import android.content.Context
//import android.support.v7.widget.LinearLayoutManager
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import com.jcodecraeer.xrecyclerview.XRecyclerView
//import com.mll.base.ui.BaseRecyclerViewAdapterNew
//import com.mll.base.ui.OnItemClickListener
//import com.mll.utils.DensitiUtil
//import com.mll.verification.R
//import com.mll.verification.db.dao.DB
//import com.mll.verification.db.dao.SearchHistoryDao
//import com.mll.verification.db.model.SearchRecordModel
//import com.uncle2000.libbase.OnItemClickListener
//import com.uncle2000.libbase.R
//
///**
// * wangwei
// * 2018.4.8
// * 搜索历史记录的类
// * 下面简称"面板"
// */
//class SearchRecord(
//        var context: Context,
//        private var searchRecord: XRecyclerView,
//        private var recordType: String? = null,
//        onItemClickListener: OnItemClickListener<SearchRecordModel>? = null
//) {
//    /* 默认recycleView的count最多有5个 */
//    private val recordSize = 5
//    private var recordList: ArrayList<SearchRecordModel>
//    private var recordAdapter: BaseRecyclerViewAdapterNew<SearchRecordModel>
//
//    /* 初始化整个view */
//    init {
//        val cleanRecord: View = LayoutInflater.from(context).inflate(R.layout.search_record_footer, null)
//        cleanRecord.setOnClickListener { cleanRecord() }
//        cleanRecord.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DensitiUtil.dp2px(context, 60F))
//
//        val head = LayoutInflater.from(context).inflate(R.layout.search_record_header, null)
//        head.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DensitiUtil.dp2px(context, 30F))
//
//        recordAdapter = BaseRecyclerViewAdapterNew<SearchRecordModel>(context, R.layout.search_record_item)
//        recordAdapter.onItemClickListener = onItemClickListener
//
//        recordList = recordAdapter.getDataList() as ArrayList<SearchRecordModel>
//
//        searchRecord.apply {
//            setPullRefreshEnabled(false)
//            adapter = recordAdapter
//            layoutManager = LinearLayoutManager(context)
//            setFootView(cleanRecord)
//            addHeaderView(head)
//        }
//    }
//
//    /* 加载历史记录 */
//    fun loadRecords() {
//        val dao = DB.get().session.searchHistoryDao
//        val list = dao.queryBuilder()
//                .where(SearchHistoryDao.Properties.Type.eq(recordType?.hashCode()))
//                .orderDesc(SearchHistoryDao.Properties.Time)
//                .limit(recordSize)
//                .build()
//                .list()
//        recordList.clear()
//        recordList.addAll(list)
//        ifShowRecordPanel()
//    }
//
//    /* 根据历史记录数判断是否显示面板 */
//    private fun ifShowRecordPanel() {
//        if (recordList.size == 0) {
//            searchRecord.visibility = View.GONE
//        } else {
//            searchRecord.visibility = View.VISIBLE
//        }
//    }
//
//    fun notifyDataSetChanged() {
//        recordAdapter.notifyDataSetChanged()
//    }
//
//    /* 添加历史记录 */
//    fun addRecord(keyword: String) {
//        val dao = DB.get().session.searchHistoryDao
//
//        recordList.filter { it.keyword == keyword }.forEach {
//            dao.delete(it)
//            recordList.remove(it)
//        }
//        val entity = SearchRecordModel()
//        entity.type = recordType?.hashCode()
//        entity.keyword = keyword
//        dao.insertOrReplace(entity)
//        recordList.add(0, entity)
//    }
//
//    /*  清除历史记录 */
//    private fun cleanRecord() {
//        closeRecord()
//        val dao = DB.get().session.searchHistoryDao
//        dao.deleteAll()
//        recordList.clear()
//        recordAdapter.notifyDataSetChanged()
//    }
//
//    /* 关闭面板 */
//    fun closeRecord() {
//        searchRecord.visibility = View.GONE
//    }
//
//    /* 打开面板 */
//    fun openRecord() {
//        ifShowRecordPanel()
//    }
//}