package com.excellence.gridmarqueetext;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by John on 2016/5/4.
 */
public class MarqueeText extends TextView
{
	public MarqueeText(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	@Override
	public boolean isFocused()
	{
		return true;
	}

	@Override
	public boolean isSelected()
	{
		return true;
	}

	@Override
	public void setSelected(boolean selected)
	{
		super.setSelected(true);
	}
}
