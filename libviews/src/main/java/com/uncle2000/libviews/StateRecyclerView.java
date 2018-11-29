//package com.uncle2000.libviews;
//
//
//import android.content.Context;
//import android.content.res.TypedArray;
//import android.support.v7.widget.RecyclerView;
//import android.text.Html;
//import android.util.AttributeSet;
//import android.view.Gravity;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.jcodecraeer.xrecyclerview.XRecyclerView;
//
//import java.util.HashMap;
//
///**
// * Created by danger on
// * 2016/08/23
// * 功能:
// */
//public class StateRecyclerView extends XRecyclerView {
//
//    public static final int STATE_NONE = 1;
//    public static final int STATE_LOADING = 2;
//    public static final int STATE_CONTENT = 3;
//    public static final int STATE_EMPTY = 4;
//    public static final int STATE_FAIL = 5;
//    public Button btn;
//    // View contentView;
//    View otherView;
//    // int layoutIdLoading = R.layout.view_state_loading;
//    int layoutIdContent = R.layout.view_state_recyclerview_content;
//    int layoutIdOther = R.layout.view_state_other;
//    int currentState = STATE_NONE;
//    // int layoutIdFail = R.layout.view_state_fail;
//    // int currentLayoutId = -1;
////    BaseRecyclerViewAdapter adapter;
//    // views in other view
//    View progressBar;
//    TextView tv;
//    TextView tv2;
//
//    ImageView iv;
//    StateChangeListener stateChangeListener;
//    private HashMap<View, Integer> headerHeight = new HashMap<>();
//
//    public StateRecyclerView(Context context) {
//        super(context);
//        init(context, null);
//    }
//
//    public StateRecyclerView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        init(context, attrs);
//    }
//
//    private void init(Context context, AttributeSet attrs) {
//        // LayoutInflater.from(context).inflate(R.layout.view_state, this);
//
//        if (attrs != null) {
//            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.StateView);
//            // int N = a.getIndexCount();
//            // for(int i=0;i<N;i++) {
//            //     int index = a.getIndex(i);
//            //     switch (index) {
//            //         case R.styleable.StateView_contentView:
//            //             a.getResourceId(index, )
//            //     }
//            // }
//            layoutIdOther = a.getResourceId(R.styleable.StateView_otherView, layoutIdOther);
//            layoutIdContent = a.getResourceId(R.styleable.StateView_contentView, layoutIdContent);
//
//            a.recycle();
//        }
//
//        // inflate(getContext(), layoutIdOther, this);
//        // inflate(getContext(), layoutIdContent, this);
//        //
//        // otherView = getChildAt(0);
//        // contentView = getChildAt(0);
//        otherView = inflate(getContext(), layoutIdOther, null);
//        otherView.setLayoutParams(new RecyclerView.LayoutParams(-1, -1));
//        // contentView = inflate(getContext(), layoutIdContent, null);
//        // addView(otherView);
//        // addView(contentView);
//
//        progressBar = otherView.findViewById(R.id.pb);
//        iv = (ImageView) otherView.findViewById(R.id.iv);
//        tv = (TextView) otherView.findViewById(R.id.tv);
//        tv2 = (TextView) otherView.findViewById(R.id.tv_2);
//        btn = (Button) otherView.findViewById(R.id.btn);
//    }
//
//    @Override
//    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
//        super.onSizeChanged(w, h, oldw, oldh);
//
//        computeOtherViewHeight(h);
//    }
//
//    @Override
//    public void setAdapter(RecyclerView.Adapter adapter) {
//        super.setAdapter(adapter);
////        if (adapter instanceof BaseRecyclerViewAdapter) {
////            this.adapter = (BaseRecyclerViewAdapter) adapter;
////        }
//    }
//
//    public void setStateChangeListener(StateChangeListener stateChangeListener) {
//        this.stateChangeListener = stateChangeListener;
//    }
//
//    private void updateHeaderViewHeights(View view) {
//        if (view.getLayoutParams() != null) {
//            // view.getLayoutParams().height = -2;
//        } else {
//            view.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
//        }
//
//        view.measure(0, 0);
//        headerHeight.put(view, view.getMeasuredHeight());
//    }
//
//    @Override
//    public void addHeaderViewAtTop(View view) {
//        super.addHeaderViewAtTop(view);
//        updateHeaderViewHeights(view);
//    }
//
//    @Override
//    public void addHeaderView(View view) {
//        super.addHeaderView(view);
//        if (view != otherView) {
//            updateHeaderViewHeights(view);
//            return;
//        }
//
//        if (getHeight() > 0) {
//            computeOtherViewHeight(getHeight());
//        }
//    }
//
//
//    public void addLongHeaderView(View view) {
//        super.addHeaderView(view);
//        view.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
////        otherView.setLayoutParams(new LayoutParams(-1, -2));
//    }
//
//
//    private void computeOtherViewHeight(int height) {
//        int totalHeaderHeight = 0;
//        for (int heightHeader : headerHeight.values()) {
//            totalHeaderHeight += heightHeader;
//        }
//        if (otherView.getLayoutParams() != null) {
//            otherView.getLayoutParams().height = height - totalHeaderHeight;
//        } else {
//            otherView.setLayoutParams(new RecyclerView.LayoutParams(-1, height - totalHeaderHeight));
//        }
//    }
//
//    public int getState() {
//        return currentState;
//    }
//
//    public void setState(int state) {
//        if (this.currentState == state) {
//            return;
//        }
//        this.currentState = state;
//
//        if (stateChangeListener != null) {
//            stateChangeListener.onStateChange(currentState);
//        }
//        // int layoutId = getLayoutIdByState(state);
//        // if (currentLayoutId == layoutId) {
//        //     return;
//        // }
//        // currentLayoutId = layoutId;
//        // removeAllViews();
//        //
//        // if (currentLayoutId != -1) {
//        //     inflate(getContext(), currentLayoutId, this);
//        // }
//
//        if (state == STATE_CONTENT) {
//            // otherView.setVisibility(GONE);
//
//            // contentView.setVisibility(VISIBLE);
//            removeHeaderView(otherView);
//            setLoadingMoreEnabled(true);
//        } else if (state == STATE_NONE) {
////            if (adapter != null) {
////                adapter.setData(null);
////            }
//            setLoadingMoreEnabled(false);
//            removeHeaderView(otherView);
//            addHeaderView(otherView);
//            if (progressBar != null) progressBar.setVisibility(View.GONE);
//            if (iv != null) tv.setVisibility(View.GONE);
//        } else {
//            // otherView.setVisibility(VISIBLE);
//            // contentView.setVisibility(GONE);
////            if (adapter != null) {
////                adapter.setData(null);
////            }
//            setLoadingMoreEnabled(false);
//            removeHeaderView(otherView);
//            addHeaderView(otherView);
//            if (state == STATE_LOADING) {
//                if (progressBar != null) progressBar.setVisibility(View.VISIBLE);
//                if (iv != null) iv.setVisibility(View.GONE);
//            } else {
//                if (progressBar != null) progressBar.setVisibility(View.GONE);
//                if (iv != null) iv.setVisibility(View.VISIBLE);
//            }
//        }
//
//        if (stateChangeListener != null) {
//            stateChangeListener.onStateChangeFinish(currentState);
//        }
//    }
//
//    // private int getLayoutIdByState(int state) {
//    //     switch (state) {
//    //         case STATE_LOADING:
//    //             return layoutIdLoading;
//    //         case STATE_CONTENT:
//    //             return layoutIdContent;
//    //         case STATE_EMPTY:
//    //             return layoutIdEmpty;
//    //         case STATE_FAIL:
//    //             return layoutIdFail;
//    //     }
//    //     return -1;
//    // }
//
//    private void setData(int text, int img) {
//        if (text <= 0) {
//            setData(null, img);
//        } else {
//            setData(getContext().getAny(text), img);
//        }
//    }
//
//    private void setData(String text, int img) {
//        if (tv != null) {
//            if (text != null) {
//                tv.setVisibility(View.VISIBLE);
//                tv.setGravity(Gravity.CENTER);
//                tv.setText(Html.fromHtml(text));
//            } else {
//                tv.setVisibility(View.GONE);
//            }
//        }
//
//
//        if (iv != null) {
//            if (img > 0) {
//                iv.setImageResource(img);
//            } else {
//                iv.setVisibility(View.GONE);
//            }
//        }
//    }
//
//    public void showContentView() {
//        setState(STATE_CONTENT);
//    }
//
//    public void showEmptyView(String text, int img) {
//        setState(STATE_EMPTY);
//        setData(text, img);
//    }
//
//    public void showEmptyView(int text, int img) {
//        setState(STATE_EMPTY);
//        setData(text, img);
//    }
//
//    public void showLoadingView(String text, int img) {
//        setState(STATE_LOADING);
//        setData(text, img);
//    }
//
//    public void showLoadingView(int text, int img) {
//        setState(STATE_LOADING);
//        setData(text, img);
//    }
//
//    public void showFailView(String text, int img) {
//        setState(STATE_FAIL);
//        setData(text, img);
//    }
//
//    public void showFailView(int text, int img) {
//        setState(STATE_FAIL);
//        setData(text, img);
//    }
//
//    public View getOtherView() {
//        return otherView;
//    }
//
//    public View getIvView() {
//        return iv;
//    }
//
//    public TextView getTv2() {
//        return tv2;
//    }
//
//    public Button getBtn() {
//        return btn;
//
//    }
//
//    public interface StateChangeListener {
//        void onStateChange(int state);
//
//        void onStateChangeFinish(int state);
//    }
//}
