package com.excellence.gridmarqueetext;

import android.content.Context;
import android.widget.ListAdapter;

import java.util.List;

/**
 * Created by John on 2016/5/4.
 */
public class GridAdapter extends CommonAdapter<String>
{
	public GridAdapter(Context context, List<String> list, int layoutId)
	{
		super(context, layoutId, list);
	}

	@Override
	public void convert(ViewHolder viewHoler, String s, int position)
	{
        viewHoler.setText(R.id.item_text, s);
	}
}
