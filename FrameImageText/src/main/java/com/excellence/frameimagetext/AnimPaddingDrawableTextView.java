package com.excellence.frameimagetext;

import java.util.List;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.LevelListDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by ZhangWei on 2016/6/21.
 */
public class AnimPaddingDrawableTextView extends TextView
{
	private int mLevel = 0;
	// 触摸状态类
	private StateListDrawable mStateListDrawable = null;
	// 等级状态类
	private LevelListDrawable mLevelListDrawable = null;

	public AnimPaddingDrawableTextView(Context context)
	{
		super(context);
	}

	public AnimPaddingDrawableTextView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AnimPaddingDrawableTextView);
		for (int i = 0; i < typedArray.getIndexCount(); i++)
		{
			int attr = typedArray.getIndex(i);
			switch (attr)
			{
			case R.styleable.AnimPaddingDrawableTextView_image_frame:
				mLevelListDrawable = (LevelListDrawable) typedArray.getDrawable(attr);
				setCompoundDrawablesWithIntrinsicBounds(mLevelListDrawable, null, null, null);
				break;

			case R.styleable.AnimPaddingDrawableTextView_image_background:
				mStateListDrawable = (StateListDrawable) typedArray.getDrawable(attr);
				setBackgroundDrawable(mStateListDrawable);
				break;
			}
		}
		typedArray.recycle();
	}

	public int getLevel()
	{
		return mLevel;
	}

	public void setLevel(int level)
	{
		this.mLevel = mLevel;
		/**
		 * 设置left padiing drawable
		 */
		if (mLevelListDrawable != null && getCompoundDrawables().length > 0 && getCompoundDrawables()[0] != null)
		{
			getCompoundDrawables()[0].setLevel(level);
		}
	}

	public void setFrameResource(List<Integer> resIds)
	{
		mLevelListDrawable = new LevelListDrawable();
		for (int i = 0; i < resIds.size(); i++)
		{
			mLevelListDrawable.addLevel(i, i + 1, getResources().getDrawable(resIds.get(i)));
		}
		setCompoundDrawablesWithIntrinsicBounds(mLevelListDrawable, null, null, null);
	}

	public void setStartLevel(int level)
	{
		//还是需要知道变化的属性
		mLevel = level;
		if (mLevelListDrawable != null && getCompoundDrawables().length > 0 && getCompoundDrawables()[0] != null)
		{
			boolean flag = getCompoundDrawables()[0].setLevel(mLevel++);
			if (!flag)
				mLevel = 0;
		}
	}
}
