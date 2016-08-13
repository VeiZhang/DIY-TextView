package com.excellence.shimmer.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.excellence.shimmer.R;

/**
 * Created by sony on 2016/8/13.
 */
public class LyricFragment extends BaseFragment
{
	@Override
	protected void setTAG()
	{
		TAG = LyricFragment.class.getSimpleName();
	}

	@Override
	public void setContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		mRootView = inflater.inflate(R.layout.fragment_lyric, null, false);
	}

	@Override
	public void initView()
	{

	}
}
