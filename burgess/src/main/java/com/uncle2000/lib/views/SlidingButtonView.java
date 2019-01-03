package com.uncle2000.lib.views;

/*
  Created by MJJ on 2015/7/25.
 */

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import com.uncle2000.lib.R;

/**
 * 这个是新的滑动删除的view 只有产品展示墙那里在用
 * 解决了3个问题
 * 1 命名“开”“关”相反的问题
 * 2 上下滑动导致卡顿的问题
 * 3 如果文字宽度满屏，水平滑动导致文字换行
 */
public class SlidingButtonView extends HorizontalScrollView {
    private View menuLayout;

    private int position;
    private int menuWidth;

    private onMenuChanged listener;
    private Boolean isOpen = false;
    private Boolean once = false;
    private boolean slidEnable = true;


    public SlidingButtonView(Context context) {
        this(context, null);
    }

    public SlidingButtonView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlidingButtonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setOverScrollMode(OVER_SCROLL_NEVER);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (!once) {
            menuLayout = findViewById(R.id.menu);
            once = true;
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (slidEnable && changed) {
            this.scrollTo(0, 0);
            //获取水平滚动条可以滑动的范围，即右侧按钮的宽度
            menuWidth = menuLayout.getWidth();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (!slidEnable)
            return false;
        if (!slidEnable) return super.onTouchEvent(ev);
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                if (null != listener)
                    listener.onMenuBeginTouch(position);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                changeScrollx();
                return true;
            default:
                break;
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 按滚动条被拖动距离判断关闭或打开菜单
     */
    public void changeScrollx() {
        int scrollX = getScrollX();
        if (scrollX > menuWidth / 2) {
            this.smoothScrollTo(menuWidth, 0);
            isOpen = true;
            if (null != listener)
                listener.onMenuOpened(position);
        } else {
            this.smoothScrollTo(0, 0);
            isOpen = false;
            if (null != listener)
                listener.onMenuClosed(position);
        }
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        /*注释掉下面的代码 右侧的menu区域会一起移动*/
//        if (slidEnable)
//            menuLayout.setTranslationX(l - menuWidth);
    }

    /**
     * 打开菜单
     */
    public void openMenu() {
        if (!slidEnable)
            return;
        if (isOpen) {
            return;
        }
        this.smoothScrollTo(menuWidth, 0);
        isOpen = true;
        if (null != listener)
            listener.onMenuOpened(position);
    }

    /**
     * 关闭菜单
     */
    public void closeMenuRightNow() {
        if (!slidEnable)
            return;
        if (!isOpen) {
            return;
        }
        /*scrollTo会出现一个问题：有时候会关不住*/
        this.scrollTo(0, 0);
        isOpen = false;
        if (null != listener)
            listener.onMenuClosed(position);
    }

    public void closeMenu() {
        if (!slidEnable)
            return;
        if (!isOpen) {
            return;
        }
        /*smoothScrollTo会出现一个问题：当手指在被打开的item上下拖动list的时候 会失效*/
        this.smoothScrollTo(0, 0);
        isOpen = false;
        if (null != listener)
            listener.onMenuClosed(position);
    }

    public void setSlidEnable(boolean slidEnable) {
        this.slidEnable = slidEnable;
    }

    public void setonMenuChangedListener(onMenuChanged listener) {
        this.listener = listener;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Boolean getOpen() {
        return isOpen;
    }

    public interface onMenuChanged {
        void onMenuClosed(int position);

        void onMenuOpened(int position);

        void onMenuBeginTouch(int position);
    }

}
