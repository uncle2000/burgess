package com.uncle2000.libviews;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by xiao on 2016/6/21.
 * 功能:
 */
public class TitleView extends FrameLayout implements View.OnClickListener {


    private static final String TAG = "TitleView";

    protected TextView title_tv, title_right_tv, title_left_tv;

    protected TextView tvSubTitle;
    protected ImageView title_left_iv, title_right_iv, title_right_iv2;

    private View title_left_fl, title_right_fl;

    private boolean needFitStatusBar = true;
    private boolean haveFitStatusBar = false;

    public TitleView(Context context) {
        super(context);
        init(context, null);
    }

    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    /**
     * It's good for viewgroup,not for view.U can put view in framelayout.
     */
    public static void setViewPadding(Context context, View... views) {

        //        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
        //            return;
        //        }
        //        if (Configure.barHeight == 0) {
        //            new getStatusBarHeight().getStatusBarHeight(context);
        //        }
        //        //		if(null==views || views.length==0){
        //        //
        //        //		}
        //        for (View v : views) {
        //            if (null == v)
        //                continue;
        //            v.setPadding(0, Configure.barHeight, 0, 0);
        //        }
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.view_title_bar, this);

        title_tv = findViewById(R.id.title_tv);
        tvSubTitle = findViewById(R.id.sub_title_tv);
        title_left_iv = findViewById(R.id.title_left_iv);
        title_right_iv = findViewById(R.id.title_right_iv);
        title_right_tv = findViewById(R.id.title_right_tv);
        title_left_tv = findViewById(R.id.title_left_tv);
        title_right_iv2 = findViewById(R.id.title_right_iv2);
        title_left_fl = findViewById(R.id.title_left_fl);
        title_right_fl = findViewById(R.id.title_right_fl);

        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TitleView);

            String leftText = null; //  = a.getAny(R.styleable.TitleView_leftText);
            String rightText = null; // = a.getAny(R.styleable.TitleView_rightText);
            String titleText = null; //  = a.getAny(R.styleable.TitleView_titleText);
            String subTitleText = null; // = a.getAny(R.styleable.TitleView_subTitleText);
            Drawable leftDrawable = ContextCompat.getDrawable(getContext(), R.drawable.svg_arrow_left); // = a.getDrawable(R.styleable.TitleView_leftDrawable);
            Drawable rightDrawable = null; // = a.getDrawable(R.styleable.TitleView_rightDrawable);
            Drawable rightDrawable2 = null; // = a.getDrawable(R.styleable.TitleView_rightDrawable);

            boolean showDivider = true;
            final int N = a.getIndexCount();
            for (int i = 0; i < N; i++) {
                int attr = a.getIndex(i);
                if (attr == R.styleable.TitleView_leftText) {
                    leftText = a.getString(attr);

                } else if (attr == R.styleable.TitleView_rightText) {
                    rightText = a.getString(attr);

                } else if (attr == R.styleable.TitleView_titleText) {
                    titleText = a.getString(attr);

                } else if (attr == R.styleable.TitleView_subTitleText) {
                    subTitleText = a.getString(attr);

                } else if (attr == R.styleable.TitleView_leftDrawable) {
                    leftDrawable = a.getDrawable(attr);

                } else if (attr == R.styleable.TitleView_rightDrawable) {
                    rightDrawable = a.getDrawable(attr);

                } else if (attr == R.styleable.TitleView_rightDrawable2) {
                    rightDrawable2 = a.getDrawable(attr);

                } else if (attr == R.styleable.TitleView_showBottomDivider) {
                    showDivider = a.getBoolean(attr, showDivider);
                } else if (attr == R.styleable.TitleView_needFitStatusBar) {
                    needFitStatusBar = a.getBoolean(attr, needFitStatusBar);
                }
            }
            a.recycle();

            setLeftText(leftText);
            setRightText(rightText);
            setTitleText(titleText);
            setImageDrawable(title_left_iv, leftDrawable);
            setImageDrawable(title_right_iv, rightDrawable);
            setImageDrawable(title_right_iv2, rightDrawable2);

            if (!showDivider) findViewById(R.id.divider).setVisibility(View.GONE);
            setSubTitle(subTitleText);

        }

        // ClickableImageTouchListener.addTouchDrak(title_left_iv);
        setLeftOnClickListener(this);

        setViewPadding(getContext(), this);

        // boolean fit = false;
        // if (Build.VERSION.SDK_INT > 16) {
        //     fit = getFitsSystemWindows();
        // } else {
        //     // fit = fitsSystemWindows();
        // }
        // if (fit)
        // setPadding(0, StatusBarTool.getStatusBarHeight(getContext()), 0, 0);
    }

    private void setImageDrawable(ImageView iv, Drawable drawable) {
        if (drawable != null) {
            iv.setImageDrawable(drawable);
            iv.setVisibility(VISIBLE);
        } else {
            iv.setVisibility(View.GONE);
        }
    }

    public void setLeftText(String text) {
        if (TextUtils.isEmpty(text)) {
            title_left_tv.setVisibility(GONE);
            return;
        }
        title_left_tv.setVisibility(VISIBLE);
        title_left_tv.setText(text);
    }

    public void setLeftText(int res) {
        if (res <= 0) {
            title_left_tv.setVisibility(GONE);
            return;
        }
        title_left_tv.setVisibility(VISIBLE);
        title_left_tv.setText(res);
    }

    public void setRightText(String text) {
        if (TextUtils.isEmpty(text)) {
            title_right_tv.setVisibility(GONE);
            return;
        }

        title_right_tv.setVisibility(VISIBLE);
        title_right_tv.setText(text);
    }

    public void setRightText(int res) {
        if (res <= 0) {
            title_right_tv.setVisibility(GONE);
            return;
        }
        title_right_tv.setVisibility(VISIBLE);
        title_right_tv.setText(res);
    }

    public void setTitleText(String text) {
        if (text != null) {
            title_tv.setText(text);
        } else {
            title_tv.setText("");
        }
    }

    public void setTitleText(int res) {
        title_tv.setText(res);
    }

    public void setSubTitle(int res) {
        if (res <= 0) {
            tvSubTitle.setVisibility(View.GONE);
        } else {
            tvSubTitle.setVisibility(View.VISIBLE);
            tvSubTitle.setText(res);
        }
    }

    public void setSubTitle(CharSequence txt) {
        if (TextUtils.isEmpty(txt)) {
            tvSubTitle.setVisibility(View.GONE);
        } else {
            tvSubTitle.setVisibility(View.VISIBLE);
            tvSubTitle.setText(txt);
        }
    }

    public void setLeftDrawable(int res) {
        if (res <= 0) {
            title_left_iv.setVisibility(View.GONE);
            title_left_iv.setImageDrawable(null);
        } else {
            title_left_iv.setVisibility(View.VISIBLE);
            title_left_iv.setImageResource(res);
        }
    }

    public void setRightDrawable(int res) {
        if (res > 0) {
            title_right_iv.setVisibility(View.VISIBLE);
            title_right_iv.setImageResource(res);
        } else {
            title_right_iv.setVisibility(View.GONE);
        }
    }

    public void setLeftDrawableVisible(boolean visible) {
        if (visible) {
            title_left_iv.setVisibility(VISIBLE);
        } else {
            title_left_iv.setVisibility(GONE);
        }
    }

    public void setRightDrawableVisible(boolean visible) {
        if (visible) {
            title_right_iv.setVisibility(VISIBLE);
        } else {
            title_right_iv.setVisibility(INVISIBLE);
        }
    }

    public void setRightTextVisible(boolean visible) {
        if (visible) {
            title_right_tv.setVisibility(VISIBLE);
        } else {
            title_right_tv.setVisibility(GONE);
        }
    }

    public void setLeftOnClickListener(OnClickListener listener) {
        title_left_fl.setOnClickListener(listener);
    }

    public void setRightOnClickListener(OnClickListener listener) {
        title_right_fl.setOnClickListener(listener);
    }

    public void setRightOnClickListener2(OnClickListener listener) {
        title_right_iv2.setOnClickListener(listener);
    }


    @Override
    public void onClick(View v) {

        if (v == title_left_fl) {
            // 内容已经全部被隐藏
            if (title_left_iv.getVisibility() != VISIBLE && title_left_tv.getVisibility() != VISIBLE) {
                return;
            }
            if (getContext() instanceof Activity) {
                ((Activity) getContext()).onBackPressed();
            }
        }
    }

    public void setRightIcon2(int i) {
        if (i <= 0) {
            title_right_iv2.setVisibility(View.GONE);
        } else {
            title_right_iv2.setVisibility(View.VISIBLE);
            title_right_iv2.setImageResource(i);
        }
    }

    public void setRightIcon2Visible(boolean visible) {
        title_right_iv2.setVisibility(visible ? VISIBLE : GONE);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

//        if (needFitStatusBar) {
//            int statusBarHeight = StatusBarTool.getStatusBarHeight(getContext());
//            int[] loc = new int[2];
//            getLocationOnScreen(loc);
//            int y = loc[1];
//
//            int requiredPaddingTop = y == 0 ? statusBarHeight : 0;
//            if (getPaddingTop() != requiredPaddingTop) {
//                post(() -> {
//                    setPadding(getPaddingLeft(), requiredPaddingTop, getPaddingRight(), getPaddingBottom());
//
//                });
//            }
//        }
    }
}
