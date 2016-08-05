package com.excellence.marquetext;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by John on 2016/1/28.
 */
public class FocusTextView extends TextView
{
	private boolean hasFocus = false;

	public FocusTextView(Context context)
	{
		super(context);
	}

	public FocusTextView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FocusTextView);
		hasFocus = typedArray.getBoolean(R.styleable.FocusTextView_isAlwaysFocus, false);
		typedArray.recycle();
	}

	@Override
	public boolean isFocused()
	{
		return hasFocus;
	}

	public void setHasFocus(boolean hasFocus)
	{
		this.hasFocus = hasFocus;
	}
}
