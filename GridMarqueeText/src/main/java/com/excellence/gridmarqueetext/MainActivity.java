package com.excellence.gridmarqueetext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		List<String> list = new ArrayList<>();
		for (int i = 0; i < 15; i++)
		{
			list.add("abcdefghijklmnopqrstuvwxyz" + i);
		}

		final GridView gridView = (GridView) findViewById(R.id.gridView);
//		gridView.setVisibility(View.GONE);
		gridView.setSelected(false);
		gridView.setFocusable(false);
		gridView.clearFocus();
		gridView.setNumColumns(5);
		gridView.setAdapter(new GridAdapter(this, list, R.layout.item));

		GridView childGrid = (GridView) findViewById(R.id.childGrid);
		childGrid.setSelected(false);
		childGrid.setFocusable(false);
		childGrid.setAdapter(new GridAdapter(this, list, R.layout.item));

		TextView textView = (TextView) findViewById(R.id.view);
		textView.setOnKeyListener(new View.OnKeyListener()
		{
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event)
			{
				if (event.getAction() == KeyEvent.ACTION_DOWN)
				{
//					gridView.setVisibility(View.VISIBLE);
					gridView.setSelected(true);
					gridView.setFocusable(true);
					gridView.requestFocus();
					gridView.setSelection(3);
					return false;
				}
				return false;
			}
		});
	}
}
