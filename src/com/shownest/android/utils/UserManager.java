package com.shownest.android.utils;

public class UserManager
{
	private static boolean _login = false;
	private static int _type = 100;
	private static String _ukey = "";

	public static boolean is_login()
	{
		return _login;
	}

	public static void set_login(boolean _login)
	{
		UserManager._login = _login;
	}

	public static int get_type()
	{
		return _type;
	}

	public static void set_type(int _type)
	{
		UserManager._type = _type;
	}

	public static String get_ukey()
	{
		return _ukey;
	}

	public static void set_ukey(String _ukey)
	{
		UserManager._ukey = _ukey;
	}

}
