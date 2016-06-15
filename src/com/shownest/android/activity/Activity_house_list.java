package com.shownest.android.activity;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Activity;
import com.shownest.android.fragment.Fragment_webview;
import com.shownest.android.model.OnChangeListener;
import com.shownest.android.utils.UserManager;

import android.os.Bundle;
import android.widget.RelativeLayout;

public class Activity_house_list extends DEBUG_Activity implements OnChangeListener
{
	private static Activity_house_list _instance;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_basic);
		_relativelayout_wait = (RelativeLayout) findViewById(R.id.relativelayout_wait);
		_instance = this;

		hideTitle(this);

		String _url = "http://app.shownest.com/house/getHouseList?ukey=" + UserManager.get_ukey();
		show_wait();
		add_fragment(this, new Fragment_webview(_url, "web", this), false);
	}

	@Override
	public void onChange(String _tag, String[] _args)
	{
		this.close_wait();
	}
}
