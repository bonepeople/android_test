package com.shownest.android.activity;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Activity;

import android.os.Bundle;

public class Activity_test extends DEBUG_Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
	}

	@Override
	protected String get_class()
	{
		return this.getClass().toString();
	}

}
