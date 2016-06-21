package com.shownest.android.activity;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Activity;
import com.shownest.android.fragment.Fragment_set;

import android.os.Bundle;
import android.widget.RelativeLayout;

public class Activity_set extends DEBUG_Activity
{
	private static Activity_set _instance;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_basic);
		_relativelayout_wait = (RelativeLayout) findViewById(R.id.relativelayout_wait);
		_instance = this;
		setTitle("设置");

		add_fragment(this, new Fragment_set(), false);
	}

	public static Activity_set get_instance()
	{
		return _instance;
	}
}
