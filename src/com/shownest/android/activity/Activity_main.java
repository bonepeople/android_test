package com.shownest.android.activity;

import com.shownest.android.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Activity_main extends Activity
{
	private static boolean DEBUG = true;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		if (DEBUG)
			System.out.println("Activity_login onCreate");
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}

	public void login(View v)
	{
		Toast.makeText(this, "login", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(this, Activity_login.class);
		startActivity(intent);
	}

	public void regist(View v)
	{
		Toast.makeText(this, "regist", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(this, Activity_regist.class);
		startActivity(intent);
	}

	public void publish(View v)
	{
		Toast.makeText(this, "publish", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(this, Activity_publish_yezhu.class);
		startActivity(intent);
	}

}
