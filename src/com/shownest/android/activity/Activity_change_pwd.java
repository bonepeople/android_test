package com.shownest.android.activity;

import com.shownest.android.R;
import com.shownest.android.fragment.Fragment_change_pwd;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

public class Activity_change_pwd extends Activity
{
	private static boolean DEBUG = true;
	
	private static Activity_change_pwd _instance;
	private static Context _context;
	private static Fragment_change_pwd _fragment_change_pwd;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		if (DEBUG)
			System.out.println("Activity_change_pwd onCreate");
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		_instance = this;
		_context = this.getApplicationContext();
		
		setContentView(R.layout.activity_change_pwd);
		_fragment_change_pwd = (Fragment_change_pwd) getFragmentManager().findFragmentById(R.id.fragment_change_pwd);
	}
}
