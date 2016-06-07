package com.shownest.android.activity;

import org.json.JSONException;
import org.json.JSONObject;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Activity;
import com.shownest.android.fragment.Fragment_login;
import com.shownest.android.model.UserInfo;
import com.shownest.android.utils.HttpUtil;
import com.shownest.android.utils.JsonUtil;
import com.shownest.android.utils.UserManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Activity_login extends DEBUG_Activity
{
	public static final int LOGIN_FAILED = 0;
	public static final int LOGIN_SUCCESSFUL = 1;
	public static final int GET_FAILED = 2;
	public static final int GET_SUCCESSFUL = 3;
	private static Activity_login _instance;
	public static Handler _handler = new Handler()
	{
		public void handleMessage(android.os.Message msg)
		{
			switch (msg.what)
			{
			case GET_FAILED:
			case LOGIN_FAILED:
				Toast.makeText(_instance, "连接服务器失败。", Toast.LENGTH_SHORT).show();
				_instance.close_wait();
				break;
			case GET_SUCCESSFUL:
			case LOGIN_SUCCESSFUL:
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

	private static void handle_string(int _what, String _str)
	{
		handle_msg(_instance, _str);
		try
		{
			JSONObject _obj = new JSONObject(_str);
			if (get_code(_obj))
				switch (_what)
				{
				case LOGIN_SUCCESSFUL:
					HttpUtil.get_userinfo(_handler, GET_SUCCESSFUL, GET_FAILED);
					break;
				case GET_SUCCESSFUL:
					JSONObject _data = _obj.getJSONArray("data").getJSONObject(0);
					UserManager.set_user_info(new UserInfo(_data));
					Toast.makeText(_instance, "用户登录成功", Toast.LENGTH_SHORT).show();
					_instance.close_wait();
					_instance.finish();
					break;
				}
			else
			{
				Toast.makeText(_instance, JsonUtil.get_string(_obj, "msg", "连接服务器失败。"), Toast.LENGTH_SHORT).show();
				_instance.close_wait();
			}
		}
		catch (JSONException e)
		{
			e.printStackTrace();
			Toast.makeText(_instance, "连接服务器失败。", Toast.LENGTH_SHORT).show();
			_instance.close_wait();
		}
	}

	public static Activity_login get_instance()
	{
		return _instance;
	}
}
