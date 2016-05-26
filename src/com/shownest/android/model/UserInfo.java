package com.shownest.android.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 用户基本信息
 * <p>
 * 在用户初次登陆之后返回的基本信息
 * 
 * @author bonepeople
 */
public class UserInfo
{
	private String _userId;// 用户ID
	private String _userName;// 用户名
	private String _userPhone;// 用户绑定的电话
	private String _userShowName;// 用户昵称
	private int _userType;// 用户身份类别
	private int _total;
	private String _headerIcon;// 用户头像
	private double _gradePraise;
	private long _createDate;// 账号创建时间
	private boolean _checkPhone;// 是否绑定电话
	private boolean _checkUsername;// 是否绑定用户名，每个用户注册之后只允许修改一次用户名
	private boolean _checkEmail;// 是否绑定邮箱
	private int _blogNum;// ？？博文
	private double _money;// 余额
	private String _ukey;// ukey
	private String _ownerName;
	private String _likeStyle;// 业主喜欢的风格
	private String _realName;// 用户真实姓名
	private int _realSex;// 用户性别 男 1 女 0
	private int _nativePlace;// 工长籍贯 1011
	private String _serviceRegion;// 服务区域 1004,100401,10040101
	private int _peopleNum;// 工队人数
	private String _introduces;// 个人介绍，工队介绍。。。
	private int _workYear;// 从业年份
	private int _workProvince;// 办公地址省 1004
	private int _workCity;// 办公地址 市 100401
	private int _workCounty;// 办公地址县 10040101
	private String _workAddress;// 办公详细地址(详细信息)
	private String _serviceItem;// 服务范围 0，1，2
	private int _authenticationType;// 认证类型 1：个人 2：企业
	private String _authenticationName;// 认证人姓名（个人或负责人）
	private String _authenticationCode;// 身份证号
	private String _authenticationCardPicF;// 身份证图片正面路径
	private String _authenticationCardPicB;// 身份证图片背面路径
	private String _businessLicensPicpath;// 企业营业执照图路径
	private int _fansNum;// ？？秀友
	private int _bookNum;// ？？关注

