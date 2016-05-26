package com.shownest.android.model;

/**
 * 个人的包装类
 * <p>
 * 用于将各种简单信息与业务Bean包装在一起
 * 
 * @author bonepeople
 */
public class Package
{
	public String _tag1 = "";
	public String _tag2 = "";
	public String _tag3 = "";

	public int _int1 = 0;
	public int _int2 = 0;
	public int _int3 = 0;

	public Object _data;

	public Package(Object _data, int _int)
	{
		this._data = _data;
		this._int1 = _int;
	}

	public Package(Object _data, int _int1, int _int2)
	{
		this._data = _data;
		this._int1 = _int1;
		this._int2 = _int2;
	}

	public Package(Object _data, String _str)
	{
		this._data = _data;
		this._tag1 = _str;
	}

	public Package(Object _data, String _str1, String _str2)
	{
		this._data = _data;
		this._tag1 = _str1;
		this._tag2 = _str2;
	}

	public Package(Object _data, String _str, int _int)
	{
		this._data = _data;
		this._tag1 = _str;
		this._int1 = _int;
	}

	public Package(Object _data, String _str1, String _str2, int _int)
	{
		this._data = _data;
		this._tag1 = _str1;
		this._tag2 = _str2;
		this._int1 = _int;
	}
}
