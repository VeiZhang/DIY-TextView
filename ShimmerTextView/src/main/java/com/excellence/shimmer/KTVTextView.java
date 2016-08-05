package com.excellence.shimmer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/***
 * Created by ZhangWei on 2016/8/4.
 */
public class KTVTextView extends View
{
	private int mTextStartX;

	public enum Direction
	{
		LEFT, RIGHT;
	}

	private int mDirection = DIRECTION_LEFT;
	private static final int DIRECTION_LEFT = 0;
	private static final int DIRECTION_RIGHT = 1;

	private String mText = "张Wei炜";
	private Paint mPaint;
	private int mTextSize = sp2px(30);

	private int mTextOriginColor = 0xff000000;
	private int mTextChangeColor = 0xffff0000;

	private Rect mTextBound = new Rect();
	private int mTextWidth;
	private int mRealWidth;
	private float mProgress = 0;

	public KTVTextView(Context context)
	{
		super(context, null);
	}

	public KTVTextView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		// 抗锯齿
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mPaint.setTextSize(mTextSize);
		measureText();
	}

	private void measureText()
	{
		mTextWidth = (int) mPaint.measureText(mText);
		mPaint.getTextBounds(mText, 0, mText.length(), mTextBound);
	}

	private int sp2px(int size)
	{
		float scale = getContext().getResources().getDisplayMetrics().scaledDensity;
		return (int) (size * scale + 0.5f);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		int width = measureWidth(widthMeasureSpec);
		int height = measureHeight(heightMeasureSpec);
		setMeasuredDimension(width, height);

		mRealWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
		mTextStartX = mRealWidth / 2 - mTextWidth / 2;
	}

	private int measureHeight(int measureSpec)
	{
		int mode = MeasureSpec.getMode(measureSpec);
		int val = MeasureSpec.getSize(measureSpec);
		int result = 0;
		switch (mode)
		{
		case MeasureSpec.EXACTLY:
			result = val;
			break;

		case MeasureSpec.AT_MOST:
		case MeasureSpec.UNSPECIFIED:
			result = mTextBound.height();
			break;
		}
		result = mode == MeasureSpec.AT_MOST ? Math.min(result, val) : result;
		return result + getPaddingTop() + getPaddingBottom();
	}

	private int measureWidth(int measureSpec)
	{
		int mode = MeasureSpec.getMode(measureSpec);
		int val = MeasureSpec.getSize(measureSpec);
		int result = 0;
		switch (mode)
		{
		case MeasureSpec.EXACTLY:
			result = val;
			break;

		case MeasureSpec.AT_MOST:
		case MeasureSpec.UNSPECIFIED:
			// result = mTextBound.width();
			result = mTextWidth;
			break;
		}
		result = mode == MeasureSpec.AT_MOST ? Math.min(result, val) : result;
		return result + getPaddingLeft() + getPaddingRight();
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		int r = (int) (mProgress * mTextWidth + mTextStartX);

		if (mDirection == DIRECTION_LEFT)
		{
			drawChangeLeft(canvas, r);
			drawOriginLeft(canvas, r);
		}
		else
		{
			drawOriginRight(canvas, r);
			drawChangeRight(canvas, r);
		}
	}

	private void drawChangeRight(Canvas canvas, int r)
	{
		drawText(canvas, mTextChangeColor, (int) (mTextStartX + (1 - mProgress) * mTextWidth), mTextStartX + mTextWidth);
	}

	private void drawOriginRight(Canvas canvas, int r)
	{
		drawText(canvas, mTextOriginColor, mTextStartX, (int) (mTextStartX + (1 - mProgress) * mTextWidth));
	}

	private void drawChangeLeft(Canvas canvas, int r)
	{
		drawText(canvas, mTextChangeColor, mTextStartX, (int) (mTextStartX + mProgress * mTextWidth));
	}

	private void drawOriginLeft(Canvas canvas, int r)
	{
		drawText(canvas, mTextOriginColor, (int) (mTextStartX + mProgress * mTextWidth), mTextStartX + mTextWidth);
	}

	// 绘制的核心就在于利用mProgress和方向去计算应该clip的范围
	private void drawText(Canvas canvas, int color, int startX, int endX)
	{
		mPaint.setColor(color);
		// 需要还原Clip
		// save()先把画布的数据保存了(如matrix等)，最后绘制完后再restore()则把中间对画布坐标等操作forget掉
		canvas.save(Canvas.CLIP_SAVE_FLAG);
		// clipRect()截取画布中的一个区域
		canvas.clipRect(startX, 0, endX, getMeasuredHeight());
		canvas.drawText(mText, mTextStartX, getMeasuredHeight() / 2 + mTextBound.height() / 2, mPaint);
		// restore()最后要将画布回复原来的数据（记住save()跟restore()要配对使用）
		canvas.restore();
	}

	public void setProgress(float progress)
	{
		mProgress = progress;
		invalidate();
	}

	public float getProgress()
	{
		return mProgress;
	}
}
