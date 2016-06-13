package com.shownest.android.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.shownest.android.utils.JsonUtil;

public class Template_shejishi
{
	private String _id;// 编号
	private String _name;// 模板名称
	private int _biddingTypeId;// 设计服务类别 1-设计图服务，2-硬装全程服务，3-硬装软装全程服务
	private long _createDate;// 创建时间 "1465748577177"

	public Template_shejishi(JSONObject _json) throws JSONException
	{
		this._id = JsonUtil.get_string(_json, "id", "");
		this._name = JsonUtil.get_string(_json, "name", "");
		this._biddingTypeId = JsonUtil.get_int(_json, "biddingTypeId", 1);
		this._createDate = JsonUtil.get_long(_json, "createDate", System.currentTimeMillis());
	}

	public String get_id()
	{
		return _id;
	}

	public void set_id(String _id)
	{
		this._id = _id;
	}

	public String get_name()
	{
		return _name;
	}

	public void set_name(String _name)
	{
		this._name = _name;
	}

	public int get_biddingTypeId()
	{
		return _biddingTypeId;
	}

	public void set_biddingTypeId(int _biddingTypeId)
	{
		this._biddingTypeId = _biddingTypeId;
	}

	public long get_createDate()
	{
		return _createDate;
	}

	public void set_createDate(long _createDate)
	{
		this._createDate = _createDate;
	}

	// "defaultItem":"1",
	// "userId":"00110c30b0b346c4af5e00e1d51bfccf"
}
