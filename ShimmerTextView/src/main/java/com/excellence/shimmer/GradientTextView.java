package com.excellence.shimmer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by ZhangWei on 2016/3/9.
 */
public class GradientTextView extends TextView
{
	private LinearGradient mLinearGradient;
	private Matrix mGradientMatrix;
	private Paint mPaint;
	private int mViewWidth = 0;
	private int mTranslate = 0;

	public GradientTextView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh)
	{
		super.onSizeChanged(w, h, oldw, oldh);
		if (mViewWidth == 0)
		{
			mViewWidth = getMeasuredWidth();
			if (mViewWidth > 0)
			{
				mPaint = getPaint();
				mLinearGradient = new LinearGradient(-mViewWidth, 0, 0, 0, new int[] { 0x59ffffff, 0xffffffff, 0x59ffffff }, new float[] { 0, 0.5f, 1 }, Shader.TileMode.CLAMP);
				mPaint.setShader(mLinearGradient);
				mGradientMatrix = new Matrix();
			}
		}
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		if (mGradientMatrix != null)
		{
			mTranslate += mViewWidth / 10;
			if (mTranslate > 2 * mViewWidth)
			{
				mTranslate = -mViewWidth;
			}
			mGradientMatrix.setTranslate(mTranslate, 0);
			mLinearGradient.setLocalMatrix(mGradientMatrix);
			postInvalidateDelayed(50);
		}
	}
}
