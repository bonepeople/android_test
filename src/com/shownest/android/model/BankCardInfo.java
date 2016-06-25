package com.shownest.android.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.shownest.android.utils.JsonUtil;

public class BankCardInfo
{
	private String _bankCardId;// 银行卡ID "1"
	private String _bankCardNo;// 银行卡号 "***************7197"
	private String _bankName;// 银行名称 "中国工商银行"
	private String _cardType;// 银行卡类型 "借记卡"
	private String _logoUrl;// 银行logo地址 "http://osspb.datatiny.com/banklogo/icbc.png"

	public BankCardInfo(JSONObject _json) throws JSONException
	{
		this._bankCardId = JsonUtil.get_string(_json, "bankCardId", "");
		this._bankCardNo = JsonUtil.get_string(_json, "bankCardNo", "");
		this._bankName = JsonUtil.get_string(_json, "bankName", "");
		this._cardType = JsonUtil.get_string(_json, "cardType", "");
		this._logoUrl = JsonUtil.get_string(_json, "logoUrl", "");
	}

	public String get_bankCardId()
	{
		return _bankCardId;
	}

	public void set_bankCardId(String _bankCardId)
	{
		this._bankCardId = _bankCardId;
	}

	public String get_bankCardNo()
	{
		return _bankCardNo;
	}

	public void set_bankCardNo(String _bankCardNo)
	{
		this._bankCardNo = _bankCardNo;
	}

	public String get_bankName()
	{
		return _bankName;
	}

	public void set_bankName(String _bankName)
	{
		this._bankName = _bankName;
	}

	public String get_cardType()
	{
		return _cardType;
	}

	public void set_cardType(String _cardType)
	{
		this._cardType = _cardType;
	}

	public String get_logoUrl()
	{
		return _logoUrl;
	}

	public void set_logoUrl(String _logoUrl)
	{
		this._logoUrl = _logoUrl;
	}

}
