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
	private String _homeId;// 招标ID
	private String _userId;// 投标人ID
	private String _userShowName;// 投标人用户昵称
	private int _userType;// 投标人用户身份类别
	private String _headerIcon;// 投标人用户头像
	private double _gradePraise;// 投标人用户评分
	private double _userPrice;// 投标人预算
	private int _state;// 招标状态 1-已投标(参与)，2-备选，3-已量房修改价格(待定)，4-淘汰，5-中标，6-结束
	private String _otherInfor;// 额外信息(投标的)
	private int _quoteWay;// 报价方式 1-手动报价，2-智能报价
	private boolean _isHide;// 是否隐藏
	private String _quotationId;// 报价单 ID

	// "gradePraise":"151.0",
	// "headerIcon":"20150716165622.107.png",
	// "homeId":"cf1686e3a11545ad971cf2addc835678",
	// "isHide":"n",
	// "otherInfor":"",
	// "quotationId":"4",
	// "quoteWay":"2",
	// "state":"1",
	// "userId":"001a3da401474e89978994a1d9d52491",
	// "userIdeas":"设计思路一大堆啊",
	// "userPhone":"131*****711",
	// "userPrice":"16600.0",
	// "userShowName":"彭工设计师",
	// "userTxt":"",
	// "userType":"12"

	public QuotaInfo(JSONObject _json) throws JSONException
	{
		this._userShowName = JsonUtil.get_string(_json, "userShowName", "");
		this._userType = JsonUtil.get_int(_json, "userType", 12);
		this._headerIcon = JsonUtil.get_string(_json, "headerIcon", "header_default.png");
		this._gradePraise = JsonUtil.get_double(_json, "gradePraise", 0);
		this._userPrice = JsonUtil.get_double(_json, "userPrice", 0);
		this._state = JsonUtil.get_int(_json, "state", 0);
		this._quotationId = JsonUtil.get_string(_json, "quotationId", "");
	}

	public String get_userId()
	{
		return _userId;
	}

	public void set_userId(String _userId)
	{
		this._userId = _userId;
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

	public String get_userType_name()
	{
		String _name = "";
		switch (_userType)
		{
		case 12:
			_name = "设计师";
			break;
		case 13:
			_name = "施工队";
			break;
		case 14:
			_name = "监理";
			break;
		default:
			_name = "未知";
			break;
		}
		return _name;
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

	public double get_userPrice()
	{
		return _userPrice;
	}

	public void set_userPrice(double _userPrice)
	{
		this._userPrice = _userPrice;
	}

	public int get_state()
	{
		return _state;
	}

	public void set_state(int _state)
	{
		this._state = _state;
	}

	public String get_quotationId()
	{
		return _quotationId;
	}

	public void set_quotationId(String _quotationId)
	{
		this._quotationId = _quotationId;
	}

}
