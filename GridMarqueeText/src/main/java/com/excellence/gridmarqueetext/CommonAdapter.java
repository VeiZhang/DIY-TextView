package com.excellence.gridmarqueetext;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by John on 2015/12/2.
 */
public abstract class CommonAdapter<T> extends BaseAdapter
{
	private Context mContext = null;
	private int mLayoutId = 0;
	private List<T> mDatas = null;
	private int showNum = 0;

	public CommonAdapter(Context context, int layoutId, List<T> datas)
	{
		mContext = context;
		mLayoutId = layoutId;
		mDatas = datas;
		showNum = mDatas.size();
	}

	public void setShowNum(int num)
	{
		showNum = num;
	}

	@Override
	public int getCount()
	{
		return showNum;
	}

	@Override
	public T getItem(int position)
	{
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		ViewHolder viewHolder = ViewHolder.getViewHolder(mContext, convertView, parent, mLayoutId, position);
		convert(viewHolder, getItem(position), position);
		return viewHolder.getConvertView();
	}

	public abstract void convert(ViewHolder viewHoler, T t, int position);
}
