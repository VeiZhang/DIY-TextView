package com.excellence.shimmer.Widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
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
	private static final int DIRECTION_TOP = 2;
	private static final int DIRECTION_BOTTOM = 3;

	public enum Direction
	{
		LEFT, RIGHT, TOP, BOTTOM;
	}

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

	/**
	 * 设置以及默认progress、max
	 */

	private int mMax = 100;
	private int mProgress = 0;

	/**
	 * 绘制进度
	 */
	private float mMiniProgress = 0;
	/**
	 * 文字宽度、高度
	 */
	private int mTextWidth;
	private int mTextHeight;

	/**
	 * 整个View宽度、高度
	 */
	private int mViewWidth;
	private int mViewHeight;

	/**
	 * 文字进度初始宽度、高度
	 */
	private int mTextStartX = 0;
	private int mTextStartY = 0;

	/**
	 * 整个View进度初始宽度、高度
	 */
	private int mViewStartX = 0;
	private int mViewStartY = 0;

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
		mMax = typedArray.getInt(R.styleable.ColorTrackView_max, mMax);
		mProgress = typedArray.getInt(R.styleable.ColorTrackView_progress, mProgress);
		mBackgroundHPadding = typedArray.getDimensionPixelOffset(R.styleable.ColorTrackView_background_horizontal_padding, mBackgroundHPadding);
		mBackgroundVPadding = typedArray.getDimensionPixelOffset(R.styleable.ColorTrackView_background_vertical_padding, mBackgroundVPadding);
		typedArray.recycle();
		if (mText == null)
			mText = ColorTrackView.class.getSimpleName();
		if (mProgressable)
		{
			mViewStartX = mBackgroundHPadding;
			mViewStartY = mBackgroundVPadding;
		}
		correctProgress();
	}

	private void correctProgress()
	{
		// 保证最大为1即100%
		if (mProgress > mMax)
			mProgress = mMax;

		switch (mDirection)
		{
		case DIRECTION_LEFT:
		case DIRECTION_TOP:
			mMiniProgress = (float) mProgress / mMax;
			break;

		case DIRECTION_RIGHT:
		case DIRECTION_BOTTOM:
			mMiniProgress = (float) (mMax - mProgress) / mMax;
			break;
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		init();
		int width = measureWidth(widthMeasureSpec);
		int height = measureHeight(heightMeasureSpec);
		setMeasuredDimension(width, height);
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
		// 横向
		mPaint.getTextBounds(mText, 0, mText.length(), mTextBound);
		mTextWidth = (int) mPaint.measureText(mText);
		mTextStartX = getMeasuredWidth() / 2 - mTextWidth / 2;
		mViewWidth = getMeasuredWidth();

		// 纵向
		mTextHeight = mTextBound.height();
		mTextStartY = getMeasuredHeight() / 2 - mTextHeight / 2;
		mViewHeight = getMeasuredHeight();
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
		switch (mDirection)
		{
		case DIRECTION_LEFT:
		case DIRECTION_RIGHT:
			// View横向进度宽度
			int rx = (int) (mMiniProgress * (mViewWidth - 2 * mBackgroundHPadding) + mViewStartX);
			drawChangeX(canvas, rx);
			drawOriginX(canvas, rx);
			break;

		case DIRECTION_TOP:
		case DIRECTION_BOTTOM:
			// View纵向进度宽度
			int ry = (int) (mMiniProgress * (mViewHeight - 2 * mBackgroundVPadding) + mViewStartY);
			drawChangeY(canvas, ry);
			drawOriginY(canvas, ry);
			break;
		}
	}

	private void drawChangeX(Canvas canvas, int r)
	{
		if (mProgressable)
		{
			int readlViewWidth = mViewWidth - 2 * mBackgroundHPadding;
			drawForegroundX(canvas, mForegroundChangeColor, mViewStartX, (int) (mViewStartX + mMiniProgress * readlViewWidth));
			if (r >= mTextStartX && r <= mTextStartX + mTextWidth)
				drawTextX(canvas, mTextChangeColor, mTextStartX, (int) (mViewStartX + mMiniProgress * readlViewWidth));
		}
		else
			drawTextX(canvas, mTextChangeColor, mTextStartX, (int) (mTextStartX + mMiniProgress * mTextWidth));
	}

	private void drawOriginX(Canvas canvas, int r)
	{
		if (mProgressable)
		{
			int readlViewWidth = mViewWidth - 2 * mBackgroundHPadding;
			drawForegroundX(canvas, mForegroundOriginColor, (int) (mViewStartX + mMiniProgress * readlViewWidth), mViewStartX + readlViewWidth);
			// 文字三个阶段，全初色、半初色半改色、全改色
			if (r >= mTextStartX && r <= mTextStartX + mTextWidth)
				drawTextX(canvas, mTextOriginColor, (int) (mViewStartX + mMiniProgress * readlViewWidth), mTextStartX + mTextWidth);
			else if (r > mTextStartX + mTextWidth)
				drawTextX(canvas, mTextChangeColor, mTextStartX, mTextStartX + mTextWidth);
			else
				drawTextX(canvas, mTextOriginColor, mTextStartX, mTextStartX + mTextWidth);
		}
		else
			drawTextX(canvas, mTextOriginColor, (int) (mTextStartX + mMiniProgress * mTextWidth), mTextStartX + mTextWidth);
	}

	private void drawChangeY(Canvas canvas, int r)
	{
		if (mProgressable)
		{
			int readlViewHeight = mViewHeight - 2 * mBackgroundVPadding;
			drawForegroundY(canvas, mForegroundChangeColor, mViewStartY, (int) (mViewStartY + mMiniProgress * readlViewHeight));
			if (r >= mTextStartY && r <= mTextStartY + mTextHeight)
				drawTextY(canvas, mTextChangeColor, mTextStartY, (int) (mViewStartY + mMiniProgress * readlViewHeight));
		}
		else
			drawTextY(canvas, mTextChangeColor, mTextStartY, (int) (mTextStartY + mMiniProgress * mTextHeight));
	}

	private void drawOriginY(Canvas canvas, int r)
	{
		if (mProgressable)
		{
			int readlViewHeight = mViewHeight - 2 * mBackgroundVPadding;
			drawForegroundY(canvas, mForegroundOriginColor, (int) (mViewStartY + mMiniProgress * readlViewHeight), mViewStartY + readlViewHeight);
			// 文字三个阶段，全初色、半初色半改色、全改色
			if (r >= mTextStartY && r <= mTextStartY + mTextHeight)
				drawTextY(canvas, mTextOriginColor, (int) (mViewStartY + mMiniProgress * readlViewHeight), mTextStartY + mTextHeight);
			else if (r > mTextStartY + mTextHeight)
				drawTextY(canvas, mTextChangeColor, mTextStartY, mTextStartY + mTextHeight);
			else
				drawTextY(canvas, mTextOriginColor, mTextStartY, mTextStartY + mTextHeight);
		}
		else
			drawTextY(canvas, mTextOriginColor, (int) (mTextStartY + mMiniProgress * mTextHeight), mTextStartY + mTextHeight);

	}

	private void drawForegroundX(Canvas canvas, int color, int startX, int endX)
	{
		// 有padding了，宽度和高度需要重新计算
		mPaint.setColor(color);

		// 方式一 绘图
		canvas.save(Canvas.CLIP_SAVE_FLAG);
		canvas.clipRect(startX, mBackgroundVPadding, endX, getMeasuredHeight() - mBackgroundVPadding);
		RectF rectF = new RectF(0, mBackgroundVPadding, getRight(), getBottom() - mBackgroundVPadding);
		canvas.drawRect(rectF, mPaint);
		canvas.restore();

		// 方式二
		// canvas.drawRect(startX, mBackgroundVPadding, endX,
		// getMeasuredHeight() - mBackgroundVPadding, mPaint);

	}

	// 绘制的核心就在于利用mProgress和方向去计算应该clip的范围
	private void drawTextX(Canvas canvas, int color, int startX, int endX)
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

	private void drawForegroundY(Canvas canvas, int color, int startY, int endY)
	{
		mPaint.setColor(color);
		canvas.save(Canvas.CLIP_SAVE_FLAG);
		canvas.clipRect(mBackgroundHPadding, startY, getMeasuredWidth() - mBackgroundHPadding, endY);
		RectF rectF = new RectF(mBackgroundHPadding, 0, getRight() - mBackgroundHPadding, getBottom());
		canvas.drawRect(rectF, mPaint);
		canvas.restore();
	}

	private void drawTextY(Canvas canvas, int color, int startY, int endY)
	{
		mPaint.setColor(color);
		canvas.save(Canvas.CLIP_SAVE_FLAG);
		canvas.clipRect(0, startY, getMeasuredWidth(), endY);
		canvas.drawText(mText, mTextStartX, getMeasuredHeight() / 2 - ((mPaint.descent() + mPaint.ascent()) / 2), mPaint);
		canvas.restore();
	}

	private int sp2px(int size)
	{
		float scale = getContext().getResources().getDisplayMetrics().scaledDensity;
		return (int) (size * scale + 0.5f);
	}

	public void setProgress(int progress)
	{
		mProgress = progress;
		correctProgress();
		invalidate();
	}

	public int getProgress()
	{
		return mProgress;
	}

	public float getMax()
	{
		return mMax;
	}

	public void setMax(int max)
	{
		mMax = max;
	}

	public String getText()
	{
		return mText;
	}

	public void setText(String text)
	{
		mText = text;
		measureText();
	}

	public void setText(int strId)
	{
		mText = getResources().getString(strId);
		measureText();
	}
}
