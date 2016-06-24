package com.shownest.android.activity;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Activity;
import com.shownest.android.fragment.Fragment_my_wallet_yezhu;
import com.shownest.android.utils.UserManager;

import android.os.Bundle;
import android.widget.RelativeLayout;

/**
 * 我的钱包
 * 
 * @author bonepeople
 */
public class Activity_my_wallet extends DEBUG_Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_basic);
		_relativelayout_wait = (RelativeLayout) findViewById(R.id.relativelayout_wait);
		setTitle("我的钱包");

		if (UserManager.get_user_info().get_userType() == 11)
			add_fragment(this, new Fragment_my_wallet_yezhu(), false);
	}

}
