package com.shownest.android.activity;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Activity;
import com.shownest.android.fragment.Fragment_my_center;
import com.shownest.android.utils.DataUtil;
import com.shownest.android.utils.UserManager;

import android.content.Intent;
import android.os.Bundle;

/**
 * 我的中心
 * 
 * @author bonepeople
 */
public class Activity_my_center extends DEBUG_Activity
{
	public static final int RESULT_SET = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_basic);
		setTitle("个人中心");
		setMenu("退出");

		if (UserManager.is_login())
		{
			add_fragment(this, new Fragment_my_center(), false);
		}
		else
		{
			Intent _login = new Intent(this, Activity_login.class);
			startActivity(_login);
			finish();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if (resultCode == -1)
		{
			switch (requestCode)
			{
			case RESULT_SET:
				finish();
				break;
			}
		}
		else
			System.out.println("resultCode=" + resultCode);
	}

	@Override
	public void menu_click()
	{
		// Intent _set = new Intent(this, Activity_set.class);
		// startActivityForResult(_set, RESULT_SET);
		UserManager.set_user_info(null);
		DataUtil.set_ukey(this, "");
		finish();
	}
}
