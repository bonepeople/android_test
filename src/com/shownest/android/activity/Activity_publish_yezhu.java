package com.shownest.android.activity;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Activity;
import com.shownest.android.fragment.Fragment_activity_publish_yezhu;

import android.os.Bundle;

/**
 * 业主-发布招标
 * 
 * @author bonepeople
 *
 */
public class Activity_publish_yezhu extends DEBUG_Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_basic);

		add_fragment(this, new Fragment_activity_publish_yezhu(), false);
	}
}
