package com.zx.highimitation;

import android.content.Context;
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
    int mStart;
    float mSpace;
    private int mTextSize;
    private final String TEST_TEXTHIGHT = "测试高度";
    //命名空间（别告诉我不熟悉）
    private final String ANDROID_NAMESPACE = "http://schemas.android.com/apk/res/android";
    private final String TEXT_SIZE = "textSize";
    private final String LINE_SPACE = "lineSpacingExtra";
    //默认字号
    private final int DEFAULT_TEXT_SIZE=20;
    //默认额外行间距
    private final int DEFAULT_LINE_SPACE=5;

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

        //获取属性中设置的最大长度
        mTextSize = a.getAttributeIntValue(ANDROID_NAMESPACE, TEXT_SIZE, DEFAULT_TEXT_SIZE);
        int lineSpace = a.getAttributeIntValue(ANDROID_NAMESPACE, LINE_SPACE, DEFAULT_LINE_SPACE);
        mSpace = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, lineSpace, context.getResources().getDisplayMetrics());
        mLineBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.line_p);
        mLineSrcRect = new Rect(0, 0, mLineBitmap.getWidth(), mLineBitmap.getHeight());

        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                getViewTreeObserver().removeOnGlobalLayoutListener(this);
                mHight = getMeasuredHeight();
                mWidth = getMeasuredWidth();
                mLineHight = getLineHeight();
                mLines = (int) (mHight / mLineHight);
                mStart = getLineBottomHeight(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, mTextSize, getResources().getDisplayMetrics()));
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
                    topStart = (int) (i * mLineHight) + mStart;
                    mLineDesRect = new Rect(0, topStart, mWidth, (int) (mLineHight + topStart));
                } else {
                    mLineDesRect = new Rect(0, 0, mWidth, (int) (mLineHight + mStart));
                }
                AppLog.print(TAG, (int) (mLineHight * (1 + i)) + " topStart:  " + topStart);
                canvas.drawBitmap(mLineBitmap, mLineSrcRect, mLineDesRect, null);
            }
        }
    }

    /**
     * 字体高度
     *
     * @param fontSize
     * @return
     */
    private int getLineBottomHeight(float fontSize) {
        Paint paint = new Paint();
        paint.setTextSize(fontSize);
        setText(TEST_TEXTHIGHT);
        Rect rect = new Rect();
        paint.getTextBounds(TEST_TEXTHIGHT, 0, TEST_TEXTHIGHT.length(), rect);
        int hight = (int) (rect.centerY() + mLineHight / 2 - mSpace*7/4);
        setText("");
        return hight;
    }
}
