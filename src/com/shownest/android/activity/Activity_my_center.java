package com.shownest.android.activity;

import org.json.JSONException;
import org.json.JSONObject;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Activity;
import com.shownest.android.fragment.Fragment_my_center;
import com.shownest.android.model.UserInfo;
import com.shownest.android.utils.HttpUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Activity_my_center extends DEBUG_Activity
{
	public static final int LOGIN_FAILED = 0;
	public static final int LOGIN_SUCCESSFUL = 1;
	private static Activity_my_center _instance;
	private static UserInfo _info;// 需要一个标记，代表目前是否正在更新

	public static Handler _handler = new Handler()
	{
		public void handleMessage(android.os.Message msg)
		{
			if (_instance == null)
				return;
			String _string_result = "";
			switch (msg.what)
			{
			case LOGIN_FAILED:
				Toast.makeText(_instance, "连接服务器失败。", Toast.LENGTH_SHORT).show();
				_string_result = (String) msg.obj;
				_instance.finish();
				break;
			case LOGIN_SUCCESSFUL:
				_string_result = (String) msg.obj;
				handle_string(_string_result);
			}
			System.out.println(_string_result);
			_instance.close_wait();
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_basic);
		_instance = this;
		_relativelayout_wait = (RelativeLayout) findViewById(R.id.relativelayout_wait);
		setTitle("个人中心");
		setMenu("设置");

		show_wait();
		HttpUtil.get_userinfo(_handler, LOGIN_SUCCESSFUL, LOGIN_FAILED);
	}

	@Override
	public void menu_click()
	{
		Toast.makeText(this, "设置", Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onDestroy()
	{
		_instance = null;
		super.onDestroy();
	}

	private static void handle_string(String str)
	{
		handle_msg(_instance, str);
		try
		{
			JSONObject _obj = new JSONObject(str);
			String _result = _obj.getString("msg");
			if (_result.equals("用户已经登陆"))
			{
				JSONObject _data = _obj.getJSONArray("data").getJSONObject(0);
				_info = new UserInfo(_data);
				add_fragment(_instance, new Fragment_my_center(), false);
			}
			else
			{
				Intent _login = new Intent(_instance, Activity_login.class);
				_instance.startActivity(_login);
				_instance.finish();
			}
		}
		catch (JSONException e)
		{
			e.printStackTrace();
			Toast.makeText(_instance, "网络连接异常", Toast.LENGTH_SHORT).show();
		}
	}

	public static UserInfo get_userinfo()
	{
		return _info;
	}

	public static Activity_my_center get_instance()
	{
		return _instance;
	}
}
