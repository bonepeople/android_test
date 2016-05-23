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
	}

	public void basicinfo(View v)
	{
		Toast.makeText(this, "basicinfo", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(this, Activity_basicinfo.class);
		startActivity(intent);
	}

	public void setinfo_yezhu(View v)
	{
		Toast.makeText(this, "setinfo_yezhu", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(this, Activity_setinfo_yezhu.class);
		startActivity(intent);
	}

	public void setinfo_shigongdui(View v)
	{
		Toast.makeText(this, "setinfo_shigongdui", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(this, Activity_setinfo_shigongdui.class);
		startActivity(intent);
	}

	public void setinfo_shejishi(View v)
	{
		Toast.makeText(this, "setinfo_shejishi", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(this, Activity_setinfo_shejishi.class);
		startActivity(intent);
	}
	
	public void setinfo_jianli(View v)
	{
		Toast.makeText(this, "setinfo_jianli", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(this, Activity_setinfo_jianli.class);
		startActivity(intent);
	}
	
	public void setinfo_offer_auto(View v)
	{
		Intent intent = new Intent(this, Activity_offer_auto.class);
		startActivity(intent);
	}

	public void my_center(View v)
	{
		Toast.makeText(this, "my_center", Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(this, Activity_my_center.class);
		startActivity(intent);
	}
}
