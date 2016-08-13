package com.excellence.shimmer.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by sony on 2016/8/13.
 */
public abstract class BaseFragment extends Fragment
{
	protected String TAG = null;
	protected View mRootView = null;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setTAG();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		setContentView(inflater, container, savedInstanceState);
		return mRootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		initView();
	}

	protected View findViewById(int viewId)
	{
		return mRootView.findViewById(viewId);
	}

	public String getTAG()
	{
		return TAG;
	}

	protected abstract void setTAG();

	public abstract void setContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

	public abstract void initView();
}
