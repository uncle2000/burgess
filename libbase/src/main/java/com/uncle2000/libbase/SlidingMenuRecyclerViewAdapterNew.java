//package com.uncle2000.libbase;
//
//import android.content.Context;
//import android.databinding.DataBindingUtil;
//import android.databinding.ViewDataBinding;
//import android.support.annotation.LayoutRes;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.uncle2000.libutils.ScreenUtils;
//import com.uncle2000.libbase.databinding.SlidingMenuContainerItemBinding;
//import com.uncle2000.libviews.SlidingButtonView;
//
//
//public abstract class SlidingMenuRecyclerViewAdapterNew<T> extends BaseRecyclerViewAdapterNew<T>
//        implements SlidingButtonView.onMenuChanged {
//    protected RecyclerView recyclerView;
//    /* 注意这个ID 要和layout的对应 */
//    private int slidingMenuResId = R.id.sliding_menu;
//
//    public SlidingMenuRecyclerViewAdapterNew(Context context, RecyclerView recyclerView) {
//        super(context);
//        this.recyclerView = recyclerView;
//        setSlidable();
//    }
//
//    public void setSlidable() {
//        this.recyclerView.removeOnItemTouchListener(itemTouchListener);
//        this.recyclerView.addOnItemTouchListener(itemTouchListener);
//    }
//
//    @Override
//    public Vh<T> onCreateViewHolder1(ViewGroup parent, int viewType) {
//        return new SlidingMenuViewHolder(View.inflate(getContext(), getLayoutId(viewType), null), parent);
//    }
//
//    public class SlidingMenuViewHolder extends Vh<T> {
//        protected ViewDataBinding parentBinding;
//        protected ViewDataBinding contentBinding;
//        protected ViewDataBinding menuBinding;
//
//        public SlidingMenuViewHolder(View itemView, ViewGroup parent) {
//            super(itemView);
//            contentBinding = DataBindingUtil.bind(LayoutInflater.from(getContext()).inflate(getContentViewId(), parent, false));
//            menuBinding = DataBindingUtil.bind(LayoutInflater.from(getContext()).inflate(getMenuViewId(), parent, false));
//            parentBinding = DataBindingUtil.bind(itemView);
//
//            if (parentBinding instanceof SlidingMenuContainerItemBinding) {
//                ((SlidingMenuContainerItemBinding) parentBinding).content.addView(contentBinding.getRoot());
//                ((SlidingMenuContainerItemBinding) parentBinding).menu.addView(menuBinding.getRoot());
//
//                /* 没有这句话 content会变成warp_content */
//                ((SlidingMenuContainerItemBinding) parentBinding).content.getLayoutParams().width = ScreenUtils.getScreenWidth(getContext());
//            }
//        }
//
//        SlidingMenuContainerItemBinding getParentBinding() {
//            return parentBinding instanceof SlidingMenuContainerItemBinding
//                    ? (SlidingMenuContainerItemBinding) parentBinding : null;
//        }
//
//        ViewDataBinding getContentBinding() {
//            return contentBinding;
//        }
//
//        ViewDataBinding getMenuBinding() {
//            return menuBinding;
//        }
//    }
//
//    /* 得到adapter的某个item的 dataBinding */
//    protected SlidingMenuContainerItemBinding getParentBinding(Vh<T> vh) {
//        return ((SlidingMenuViewHolder) vh).getParentBinding();
//    }
//
//    /* 得到adapter的某个item的 content的 dataBinding */
//    protected ViewDataBinding getContentBinding(Vh<T> vh) {
//        return ((SlidingMenuViewHolder) vh).getContentBinding();
//    }
//
//    /* 得到adapter的某个item的 滑出来的binding */
//    protected ViewDataBinding getMenuBinding(Vh<T> vh) {
//        return ((SlidingMenuViewHolder) vh).getMenuBinding();
//    }
//
//    private final RecyclerView.OnItemTouchListener itemTouchListener = new RecyclerView.OnItemTouchListener() {
//        @Override
//        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
//            View item = rv.findChildViewUnder(e.getX(), e.getY()); //finding the view that clicked , using coordinates X and Y
//
//            // 因为有一个是下拉刷新的header,所有-1
//            int position = rv.getChildLayoutPosition(item) - 1; //getting the position of the item inside the list
//
//            int openMenuId = getOpenedPosition() - 1;
//            if (openMenuId >= 0 && position != openMenuId) {
//                closeAllMenu();
//                return true;
//            }
//            return false;
//        }
//
//        @Override
//        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
//
//        }
//
//        @Override
//        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//
//        }
//    };
//
//    private int getOpenedPosition() {
//        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
//        for (int i = 0; i < layoutManager.getChildCount(); i++) {
//            View v = layoutManager.getChildAt(i);
//            if (v != null) {
//                SlidingButtonView sMenu = v.findViewById(slidingMenuResId);
//                if (sMenu != null && sMenu.getOpen()) {
//                    return recyclerView.getChildLayoutPosition(v);
//                }
//            }
//        }
//        return -1;
//    }
//
//    /* 关闭所有的item */
//    public void closeAllMenu() {
//        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
//        int count = layoutManager.getChildCount();
//        for (int i = 0; i < count; i++) {
//            View v = layoutManager.getChildAt(i);
//            if (v != null) {
//                SlidingButtonView sMenu = v.findViewById(slidingMenuResId);
//                if (sMenu != null) {
//                    sMenu.closeMenu();
//                }
//            }
//        }
//    }
//
//    /* 关闭某一个item */
//    protected void closeMenuRightNow(Vh<T> vh) {
//        ((SlidingMenuViewHolder) vh).getParentBinding().slidingMenu.closeMenuRightNow();
//    }
//
//    @Override
//    public void onMenuClosed(int position) {
//
//    }
//
//    @Override
//    public void onMenuOpened(int position) {
//
//    }
//
//    @Override
//    public void onMenuBeginTouch(int position) {
//
//    }
//
//    /* 要重写的话记得 必须包含 R.id.sliding_menu 和 R.id.menu 和 R.id.content */
//    @LayoutRes
//    @Override
//    protected int getLayoutId(int viewType) {
//        return R.layout.sliding_menu_container_item;
//    }
//
//    @LayoutRes
//    protected int getMenuViewId() {
//        return R.layout.view_item_below_view;
//    }
//
//    @LayoutRes
//    protected abstract int getContentViewId();
//
//}
