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
	private String _tag;// 目前暂时无用，不过也保存了类型：room，parlour，kitchen，toilet，balcony，hydropower，mount，cost，tax
	private double _wallTotals;// 墙面总价
	private double _roofTotals;// 顶面总价
	private double _groundTotals;// 地面总价
	private double _hydropowerTotals;// 水电总价
	private double _mountTotals;// 安装总价
	private double _costTotals;// 杂费总价
	private double _taxTotals;// 税费总价
	private SparseArray<Package> _wall = new SparseArray<Package>();// 墙面工艺细节
	private SparseArray<Package> _roof = new SparseArray<Package>();// 顶面工艺细节
	private SparseArray<Package> _ground = new SparseArray<Package>();// 地面工艺细节
	private SparseArray<Package> _hydropower = new SparseArray<Package>();// 水电工艺细节
	private SparseArray<Package> _mount = new SparseArray<Package>();// 安装工艺细节
	private SparseArray<Package> _cost = new SparseArray<Package>();// 杂费工艺细节
	private SparseArray<Package> _tax = new SparseArray<Package>();// 税费工艺细节

	public RoomDetail(JSONObject _json, String _tag) throws JSONException
	{
		this._tag = _tag;
		this._wallTotals = JsonUtil.get_double(_json, "wallTotals", 0);
		this._roofTotals = JsonUtil.get_double(_json, "roofTotals", 0);
		this._groundTotals = JsonUtil.get_double(_json, "groundTotals", 0);
		this._hydropowerTotals = JsonUtil.get_double(_json, "hydropowerTotals", 0);
		this._mountTotals = JsonUtil.get_double(_json, "mountTotals", 0);
		this._costTotals = JsonUtil.get_double(_json, "costTotals", 0);
		this._taxTotals = JsonUtil.get_double(_json, "taxTotals", 0);

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

	public String get_tag()
	{
		return _tag;
	}

	public void set_tag(String _tag)
	{
		this._tag = _tag;
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

	public double get_hydropowerTotals()
	{
		return _hydropowerTotals;
	}

	public void set_hydropowerTotals(double _hydropowerTotals)
	{
		this._hydropowerTotals = _hydropowerTotals;
	}

	public double get_mountTotals()
	{
		return _mountTotals;
	}

	public void set_mountTotals(double _mountTotals)
	{
		this._mountTotals = _mountTotals;
	}

	public double get_costTotals()
	{
		return _costTotals;
	}

	public void set_costTotals(double _costTotals)
	{
		this._costTotals = _costTotals;
	}

	public double get_taxTotals()
	{
		return _taxTotals;
	}

	public void set_taxTotals(double _taxTotals)
	{
		this._taxTotals = _taxTotals;
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
