package com.shownest.android.activity;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Activity;
import com.shownest.android.fragment.Fragment_basicinfo;

import android.os.Bundle;

public class Activity_basicinfo extends DEBUG_Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_basic);

		add_fragment(this, new Fragment_basicinfo(), false);
	}
}
