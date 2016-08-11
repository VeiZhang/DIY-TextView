package com.excellence.shimmer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity
{
	private ColorTrackView mColorTrackView = null;
	private ColorTrackView mColorTrackView1 = null;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mColorTrackView = (ColorTrackView) findViewById(R.id.ktvtextview);
		mColorTrackView1 = (ColorTrackView) findViewById(R.id.ktvtextview1);
		mColorTrackView.postDelayed(mDrawRunnable, 200);
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
			if (mColorTrackView.getProgress() >= 1)
			{
				mColorTrackView.setProgress(0);
				mColorTrackView1.setProgress(0);
			}
			mColorTrackView.setProgress(mColorTrackView.getProgress() + 0.01f);
			mColorTrackView1.setProgress(mColorTrackView1.getProgress() + 0.01f);
			mColorTrackView.postDelayed(mDrawRunnable, 200);
		}
	};
}
