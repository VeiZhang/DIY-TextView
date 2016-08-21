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

	private ColorTrackView mColorTrackView4 = null;
	private ColorTrackView mColorTrackView5 = null;
	private ColorTrackView mColorTrackView6 = null;
	private ColorTrackView mColorTrackView7 = null;

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

		mColorTrackView4 = (ColorTrackView) findViewById(R.id.colortrackview4);
		mColorTrackView5 = (ColorTrackView) findViewById(R.id.colortrackview5);
		mColorTrackView6 = (ColorTrackView) findViewById(R.id.colortrackview6);
		mColorTrackView7 = (ColorTrackView) findViewById(R.id.colortrackview7);

		mColorTrackView.postDelayed(mDrawRunnable, 50);

		setListener(mColorTrackView2);
		setListener(mColorTrackView6);
	}

	private void setListener(ColorTrackView view)
	{
		view.setClickable(true);
		view.setFocusable(true);
		view.setOnClickListener(this);
		view.setOnKeyListener(this);
		view.setOnClickListener(this);
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

		case R.id.colortrackview6:
			if (mColorTrackView6.getProgress() >= mColorTrackView6.getMax())
				mColorTrackView6.setProgress(0);
			else
				mColorTrackView6.setProgress(mColorTrackView6.getProgress() + 1);
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
				mColorTrackView4.setProgress(0);
				mColorTrackView5.setProgress(0);
				mColorTrackView7.setProgress(0);
			}
			mColorTrackView.setProgress(mColorTrackView.getProgress() + 1);
			mColorTrackView1.setProgress(mColorTrackView1.getProgress() + 1);
			mColorTrackView3.setProgress(mColorTrackView3.getProgress() + 1);
			mColorTrackView4.setProgress(mColorTrackView4.getProgress() + 1);
			mColorTrackView5.setProgress(mColorTrackView5.getProgress() + 1);
			mColorTrackView7.setProgress(mColorTrackView7.getProgress() + 1);

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
