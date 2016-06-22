package com.shownest.android.model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.shownest.android.utils.JsonUtil;

/**
 * 房屋的所有订单的信息
 * 
 * @author bonepeople
 */
public class HouseOrderState
{
	private String _houseId;// 房屋ID
	private String _houseName;// 房屋名称
	private ArrayList<OrderInfo> _orders = new ArrayList<OrderInfo>();// 所有招标信息

	public HouseOrderState(JSONObject _json) throws JSONException
	{
		this._houseId = JsonUtil.get_string(_json, "houseId", "");
		this._houseName = JsonUtil.get_string(_json, "houseName", "");
		JSONArray _array = JsonUtil.get_array(_json, "decorateOrderBases");

		for (int _temp_i = 0; _array != null && _temp_i < _array.length(); _temp_i++)
		{
			OrderInfo _temp_info = new OrderInfo(_array.getJSONObject(_temp_i));
			_orders.add(_temp_info);
		}
	}

	public String get_houseId()
	{
		return _houseId;
	}

	public void set_houseId(String _houseId)
	{
		this._houseId = _houseId;
	}

	public String get_houseName()
	{
		return _houseName;
	}

	public void set_houseName(String _houseName)
	{
		this._houseName = _houseName;
	}

	public ArrayList<OrderInfo> get_orders()
	{
		return _orders;
	}

	public void set_orders(ArrayList<OrderInfo> _orders)
	{
		this._orders = _orders;
	}

}
