//package com.uncle2000.lib.views;
//
//import android.content.Context;
//import android.content.res.TypedArray;
//import android.util.AttributeSet;
//import android.view.View;
//import android.widget.FrameLayout;
//import android.widget.ImageView;
//import android.widget.TextView;
//import com.uncle2000.lib.R;
//
//
///**
// * Created by danger on
// * 2016/08/23
// * 功能:
// */
//public class StateView extends FrameLayout {
//
//    public static final int STATE_NONE = 1;
//    public static final int STATE_LOADING = 2;
//    public static final int STATE_CONTENT = 3;
//    public static final int STATE_EMPTY = 4;
//    public static final int STATE_FAIL = 5;
//    public static final int STATE_NO_NETWORK = 6;
//    View contentView;
//    View otherView;
//    // int layoutIdLoading = R.layout.view_state_loading;
//    int layoutIdContent = R.layout.view_state_recyclerview_content;
//    int layoutIdOther = R.layout.view_state_other;
//    int currentState = STATE_NONE;
//    // int layoutIdFail = R.layout.view_state_fail;
//    // views in other view
//    View progressBar;
//
//    // int currentLayoutId = -1;
//    TextView tv;
//    ImageView iv;
//    StateChangeListener stateChangeListener;
//
//    public StateView(Context context) {
//        super(context);
//        init(context, null);
//    }
//
//    public StateView(Context context, AttributeSet attrs) {
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
//        contentView = inflate(getContext(), layoutIdContent, null);
//        addView(otherView);
//        addView(contentView);
//
//        progressBar = findViewById(R.id.pb);
//        iv = (ImageView) findViewById(R.id.iv);
//        tv = (TextView) findViewById(R.id.tv);
//    }
//
//    public void setStateChangeListener(StateChangeListener stateChangeListener) {
//        this.stateChangeListener = stateChangeListener;
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
//            otherView.setVisibility(GONE);
//            contentView.setVisibility(VISIBLE);
//        } else {
//            otherView.setVisibility(VISIBLE);
//            contentView.setVisibility(GONE);
//
//            if (state == STATE_LOADING) {
//                progressBar.setVisibility(View.VISIBLE);
//                iv.setVisibility(View.GONE);
//            } else {
//                progressBar.setVisibility(View.GONE);
//                iv.setVisibility(View.VISIBLE);
//            }
//        }
//
//        if (stateChangeListener != null) {
//            stateChangeListener.onStateChange(currentState);
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
//            setData(getContext().getString(text), img);
//        }
//    }
//
//    private void setData(String text, int img) {
//        if (tv != null) {
//            if (text != null) {
//                tv.setVisibility(View.VISIBLE);
//                tv.setText(text);
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
//    /**
//     * 默认空界面
//     */
//    public void showDefaltEmptyView() {
//        showEmptyView("这里什么都没有~", R.drawable.no_data);
//    }
//
//    /**
//     * 默认失败界面
//     */
//    public void showDefaltFailView() {
////        if (State.netState == 1) {
////            showDefaltNoNetworkView();
////        } else {
//        showFailView("服务器在偷懒,点击空白处刷新", R.drawable.no_data);
////        }
//    }
//
//    /**
//     * 默认无网络界面
//     */
//    public void showDefaltNoNetworkView() {
//        setState(STATE_NO_NETWORK);
//        setData("网络连接差,请检查手机网络", R.drawable.no_net);
//    }
//
//    public interface StateChangeListener {
//        void onStateChange(int state);
//    }
//}
