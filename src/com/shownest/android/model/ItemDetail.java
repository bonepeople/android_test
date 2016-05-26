package com.shownest.android.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 工艺详细信息
 * 
 * <p>
 * 包括工艺名称，辅材型号，工艺说明，单价，工程量等。主要是用于保存报价单中每个工艺的详细信息
 * 
 * @author bonepeople
 */
public class ItemDetail
{
	private int _common;// 工艺标志，1常用 2非常用
	private int _delMarks;// 删除修改标记
	private int _itemId;// 工艺ID
	private String _itemName;// 工艺名称
	private String _material;// 辅材品牌型号
	private String _metricUnit;// 计算单位
	private double _number;// 工程量
	private double _price;// 单价
	private double _total;// 合计，由单价乘以工程量计算得出
	private String _technics;// 工艺说明
	private String _unit;// 货币单位：元

	public ItemDetail(JSONObject _json) throws JSONException
	{
		this._common = get_int(_json, "common", 1);
		this._delMarks = get_int(_json, "delMarks", 1);
		this._itemId = get_int(_json, "itemId", 0);
		this._itemName = get_string(_json, "itemName", "");
		this._material = get_string(_json, "material", "");
		this._metricUnit = get_string(_json, "metricUnit", "");
		this._number = get_double(_json, "number", 1);
		this._price = get_double(_json, "price", 0);
		set_total();
		this._technics = get_string(_json, "technics", "");
		this._unit = get_string(_json, "unit", "元");
	}

	public int get_common()
	{
		return _common;
	}

	public void set_common(int _common)
	{
		this._common = _common;
	}

	public int get_delMarks()
	{
		return _delMarks;
	}

	public void set_delMarks(int _delMarks)
	{
		this._delMarks = _delMarks;
	}

	public int get_itemId()
	{
		return _itemId;
	}

	public void set_itemId(int _itemId)
	{
		this._itemId = _itemId;
	}

	public String get_itemName()
	{
		return _itemName;
	}

	public void set_itemName(String _itemName)
	{
		this._itemName = _itemName;
	}

	public String get_material()
	{
		return _material;
	}

	public void set_material(String _material)
	{
		this._material = _material;
	}

	public String get_metricUnit()
	{
		return _metricUnit;
	}

	public void set_metricUnit(String _metricUnit)
	{
		this._metricUnit = _metricUnit;
	}

	public double get_number()
	{
		return _number;
	}

	public void set_number(double _number)
	{
		this._number = _number;
		set_total();
	}

	public double get_price()
	{
		return _price;
	}

	public void set_price(double _price)
	{
		this._price = _price;
		set_total();
	}

	public double get_total()
	{
		return _total;
	}

	public void set_total()
	{
		this._total = this._number * this._price;
	}

	public String get_technics()
	{
		return _technics;
	}

	public void set_technics(String _technics)
	{
		this._technics = _technics;
	}

	public String get_unit()
	{
		return _unit;
	}

	public void set_unit(String _unit)
	{
		this._unit = _unit;
	}

	private String get_string(JSONObject _json, String _name, String _default) throws JSONException
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

	private int get_int(JSONObject _json, String _name, int _default) throws JSONException
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

	private double get_double(JSONObject _json, String _name, double _default) throws JSONException
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
}
