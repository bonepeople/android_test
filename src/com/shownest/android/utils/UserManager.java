package com.shownest.android.utils;

import org.json.JSONObject;

import com.shownest.android.model.UserInfo;

import android.os.Handler;

public class UserManager
{
	private static UserInfo _user_info;// 需要一个标记，代表目前是否正在更新
	private static boolean _login = false;

	private static Handler _handler = new Handler()
	{
		public void handleMessage(android.os.Message msg)
		{
			String _string_result = "";
			switch (msg.what)
			{
			case 0:
				set_login(false);
				break;
			case 1:
				_string_result = (String) msg.obj;
				System.out.println(_string_result);
				try
				{
					JSONObject _obj = new JSONObject(_string_result);
					JSONObject _data = _obj.getJSONArray("data").getJSONObject(0);
					set_user_info(new UserInfo(_data));
					System.out.println("fresh user data");
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		};
	};

	public static UserInfo get_user_info()
	{
		return _user_info;
	}

	public static void set_user_info(UserInfo _user_info)
	{
		UserManager._user_info = _user_info;
	}

	public static boolean is_login()
	{
		return _login;
	}

	public static void set_login(boolean _login)
	{
		UserManager._login = _login;
		if (_login)
			HttpUtil.get_userinfo(_handler, 1, 0);
	}
}
