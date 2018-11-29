//package com.uncle2000.libbase
//
//import android.content.Context
//import android.databinding.DataBindingUtil
//import android.databinding.ViewDataBinding
//import android.support.annotation.LayoutRes
//import android.support.v7.widget.RecyclerView
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import org.jetbrains.annotations.NotNull
//import java.util.*
//
///**
// * 吊炸天的军哥
// * 公元前3000年
// */
//
//open class BaseRecyclerViewAdapterNew<T> @JvmOverloads constructor(protected var context: Context?, itemLayoutId: Int = 0) : RecyclerView.Adapter<BaseRecyclerViewAdapterNew.Vh<T>>() {
//    private val dataList = ArrayList<T>()
//    private val headerList = ArrayList<Any>()
//    private val footerList = ArrayList<Any>()
//
//    var isShowHeader = true
//    var isShowFooter = true
//
//    private val headerTypes = ArrayList<Int>()
//    private val footerTypes = ArrayList<Int>()
//    private var currentHeaderType = 0
//    private var currentFooterType = 0
//
//    protected var layoutInflater: LayoutInflater
//
//    var onItemClickListener: OnItemClickListener<T>? = null
//    var onItemLongClickListener: OnItemLongClickListener<T>? = null
//
//    @LayoutRes
//    private var itemLayoutId = -1
//
//
//    var data: List<T>?
//        get() = dataList
//        set(data) = setData(data, true)
//
//    val headerCount: Int
//        get() = if (isShowHeader) headerList.size else 0
//
//    val footerCount: Int
//        get() = if (isShowFooter) footerList.size else 0
//
//    init {
//        this.itemLayoutId = itemLayoutId
//
//        layoutInflater = LayoutInflater.from(context)
//    }
//
//    @NotNull
//    @LayoutRes
//    protected open fun getLayoutId(viewType: Int): Int {
//        return itemLayoutId
//    }
//
//    protected open fun bindData(vh: Vh<T>, t: T, pos: Int) {
//        if (onItemClickListener != null) {
//            vh.itemView.setOnClickListener(vh)
//        }
//        if (onItemLongClickListener != null) {
//            vh.itemView.setOnLongClickListener(vh)
//        }
//        vh.bindData(t, pos)
//    }
//
//    protected open fun bindHeader(holder: Vh<T>, position: Int, headerPosition: Int) {
//
//    }
//
//    protected open fun bindFooter(holder: Vh<T>, position: Int, footerPosition: Int) {
//
//    }
//
//    /**
//     * 注意不允许重写些方法，请重写onCreateViewHolder1
//     * if(isHeader(viewType) || isFooter(viewType)) {
//     * return super.onCreateViewHolder(parent, viewType);
//     * }
//     * return xxxxx;
//     */
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh<T> {
//        val vh: Vh<T>
//
//        if (isHeader(viewType)) {
//            val viewOrLayoutId = headerList[headerTypes.indexOf(viewType - VIEW_TYPE_HEADER)]
//            if (viewOrLayoutId is View) {
//                vh = Vh(viewOrLayoutId)
//            } else {
//                vh = Vh(DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, viewOrLayoutId as Int, parent, false))
//            }
//
//            // vh.itemView.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
//        } else if (isFooter(viewType)) {
//            val viewOrLayoutId = footerList[footerTypes.indexOf(viewType - VIEW_TYPE_FOOTER)]
//            if (viewOrLayoutId is View) {
//                vh = Vh(viewOrLayoutId)
//            } else {
//                vh = Vh(DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, viewOrLayoutId as Int, parent, false))
//            }
//
//            // vh.itemView.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
//        } else {
//            // if (getLayoutId(viewType) <= 0) return null;
//            // vh = new Vh(DataBindingUtil.inflate(layoutInflater, getLayoutId(viewType), parent, false));
//            vh = onCreateViewHolder1(parent, viewType)
//        }
//
//        vh!!.viewType = viewType
//        return vh
//    }
//
//    protected open fun onCreateViewHolder1(parent: ViewGroup, viewType: Int): Vh<T> {
//        return Vh(DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, getLayoutId(viewType), parent, false))
//    }
//
//    override fun onBindViewHolder(holder: Vh<T>, position: Int) {
//        val headerCount = headerCount
//
//        holder.headerCount = headerCount
//
//        if (position < headerCount) {
//            bindHeader(holder, position, position)
//        } else if (position < headerCount + dataList.size) {
//            val t = getItem(position - headerCount)
//            if (holder.binding != null)
//                holder.binding!!.setVariable(BR.data, t)
//            holder.onItemClickListener = this.onItemClickListener
//            holder.onItemLongClickListener = this.onItemLongClickListener
//            holder.t = t
//            bindData(holder, t, position - headerCount)
//        } else {
//            bindFooter(holder, position, position - dataList.size - headerCount)
//        }
//
//        // holder.dataBinding.invalidateAll();
//    }
//
//
//    /**
//     * 此方法不允许重写，请重写getItemViewType1
//     * 注意如果重写此方法,写法应该是
//     * int type = super.getItemViewType(position);
//     * if(isHeader(viewType) || isFooter(viewType)) {
//     * return type;
//     * }
//     * return xxxxx;
//     */
//    override fun getItemViewType(position: Int): Int {
//        val headerCount = headerCount
//        return if (position < headerCount) { // 在header范围内
//            VIEW_TYPE_HEADER + headerTypes[position]
//        } else if (position < headerCount + dataList.size) {
//            getItemViewType1(position - headerCount)
//        } else {
//            VIEW_TYPE_FOOTER + footerTypes[position - dataList.size - headerCount]
//        }
//    }
//
//    protected open fun getItemViewType1(position: Int): Int {
//        return VIEW_TYPE_ITEM
//    }
//
//    override fun getItemCount(): Int {
//        return dataList.size + headerCount + footerCount
//    }
//
//    fun setData(data: List<T>?, refresh: Boolean) {
//        this.dataList.clear()
//        if (data != null) {
//            this.dataList.addAll(data)
//        }
//        if (refresh)
//            notifyDataSetChanged()
//    }
//
//    fun getDataList(): List<T> {
//        return dataList
//    }
//
//    fun setItemLayoutId(itemLayoutId: Int) {
//        this.itemLayoutId = itemLayoutId
//    }
//
//    fun setShowHeaderAndFooter(show: Boolean) {
//        val changed = isShowHeader != show || isShowFooter != show
//        if (!changed) return
//        isShowHeader = show
//        isShowFooter = show
//        notifyDataSetChanged()
//    }
//
//    @JvmOverloads
//    fun addItem(t: T, refresh: Boolean = false) {
//        this.dataList.add(t)
//        if (refresh) notifyDataSetChanged()
//    }
//
//    @JvmOverloads
//    fun removeItem(t: T, refresh: Boolean = false) {
//        this.dataList.remove(t)
//        if (refresh) notifyDataSetChanged()
//    }
//
//    fun getItem(position: Int): T {
//        return /*if (position < 0 || position >= dataList.size) null else*/ dataList[position]
//    }
//
//
//    fun addHeader(layoutId: Int) {
//        if (layoutId <= 0) return
//
//        // View headerView = LayoutInflater.from(context).inflate(layoutId, null);
//        // headerView.setTag(layoutId);
//
//        headerTypes.add(++currentHeaderType)
//        headerList.add(layoutId)
//    }
//
//    fun addHeader(headerView: View) {
//        headerTypes.add(++currentHeaderType)
//        headerList.add(headerView)
//    }
//
//    /*public void removeHeader(int layoutId) {
//        for (int i = 0; i < headerList.size(); i++) {
//            if (headerList.get(i).getTag() == layoutId) {
//                removeHeader(headerList.get(i--));
//            }
//        }
//    }
//
//    public void removeHeader(View headerView) {
//        headerList.remove(headerView);
//    }*/
//
//    fun removeAllHeader() {
//        headerList.clear()
//        headerTypes.clear()
//    }
//
//    fun addFooter(layoutId: Int) {
//        if (layoutId <= 0) return
//
//        // View footerView = layoutInflater.inflate(layoutId, null);
//        // footerView.setTag(layoutId);
//        footerTypes.add(++currentFooterType)
//        footerList.add(layoutId)
//    }
//
//    fun addFooter(footerView: View) {
//        footerTypes.add(++currentFooterType)
//        footerList.add(footerView)
//    }
//
//
//    fun isHeader(viewType: Int): Boolean {
//        return 0 != viewType and VIEW_TYPE_HEADER
//    }
//
//    fun isFooter(viewType: Int): Boolean {
//        return 0 != viewType and VIEW_TYPE_FOOTER
//    }
//
//    /*public void removeFooter(View footerView) {
//        footerList.remove(footerView);
//    }
//
//    public void removeFooter(int layoutId) {
//        for (int i = 0; i < footerList.size(); i++) {
//            if (footerList.get(i).getTag() == layoutId) {
//                removeFooter(footerList.get(i--));
//            }
//        }
//    }*/
//
//    fun removeAllFooter() {
//        footerList.clear()
//        footerTypes.clear()
//    }
//
//
//    open class Vh<T> : RecyclerView.ViewHolder, View.OnClickListener, View.OnLongClickListener {
//        internal var binding: ViewDataBinding? = null
//
//        var t: T? = null
//        var viewType: Int = 0
//        var headerCount: Int = 0
//        var onItemClickListener: OnItemClickListener<T>? = null
//        var onItemLongClickListener: OnItemLongClickListener<T>? = null
//
//        constructor(itemView: View) : this(DataBindingUtil.bind<ViewDataBinding>(itemView)!!)
//
//
//        constructor(binding: ViewDataBinding) : super(binding.root) {
//            this.binding = binding
//        }
//
//        constructor(layoutInflater: LayoutInflater, layoutId: Int, parent: ViewGroup) : this(DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, layoutId, parent, false))
//
//
//        fun <T> getBinding(): T {
//            return binding as T
//        }
//
//        override fun onClick(v: View) {
//            if (onItemClickListener != null && t != null) {
//                onItemClickListener!!.onItemClick(v, t!!, position())
//            }
//        }
//
//        open fun bindData(t: T?, pos: Int) {
//
//        }
//
//        fun position(): Int {
//            return adapterPosition - headerCount
//        }
//
//        override fun onLongClick(v: View): Boolean {
//            if (onItemLongClickListener != null) {
//                onItemLongClickListener!!.onItemLongClick(v, t, position())
//            }
//            return true
//        }
//    }
//
//
//    companion object {
//
//        private val VIEW_TYPE_HEADER = 0x10000000
//        private val VIEW_TYPE_FOOTER = 0x20000000
//        private val VIEW_TYPE_ITEM = 0x1
//    }
//
//}
