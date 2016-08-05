package com.excellence.textclickevent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final TextView textView = (TextView) findViewById(R.id.text);
		textView.setOnClickListener(this);
		textView.setClickable(false);
		//要让setclickable 有效，要放在setOnClickListener后面；源码里setOnClickListener有对setclickable的设置
	}

	@Override
	public void onClick(View v)
	{
		System.out.println("hello world");
	}
}
