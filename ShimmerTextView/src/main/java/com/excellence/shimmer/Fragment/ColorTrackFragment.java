package com.excellence.shimmer.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.excellence.shimmer.MainActivity;
import com.excellence.shimmer.R;
import com.excellence.shimmer.Widget.ColorTrackView;

/**
 * Created by sony on 2016/8/13.
 */
public class ColorTrackFragment extends BaseFragment implements View.OnClickListener, View.OnKeyListener
{
	private ColorTrackView mColorTrackView = null;
	private ColorTrackView mColorTrackView1 = null;
	private ColorTrackView mColorTrackView2 = null;
	private ColorTrackView mColorTrackView3 = null;

	@Override
	protected void setTAG()
	{
		TAG = ColorTrackFragment.class.getSimpleName();
	}

	@Override
	public void setContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		mRootView = inflater.inflate(R.layout.fragment_colortrack, null, false);
	}

	@Override
	public void initView()
	{
		mColorTrackView = (ColorTrackView) findViewById(R.id.colortrackview);
		mColorTrackView1 = (ColorTrackView) findViewById(R.id.colortrackview1);
		mColorTrackView2 = (ColorTrackView) findViewById(R.id.colortrackview2);
		mColorTrackView3 = (ColorTrackView) findViewById(R.id.colortrackview3);
		mColorTrackView.postDelayed(mDrawRunnable, 50);
		mColorTrackView2.setClickable(true);
		mColorTrackView2.setFocusable(true);
		mColorTrackView2.setOnClickListener(this);
		mColorTrackView2.setOnKeyListener(this);
		mColorTrackView3.setOnClickListener(this);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.colortrackview2:
			if (mColorTrackView2.getProgress() >= mColorTrackView2.getMax())
				mColorTrackView2.setProgress(0);
			else
				mColorTrackView2.setProgress(mColorTrackView2.getProgress() + 1);
			break;

		case R.id.colortrackview3:
			Intent intent = new Intent(getActivity(), MainActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
			break;
		}
	}

	private Runnable mDrawRunnable = new Runnable()
	{
		@Override
		public void run()
		{
			if (mColorTrackView.getProgress() >= mColorTrackView.getMax())
			{
				mColorTrackView.setProgress(0);
				mColorTrackView1.setProgress(0);
				mColorTrackView3.setProgress(0);
			}
			mColorTrackView.setProgress(mColorTrackView.getProgress() + 1);
			mColorTrackView1.setProgress(mColorTrackView1.getProgress() + 1);
			mColorTrackView3.setProgress(mColorTrackView3.getProgress() + 1);
			mColorTrackView.postDelayed(mDrawRunnable, 50);
		}
	};

	@Override
	public boolean onKey(View v, int keyCode, KeyEvent event)
	{
		if (event.getAction() == KeyEvent.ACTION_DOWN)
		{
			switch (keyCode)
			{
			case KeyEvent.KEYCODE_ENTER:
				v.performClick();
				return true;
			}
		}
		return false;
	}
}
