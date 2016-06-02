package com.shownest.android.utils;

import com.shownest.android.model.UserInfo;

public class UserManager
{
	private static UserInfo _user_info;// 需要一个标记，代表目前是否正在更新
	private static boolean _login = false;

	public static UserInfo get_user_info()
	{
		return _user_info;
	}

	public static void set_user_info(UserInfo _info)
	{
		UserManager._user_info = _info;
		_login = _info != null ? true : false;
	}

	public static boolean is_login()
	{
		return _login;
	}
}
