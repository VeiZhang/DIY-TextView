package com.excellence.shimmer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity
{
	private KTVTextView mKTVTextView = null;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mKTVTextView = (KTVTextView) findViewById(R.id.ktvtextview);
		mKTVTextView.postDelayed(mDrawRunnable, 50);
	}

	private Runnable mDrawRunnable = new Runnable()
	{
		@Override
		public void run()
		{
			if (mKTVTextView.getProgress() >= 1)
				mKTVTextView.setProgress(0);
			mKTVTextView.setProgress(mKTVTextView.getProgress() + 0.01f);
			mKTVTextView.postDelayed(mDrawRunnable, 50);
		}
	};
}
