package com.shownest.android.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * json工具类 <br>
 * 主要用于读取JSONObject
 * 
 * @author bonepeople
 */
public class JsonUtil
{
	public static JSONArray get_array(JSONObject _json, String _name) throws JSONException
	{
		JSONArray _array = null;
		if (_json.has(_name))
		{
			_array = _json.getJSONArray(_name);
		}
		return _array;
	}

	public static String get_string(JSONObject _json, String _name, String _default) throws JSONException
	{
		String _result = _default;
		if (_json.has(_name))
		{
			String _temp_str = _json.getString(_name);
			if (!_temp_str.equals("null"))
				_result = _temp_str;
		}
		return _result;
	}

	public static int get_int(JSONObject _json, String _name, int _default) throws JSONException
	{
		int _result = _default;
		if (_json.has(_name))
		{
			String _temp_str = _json.getString(_name);
			if (!_temp_str.equals("null"))
				_result = _json.getInt(_name);
		}
		return _result;
	}

	public static double get_double(JSONObject _json, String _name, double _default) throws JSONException
	{
		double _result = _default;
		if (_json.has(_name))
		{
			String _temp_str = _json.getString(_name);
			if (!_temp_str.equals("null"))
				_result = _json.getDouble(_name);
		}
		return _result;
	}

	public static long get_long(JSONObject _json, String _name, long _default) throws JSONException
	{
		long _result = _default;
		if (_json.has(_name))
		{
			String _temp_str = _json.getString(_name);
			if (!_temp_str.equals("null"))
				_result = _json.getLong(_name);
		}
		return _result;
	}

	public static boolean get_bool(JSONObject _json, String _name, boolean _default) throws JSONException
	{
		boolean _result = _default;
		if (_json.has(_name))
		{
			String _temp_str = _json.getString(_name);
			if (!_temp_str.equals("null"))
				_result = _temp_str.equals("y") ? true : false;
		}
		return _result;
	}
}
