package com.shownest.android.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class DataUtil
{

	public static String get_ukey(Context _context)
	{
		SharedPreferences sharedPreferences = _context.getSharedPreferences("config", Context.MODE_PRIVATE);

		String name = sharedPreferences.getString("ukey", "");

		return name.equals("") ? null : name;
	}

	public static void set_ukey(Context _context, String _ukey)
	{
		SharedPreferences sharedPreferences = _context.getSharedPreferences("config", Context.MODE_PRIVATE);

		Editor editor = sharedPreferences.edit();// 获取编辑器

		editor.putString("ukey", _ukey);

		editor.commit();// 提交修改
	}
}
