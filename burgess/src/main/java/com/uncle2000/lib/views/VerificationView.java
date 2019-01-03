package com.uncle2000.lib.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.text.InputType;
import android.util.AttributeSet;

import com.blankj.utilcode.util.SizeUtils;


/**
 * Created by xiao on 2016/11/28.
 * 功能:
 * 需求地址:
 */

public class VerificationView extends android.support.v7.widget.AppCompatEditText {
    public static final int TEXT_LENGTH = 6;//密码的长度
    private int textSize = 18;
    private Paint borderPaint;     //外框画笔
    //    private Paint linePaint;       //线的画笔
    private Paint textPaint;   //文字画笔
    private int textLength;  //输入文字的长度
    private int width;
    private int height;
    private int margin;

    public VerificationView(Context context) {
        this(context, null);
    }

    public VerificationView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VerificationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView();
    }

    private void initView() {
        borderPaint = new Paint();
        borderPaint.setStrokeWidth(2);
        borderPaint.setColor(Color.parseColor("#b1b1b1"));
        borderPaint.setStyle(Paint.Style.STROKE);

//        linePaint = new Paint();
//        linePaint.setColor(Color.parseColor("#b1b1b1"));
//        linePaint.setStrokeWidth(4);

        textPaint = new Paint();
        textPaint.setColor(Color.parseColor("#222222"));
        textPaint.setTextSize(SizeUtils.dp2px(textSize));
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setAntiAlias(true);

        setInputType(InputType.TYPE_CLASS_NUMBER);
        setMaxEms(TEXT_LENGTH);
        margin = SizeUtils.dp2px(10);
    }


    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);

        width = getMeasuredWidth();
        height = getMeasuredHeight();

        drawRoundRect(canvas);
//        drawLine(canvas);
        drawText(canvas);
    }

    /**
     * 绘制圆角矩形背景
     *
     * @param canvas
     */
    private void drawRoundRect(Canvas canvas) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas.drawRoundRect(0, 0, width, height, 0, 0, borderPaint);
        }
        for (int i = 0; i < TEXT_LENGTH - 1; i++) {
            canvas.drawLine(width / TEXT_LENGTH * (i + 1), 0, width / TEXT_LENGTH * (i + 1), height, borderPaint);
        }
    }

    /**
     * 绘制下滑线
     *
     * @param canvas
     */
//    private void drawLine(Canvas canvas) {
////        canvas.save();
//        float half = width / TEXT_LENGTH / 2;
//        float len = half * 4 / 5;
//        float y = textPaint.getTextSize() + margin;
//
////        canvas.translate(0,y);
//        for (int i = 0; i < TEXT_LENGTH; i++) {
//            float x = width * i / TEXT_LENGTH + half;
//            canvas.drawLine(x - len, y, x + len, y, linePaint);
////            canvas.drawLine(x,0,x, height, linePaint);
////            canvas.drawLine(0,height/2,width, height/2, linePaint);
//
//        }
////        canvas.restore();
//    }

    /**
     * 绘制文字
     *
     * @param canvas
     */
    private void drawText(Canvas canvas) {
        float cx;
        float cy = height / 2 + SizeUtils.dp2px(textSize / 3);
        float half = width / TEXT_LENGTH / 2;
//        float halfTextSize = textPaint.getTextSize() / 4;
        for (int i = 0; i < textLength; i++) {
            //-2是为了解决浮点运算的误差
            cx = width * i / TEXT_LENGTH + half /*- halfTextSize - 2*/;
//            cy = height / 2+halfTextSize;
//            canvas.drawCircle(cx, cy, PASSWORD_RADIUS, textPaint);
            char a = getEditableText().charAt(i);
            canvas.drawText(String.valueOf(a), cx, cy, textPaint);
        }
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);

        textLength = text.toString().length();

        invalidate();
    }

    public boolean isInputCompleted() {
        return VerificationView.TEXT_LENGTH == getText().length();
    }

    public void reset() {
        setText("");
        invalidate();
    }

//    public void addTextChangedListener(TextWatcher watcher) {
//        this.addTextChangedListener(watcher);
//    }

}
