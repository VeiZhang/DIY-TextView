package com.excellence.shimmer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity
{
	private KTVTextView mKTVTextView = null;
	private KTVTextView mKTVTextView1 = null;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mKTVTextView = (KTVTextView) findViewById(R.id.ktvtextview);
		mKTVTextView1 = (KTVTextView) findViewById(R.id.ktvtextview1);
		mKTVTextView.postDelayed(mDrawRunnable, 200);
		/*
		mKTVTextView.setClickable(true);
		mKTVTextView.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (mKTVTextView.getProgress() >= 1)
					mKTVTextView.setProgress(0);
				else
					mKTVTextView.setProgress(mKTVTextView.getProgress() + 0.01f);
			}
		});
		*/
	}

	private Runnable mDrawRunnable = new Runnable()
	{
		@Override
		public void run()
		{
			if (mKTVTextView.getProgress() >= 1)
			{
				mKTVTextView.setProgress(0);
				mKTVTextView1.setProgress(0);
			}
			mKTVTextView.setProgress(mKTVTextView.getProgress() + 0.01f);
			mKTVTextView1.setProgress(mKTVTextView1.getProgress() + 0.01f);
			mKTVTextView.postDelayed(mDrawRunnable, 200);
		}
	};
}
