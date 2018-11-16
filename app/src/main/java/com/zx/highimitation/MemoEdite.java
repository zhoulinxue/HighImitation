package com.zx.highimitation;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewTreeObserver;

import com.zx.api.api.utils.AppLog;

/**
 * Name: MemoEdite
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-16 00:14
 */
public class MemoEdite extends AppCompatEditText {
    private final String TAG = MemoEdite.class.getSimpleName();
    private float mLineHight;
    private int mWidth;
    private int mHight;
    private int mLines;
    private Bitmap mLineBitmap;
    private Rect mLineSrcRect, mLineDesRect;

    public MemoEdite(Context context) {
        super(context);
    }

    public MemoEdite(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MemoEdite(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet a) {
        //命名空间（别告诉我不熟悉）
        String namespace = "http://schemas.android.com/apk/res/android";
        //获取属性中设置的最大长度
        int textSize = a.getAttributeIntValue(namespace, "textSize", 14);

        mLineHight = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, textSize, context.getResources().getDisplayMetrics());

        mLineBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.line_p);
        mLineSrcRect = new Rect(0, 0, mLineBitmap.getWidth(), mLineBitmap.getHeight());
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                getViewTreeObserver().removeOnGlobalLayoutListener(this);
                mHight = getMeasuredHeight();
                mWidth = getMeasuredWidth();
                mLines = (int) ((mHight - (getPaddingTop() + getPaddingBottom())) / mLineHight);
                AppLog.print(TAG, mWidth + "!!!" + mHight + "!!!" + mLines);
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < mLines; i++) {
            int topStart = 0;
            if (topStart < mHight) {
                if (i != 0) {
                    topStart = 2 * (int) (i * mLineHight);
                    mLineDesRect = new Rect(0, topStart, mWidth, (int) (topStart + 2 * mLineHight));
                } else {
                    mLineDesRect = new Rect(0, 0, mWidth, (int) (2 * mLineHight));
                }
//                AppLog.print(TAG, mLineHight + " topStart:  " + topStart);
                canvas.drawBitmap(mLineBitmap, mLineSrcRect, mLineDesRect, null);
            }
        }
    }
}