	public UserInfo(JSONObject _json) throws JSONException
	{
		this._userId = get_string(_json, "userId", "");
		this._userName = get_string(_json, "userName", "");
		this._userPhone = get_string(_json, "userPhone", "");
		this._userShowName = get_string(_json, "userShowName", "");
		this._userType = get_int(_json, "userType", 100);
		this._total = get_int(_json, "total", 0);
		this._headerIcon = get_string(_json, "headerIcon", "");// 需要一个正确的初始值
		this._gradePraise = get_double(_json, "gradePraise", 0);
		this._createDate = get_long(_json, "createDate", System.currentTimeMillis());
		this._checkPhone = get_bool(_json, "checkPhone", false);
		this._checkUsername = get_bool(_json, "checkUsername", false);
		this._checkEmail = get_bool(_json, "checkEmail", false);
		this._blogNum = get_int(_json, "blogNum", 0);
		this._money = get_double(_json, "money", 0);
		this._ukey = get_string(_json, "ukey", "");
		this._ownerName = get_string(_json, "ownerName", "");
		this._likeStyle = get_string(_json, "likeStyle", "");
		this._realName = get_string(_json, "realName", "");
		this._realSex = get_int(_json, "realSex", 1);
		this._nativePlace = get_int(_json, "nativePlace", -1);
		this._serviceRegion = get_string(_json, "serviceRegion", "");
		this._peopleNum = get_int(_json, "peopleNum", 1);
		this._introduces = get_string(_json, "introduces", "");
		this._workYear = get_int(_json, "workYear", 2016);
		this._workProvince = get_int(_json, "workProvince", 1004);
		this._workCity = get_int(_json, "workCity", 100401);
		this._workCounty = get_int(_json, "workCounty", 10040101);
		this._workAddress = get_string(_json, "workAddress", "");
		this._serviceItem = get_string(_json, "serviceItem", "");
		this._authenticationType = get_int(_json, "authenticationType", 1);
		this._authenticationName = get_string(_json, "authenticationName", "");
		this._authenticationCode = get_string(_json, "authenticationCode", "");
		this._authenticationCardPicF = get_string(_json, "authenticationCardPicF", "");
		this._authenticationCardPicB = get_string(_json, "authenticationCardPicB", "");
		this._businessLicensPicpath = get_string(_json, "businessLicensPicpath", "");
		this._fansNum = get_int(_json, "fansNum", 0);
		this._bookNum = get_int(_json, "bookNum", 0);
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

	public int get_nativePlace()
	{
		return _nativePlace;
	}

	public void set_nativePlace(int _nativePlace)
	{
		this._nativePlace = _nativePlace;
	}

	public String get_serviceRegion()
	{
		return _serviceRegion;
	}

	public void set_serviceRegion(String _serviceRegion)
	{
		this._serviceRegion = _serviceRegion;
	}

	public int get_peopleNum()
	{
		return _peopleNum;
	}

	public void set_peopleNum(int _peopleNum)
	{
		this._peopleNum = _peopleNum;
	}

	public String get_introduces()
	{
		return _introduces;
	}

	public void set_introduces(String _introduces)
	{
		this._introduces = _introduces;
	}

	public int get_workYear()
	{
		return _workYear;
	}

	public void set_workYear(int _workYear)
	{
		this._workYear = _workYear;
	}

	public int get_workProvince()
	{
		return _workProvince;
	}

	public void set_workProvince(int _workProvince)
	{
		this._workProvince = _workProvince;
	}

	public int get_workCity()
	{
		return _workCity;
	}

	public void set_workCity(int _workCity)
	{
		this._workCity = _workCity;
	}

	public int get_workCounty()
	{
		return _workCounty;
	}

	public void set_workCounty(int _workCounty)
	{
		this._workCounty = _workCounty;
	}

	public String get_workAddress()
	{
		return _workAddress;
	}

	public void set_workAddress(String _workAddress)
	{
		this._workAddress = _workAddress;
	}

	public String get_serviceItem()
	{
		return _serviceItem;
	}

	public void set_serviceItem(String _serviceItem)
	{
		this._serviceItem = _serviceItem;
	}

	public int get_authenticationType()
	{
		return _authenticationType;
	}

	public void set_authenticationType(int _authenticationType)
	{
		this._authenticationType = _authenticationType;
	}

	public String get_authenticationName()
	{
		return _authenticationName;
	}

	public void set_authenticationName(String _authenticationName)
	{
		this._authenticationName = _authenticationName;
	}

	public String get_authenticationCode()
	{
		return _authenticationCode;
	}

	public void set_authenticationCode(String _authenticationCode)
	{
		this._authenticationCode = _authenticationCode;
	}

	public String get_authenticationCardPicF()
	{
		return _authenticationCardPicF;
	}

	public void set_authenticationCardPicF(String _authenticationCardPicF)
	{
		this._authenticationCardPicF = _authenticationCardPicF;
	}

	public String get_authenticationCardPicB()
	{
		return _authenticationCardPicB;
	}

	public void set_authenticationCardPicB(String _authenticationCardPicB)
	{
		this._authenticationCardPicB = _authenticationCardPicB;
	}

	public String get_businessLicensPicpath()
	{
		return _businessLicensPicpath;
	}

	public void set_businessLicensPicpath(String _businessLicensPicpath)
	{
		this._businessLicensPicpath = _businessLicensPicpath;
	}

	public int get_fansNum()
	{
		return _fansNum;
	}

	public void set_fansNum(int _fansNum)
	{
		this._fansNum = _fansNum;
	}

	public int get_bookNum()
	{
		return _bookNum;
	}

	public void set_bookNum(int _bookNum)
	{
		this._bookNum = _bookNum;
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

	private long get_long(JSONObject _json, String _name, long _default) throws JSONException
	{
		long _result = _default;
		if (_json.has(_name))
		{
			String _temp_str = _json.getString(_name);
			if (!_temp_str.equals("null"))
				_result = _json.getLong(_name);
		}
		return _result;
	}

	private boolean get_bool(JSONObject _json, String _name, boolean _default) throws JSONException
	{
		boolean _result = _default;
		if (_json.has(_name))
		{
			String _temp_str = _json.getString(_name);
			if (!_temp_str.equals("null"))
				_result = _temp_str.equals("y") ? true : false;
		}
		return _result;
	}
}
