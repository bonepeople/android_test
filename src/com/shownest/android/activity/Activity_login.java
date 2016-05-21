package com.shownest.android.activity;

import org.json.JSONException;
import org.json.JSONObject;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Activity;
import com.shownest.android.fragment.Fragment_login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Activity_login extends DEBUG_Activity
{
	public static final int LOGIN_FAILED = 0;
	public static final int LOGIN_SUCCESSFUL = 1;
	private static Activity_login _instance;
	public static Handler _handler = new Handler()
	{
		public void handleMessage(android.os.Message msg)
		{
			String _string_result = "";
			switch (msg.what)
			{
			case LOGIN_FAILED:
				Toast.makeText(_instance, "连接服务器失败。", Toast.LENGTH_SHORT).show();
				break;
			case LOGIN_SUCCESSFUL:
				_string_result = (String) msg.obj;
				handle_string(_string_result);
			}
			_instance.close_wait();
			System.out.println(_string_result);
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_basic);
		_instance = this;
		_relativelayout_wait = (RelativeLayout) findViewById(R.id.relativelayout_wait);
		setTitle("登录");
		setMenu("注册");

		add_fragment(this, new Fragment_login(), false);
	}

	@Override
	public void menu_click()
	{
		Intent _regist = new Intent(this, Activity_regist.class);
		startActivity(_regist);
	}

	private static void handle_string(String str)
	{
		handle_msg(_instance, str);
		try
		{
			JSONObject _obj = new JSONObject(str);
			String _result = _obj.getString("msg");
			Toast.makeText(_instance, _result, Toast.LENGTH_SHORT).show();
			if (_result.equals("用户登录成功"))
			{
				_instance.finish();
			}
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
	}

	public static Activity_login get_instance()
	{
		return _instance;
	}
}
