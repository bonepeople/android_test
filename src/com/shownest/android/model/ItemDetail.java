package com.shownest.android.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.shownest.android.utils.JsonUtil;
import com.shownest.android.utils.NumberUtil;

/**
 * 工艺详细信息
 * <p>
 * 包括工艺名称，辅材型号，工艺说明，单价，工程量等。主要是用于保存报价单中每个工艺的详细信息
 * 
 * @author bonepeople
 */
public class ItemDetail
{
	private String _tag;// ground，wall，roof，hydropower，mount，cost，tax
	private int _common;// 工艺标志，1常用 2非常用
	private int _delMarks;// 删除修改标记,1修改 0删除
	private int _hydropowerWay;// 水电安装方式 1按面积计算
	private int _typeName;// 安装类型 1灯安装 2五金安装
	private String _itemName;// 工艺名称
	private String _material;// 辅材品牌型号
	private String _metricUnit;// 计算单位:平米
	private double _number;// 工程量
	private int _numerical;// 工艺ID
	private int _itemId;// 已用工艺ID
	private double _price;// 单价
	private double _total;// 合计，由单价乘以工程量计算得出
	private String _technics;// 工艺说明
	private String _unit;// 货币单位：元

	public ItemDetail(JSONObject _json, String _tag) throws JSONException
	{
		this._tag = _tag;
		this._common = JsonUtil.get_int(_json, "common", 1);
		this._delMarks = JsonUtil.get_int(_json, "delMarks", 1);
		this._hydropowerWay = JsonUtil.get_int(_json, "hydropowerWay", 1);
		this._typeName = JsonUtil.get_int(_json, "typeName", 1);
		this._itemName = JsonUtil.get_string(_json, "itemName", "");
		this._material = JsonUtil.get_string(_json, "material", "");
		if (this._material.equals(""))
			this._material = "无";
		this._metricUnit = JsonUtil.get_string(_json, "metricUnit", "");
		this._number = JsonUtil.get_double(_json, "number", 1);
		this._numerical = JsonUtil.get_int(_json, "numerical", 1);
		this._itemId = JsonUtil.get_int(_json, "itemId", 0);
		this._price = JsonUtil.get_double(_json, "price", 0);
		set_total();
		this._technics = JsonUtil.get_string(_json, "technics", "");
		this._unit = JsonUtil.get_string(_json, "unit", "元");
	}

	public ItemDetail(ItemDetail _obj)
	{
		this._tag = _obj._tag;
		this._common = _obj._common;
		this._delMarks = _obj._delMarks;
		this._hydropowerWay = _obj._hydropowerWay;
		this._typeName = _obj._typeName;
		this._itemName = _obj._itemName;
		this._material = _obj._material;
		this._metricUnit = _obj._metricUnit;
		this._number = _obj._number;
		this._numerical = _obj._numerical;
		this._itemId = _obj._itemId;
		this._price = _obj._price;
		this._total = _obj._total;
		this._technics = _obj._technics;
		this._unit = _obj._unit;
	}

	public String get_tag()
	{
		return _tag;
	}

	public void set_tag(String _tag)
	{
		this._tag = _tag;
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

	public int get_hydropowerWay()
	{
		return _hydropowerWay;
	}

	public void set_hydropowerWay(int _hydropowerWay)
	{
		this._hydropowerWay = _hydropowerWay;
	}

	public int get_typeName()
	{
		return _typeName;
	}

	public void set_typeName(int _typeName)
	{
		this._typeName = _typeName;
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

	public int get_numerical()
	{
		return _numerical;
	}

	public void set_numerical(int _numerical)
	{
		this._numerical = _numerical;
	}

	public int get_itemId()
	{
		return _itemId;
	}

	public void set_itemId(int _itemId)
	{
		this._itemId = _itemId;
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
		this._total = NumberUtil.round(NumberUtil.mul(_number, _price), 2);
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
}
