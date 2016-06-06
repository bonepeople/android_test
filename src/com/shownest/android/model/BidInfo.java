package com.shownest.android.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.shownest.android.utils.JsonUtil;

import android.text.format.DateFormat;

public class BidInfo
{
	private String _id;// 招标ID
	private String _areaName;// 小区名称
	private String _houseName;// 房屋名称，和小区名称值相同
	private String _homeRegion;// 所在区域 "1001,100101,10010101"
	private String _homeRegionName;// 所在区域 "北京市,北京市,东城区"
	private int _houseType;// 房屋类型 1平层，2复式，3别墅，4商业
	private int _houseState;// 房屋状态 1毛坯新房，2二手旧房
	private double _homeSq;// 建筑面积
	private double _homeUseSq;// 使用面积
	private int _roomNum;// 卧室个数
	private int _parlourNum;// 客厅个数
	private int _kitchenNum;// 厨房个数
	private int _toiletNum;// 卫生间个数
	private int _balconyNum;// 阳台个数
	private String _contacts;// 业主称呼
	private String _phone;// 联系方式
	private int _bookType;// 招标类型 12设计标，13施工标，14监理标
	private int _consType;// 施工标类型 0半包1全包2清包
	private double _constructerMoney;// 装修预算
	private String _ownerQuotationId;// 业主智能报价单 ID
	private int _bidsStateCon;// 招标状态 ？施工标
	private String _createDateCon;// 施工标发布时间 ？
	private String _ownerIdea;// 需求描述

	public BidInfo(JSONObject _json) throws JSONException
	{
		this._id = JsonUtil.get_string(_json, "id", "");
		this._areaName = JsonUtil.get_string(_json, "areaName", "");
		this._houseName = JsonUtil.get_string(_json, "houseName", "");
		this._homeRegion = JsonUtil.get_string(_json, "homeRegion", "1001,100101,10010101");
		this._homeRegionName = JsonUtil.get_string(_json, "homeRegionName", "北京市,北京市,东城区");
		this._houseType = JsonUtil.get_int(_json, "houseType", 1);
		this._houseState = JsonUtil.get_int(_json, "houseState", 1);
		this._homeSq = JsonUtil.get_double(_json, "homeSq", 0);
		this._homeUseSq = JsonUtil.get_double(_json, "homeUseSq", 0);
		this._roomNum = JsonUtil.get_int(_json, "roomNum", 1);
		this._parlourNum = JsonUtil.get_int(_json, "parlourNum", 1);
		this._kitchenNum = JsonUtil.get_int(_json, "kitchenNum", 1);
		this._toiletNum = JsonUtil.get_int(_json, "toiletNum", 1);
		this._balconyNum = JsonUtil.get_int(_json, "balconyNum", 1);
		this._contacts = JsonUtil.get_string(_json, "contacts", "");
		this._phone = JsonUtil.get_string(_json, "phone", "");
		this._bookType = JsonUtil.get_int(_json, "bookType", 13);
		this._consType = JsonUtil.get_int(_json, "consType", 0);
		this._constructerMoney = JsonUtil.get_double(_json, "constructerMoney", 0);
		this._ownerQuotationId = JsonUtil.get_string(_json, "ownerQuotationId", "");
		this._bidsStateCon = JsonUtil.get_int(_json, "bidsStateCon", 2);
		this._createDateCon = JsonUtil.get_string(_json, "createDateCon", "");
		this._ownerIdea = JsonUtil.get_string(_json, "ownerIdea", "");
	}

	public String get_id()
	{
		return _id;
	}

	public void set_id(String _id)
	{
		this._id = _id;
	}

	public String get_areaName()
	{
		return _areaName;
	}

	public void set_areaName(String _areaName)
	{
		this._areaName = _areaName;
	}

	public String get_houseName()
	{
		return _houseName;
	}

	public void set_houseName(String _houseName)
	{
		this._houseName = _houseName;
	}

	public String get_homeRegion()
	{
		return _homeRegion;
	}

	public void set_homeRegion(String _homeRegion)
	{
		this._homeRegion = _homeRegion;
	}

	public String get_homeRegionName()
	{
		return _homeRegionName;
	}

	public void set_homeRegionName(String _homeRegionName)
	{
		this._homeRegionName = _homeRegionName;
	}

	public int get_houseType()
	{
		return _houseType;
	}

	public String get_houseType_name()
	{
		String _result = "";
		switch (_houseType)
		{
		case 1:
			_result = "平层住宅";
			break;
		case 2:
			_result = "复试住宅";
			break;
		case 3:
			_result = "别墅";
			break;
		case 4:
			_result = "商业";
			break;
		}
		return _result;
	}

	public void set_houseType(int _houseType)
	{
		this._houseType = _houseType;
	}

	public int get_houseState()
	{
		return _houseState;
	}

