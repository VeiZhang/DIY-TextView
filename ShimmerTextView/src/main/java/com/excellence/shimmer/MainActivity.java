package com.excellence.shimmer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.excellence.shimmer.Common.CommonAdapter;
import com.excellence.shimmer.Common.ViewHolder;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		String[] strings = getResources().getStringArray(R.array.view_name);
		List<String> data = Arrays.asList(strings);
		GridView gridView = (GridView) findViewById(R.id.gridView);
		gridView.setAdapter(new TextAdapter(this, android.R.layout.simple_list_item_1, data));
		gridView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{
		Intent intent = new Intent(this, TextActivity.class);
		intent.putExtra(TextActivity.PARAM_ITEM, position);
		startActivity(intent);
	}

	private class TextAdapter extends CommonAdapter<String>
	{

		public TextAdapter(Context context, int layoutId, List<String> datas)
		{
			super(context, layoutId, datas);
		}

		@Override
		public void convert(ViewHolder viewHoler, String item, int position)
		{
			viewHoler.setText(android.R.id.text1, item);
		}
	}
}
