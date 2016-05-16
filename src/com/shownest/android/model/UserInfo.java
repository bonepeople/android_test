package com.shownest.android.model;

import org.json.JSONException;
import org.json.JSONObject;

public class UserInfo
{
	private String _userId;
	private String _userName;
	private String _userPhone;
	private String _userShowName;
	private int _userType;
	private int _total;
	private String _headerIcon;
	private double _gradePraise;
	private long _createDate;
	private boolean _checkPhone;
	private boolean _checkUsername;
	private boolean _checkEmail;
	private int _blogNum;
	private double _money;
	private String _ukey;
	private String _ownerName;
	private String _likeStyle;
	private String _realName;
	private int _realSex;

	// "userId":"7292461a67bf4865b27df9ddfdda1a25",
	// "userName":"jsbj520",
	// "userPhone":"15901515556",
	// "userShowName":"超级无敌思哥",
	// "userType":"11",
	// "total":"100",
	// "headerIcon":"6dfdd424ea49110400eba7dceb7.jpg",
	// "gradePraise":"51.0",
	// "createDate":"1431880825793",
	// "checkPhone":"y",
	// "checkUsername":"n",
	// "checkEmail":"y",
	// "blogNum":"0",
	// "money":"1.0",
	// "ukey":"MTIxNzQ5OT11NzI5MjQ2MWE2N2JmNDg2NWIyN2RmOWRkZmRkYTFhMjUg2MjU1ODY0AAABVK4POe0",
	// "ownerName":"超级无敌思哥",
	// "likeStyle":"1,2,4,3,7",
	// "realName":"蒋大帅",
	// "realSex":"0"

	public UserInfo(JSONObject _json) throws JSONException
	{
		this._userId = _json.has("userId") ? _json.getString("userId") : "";
		this._userName = _json.has("userName") ? _json.getString("userName") : "";
		this._userPhone = _json.has("userPhone") ? _json.getString("userPhone") : "";
		this._userShowName = _json.has("userShowName") ? _json.getString("userShowName") : "";
		this._userType = _json.has("userType") ? _json.getInt("userType") : 100;
		this._total = _json.has("total") ? _json.getInt("total") : 100;
		this._headerIcon = _json.has("headerIcon") ? _json.getString("headerIcon") : "";// 需要一个正确的初始值
		this._gradePraise = _json.has("gradePraise") ? _json.getDouble("gradePraise") : 0.0;
		this._createDate = _json.has("createDate") ? _json.getLong("createDate") : System.currentTimeMillis();
		this._checkPhone = _json.has("checkPhone") ? _json.getString("checkPhone").equals("y") ? true : false : false;

		this._checkUsername = _json.has("checkUsername") ? _json.getString("checkUsername").equals("y") ? true : false : false;
		this._checkEmail = _json.has("checkEmail") ? _json.getString("checkEmail").equals("y") ? true : false : false;
		this._blogNum = _json.has("blogNum") ? _json.getInt("blogNum") : 0;
		this._money = _json.has("money") ? _json.getDouble("money") : 0.0;
		this._ukey = _json.has("ukey") ? _json.getString("ukey") : "";
		this._ownerName = _json.has("ownerName") ? _json.getString("ownerName") : "";

		this._likeStyle = _json.has("likeStyle") ? _json.getString("likeStyle") : "";
		this._realName = _json.has("realName") ? _json.getString("realName") : "";
		this._realSex = _json.has("realSex") ? _json.getInt("realSex") : 0;//男  1  女 0
	}

	public String get_userId()
	{
		return _userId;
	}

	public void set_userId(String _userId)
	{
		this._userId = _userId;
	}

	public String get_userName()
	{
		return _userName;
	}

	public void set_userName(String _userName)
	{
		this._userName = _userName;
	}

	public String get_userPhone()
	{
		return _userPhone;
	}

	public void set_userPhone(String _userPhone)
	{
		this._userPhone = _userPhone;
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

	public int get_total()
	{
		return _total;
	}

	public void set_total(int _total)
	{
		this._total = _total;
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

	public long get_createDate()
	{
		return _createDate;
	}

	public void set_createDate(long _createDate)
	{
		this._createDate = _createDate;
	}

	public boolean is_checkPhone()
	{
		return _checkPhone;
	}

	public void set_checkPhone(boolean _checkPhone)
	{
		this._checkPhone = _checkPhone;
	}

	public boolean is_checkUsername()
	{
		return _checkUsername;
	}

	public void set_checkUsername(boolean _checkUsername)
	{
		this._checkUsername = _checkUsername;
	}

	public boolean is_checkEmail()
	{
		return _checkEmail;
	}

	public void set_checkEmail(boolean _checkEmail)
	{
		this._checkEmail = _checkEmail;
	}

	public int get_blogNum()
	{
		return _blogNum;
	}

	public void set_blogNum(int _blogNum)
	{
		this._blogNum = _blogNum;
	}

	public double get_money()
	{
		return _money;
	}

	public void set_money(double _money)
	{
		this._money = _money;
	}

	public String get_ukey()
	{
		return _ukey;
	}

	public void set_ukey(String _ukey)
	{
		this._ukey = _ukey;
	}

	public String get_ownerName()
	{
		return _ownerName;
	}

	public void set_ownerName(String _ownerName)
	{
		this._ownerName = _ownerName;
	}

	public String get_likeStyle()
	{
		return _likeStyle;
	}

	public void set_likeStyle(String _likeStyle)
	{
		this._likeStyle = _likeStyle;
	}

	public String get_realName()
	{
		return _realName;
	}

	public void set_realName(String _realName)
	{
		this._realName = _realName;
	}

	public int get_realSex()
	{
		return _realSex;
	}

	public void set_realSex(int _realSex)
	{
		this._realSex = _realSex;
	}

	@Override
	public String toString()
	{
		return "UserInfo [" + (_userId != null ? "_userId=" + _userId + ", " : "") + (_userName != null ? "_userName=" + _userName + ", " : "")
				+ (_userPhone != null ? "_userPhone=" + _userPhone + ", " : "") + (_userShowName != null ? "_userShowName=" + _userShowName + ", " : "") + "_userType=" + _userType + ", _total="
				+ _total + ", " + (_headerIcon != null ? "_headerIcon=" + _headerIcon + ", " : "") + "_gradePraise=" + _gradePraise + ", _createDate=" + _createDate + ", _checkPhone=" + _checkPhone
				+ ", _checkUsername=" + _checkUsername + ", _checkEmail=" + _checkEmail + ", _blogNum=" + _blogNum + ", _money=" + _money + ", " + (_ukey != null ? "_ukey=" + _ukey + ", " : "")
				+ (_ownerName != null ? "_ownerName=" + _ownerName + ", " : "") + (_likeStyle != null ? "_likeStyle=" + _likeStyle + ", " : "")
				+ (_realName != null ? "_realName=" + _realName + ", " : "") + "_realSex=" + _realSex + "]";
	}
}
