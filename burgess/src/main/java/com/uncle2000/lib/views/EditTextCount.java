package com.uncle2000.lib.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.Size;
import android.support.constraint.ConstraintLayout;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.TextView;
import com.uncle2000.lib.R;


/**
 * 实时显示当前字数的EditText
 * Created by 2000 on 2017/6/2.
 */

public class EditTextCount extends ConstraintLayout {
    public EditText contentTv;
    private TextView countTv;
    private int limit;
    private String hint;
    private float textSize;
    private float textSizeCount;
    private int lineCountType = 1;
    @ColorInt
    private int hintColor;
    @ColorInt
    private int textColor = 0xff666666;

    //ContextCompat.getColor(VApplication.getInstance(), R.color.color_9)
    public EditTextCount(Context context) {
        super(context);
        init(context, null);
    }

    public EditTextCount(Context context, int lineCountType) {
        super(context);
        this.lineCountType = lineCountType;
        init(context, null);
    }


    public EditTextCount(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.EditTextCount);
            if (typedArray.hasValue(R.styleable.EditTextCount_defLines)) {
                lineCountType = typedArray.getInt(R.styleable.EditTextCount_defLines, 1);
            }
            typedArray.recycle();
        }
        if (lineCountType <= 1) {
            LayoutInflater.from(context).inflate(R.layout.count_edittext1, this);
        } else {
            LayoutInflater.from(context).inflate(R.layout.count_edittext2, this);
        }


        contentTv = (EditText) findViewById(R.id.content);
        countTv = (TextView) findViewById(R.id.count);


        contentTv.setMinLines(lineCountType);

        contentTv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    countTv.setTextColor(Color.parseColor("#ff009688"));
                    if (limit > 0)
                        countTv.setText(s.length() + "/" + limit);
                    else
                        countTv.setText("" + s.length());
                } else {
                    countTv.setTextColor(Color.parseColor("#ffbbbbbb"));
                    if (limit > 0)
                        countTv.setText("0/" + limit);
                    else
                        countTv.setText("0");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        initAttrs(context, attrs);
        initParams();
    }


    private void initAttrs(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.EditTextCount);
            if (typedArray.hasValue(R.styleable.EditTextCount_hintContent)) {
                hint = typedArray.getString(R.styleable.EditTextCount_hintContent);
            }
            if (typedArray.hasValue(R.styleable.EditTextCount_hintColor)) {
                hintColor = typedArray.getColor(R.styleable.EditTextCount_hintColor, 0);
            }
            if (typedArray.hasValue(R.styleable.EditTextCount_limit)) {
                limit = typedArray.getInt(R.styleable.EditTextCount_limit, -1);
            }
            if (typedArray.hasValue(R.styleable.EditTextCount_textSizeContent)) {
                textSize = typedArray.getDimensionPixelSize(R.styleable.EditTextCount_textSizeContent, 0);
            }
            if (typedArray.hasValue(R.styleable.EditTextCount_textColorContent)) {
                textColor = typedArray.getColor(R.styleable.EditTextCount_textColorContent, 0);
            }
            if (typedArray.hasValue(R.styleable.EditTextCount_textSizeCount)) {
                textSizeCount = typedArray.getDimension(R.styleable.EditTextCount_textSizeCount, 0);
            }
//            if (typedArray.hasValue(R.styleable.EditTextCount_background))
            {
                findViewById(R.id.root).setBackground(typedArray.getDrawable(R.styleable.EditTextCount_background));
            }

            typedArray.recycle();
        }

    }

    private void initParams() {
        if (!TextUtils.isEmpty(hint)) {
            contentTv.setHint(hint);
        }
        if (limit > 0) {
            contentTv.setFilters(new InputFilter[]{new InputFilter.LengthFilter(limit)});
            countTv.setText("0/" + limit);
        } else {
            countTv.setText("0");
        }
        if (textSize > 0) {
            contentTv.setTextSize(textSize);
        }
        if (textColor > 0) {
            contentTv.setTextColor(textColor);
        }
        if (hintColor > 0) {
            contentTv.setHintTextColor(hintColor);
        }
        if (textSizeCount > 0) {
            countTv.setTextSize(textSizeCount);
        }

        if (contentTv.getText().length() > 0) {
            countTv.setTextColor(Color.parseColor("#ff900c"));
            if (limit > 0)
                countTv.setText(contentTv.getText().length() + "/" + limit);
            else
                countTv.setText("" + contentTv.getText().length());
        }
    }

    public String getContent() {
        return contentTv.getText().toString();
    }

    public void setText(String str) {
        if (null != contentTv) {
            contentTv.setText(str);
            contentTv.setSelection(contentTv.length());
        }
    }

    public void setLimit(@Size(min = 0) int limit) {
        this.limit = limit;
        initParams();
    }
}
