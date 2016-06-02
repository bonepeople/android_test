package com.shownest.android.activity;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Activity;
import com.shownest.android.fragment.Fragment_my_center;
import com.shownest.android.utils.UserManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Activity_my_center extends DEBUG_Activity
{
	public static final int LOGIN_FAILED = 0;
	public static final int LOGIN_SUCCESSFUL = 1;
	private static Activity_my_center _instance;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_basic);
		_instance = this;
		_relativelayout_wait = (RelativeLayout) findViewById(R.id.relativelayout_wait);
		setTitle("个人中心");
		setMenu("设置");

		if (UserManager.is_login())
		{
			add_fragment(_instance, new Fragment_my_center(), false);
		}
		else
		{
			Intent _login = new Intent(_instance, Activity_login.class);
			_instance.startActivity(_login);
			_instance.finish();
		}
	}

	@Override
	public void menu_click()
	{
		Toast.makeText(this, "设置", Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onDestroy()
	{
		_instance = null;
		super.onDestroy();
	}

	public static Activity_my_center get_instance()
	{
		return _instance;
	}
}
