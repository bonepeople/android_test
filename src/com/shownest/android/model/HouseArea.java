package com.shownest.android.model;

import java.util.ArrayList;
import java.util.HashMap;

public class HouseArea
{
	private int _total_area = 0;
	private int house_num = 5;
	// 左边的float代表当前房间的面积，右侧的数字0表示未被用户修改，1表示已被用户修改，用户最后均衡面试使用
	private HashMap<Float, Integer> _room = new HashMap<Float, Integer>();// 卧室
	private HashMap<Float, Integer> _parlour = new HashMap<Float, Integer>();// 客厅
	private HashMap<Float, Integer> _toilet = new HashMap<Float, Integer>();// 卫生间
	private HashMap<Float, Integer> _balcony = new HashMap<Float, Integer>();// 阳台
	private HashMap<Float, Integer> _kitchen = new HashMap<Float, Integer>();// 厨房

	public HouseArea()
	{

	}

	public void set_total_area(int _area)
	{
		this._total_area = _area;
	}

	public int get_total_area()
	{
		return this._total_area;
	}

	public void set_house_num(String _num)
	{
		String[] _number = _num.split(",");
		for (String string : _number)
		{

		}

		int _temp_i;
		_room.clear();
		// _room.add(0f);
		for (_temp_i = 0; _temp_i < Integer.parseInt(_number[0]); _temp_i++)
		{

		}
	}
}
