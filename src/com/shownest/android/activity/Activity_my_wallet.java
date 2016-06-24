package com.shownest.android.activity;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Activity;
import com.shownest.android.fragment.Fragment_my_wallet_yezhu;
import com.shownest.android.utils.UserManager;

import android.content.Intent;
import android.os.Bundle;

/**
 * 我的中心
 * 
 * @author bonepeople
 */
public class Activity_my_wallet extends DEBUG_Activity
{
	public static final int RESULT_SET = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_basic);
		setTitle("我的钱包");

		if (UserManager.get_user_info().get_userType() == 11)
			add_fragment(this, new Fragment_my_wallet_yezhu(), false);
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
}
