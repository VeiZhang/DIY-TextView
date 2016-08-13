package com.excellence.shimmer;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.excellence.shimmer.Fragment.BaseFragment;
import com.excellence.shimmer.Fragment.ColorTrackFragment;
import com.excellence.shimmer.Fragment.LyricFragment;
import com.excellence.shimmer.Fragment.GradientFragment;

public class TextActivity extends AppCompatActivity
{
	public static final String PARAM_ITEM = "param_item";
	private FragmentManager mFragmentManager = null;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_text);

		mFragmentManager = getFragmentManager();
		int position = getIntent().getIntExtra(PARAM_ITEM, 0);
		switch (position)
		{
		case 0:
			initGradientView();
			break;

		case 1:
			initColorTrackView();
			break;

		case 2:
			initLyricView();
			break;
		}

	}

	private void initGradientView()
	{
		addOrReplaceFragment(new GradientFragment());
	}

	private void initColorTrackView()
	{
		addOrReplaceFragment(new ColorTrackFragment());
	}

	private void initLyricView()
	{
		addOrReplaceFragment(new LyricFragment());
	}

	private void addOrReplaceFragment(BaseFragment fragment)
	{
		FragmentTransaction transaction = mFragmentManager.beginTransaction();
		transaction.add(R.id.frame, fragment, fragment.getTAG());
		transaction.commit();
	}
}
