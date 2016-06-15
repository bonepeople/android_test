package com.shownest.android.activity;

import org.json.JSONException;
import org.json.JSONObject;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Activity;
import com.shownest.android.fragment.Fragment_test;
import com.shownest.android.fragment.Fragment_webview;
import com.shownest.android.model.OnChangeListener;
import com.shownest.android.model.UserInfo;
import com.shownest.android.utils.CommonUtil;
import com.shownest.android.utils.JsonUtil;
import com.shownest.android.utils.UserManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

/**
 * 测试页面
 * 
 * @author bonepeople
 */
public class Activity_test extends DEBUG_Activity
{
	public static final int TEST_FAILED = 0;
	public static final int TEST_SUCCESSFUL = 1;
	private static Activity_test _instance;
	public static Handler _handler = new Handler()
	{
		public void handleMessage(android.os.Message msg)
		{
			switch (msg.what)
			{
			case TEST_FAILED:
				Toast.makeText(_instance, "连接服务器失败。", Toast.LENGTH_SHORT).show();
				break;
			case TEST_SUCCESSFUL:
				handle_string(msg.what, (String) msg.obj);
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_basic);
		_instance = this;
		setTitle("测试");

		add_fragment(this, new Fragment_test(), false);
	}

	private static void handle_string(int _what, String _str)
	{
		handle_msg(_instance, _str);
		try
		{
			JSONObject _obj = new JSONObject(_str);
			if (get_code(_obj))
				switch (_what)
				{
				case TEST_SUCCESSFUL:
					Toast.makeText(_instance, JsonUtil.get_string(_obj, "msg", "连接服务器失败。"), Toast.LENGTH_SHORT).show();
					break;
				}
			else
				Toast.makeText(_instance, JsonUtil.get_string(_obj, "msg", "连接服务器失败。"), Toast.LENGTH_SHORT).show();
		}
		catch (JSONException e)
		{
			e.printStackTrace();
			Toast.makeText(_instance, "连接服务器失败。", Toast.LENGTH_SHORT).show();
		}
	}

	public void test(View v)
	{

		if (UserManager.is_login())
		{

			// add_fragment(this, new
			// Fragment_webview("http://baike.baidu.com/link?url=0dlZFtaGLAYuloobGXYfOxmOlnRlzGgLz810J1z8LSJ5hUDlKGU3EJvAtnJcbf3smvZ5inON_d3fdx30069O3_"),
			// true);
			String _url = "http://app.shownest.com/shuttering/getDesiShutterList?ukey=" + UserManager.get_ukey();

			add_fragment(this, new Fragment_webview(_url, "web", new OnChangeListener()
			{
				@Override
				public void onChange(String _tag, String[] _args)
				{
					// TODO Auto-generated method stub
					Toast.makeText(Activity_test.this, "网页加载完成", Toast.LENGTH_SHORT).show();
				}
			}), true);
		}
		else
		{
			Intent intent = new Intent(this, Activity_login.class);
			startActivity(intent);
		}

		return;
	}

	public static Activity_test get_instance()
	{
		return _instance;
	}
}
