package com.shownest.android.utils;

import com.shownest.android.model.UserInfo;

public class UserManager
{
	private static String _SESSION = null; // 定义一个静态的字段，保存sessionID
	private static String _ukey = "";// 用户登陆时返回的ukey
	private static UserInfo _user_info;// 需要一个标记，代表目前是否正在更新
	private static boolean _login = false;

	public static UserInfo get_user_info()
	{
		return _user_info;
	}

	public static void set_user_info(UserInfo _info)
	{
		UserManager._user_info = _info;
		if (_info == null)
		{
			_login = false;
			_ukey = "";
			_SESSION = null;
		}
		else
		{
			_login = true;
		}
	}

	public static boolean is_login()
	{
		return _login;
	}

	public static String get_ukey()
	{
		return _ukey;
	}

	public static void set_ukey(String _ukey)
	{
		UserManager._ukey = _ukey;
	}

	public static String get_SESSION()
	{
		return _SESSION;
	}

	public static void set_SESSION(String _SESSION)
	{
		UserManager._SESSION = _SESSION;
	}

}
