package com.shownest.android.activity;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Activity;
import com.shownest.android.thread.Service_login;
import com.shownest.android.utils.DataUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * 主界面(测试使用)
 * 
 * @author bonepeople
 */
public class Activity_main extends DEBUG_Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_test);
		if (DataUtil.get_ukey(this) != null)
			startService(new Intent(this, Service_login.class));
	}

	public void login(View v)
	{
		Intent intent = new Intent(this, Activity_login.class);
		startActivity(intent);
	}

	public void regist(View v)
	{
		Intent intent = new Intent(this, Activity_regist.class);
		startActivity(intent);
	}

	public void publish(View v)
	{
		Intent intent = new Intent(this, Activity_publish_yezhu.class);
		startActivity(intent);
	}

	public void forget(View v)
	{
		Intent intent = new Intent(this, Activity_forget.class);
		startActivity(intent);
	}

	public void test(View v)
	{
		Intent intent = new Intent(this, Activity_test.class);
		startActivity(intent);
	}

	public void change_pwd(View v)
	{
		Intent intent = new Intent(this, Activity_change_pwd.class);
		startActivity(intent);
	}

	public void change_phone(View v)
	{
		Intent intent = new Intent(this, Activity_change_phone.class);
		startActivity(intent);
	}

	public void select_role(View v)
	{
		Intent intent = new Intent(this, Activity_select_role.class);
		startActivity(intent);
	}

	public void basicinfo(View v)
	{
		Intent intent = new Intent(this, Activity_basicinfo.class);
		startActivity(intent);
	}

	public void setinfo_yezhu(View v)
	{
		Intent intent = new Intent(this, Activity_setinfo_yezhu.class);
		startActivity(intent);
	}

	public void setinfo_shigongdui(View v)
	{
		Intent intent = new Intent(this, Activity_setinfo_shigongdui.class);
		startActivity(intent);
	}

	public void setinfo_shejishi(View v)
	{
		Intent intent = new Intent(this, Activity_setinfo_shejishi.class);
		startActivity(intent);
	}

	public void setinfo_jianli(View v)
	{
		Intent intent = new Intent(this, Activity_setinfo_jianli.class);
		startActivity(intent);
	}

	public void setinfo_offer_auto(View v)
	{
		Intent intent = new Intent(this, Activity_offer_auto.class);
		startActivity(intent);
	}

	public void addhouse(View v)
	{
		Intent intent = new Intent(this, Activity_house_add.class);
		startActivity(intent);
	}
	
	public void AH_bid(View v)
	{
		Intent intent = new Intent(this, Activity_AH_bid.class);
		startActivity(intent);
	}

	public void my_center(View v)
	{
		Intent intent = new Intent(this, Activity_my_center.class);
		startActivity(intent);
	}

}
