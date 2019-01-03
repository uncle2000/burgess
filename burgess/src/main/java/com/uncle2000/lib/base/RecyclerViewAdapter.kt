//package com.uncle2000.lib.base
//
//import android.content.Context
//import android.databinding.DataBindingUtil
//import android.databinding.ViewDataBinding
//import android.support.annotation.LayoutRes
//import android.support.v7.widget.RecyclerView
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import com.uncle2000.lib.BR
//import org.jetbrains.annotations.NotNull
//
//open class RecyclerViewAdapter<T>(
//        var context: Context,
//        @LayoutRes itemLayoutId: Int = 0,
//        headLayoutId: Int = 0,
//        footLayoutId: Int = 0) : RecyclerView.Adapter<Vh<T>>() {
//    private var state: Int = STATE_CONTENT
//    @LayoutRes
//    private var itemLayoutId = 0
//    private var headLayoutId = 0
//    private var footLayoutId = 0
//    private var hasHead: Boolean = false
//    private var hasFoot: Boolean = false
//    private var headView: View? = null
//    private var footView: View? = null
//    var phView: ViewGroup? = null
//    var data: List<T>? = null
//    var headHeight = 0
//    var footHeight = 0
//
//    var onItemClickListener: OnItemClickListener<T>? = null
////    fun setOnItemClickListener(onItemClickListener: OnItemClickListener<T>?): RecyclerViewAdapter<T> {
////        this.onItemClickListener = onItemClickListener
////        return this
////    }
//
//    var onItemLongClickListener: OnItemLongClickListener<T>? = null
//
//    constructor(context: Context) : this(context, 0, 0, 0)
//    constructor(context: Context, @LayoutRes itemLayoutId: Int = 0) : this(context, itemLayoutId, 0, 0)
//
//    init {
//        this.itemLayoutId = itemLayoutId
//        if (headLayoutId > 0)
//            this.headLayoutId = headLayoutId
//        else
//            this.headLayoutId = getHeadLayoutId()
//
//        if (footLayoutId > 0)
//            this.footLayoutId = footLayoutId
//        else
//            this.footLayoutId = getFootLayoutId()
//        if (this.headLayoutId > 0) {
//            hasHead = true
//            headView = View.inflate(context, this.headLayoutId, null)
//            headView?.layoutParams = RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
//            headView?.measure(0, 0)
//            headHeight = headView!!.measuredHeight
//        }
//        if (this.footLayoutId > 0) {
//            hasFoot = true
//            footView = View.inflate(context, this.footLayoutId, null)
//            footView?.layoutParams = RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
//            footView?.measure(0, 0)
//            footHeight = footView!!.measuredHeight
//        }
//    }
//
//    fun changeState(state: Int) {
//        this.state = state
//        notifyDataSetChanged()
//    }
//
//
//    fun getItem(position: Int): T {
//        if (hasHead)
//            return data!![position - 1]
//        return data!![position]
//    }
//
//    protected open fun bindData(vh: Vh<T>, t: T, pos: Int) {
//        if (vh is ContentVh) {
//            vh.onItemClickListener = this.onItemClickListener
//            vh.onItemLongClickListener = this.onItemLongClickListener
//            if (onItemClickListener != null) {
//                vh.itemView.setOnClickListener(vh)
//            }
//            if (onItemLongClickListener != null) {
//                vh.itemView.setOnLongClickListener(vh)
//            }
//            vh.bindData(t, pos)
//        }
//    }
//
//    protected open fun bindHeader(vh: Vh<T>) {
//        (vh as HeadVh).bindData()
//    }
//
//    protected open fun bindFooter(vh: Vh<T>) {
//        (vh as FootVh).bindData()
//    }
//
//    protected open fun bindPlaceholder(vh: Vh<T>) {
//        (vh as PlaceholderVh).bindData()
//    }
//
//    /* 重写则不会再有head和foot */
//    final override fun getItemViewType(position: Int): Int {
//        if (state > 0) {
//            if (hasHead && hasFoot) {
//                return when (position) {
//                    0 -> HEADS
//                    1 -> PLACEHOLDER
//                    else -> {
//                        FOOTS
//                    }
//                }
//            } else if (hasHead || hasFoot) {
//                return if (hasHead && position == 0) {
//                    HEADS
//                } else if (hasFoot && position == 1) {
//                    FOOTS
//                } else {
//                    PLACEHOLDER
//                }
//            } else
//                return PLACEHOLDER
//        } else {
//            if (hasHead && hasFoot) {
//                return when (position) {
//                    0 -> HEADS
//                    data?.size?.plus(1) -> FOOTS
//                    else -> {
//                        getContentItemType(position - 1)
//                    }
//                }
//            } else if (hasFoot || hasHead) {
//                return if (hasHead && position == 0) {
//                    HEADS
//                } else if (hasFoot && position == data?.size) {
//                    FOOTS
//                } else {
//                    getContentItemType(position)//TODO 之前是-2
//                }
//            } else
//                return getContentItemType(position)
//        }
//    }
//
//    /* 重写这个方法然后可以写返回你们自己的TYPE */
//    open fun getContentItemType(position: Int): Int {
//        return CONTENT
//    }
//
//    /* 不需要重写 */
//    final override fun getItemCount(): Int {
//        if (state > 0) {
//            return if (hasHead && hasFoot)
//                3
//            else if (hasHead || hasFoot)
//                2
//            else
//                1
//        } else {
//            if (data != null) {
//                return if (hasHead && hasFoot)
//                    data!!.size + 2
//                else if (hasHead || hasFoot)
//                    data!!.size + 1
//                else
//                    data!!.size
//            }
//            return 0
//        }
//    }
//
//    /* 其他type一律继承ContentVh */
//    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh<T> {
//        return when (viewType) {
//            PLACEHOLDER -> PlaceholderVh(
//                phView
//            )
//            HEADS -> HeadVh(headView)
//            FOOTS -> FootVh(footView)
//            else -> onCreateContentViewHolder(parent, viewType)
//        }
//    }
//
//    open fun onCreateContentViewHolder(parent: ViewGroup, viewType: Int): ContentVh<T> {
//        return ContentVh(
//            DataBindingUtil.inflate<ViewDataBinding>(
//                LayoutInflater.from(context),
//                getLayoutId(viewType),
//                parent,
//                false
//            )
//        )
//    }
//
//    override fun onBindViewHolder(holder: Vh<T>, position: Int) {
//        when (holder) {
//            is PlaceholderVh -> bindPlaceholder(holder)
//            is HeadVh -> bindHeader(holder)
//            is FootVh -> bindFooter(holder)
//            is ContentVh -> {
//                val t = getItem(position)
////                holder.dataBinding.setVariable(BR.data, t)
//                var pos = position
//                if (hasHead) {
//                    pos--
//                    holder.headerCount = 1
//                }
////                holder.t = getItem(position)
//                bindData(holder, getItem(position), pos)
//            }
//        }
//    }
//
//    companion object {
//        const val HEADS = 4444
//        const val CONTENT = 666666
//        const val FOOTS = 55555
//        const val PLACEHOLDER = 99999
//        //...
//    }
//
//    @NotNull
//    @LayoutRes
//    protected open fun getLayoutId(viewType: Int): Int {
//        return itemLayoutId
//    }
//
//
//    @NotNull
//    protected open fun getHeadLayoutId(): Int {
//        return headLayoutId
//    }
//
//    @NotNull
//    protected open fun getFootLayoutId(): Int {
//        return footLayoutId
//    }
//
//}
//
////vh类------------------------------------------------------------------------------
//open class Vh<T> : RecyclerView.ViewHolder {
//    var dataBinding: ViewDataBinding
//    var onItemClickListener: OnItemClickListener<T>? = null
//    var onItemLongClickListener: OnItemLongClickListener<T>? = null
//
//    constructor(binding: ViewDataBinding) : super(binding.root) {
//        this.dataBinding = binding
//    }
//
//    constructor(itemView: View) : this(DataBindingUtil.bind<ViewDataBinding>(itemView)!!)
//
//    public fun <B : ViewDataBinding> getBinding() = dataBinding as B
//}
//
//open class ContentVh<T> : Vh<T>, View.OnClickListener, View.OnLongClickListener {
//
//    constructor(binding: ViewDataBinding) : super(binding.root) {
//        this.dataBinding = binding
//    }
//
//    constructor(itemView: View) : this(DataBindingUtil.bind<ViewDataBinding>(itemView)!!)
//
//    constructor(context: Context, itemViewLayoutId: Int, parent: ViewGroup? = null) : this(DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(context), itemViewLayoutId, parent, false))
//
//    internal var contentBinding: ViewDataBinding = dataBinding
//    var headerCount: Int = 0
//    var t: T? = null
//    open fun bindData(t: T?, pos: Int) {
//        this.t = t
//    }
//
//    override fun onClick(v: View) {
//        if (onItemClickListener != null && t != null) {
//            onItemClickListener!!.onItemClick(v, t!!, position())
//        }
//    }
//
//    override fun onLongClick(v: View): Boolean {
//        if (onItemLongClickListener != null) {
//            onItemLongClickListener!!.onItemLongClick(v, t, position())
//        }
//        return true
//    }
//
//    fun position(): Int {
//        return adapterPosition - headerCount
//    }
//}
//
//open class PlaceholderVh<T>(binding: ViewDataBinding) : Vh<T>(binding.root) {
//    internal var stateBinding: ViewDataBinding = binding
//
//    constructor(itemView: View?) : this(DataBindingUtil.bind<ViewDataBinding>(itemView!!)!!)
//
//    open fun bindData() {
//
//    }
//}
//
//open class HeadVh<T>(binding: ViewDataBinding) : Vh<T>(binding.root) {
//    var headBinding: ViewDataBinding = binding
//
//    constructor(itemView: View?) : this(DataBindingUtil.bind<ViewDataBinding>(itemView!!)!!)
//
//    open fun bindData() {
//    }
//
//}
//
//open class FootVh<T>(binding: ViewDataBinding) : Vh<T>(binding.root) {
//    internal var footBinding: ViewDataBinding = binding
//
//    constructor(itemView: View?) : this(DataBindingUtil.bind<ViewDataBinding>(itemView!!)!!)
//
//    open fun bindData() {
//
//    }
//}
//
