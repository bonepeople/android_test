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
			System.out.println(get_class() + "-onCreateView");
		return null;
	}

	@Override
	public void onResume()
	{
		if (DEBUG)
			System.out.println(get_class() + "-onResume");
		super.onResume();
	}

	@Override
	public void onPause()
	{
		if (DEBUG)
			System.out.println(get_class() + "-onPause");
		super.onPause();
	}

	@Override
	public void onStop()
	{
		if (DEBUG)
			System.out.println(get_class() + "-onStop");
		super.onStop();
	}

	@Override
	public void onDestroy()
	{
		if (DEBUG)
			System.out.println(get_class() + "-onDestroy");
		super.onDestroy();
	}

	protected abstract String get_class();
}
