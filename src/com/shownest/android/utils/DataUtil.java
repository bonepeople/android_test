package com.shownest.android.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * 操作SharedPreferences的工具类
 * 
 * @author bonepeople
 */
public class DataUtil
{
	/**
	 * 从config.xml文件中读取ukey字段，若为空则返回<b>null</b>
	 */
	public static String get_ukey(Context _context)
	{
		SharedPreferences sharedPreferences = _context.getSharedPreferences("config", Context.MODE_PRIVATE);

		String name = sharedPreferences.getString("ukey", "");

		return name.equals("") ? null : name;
	}

	/**
	 * 将ukey写入config.xml文件中
	 * 
	 * @param _ukey
	 */
	public static void set_ukey(Context _context, String _ukey)
	{
		SharedPreferences sharedPreferences = _context.getSharedPreferences("config", Context.MODE_PRIVATE);

		Editor editor = sharedPreferences.edit();

		editor.putString("ukey", _ukey);

		editor.commit();
	}
}
