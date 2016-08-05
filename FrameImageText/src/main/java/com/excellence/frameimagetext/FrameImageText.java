package com.excellence.frameimagetext;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ZhangWei on 2016/6/21.
 */
public class FrameImageText extends TextView
{
	private int mLevel = 0;
	private List<Integer> mRes = null;

	public FrameImageText(Context context)
	{
		super(context);
	}

	public FrameImageText(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public int getLevel()
	{
		return mLevel;
	}

	public void setLevel(int level)
	{
		this.mLevel = mLevel;
		if (mRes.size() > 0)
			setCompoundDrawablesWithIntrinsicBounds(mRes.get(level % mRes.size()), 0, 0, 0);
	}

	public void setFrameResource(List<Integer> res)
	{
		mRes = res;
	}
}
