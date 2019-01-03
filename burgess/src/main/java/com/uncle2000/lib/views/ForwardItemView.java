package com.uncle2000.lib.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.uncle2000.lib.R;


/**
 * Created by xiao on 2016/6/22.
 * 功能: 可点击下一步的itemview
 */
public class ForwardItemView extends RelativeLayout {

    private TextView leftTv;
    public TextView rightTv;
    public View divider;

    public ForwardItemView(Context context) {
        super(context);
        init(context, null);
    }

    public ForwardItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs) {

        LayoutInflater.from(context).inflate(R.layout.forward_item, this);

        leftTv = findViewById(R.id.left_tv);
        rightTv = findViewById(R.id.right_tv);
        divider = findViewById(R.id.divider);
        setBackgroundResource(R.drawable.fillet_white_bg_no_boder);
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ForwardItemView);

            String leftText = a.getString(R.styleable.ForwardItemView_leftItemText);
            String rightText = a.getString(R.styleable.ForwardItemView_rightItemText);
            String rightTextHint = a.getString(R.styleable.ForwardItemView_rightItemTextHint);
            int leftDrawable = a.getResourceId(R.styleable.ForwardItemView_leftItemDrawable, 0);
            int rightDrawable = a.getResourceId(R.styleable.ForwardItemView_rightItemDrawable, R.drawable.svg_arrow_right);
            boolean showDivider = a.getBoolean(R.styleable.ForwardItemView_showDivider, true);
            boolean showRightDrawable = a.getBoolean(R.styleable.ForwardItemView_showRightDrawable, true);


            if (leftText != null) {
                leftTv.setText(leftText);
            }
            if (rightText != null) {
                rightTv.setText(rightText);
            }
            if (rightTextHint != null) {
                rightTv.setHint(rightTextHint);
            }

            /*if (leftDrawable != null)*/
            {
                setLeftDrawable(leftDrawable);
            }
            /*if (rightDrawable != null)*/
            if (showRightDrawable) {
                setRightDrawable(rightDrawable);
            } else {
                setRightDrawable(0);
            }
            if (showDivider) {
                divider.setVisibility(VISIBLE);
            } else {
                divider.setVisibility(GONE);
            }

            a.recycle();
        }

    }


    public void showDot(boolean show) {
        findViewById(R.id.dot).setVisibility(show ? VISIBLE : GONE);
    }

    public void setLeftText(String text) {
        leftTv.setText(text);
    }

    public void setLeftText(int res) {
        leftTv.setText(res);
    }

    public void setRightText(String text) {
        rightTv.setText(text);
    }

    public void setRightText(int res) {
        rightTv.setText(res);
    }

    public void setLeftDrawable(int drawable) {

        leftTv.setCompoundDrawablesWithIntrinsicBounds(drawable, 0, 0, 0);
    }

    public void setRightDrawable(int drawable) {
        rightTv.setCompoundDrawablesWithIntrinsicBounds(0, 0, drawable, 0);
    }

    public void setLeftVisible(boolean visible) {
        if (visible) {
            leftTv.setVisibility(VISIBLE);
        } else {
            leftTv.setVisibility(INVISIBLE);
        }
    }

    public void setRightVisible(boolean visible) {
        if (visible) {
            rightTv.setVisibility(VISIBLE);
        } else {
            rightTv.setVisibility(INVISIBLE);
        }
    }
}
