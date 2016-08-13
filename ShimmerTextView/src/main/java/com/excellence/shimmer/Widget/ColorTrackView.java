package com.excellence.shimmer.Widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.excellence.shimmer.R;

/***
 * Created by ZhangWei on 2016/8/4.
 */
public class ColorTrackView extends View
{
	private int mDirection = DIRECTION_LEFT;
	private static final int DIRECTION_LEFT = 0;
	private static final int DIRECTION_RIGHT = 1;

	private String mText = ColorTrackView.class.getSimpleName();
	private Paint mPaint = null;
	private int mTextSize = sp2px(30);

	private int mTextOriginColor = Color.BLACK;
	private int mTextChangeColor = Color.WHITE;
	private int mForegroundOriginColor = Color.TRANSPARENT;
	private int mForegroundChangeColor = Color.TRANSPARENT;

	private Rect mTextBound = new Rect();

	/**
	 * ture 开启进度和文字进度 false 只开启文字进度
	 */
	private boolean mProgressable = false;

	private float mProgress = 0;
	/**
	 * 文字宽度
	 */
	private int mTextWidth;

	/**
	 * 整个View宽度
	 */
	private int mViewWidth;

	/**
	 * 文字进度初始宽度
	 */
	private int mTextStartX = 0;

	/**
	 * 整个View进度初始宽度
	 */
	private int mViewStartX = 0;

	/**
	 * 横向纵向保留背景图片的padding 由于图片有光晕的情况
	 */
	private int mBackgroundVPadding = 0;
	private int mBackgroundHPadding = 0;

	public ColorTrackView(Context context)
	{
		super(context, null);
	}

	public ColorTrackView(Context context, AttributeSet attrs)
	{
		super(context, attrs);

		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ColorTrackView);
		mTextOriginColor = typedArray.getColor(R.styleable.ColorTrackView_text_origin_color, mTextOriginColor);
		mTextChangeColor = typedArray.getColor(R.styleable.ColorTrackView_text_change_color, mTextChangeColor);
		mForegroundOriginColor = typedArray.getColor(R.styleable.ColorTrackView_foreground_origin_color, mForegroundOriginColor);
		mForegroundChangeColor = typedArray.getColor(R.styleable.ColorTrackView_foreground_change_color, mForegroundChangeColor);
		mDirection = typedArray.getInt(R.styleable.ColorTrackView_direction, mDirection);
		mText = typedArray.getString(R.styleable.ColorTrackView_text);
		mTextSize = typedArray.getDimensionPixelOffset(R.styleable.ColorTrackView_text_size, mTextSize);
		mProgressable = typedArray.getBoolean(R.styleable.ColorTrackView_progressable, mProgressable);
		mProgress = typedArray.getFloat(R.styleable.ColorTrackView_progress, mProgress);
		mBackgroundHPadding = typedArray.getDimensionPixelOffset(R.styleable.ColorTrackView_background_horizontal_padding, mBackgroundHPadding);
		mBackgroundVPadding = typedArray.getDimensionPixelOffset(R.styleable.ColorTrackView_background_vertical_padding, mBackgroundVPadding);
		typedArray.recycle();
		if (mText == null)
			mText = ColorTrackView.class.getSimpleName();
		if (mProgressable)
			mViewStartX = mBackgroundHPadding;
		correctProgress();
	}

	private void correctProgress()
	{
		// 保证最大为1即100%
		if (mProgress > 1)
			mProgress = 1;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		init();
		int width = measureWidth(widthMeasureSpec);
		int height = measureHeight(heightMeasureSpec);
		setMeasuredDimension(width, height);
		mViewWidth = getMeasuredWidth();
		mTextStartX = getMeasuredWidth() / 2 - mTextWidth / 2;
	}

	private void init()
	{
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
		// View进度宽度
		int r = (int) (mProgress * (mViewWidth - 2 * mBackgroundHPadding) + mViewStartX);

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
		if (mProgressable)
		{
			int readlViewWidth = mViewWidth - 2 * mBackgroundHPadding;
			drawForeground(canvas, mForegroundChangeColor, mViewStartX, (int) (mViewStartX + mProgress * readlViewWidth));
			if (r >= mTextStartX && r <= mTextStartX + mTextWidth)
				drawText(canvas, mTextChangeColor, mTextStartX, (int) (mViewStartX + mProgress * readlViewWidth));
		}
		else
			drawText(canvas, mTextChangeColor, mTextStartX, (int) (mTextStartX + mProgress * mTextWidth));
	}

	private void drawOriginLeft(Canvas canvas, int r)
	{
		if (mProgressable)
		{
			int readlViewWidth = mViewWidth - 2 * mBackgroundHPadding;
			drawForeground(canvas, mForegroundOriginColor, (int) (mViewStartX + mProgress * readlViewWidth), mViewStartX + readlViewWidth);
			// 三个阶段，全初色、半初色半改色、全改色
			if (r >= mTextStartX && r <= mTextStartX + mTextWidth)
				drawText(canvas, mTextOriginColor, (int) (mViewStartX + mProgress * readlViewWidth), mTextStartX + mTextWidth);
			else if (r > mTextStartX + mTextWidth)
				drawText(canvas, mTextChangeColor, mTextStartX, mTextStartX + mTextWidth);
			else
				drawText(canvas, mTextOriginColor, mTextStartX, mTextStartX + mTextWidth);
		}
		else
			drawText(canvas, mTextOriginColor, (int) (mTextStartX + mProgress * mTextWidth), mTextStartX + mTextWidth);
	}

	private void drawForeground(Canvas canvas, int color, int startX, int endX)
	{
		// 有padding了，宽度和高度需要重新计算
		mPaint.setColor(color);
		canvas.save(Canvas.CLIP_SAVE_FLAG);
		canvas.clipRect(startX, mBackgroundVPadding, endX, getMeasuredHeight() - mBackgroundVPadding);
		canvas.drawRect(0, mBackgroundVPadding, getRight(), getBottom() - mBackgroundVPadding, mPaint);
		canvas.restore();
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
		canvas.drawText(mText, mTextStartX, getMeasuredHeight() / 2 - ((mPaint.descent() + mPaint.ascent()) / 2), mPaint);
		// restore()最后要将画布回复原来的数据（记住save()跟restore()要配对使用）
		canvas.restore();
	}

	private int sp2px(int size)
	{
		float scale = getContext().getResources().getDisplayMetrics().scaledDensity;
		return (int) (size * scale + 0.5f);
	}

	public void setProgress(float progress)
	{
		mProgress = progress;
		correctProgress();
		invalidate();
	}

	public float getProgress()
	{
		return mProgress;
	}
}
