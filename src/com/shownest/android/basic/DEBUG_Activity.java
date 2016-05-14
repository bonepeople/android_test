package com.shownest.android.basic;

import android.app.Activity;
import android.os.Bundle;

public abstract class DEBUG_Activity extends Activity
{
	private static boolean DEBUG = true;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		if (DEBUG)
			System.out.println(this.getClass().getName() + "-onCreate");
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onStart()
	{
		if (DEBUG)
			System.out.println(this.getClass().getName() + "-onStart");
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	protected void onResume()
	{
		if (DEBUG)
			System.out.println(this.getClass().getName() + "-onResume");
		// TODO Auto-generated method stub
		super.onResume();

	}

	@Override
	protected void onPause()
	{
		if (DEBUG)
			System.out.println(this.getClass().getName() + "-onPause");
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onStop()
	{
		if (DEBUG)
			System.out.println(this.getClass().getName() + "-onStop");
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	protected void onRestart()
	{
		if (DEBUG)
			System.out.println(this.getClass().getName() + "-onRestart");
		// TODO Auto-generated method stub
		super.onRestart();
	}

	@Override
	protected void onDestroy()
	{
		if (DEBUG)
			System.out.println(this.getClass().getName() + "-onDestroy");
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	protected static void handle_msg(DEBUG_Activity _activity, String _msg)
	{
		if (DEBUG)
		{
			String _class = _activity.getClass().getName();
			System.out.println(_class + "-handle msg:" + _msg);
		}
	}

	protected abstract String get_class();

}
