package com.excellence.gridmarqueetext;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by John on 2015/12/2.
 */
public class ViewHolder
{
	private SparseArray<View> mViews = null;
	private View mConvertView = null;
	private int mPosition = -1;

	private ViewHolder(Context context, ViewGroup parent, int layoutId, int position)
	{
		mPosition = position;
		mViews = new SparseArray<>();
		mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
		mConvertView.setTag(this);
	}

	public static ViewHolder getViewHolder(Context context, View convertView, ViewGroup parent, int layoutId, int position)
	{
		if (null == convertView)
			return new ViewHolder(context, parent, layoutId, position);
		else
		{
			ViewHolder viewHolder = (ViewHolder) convertView.getTag();
			viewHolder.mPosition = position;
			return viewHolder;
		}
	}

	public <T extends View> T getView(int viewId)
	{
		View view = mViews.get(viewId);
		if (null == view)
		{
			view = mConvertView.findViewById(viewId);
			mViews.put(viewId, view);
		}
		return (T) view;
	}

	public ViewHolder setText(int viewId, String str)
	{
		TextView textView = getView(viewId);
		textView.setText(str);
		return this;
	}

	public View getConvertView()
	{
		return mConvertView;
	}
}
