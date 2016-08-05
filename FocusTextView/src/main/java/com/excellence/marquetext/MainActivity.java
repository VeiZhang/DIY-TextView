package com.excellence.marquetext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final FocusTextView focusTextView = (FocusTextView) findViewById(R.id.focusTextView);
		final TextView selectText = (TextView) findViewById(R.id.selectTextView);

		RelativeLayout focusLayout = (RelativeLayout) findViewById(R.id.focus_layout);
		RelativeLayout selectLayout = (RelativeLayout) findViewById(R.id.select_layout);
		focusLayout.setOnFocusChangeListener(new View.OnFocusChangeListener()
		{
			@Override
			public void onFocusChange(View v, boolean hasFocus)
			{
				focusTextView.setHasFocus(hasFocus);
				//这个不太好；但是重写TextView 可以让他一直走马灯
			}
		});

		selectLayout.setOnFocusChangeListener(new View.OnFocusChangeListener()
		{
			@Override
			public void onFocusChange(View v, boolean hasFocus)
			{
				selectText.setSelected(hasFocus);
			}
		});
	}
}
