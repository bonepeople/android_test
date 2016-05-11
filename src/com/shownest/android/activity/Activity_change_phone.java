package com.shownest.android.activity;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Activity;

import android.os.Bundle;

public class Activity_change_phone extends DEBUG_Activity
{
	private static String _change_phone = "";
	private static String _change_code = "";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_change_phone);
	}

	@Override
	protected String get_class()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public static String get_change_phone()
	{
		return _change_phone;
	}

	public static void set_change_phone(String _change_phone_new)
	{
		_change_phone = _change_phone_new;
	}

	public static String get_change_code()
	{
		return _change_code;
	}

	public static void set_change_code(String _change_code_new)
	{
		_change_code = _change_code_new;
	}
}
