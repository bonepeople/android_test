package com.shownest.android.model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.shownest.android.utils.JsonUtil;

/**
 * 房屋的所有标的信息
 * 
 * @author bonepeople
 */
public class HouseBidState
{
	private String _houseId;// 房屋ID
	private String _houseName;// 房屋名称
	private ArrayList<BidInfo_common> _bids = new ArrayList<BidInfo_common>();// 所有招标信息

	public HouseBidState(JSONObject _json) throws JSONException
	{
		this._houseId = JsonUtil.get_string(_json, "houseId", "");
		this._houseName = JsonUtil.get_string(_json, "houseName", "");
		JSONArray _array = JsonUtil.get_array(_json, "houseBids");

		for (int _temp_i = 0; _array != null && _temp_i < _array.length(); _temp_i++)
		{
			BidInfo_common _temp_info = new BidInfo_common(_array.getJSONObject(_temp_i));
			_bids.add(_temp_info);
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

	public ArrayList<BidInfo_common> get_bids()
	{
		return _bids;
	}

	public void set_bids(ArrayList<BidInfo_common> _bids)
	{
		this._bids = _bids;
	}
}
