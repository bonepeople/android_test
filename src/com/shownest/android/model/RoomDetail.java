package com.shownest.android.model;

import java.util.HashMap;

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
	private String _tag;// 目前暂时无用，不过也保存了类型：room，parlour，kitchen，toilet，balcony，hydropower，mount，cost，tax
	private SparseArray<Package> _wall = new SparseArray<Package>();// 墙面工艺细节
	private SparseArray<Package> _roof = new SparseArray<Package>();// 顶面工艺细节
	private SparseArray<Package> _ground = new SparseArray<Package>();// 地面工艺细节
	private SparseArray<Package> _hydropower = new SparseArray<Package>();// 水电工艺细节
	private SparseArray<Package> _mount = new SparseArray<Package>();// 安装工艺细节
	private SparseArray<Package> _cost = new SparseArray<Package>();// 杂费工艺细节
	private SparseArray<Package> _tax = new SparseArray<Package>();// 税费工艺细节

	private HashMap<String, Double> _totals = new HashMap<>();
	private HashMap<String, SparseArray<ItemDetail>> _details = new HashMap<String, SparseArray<ItemDetail>>();

	public RoomDetail(JSONObject _json, String _tag) throws JSONException
	{
		this._tag = _tag;

		// ==========
		_totals.put("ground", JsonUtil.get_double(_json, "groundTotals", 0));
		_totals.put("wall", JsonUtil.get_double(_json, "wallTotals", 0));
		_totals.put("roof", JsonUtil.get_double(_json, "roofTotals", 0));
		_totals.put("hydropower", JsonUtil.get_double(_json, "hydropowerTotals", 0));
		_totals.put("mount", JsonUtil.get_double(_json, "mountTotals", 0));
		_totals.put("cost", JsonUtil.get_double(_json, "costTotals", 0));
		_totals.put("tax", JsonUtil.get_double(_json, "taxTotals", 0));
		// ==========

		creat_items(_json, "ground");
		creat_items(_json, "wall");
		creat_items(_json, "roof");
		creat_items(_json, "hydropower");
		creat_items(_json, "mount");
		creat_items(_json, "cost");
		creat_items(_json, "tax");

		JSONArray _array;
		_array = JsonUtil.get_array(_json, "wall");
		for (int _temp_i = 0; _array != null && _temp_i < _array.length(); _temp_i++)
		{
			JSONObject _obj = _array.getJSONObject(_temp_i);
			ItemDetail _temp_item = new ItemDetail(_obj, "wall");
			_wall.put(_temp_i + 1, new Package(_temp_item, _temp_i + 1));
		}
		_array = JsonUtil.get_array(_json, "roof");
		for (int _temp_i = 0; _array != null && _temp_i < _array.length(); _temp_i++)
		{
			JSONObject _obj = _array.getJSONObject(_temp_i);
			ItemDetail _temp_item = new ItemDetail(_obj, "roof");
			_roof.put(_temp_i + 1, new Package(_temp_item, _temp_i + 1));
		}
		_array = JsonUtil.get_array(_json, "ground");
		for (int _temp_i = 0; _array != null && _temp_i < _array.length(); _temp_i++)
		{
			JSONObject _obj = _array.getJSONObject(_temp_i);
			ItemDetail _temp_item = new ItemDetail(_obj, "ground");
			_ground.put(_temp_i + 1, new Package(_temp_item, _temp_i + 1));
		}
		_array = JsonUtil.get_array(_json, "hydropower");
		for (int _temp_i = 0; _array != null && _temp_i < _array.length(); _temp_i++)
		{
			JSONObject _obj = _array.getJSONObject(_temp_i);
			ItemDetail _temp_item = new ItemDetail(_obj, "hydropower");
			_hydropower.put(_temp_i + 1, new Package(_temp_item, _temp_i + 1));
		}
		_array = JsonUtil.get_array(_json, "mount");
		for (int _temp_i = 0; _array != null && _temp_i < _array.length(); _temp_i++)
		{
			JSONObject _obj = _array.getJSONObject(_temp_i);
			ItemDetail _temp_item = new ItemDetail(_obj, "mount");
			_mount.put(_temp_i + 1, new Package(_temp_item, _temp_i + 1));
		}
		_array = JsonUtil.get_array(_json, "cost");
		for (int _temp_i = 0; _array != null && _temp_i < _array.length(); _temp_i++)
		{
			JSONObject _obj = _array.getJSONObject(_temp_i);
			ItemDetail _temp_item = new ItemDetail(_obj, "cost");
			_cost.put(_temp_i + 1, new Package(_temp_item, _temp_i + 1));
		}
		_array = JsonUtil.get_array(_json, "tax");
		for (int _temp_i = 0; _array != null && _temp_i < _array.length(); _temp_i++)
		{
			JSONObject _obj = _array.getJSONObject(_temp_i);
			ItemDetail _temp_item = new ItemDetail(_obj, "tax");
			_tax.put(_temp_i + 1, new Package(_temp_item, _temp_i + 1));
		}
	}

	private void creat_items(JSONObject _json, String _tag) throws JSONException
	{
		JSONArray _json_array;
		_json_array = JsonUtil.get_array(_json, _tag);
		SparseArray<ItemDetail> _temp_array = new SparseArray<ItemDetail>();
		for (int _temp_i = 0; _json_array != null && _temp_i < _json_array.length(); _temp_i++)
		{
			JSONObject _obj = _json_array.getJSONObject(_temp_i);
			ItemDetail _temp_item = new ItemDetail(_obj, _tag);
			_temp_array.put(_temp_i, _temp_item);
		}
		_details.put(_tag, _temp_array);
	}

	public String get_tag()
	{
		return _tag;
	}

	public double get_totals(String _name)
	{
		return _totals.get(_name);
	}

	public void set_totals(String _name, double _number)
	{
		_totals.put(_name, _number);
	}

	public SparseArray<ItemDetail> get_details(String _name)
	{
		return _details.get(_name);
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

	public SparseArray<Package> get_hydropower()
	{
		return _hydropower;
	}

	public SparseArray<Package> get_mount()
	{
		return _mount;
	}

	public SparseArray<Package> get_cost()
	{
		return _cost;
	}

	public SparseArray<Package> get_tax()
	{
		return _tax;
	}
}
