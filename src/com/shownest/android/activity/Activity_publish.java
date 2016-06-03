package com.shownest.android.activity;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Activity;
import com.shownest.android.fragment.Fragment_publish;

import android.os.Bundle;
import android.widget.RelativeLayout;

/**
 * 发布招标
 * 
 * @author bonepeople
 */
public class Activity_publish extends DEBUG_Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_basic);
		_relativelayout_wait = (RelativeLayout) findViewById(R.id.relativelayout_wait);
		setTitle("发布招标");

		add_fragment(this, new Fragment_publish(), false);
	}
}
