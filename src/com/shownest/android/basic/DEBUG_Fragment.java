package com.shownest.android.basic;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class DEBUG_Fragment extends Fragment
{
	private static boolean DEBUG = true;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		if (DEBUG)
			System.out.println(this.getClass().getName() + "-onCreateView");
		return null;
	}

	@Override
	public void onResume()
	{
		if (DEBUG)
			System.out.println(this.getClass().getName() + "-onResume");
		setContent();
		super.onResume();
	}

	@Override
	public void onPause()
	{
		if (DEBUG)
			System.out.println(this.getClass().getName() + "-onPause");
		super.onPause();
	}

	@Override
	public void onStop()
	{
		if (DEBUG)
			System.out.println(this.getClass().getName() + "-onStop");
		super.onStop();
	}

	@Override
	public void onDestroy()
	{
		if (DEBUG)
			System.out.println(this.getClass().getName() + "-onDestroy");
		super.onDestroy();
	}
	
	public void setContent()
	{
		//在fragment的onResume中会被调用，需要设置初始数据的fragment只需重写此方法。
	}
}
