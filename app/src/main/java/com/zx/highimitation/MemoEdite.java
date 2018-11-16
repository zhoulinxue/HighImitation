package com.zx.highimitation;

import android.app.ActionBar;
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
import android.view.ViewGroup;
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
    private float mHight;
    private int mLines;
    private Bitmap mCenterLineBitmap,mBottomBitmap;
    private Rect mLineSrcRect, mLineDesRect,mBottomSrcRect;
    int mStart;
    float mSpace;
    private int mTextSize;
    private final String TEST_TEXTHIGHT = "测";
    //命名空间（别告诉我不熟悉）
    private final String ANDROID_NAMESPACE = "http://schemas.android.com/apk/res/android";
    private final String TEXT_SIZE = "textSize";
    private final String LINE_SPACE = "lineSpacingExtra";
    //默认字号
    private final int DEFAULT_TEXT_SIZE=20;
    //默认额外行间距
    private final int DEFAULT_LINE_SPACE=5;
    private Paint mTextPaint,mCicelPaint;
    private int mIndexHight;
    private Rect mIndexRect;

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
        mCenterLineBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.line_p);
        mBottomBitmap=BitmapFactory.decodeResource(getResources(),R.mipmap.bottom_p);
        mLineSrcRect = new Rect(0, 0, mCenterLineBitmap.getWidth(), mCenterLineBitmap.getHeight());
        mBottomSrcRect=new Rect(0,0,mBottomBitmap.getWidth(),mBottomBitmap.getHeight());
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                getViewTreeObserver().removeOnGlobalLayoutListener(this);
                mHight = getMeasuredHeight();
                mWidth = getMeasuredWidth();
                mLineHight = getLineHeight();
                float more=mHight%mLineHight;
                mHight=mHight-more;
                mLines = (int) (mHight / mLineHight);
                mStart = (int) (getLineBottomHeight(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, mTextSize, getResources().getDisplayMetrics()))-mSpace);
            }
        });
        Paint paint=getPaint();
        mTextPaint=new Paint();
        mTextPaint.set(paint);
        mCicelPaint=new Paint();
        mCicelPaint.set(paint);
        mCicelPaint.setStyle(Paint.Style.STROKE);
        mTextPaint.setTextSize(mTextSize-1);
        mIndexRect=getFontRect(mTextPaint.getTextSize());
        mIndexHight=getFontHight(mTextPaint.getTextSize());

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < mLines+1; i++) {
            int topStart = 0;
                if (i != 0) {
                    topStart = (int) (i * mLineHight) + mStart;
                    if(i==mLines){
                        mLineDesRect = new Rect(0, topStart, mWidth, (int)(mHight+2*mSpace));
                        canvas.drawBitmap(mBottomBitmap, mBottomSrcRect, mLineDesRect, null);
                    }else {
                        mLineDesRect = new Rect(0, topStart, mWidth, (int) (mLineHight + topStart));
                        canvas.drawBitmap(mCenterLineBitmap, mLineSrcRect, mLineDesRect, null);
                    }
                }
                canvas.drawText(i+"",30,topStart-(mLineHight-mIndexHight)/2,mTextPaint);
                canvas.drawCircle(30+mIndexRect.centerX(),topStart-mLineHight/2,mTextSize+3,mCicelPaint);

            }
    }

    /**
     * 字体底边
     *
     * @param fontSize
     * @return
     */
    private int getLineBottomHeight(float fontSize) {
        Rect rect = getFontRect(fontSize);
        int hight = (int) (rect.bottom-mSpace);
        return hight;
    }

    /**
     * 字体高度
     * @param fontSize
     * @return
     */
    private int getFontHight(float fontSize){
        return getFontRect(fontSize).height();
    }
    /**
     * 字体矩形
     *
     * @param fontSize
     * @return
     */
    private Rect getFontRect(float fontSize) {
        Paint paint = new Paint();
        paint.setTextSize(fontSize);
        setText(TEST_TEXTHIGHT);
        Rect rect = new Rect();
        paint.getTextBounds(TEST_TEXTHIGHT, 0, TEST_TEXTHIGHT.length(), rect);
        setText("");
        return rect;
    }
}
