package com.shownest.android.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.shownest.android.utils.JsonUtil;

import android.util.SparseArray;

/**
 * 房间详细信息 <br>
 * 保存每个房间报价的详细信息
 * 
 * @author bonepeople
 */
public class RoomDetail
{
	private double _wallTotals;// 墙面总价
	private double _roofTotals;// 顶面总价
	private double _groundTotals;// 地面总价
	private SparseArray<Package> _wall = new SparseArray<Package>();// 墙面工艺细节
	private SparseArray<Package> _roof = new SparseArray<Package>();// 顶面工艺细节
	private SparseArray<Package> _ground = new SparseArray<Package>();// 地面工艺细节

	public RoomDetail(JSONObject _json) throws JSONException
	{
		this._wallTotals = JsonUtil.get_double(_json, "wallTotals", 1);
		this._roofTotals = JsonUtil.get_double(_json, "roofTotals", 1);
		this._groundTotals = JsonUtil.get_double(_json, "groundTotals", 1);

		JSONArray _array;
		_array = _json.getJSONArray("wall");
		for (int _temp_i = 0; _temp_i < _array.length(); _temp_i++)
		{
			JSONObject _obj = _array.getJSONObject(_temp_i);
			ItemDetail _temp_item = new ItemDetail(_obj);
			_wall.put(_temp_i + 1, new Package(_temp_item, _temp_i + 1));
		}
		_array = _json.getJSONArray("roof");
		for (int _temp_i = 0; _temp_i < _array.length(); _temp_i++)
		{
			JSONObject _obj = _array.getJSONObject(_temp_i);
			ItemDetail _temp_item = new ItemDetail(_obj);
			_roof.put(_temp_i + 1, new Package(_temp_item, _temp_i + 1));
		}
		_array = _json.getJSONArray("ground");
		for (int _temp_i = 0; _temp_i < _array.length(); _temp_i++)
		{
			JSONObject _obj = _array.getJSONObject(_temp_i);
			ItemDetail _temp_item = new ItemDetail(_obj);
			_ground.put(_temp_i + 1, new Package(_temp_item, _temp_i + 1));
		}
	}

	public double get_wallTotals()
	{
		return _wallTotals;
	}

	public void set_wallTotals(double _wallTotals)
	{
		this._wallTotals = _wallTotals;
	}

	public double get_roofTotals()
	{
		return _roofTotals;
	}

	public void set_roofTotals(double _roofTotals)
	{
		this._roofTotals = _roofTotals;
	}

	public double get_groundTotals()
	{
		return _groundTotals;
	}

	public void set_groundTotals(double _groundTotals)
	{
		this._groundTotals = _groundTotals;
	}

	public SparseArray<Package> get_wall()
	{
		return _wall;
	}

	public SparseArray<Package> get_roof()
	{
		return _roof;
	}

	public SparseArray<Package> get_ground()
	{
		return _ground;
	}

}
