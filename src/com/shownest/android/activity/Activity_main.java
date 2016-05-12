package com.shownest.android.activity;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Activity_main extends DEBUG_Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
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

	public void forget(View v)
	{
		Toast.makeText(this, "forget", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(this, Activity_forget.class);
		startActivity(intent);
	}

	public void test(View v)
	{
		Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(this, Activity_test.class);
		startActivity(intent);
	}

	public void change_pwd(View v)
	{
		Toast.makeText(this, "change_pwd", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(this, Activity_change_pwd.class);
		startActivity(intent);
	}

	public void change_phone(View v)
	{
		Toast.makeText(this, "change_phone", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(this, Activity_change_phone.class);
		startActivity(intent);
	}

	public void select_role(View v)
	{
		Toast.makeText(this, "select_role", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(this, Activity_select_role.class);
		startActivity(intent);
		finish();
	}

	@Override
	protected String get_class()
	{
		return this.getClass().toString();
	}
}
