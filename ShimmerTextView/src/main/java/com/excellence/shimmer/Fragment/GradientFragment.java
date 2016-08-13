package com.excellence.shimmer.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.excellence.shimmer.R;

/**
 * Created by sony on 2016/8/13.
 */
public class GradientFragment extends BaseFragment
{
	@Override
	protected void setTAG()
	{
        TAG = GradientFragment.class.getSimpleName();
	}

	@Override
	public void setContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
        mRootView = inflater.inflate(R.layout.fragment_gradient, null, false);
	}

	@Override
	public void initView()
	{

	}
}
