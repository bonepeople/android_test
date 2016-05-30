package com.shownest.android.model;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.shownest.android.utils.JsonUtil;
import com.shownest.android.utils.NumberUtil;

import android.util.SparseArray;

/**
 * 房间详细信息
 * <p>
 * 保存每个房间报价的详细信息
 * 
 * @author bonepeople
 */
public class RoomDetail
{
	private String _tag;// 目前暂时无用，不过也保存了类型：room，parlour，kitchen，toilet，balcony，hydropower，mount，cost，tax

	private HashMap<String, Double> _totals = new HashMap<>();
	private HashMap<String, SparseArray<ItemDetail>> _details = new HashMap<String, SparseArray<ItemDetail>>();

	public RoomDetail(JSONObject _json, String _tag) throws JSONException
	{
		this._tag = _tag;

		_totals.put("ground", JsonUtil.get_double(_json, "groundTotals", 0));
		_totals.put("wall", JsonUtil.get_double(_json, "wallTotals", 0));
		_totals.put("roof", JsonUtil.get_double(_json, "roofTotals", 0));
		_totals.put("hydropower", JsonUtil.get_double(_json, "hydropowerTotals", 0));
		_totals.put("mount", JsonUtil.get_double(_json, "mountTotals", 0));
		_totals.put("cost", JsonUtil.get_double(_json, "costTotals", 0));
		_totals.put("tax", JsonUtil.get_double(_json, "taxTotals", 0));

		creat_items(_json, "ground");
		creat_items(_json, "wall");
		creat_items(_json, "roof");
		creat_items(_json, "hydropower");
		creat_items(_json, "mount");
		creat_items(_json, "cost");
		creat_items(_json, "tax");
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

	/**
	 * 查询每一项的总价
	 * 
	 * @param _name
	 *            ground，wall，roof，hydropower，mount，cost，tax
	 */
	public double get_totals(String _name)
	{
		return _totals.get(_name);
	}

	/**
	 * 设置个别项的总价
	 * 
	 * @param _name
	 *            ground，wall，roof，hydropower，mount，cost，tax
	 */
	public void set_totals(String _name, double _number)
	{
		_totals.put(_name, _number);
	}

	/**
	 * 根据自身数据刷新某一项的总价
	 * 
	 * @param _part
	 *            ground，wall，roof，hydropower，mount，cost，tax
	 */
	public void fresh_totals(String _part)
	{
		SparseArray<ItemDetail> _temp_array = get_details(_part);
		if (_temp_array.size() != 0)
		{
			double _temp_total = 0;
			for (int _temp_i = 0; _temp_i < _temp_array.size(); _temp_i++)
			{
				ItemDetail _temp_item = _temp_array.get(_temp_i);
				_temp_total += _temp_item.get_total();
			}
			set_totals(_part, NumberUtil.double_format(_temp_total));
		}
	}

	/**
	 * 获取某一项的详细信息
	 * 
	 * @param _name
	 *            ground，wall，roof，hydropower，mount，cost，tax
	 */
	public SparseArray<ItemDetail> get_details(String _name)
	{
		return _details.get(_name);
	}

}
