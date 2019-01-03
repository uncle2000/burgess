//package com.uncle2000.libviews;
//
//import android.content.Context;
//import android.content.res.TypedArray;
//import android.databinding.DataBindingUtil;
//import android.support.annotation.ColorInt;
//import android.text.Editable;
//import android.text.InputFilter;
//import android.text.TextUtils;
//import android.text.TextWatcher;
//import android.util.AttributeSet;
//import android.util.TypedValue;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.inputmethod.EditorInfo;
//import android.widget.EditText;
//import android.widget.FrameLayout;
//
//import com.uncle2000.libutils.ToolUtil;
//import com.uncle2000.libviews.databinding.CleanEdittextBinding;
//
//
///**
// * Created by 2000
// */
//
//public class CleanEditText extends FrameLayout {
//    private String hint = "";
//    private int hintColor = 0;
//    @ColorInt
//    private int textSize = 0;
//    @ColorInt
//    private int textColor = 0;
//    private int maxLength = -1;
//    private boolean hideDelBtn = false;
//    private boolean isNumber = false;
//    private boolean isPassword = false;
//    private boolean isDarkBackground = false;
//    private boolean noBackground;
//    private boolean forbidSpecialCharacter = false;
//    private int minLimit;
//    private boolean isOverLimit;
//
//    public CleanEdittextBinding binding;
//
//    public CleanEditText(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        init(context, attrs);
//    }
//
//    public CleanEditText(Context context) {
//        super(context);
//        init(context, null);
//    }
//
//    private void init(Context context, AttributeSet attrs) {
//        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.clean_edittext, this, true);
//
//        /*放到这里 以便设置编辑框前可以得到参数*/
//        initAttrs(context, attrs);
//
//        binding.del.setOnClickListener(v -> {
//            binding.editText.setText("");
//            binding.editText.requestFocus();
//        });
//
//        binding.editText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (!isNumber && forbidSpecialCharacter) {
//                    String tmp = s.toString();
//                    String temp1 = ToolUtil.clearSpecialChar(tmp); // 禁止输入特殊符号
//                    if (!TextUtils.equals(temp1, tmp)) {
//                        binding.editText.removeTextChangedListener(this);
//                        binding.editText.setText(temp1);
//                        binding.editText.setSelection(temp1.length());
//                        binding.editText.addTextChangedListener(this);
//                    }
//                }
//                if (hideDelBtn || TextUtils.isEmpty(s)) {
//                    binding.del.setVisibility(GONE);
//                } else {
//                    binding.del.setVisibility(VISIBLE);
//                }
//                if (minLimit > 0) {
//                    if (binding.editText.getText().length() >= minLimit) {
//                        setOverLimit(true);
//                    } else {
//                        setOverLimit(false);
//                    }
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//    }
//
//    private void initAttrs(Context context, AttributeSet attrs) {
//        if (attrs != null) {
//            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CleanEditText);
//
//            hint = typedArray.getString(R.styleable.CleanEditText_hint);
//            hintColor = typedArray.getColor(R.styleable.CleanEditText_hintColor, hintColor);
//
//            textSize = typedArray.getDimensionPixelSize(R.styleable.CleanEditText_textSize, textSize);
//            textColor = typedArray.getColor(R.styleable.CleanEditText_textColor, textColor);
//
//            maxLength = typedArray.getInt(R.styleable.CleanEditText_maxLength, maxLength);
//
//            isNumber = typedArray.getBoolean(R.styleable.CleanEditText_isNumber, isNumber);
//            isPassword = typedArray.getBoolean(R.styleable.CleanEditText_isPassword, isPassword);
//            hideDelBtn = typedArray.getBoolean(R.styleable.CleanEditText_hideDelBtn, hideDelBtn);
//            noBackground = typedArray.getBoolean(R.styleable.CleanEditText_noBackground, false);
//            isDarkBackground = typedArray.getBoolean(R.styleable.CleanEditText_isDarkBackground, isDarkBackground);
//            forbidSpecialCharacter = typedArray.getBoolean(R.styleable.CleanEditText_forbidSpecialCharacter, forbidSpecialCharacter);
//
//            typedArray.recycle();
//        }
//
//        if (!TextUtils.isEmpty(hint)) {
//            setHint(hint);
//        }
//        if (hintColor != 0) {
//            binding.editText.setTextColor(hintColor);
//        }
//        if (textSize > 0) {
//            binding.editText.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
//        }
//        if (textColor != 0) {
//            binding.editText.setTextColor(textColor);
//        }
//        if (maxLength > 0) {
//            setMaxLength(maxLength);
//        }
//
//        setIsNumber(isNumber);
//
//        if (isPassword) {
//            binding.editText.setInputType(EditorInfo.TYPE_CLASS_TEXT | EditorInfo.TYPE_TEXT_VARIATION_PASSWORD);
//        }
//        if (hideDelBtn) setDelBtnGone(true);
//        if (isDarkBackground) {
//            binding.getRoot().setBackgroundResource(R.drawable.fillet_gray_bg);
//        }
//        if (noBackground) {
//            binding.getRoot().setBackgroundResource(0);
//        }
//    }
//
//    public void setHint(String hint) {
//        binding.editText.setHint(hint);
//    }
//
//    public void setMaxLength(int maxLength) {
//        binding.editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
//    }
//
//
//    public String getContent() {
//        return binding.editText.getText().toString();
//    }
//
//    public int getLength() {
//        return binding.editText.getText().toString().length();
//    }
//
//    public EditText getEditText() {
//        return binding.editText;
//    }
//
//    public void setText(String str) {
//        binding.editText.setText(str);
//    }
//
//    public void setIsNumber(boolean isNumber) {
//        this.isNumber = isNumber;
//        if (isNumber)
//            binding.editText.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
//    }
//
//    public void setDelBtnGone(boolean isGone) {
//        hideDelBtn = isGone;
//        binding.del.setVisibility(isGone ? View.GONE : View.VISIBLE);
//    }
//
//    public void setForbidSpecialCharacter(boolean forbidSpecialCharacter) {
//        this.forbidSpecialCharacter = forbidSpecialCharacter;
//    }
//
//    public void setMinLimit(int minLimit) {
//        this.minLimit = minLimit;
//    }
//
//    private void setOverLimit(boolean overLimit) {
//        isOverLimit = overLimit;
//        if (null != limitInterface)
//            limitInterface.notifyViewEnable();
//
//    }
//
//    public boolean isOverLimit() {
//        return isOverLimit;
//    }
//
//    private OverLimitInterface limitInterface;
//
//    public void setLimitInterface(OverLimitInterface limitInterface) {
//        this.limitInterface = limitInterface;
//    }
//
//    public interface OverLimitInterface {
//        void notifyViewEnable();
//    }
//}
