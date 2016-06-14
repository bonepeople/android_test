package com.shownest.android.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.shownest.android.utils.JsonUtil;

/**
 * 投标信息
 * 
 * @author bonepeople
 */
public class QuotaInfo
{
	private String _userShowName;// 用户昵称
	private int _userType;// 用户身份类别
	private String _userTypeName;// 用户身份名称
	private String _headerIcon;// 用户头像
	private double _gradePraise;// 用户评分
	private double _budget;// 装修预算
	private int _bidsState;// 招标状态
	private boolean _change;// 是否修改过
	private String _QuotationId;// 报价单 ID

	public QuotaInfo(JSONObject _json) throws JSONException
	{
		this._userShowName = JsonUtil.get_string(_json, "userShowName", "");
		this._userType = JsonUtil.get_int(_json, "userType", 12);
		this._userTypeName = JsonUtil.get_string(_json, "userTypeName", "未知");
		this._headerIcon = JsonUtil.get_string(_json, "headerIcon", "header_default.png");
		this._gradePraise = JsonUtil.get_double(_json, "gradePraise", 0);
		this._budget = JsonUtil.get_double(_json, "budget", 0);
		this._bidsState = JsonUtil.get_int(_json, "bidsState", 0);
		this._change = JsonUtil.get_bool(_json, "change", false);
		this._QuotationId = JsonUtil.get_string(_json, "QuotationId", "");
	}

	public String get_userShowName()
	{
		return _userShowName;
	}

	public void set_userShowName(String _userShowName)
	{
		this._userShowName = _userShowName;
	}

	public int get_userType()
	{
		return _userType;
	}

	public void set_userType(int _userType)
	{
		this._userType = _userType;
	}

	public String get_userTypeName()
	{
		return _userTypeName;
	}

	public void set_userTypeName(String _userTypeName)
	{
		this._userTypeName = _userTypeName;
	}

	public String get_headerIcon()
	{
		return _headerIcon;
	}

	public void set_headerIcon(String _headerIcon)
	{
		this._headerIcon = _headerIcon;
	}

	public double get_gradePraise()
	{
		return _gradePraise;
	}

	public void set_gradePraise(double _gradePraise)
	{
		this._gradePraise = _gradePraise;
	}

	public double get_budget()
	{
		return _budget;
	}

	public void set_budget(double _budget)
	{
		this._budget = _budget;
	}

	public int get_bidsState()
	{
		return _bidsState;
	}

	public void set_bidsState(int _bidsState)
	{
		this._bidsState = _bidsState;
	}

	public boolean is_change()
	{
		return _change;
	}

	public void set_change(boolean _change)
	{
		this._change = _change;
	}

	public String get_QuotationId()
	{
		return _QuotationId;
	}

	public void set_QuotationId(String _QuotationId)
	{
		this._QuotationId = _QuotationId;
	}
}