	public String get_houseState_name()
	{
		String _result = "";
		switch (_houseState)
		{
		case 1:
			_result = "毛坯新房";
			break;
		case 2:
			_result = "二手旧房";
			break;
		}
		return _result;
	}

	public void set_houseState(int _houseState)
	{
		this._houseState = _houseState;
	}

	public double get_homeSq()
	{
		return _homeSq;
	}

	public void set_homeSq(double _homeSq)
	{
		this._homeSq = _homeSq;
	}

	public double get_homeUseSq()
	{
		return _homeUseSq;
	}

	public void set_homeUseSq(double _homeUseSq)
	{
		this._homeUseSq = _homeUseSq;
	}

	public String get_rooms()
	{
		String _result;
		_result = _roomNum + "," + _parlourNum + "," + _kitchenNum + "," + _toiletNum + "," + _balconyNum;
		return _result;
	}

	public int get_roomNum()
	{
		return _roomNum;
	}

	public void set_roomNum(int _roomNum)
	{
		this._roomNum = _roomNum;
	}

	public int get_parlourNum()
	{
		return _parlourNum;
	}

	public void set_parlourNum(int _parlourNum)
	{
		this._parlourNum = _parlourNum;
	}

	public int get_kitchenNum()
	{
		return _kitchenNum;
	}

	public void set_kitchenNum(int _kitchenNum)
	{
		this._kitchenNum = _kitchenNum;
	}

	public int get_toiletNum()
	{
		return _toiletNum;
	}

	public void set_toiletNum(int _toiletNum)
	{
		this._toiletNum = _toiletNum;
	}

	public int get_balconyNum()
	{
		return _balconyNum;
	}

	public void set_balconyNum(int _balconyNum)
	{
		this._balconyNum = _balconyNum;
	}

	public String get_contacts()
	{
		return _contacts;
	}

	public void set_contacts(String _contacts)
	{
		this._contacts = _contacts;
	}

	public String get_phone()
	{
		return _phone;
	}

	public void set_phone(String _phone)
	{
		this._phone = _phone;
	}

	public int get_bookType()
	{
		return _bookType;
	}

	public String get_bookType_name()
	{
		String _result = "";
		switch (_bookType)
		{
		case 12:
			_result = "设计师";
			break;
		case 13:
			_result = "施工队";
			break;
		case 14:
			_result = "监理";
			break;
		}
		return _result;
	}

	public void set_bookType(int _bookType)
	{
		this._bookType = _bookType;
	}

	public int get_consType()
	{
		return _consType;
	}

	public String get_consType_name()
	{
		String _result = "";
		switch (_consType)
		{
		case 0:
			_result = "半包";
			break;
		case 1:
			_result = "全包";
			break;
		case 2:
			_result = "清包";
			break;
		}
		return _result;
	}

	public void set_consType(int _consType)
	{
		this._consType = _consType;
	}

	public double get_constructerMoney()
	{
		return _constructerMoney;
	}

	public void set_constructerMoney(double _constructerMoney)
	{
		this._constructerMoney = _constructerMoney;
	}

	public String get_ownerQuotationId()
	{
		return _ownerQuotationId;
	}

	public void set_ownerQuotationId(String _ownerQuotationId)
	{
		this._ownerQuotationId = _ownerQuotationId;
	}

	public int get_bidsStateCon()
	{
		return _bidsStateCon;
	}

	public String get_bidsStateCon_name()
	{
		String _result = "";
		switch (_bidsStateCon)
		{
		case 1:
			_result = "半包";
			break;
		case 2:
			_result = "招标中";
			break;
		case 3:
			_result = "清包";
			break;
		}
		return _result;
	}

	public void set_bidsStateCon(int _bidsStateCon)
	{
		this._bidsStateCon = _bidsStateCon;
	}

	public String get_createDateCon()
	{
		return _createDateCon;
	}

	public String get_createDateCon_name()
	{
		return DateFormat.format("yyyy年MM月dd日", Long.parseLong(_createDateCon)).toString();
	}

	public void set_createDateCon(String _createDateCon)
	{
		this._createDateCon = _createDateCon;
	}

	public String get_ownerIdea()
	{
		return _ownerIdea.equals("") ? "无" : _ownerIdea;
	}

	public void set_ownerIdea(String _ownerIdea)
	{
		this._ownerIdea = _ownerIdea;
	}

	// "areaId":216,
	// "bidNum":0,
	// "bidsState":2,
	// "budget":95000,
	// "createDate":1464937535011,
	// "homeBelongOwner":"7292461a67bf4865b27df9ddfdda1a25",
	// "houseId":"107",
	// "isOver":"n",
	// "listResBids":[
	//
	// ],
	// "states":"2",
	// "userId":"7292461a67bf4865b27df9ddfdda1a25"
}
