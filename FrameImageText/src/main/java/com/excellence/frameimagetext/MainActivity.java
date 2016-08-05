package com.excellence.frameimagetext;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
	private List<Integer> resList = null;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initData();
		textFrame1();
		textFrame2();
		textFrame3();
	}

	private void textFrame3()
	{
		//还是需要知道变化的属性，即int...
		AnimPaddingDrawableTextView animPaddingDrawableTextView = (AnimPaddingDrawableTextView) findViewById(R.id.view);
		ObjectAnimator objectAnimator = ObjectAnimator.ofInt(animPaddingDrawableTextView, "startLevel", 30);
		objectAnimator.setRepeatMode(ObjectAnimator.RESTART);
		objectAnimator.setRepeatCount(ObjectAnimator.INFINITE);
		objectAnimator.setDuration(500);
		objectAnimator.start();
	}

	private void initData()
	{
		resList = new ArrayList<>();
		do
		{
			int resId = getResources().getIdentifier("download_state_" + resList.size(), "drawable", getPackageName());
			if (resId == 0)
				break;
			resList.add(resId);
		} while (true);
		System.out.println(resList.size() + " **************** ");
	}

	private void textFrame2()
	{
		AnimPaddingDrawableTextView animPaddingDrawableTextView = (AnimPaddingDrawableTextView) findViewById(R.id.anim_image_textview);
		animPaddingDrawableTextView.setFrameResource(resList);

		ObjectAnimator objectAnimator = ObjectAnimator.ofInt(animPaddingDrawableTextView, "level", resList.size());
		objectAnimator.setRepeatMode(ObjectAnimator.RESTART);
		objectAnimator.setRepeatCount(ObjectAnimator.INFINITE);
		objectAnimator.setDuration(500);
		objectAnimator.start();

		animPaddingDrawableTextView.setText(AnimPaddingDrawableTextView.class.getSimpleName());
		animPaddingDrawableTextView.setTextColor(Color.WHITE);
		animPaddingDrawableTextView.setTextSize(25);
	}

	private void textFrame1()
	{

		FrameImageText frameImageText = (FrameImageText) findViewById(R.id.frame_image_text);

		/**一种方式
		 int[] res = { R.drawable.download_state_0, R.drawable.download_state_1, R.drawable.download_state_2, R.drawable.download_state_3, R.drawable.download_state_4, R.drawable.download_state_5,
		 R.drawable.download_state_6, R.drawable.download_state_7, R.drawable.download_state_8, R.drawable.download_state_9, R.drawable.download_state_10, R.drawable.download_state_11,
		 R.drawable.download_state_12, R.drawable.download_state_13, R.drawable.download_state_14, R.drawable.download_state_15, R.drawable.download_state_16, R.drawable.download_state_17,
		 R.drawable.download_state_18, R.drawable.download_state_19, R.drawable.download_state_20, R.drawable.download_state_21, R.drawable.download_state_22, R.drawable.download_state_23,
		 R.drawable.download_state_24 };
		 List<Integer> resList = new ArrayList<>();
		 for (int i = 0; i < res.length; i++)
		 {
		 resList.add(res[i]);
		 }
		 */

		/**第二种方式
		 List<Integer> resList = Arrays.asList(R.drawable.download_state_0, R.drawable.download_state_1, R.drawable.download_state_2, R.drawable.download_state_3, R.drawable.download_state_4, R.drawable.download_state_5,
		 R.drawable.download_state_6, R.drawable.download_state_7, R.drawable.download_state_8, R.drawable.download_state_9, R.drawable.download_state_10, R.drawable.download_state_11,
		 R.drawable.download_state_12, R.drawable.download_state_13, R.drawable.download_state_14, R.drawable.download_state_15, R.drawable.download_state_16, R.drawable.download_state_17,
		 R.drawable.download_state_18, R.drawable.download_state_19, R.drawable.download_state_20, R.drawable.download_state_21, R.drawable.download_state_22, R.drawable.download_state_23,
		 R.drawable.download_state_24);
		 */

		frameImageText.setFrameResource(resList);
		ObjectAnimator objectAnimator = ObjectAnimator.ofInt(frameImageText, "level", resList.size());
		objectAnimator.setRepeatMode(ObjectAnimator.RESTART);
		objectAnimator.setRepeatCount(ObjectAnimator.INFINITE);
		objectAnimator.setDuration(500);
		objectAnimator.start();

		frameImageText.setText(FrameImageText.class.getSimpleName());
		frameImageText.setTextColor(Color.WHITE);
		frameImageText.setTextSize(25);
	}
}
