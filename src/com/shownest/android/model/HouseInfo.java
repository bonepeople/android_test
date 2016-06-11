package com.shownest.android.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.shownest.android.utils.JsonUtil;

public class HouseInfo
{
	private String _houseId;// 房屋ID
	private String _houseName;// 房屋名称
	private String _region;// 房屋位置 "1001,100101,10010104"
	private String _regionName;// 房屋位置 "北京市,北京市,朝阳区"
	private double _homeSq;// 房屋建筑面积
	private int _houseState;// 房屋状态 1-新房，2-旧房
	private int _houseType;// 房屋类型 1-平层，2-复式，3-别墅，4-商业
	private int _roomNum;// 卧室个数
	private int _parlourNum;// 卧室个数
	private int _kitchenNum;// 卧室个数
	private int _toiletNum;// 卧室个数
	private int _balconyNum;// 卧室个数
	private String _roomAcreage;// 卧室面积 "15.95,15.95"
	private String _parlourAcreage;// 客厅面积 "15.95,15.95"
	private String _kitchenAcreage;// 厨房面积 "15.95,15.95"
	private String _toiletAcreage;// 卫生间面积 "15.95,15.95"
	private String _balconyAcreage;// 阳台面积 "15.95,15.95"
	private String _floors;// 楼层 "0/0"
	// "areaId":"233",
	// "userId":"764de29e9d214d6bafb029a0f97d1909"

	public HouseInfo(JSONObject _json) throws JSONException
	{
		this._houseId = JsonUtil.get_string(_json, "houseId", "");
		this._houseName = JsonUtil.get_string(_json, "houseName", "");
		this._region = JsonUtil.get_string(_json, "region", "1001,100101,10010101");
		this._regionName = JsonUtil.get_string(_json, "regionName", "北京市,北京市,东城区");
		this._homeSq = JsonUtil.get_double(_json, "homeSq", 0);
		this._houseState = JsonUtil.get_int(_json, "houseState", 1);
		this._houseType = JsonUtil.get_int(_json, "houseType", 1);
		this._roomNum = JsonUtil.get_int(_json, "roomNum", 1);
		this._parlourNum = JsonUtil.get_int(_json, "parlourNum", 1);
		this._kitchenNum = JsonUtil.get_int(_json, "kitchenNum", 1);
		this._toiletNum = JsonUtil.get_int(_json, "toiletNum", 1);
		this._balconyNum = JsonUtil.get_int(_json, "balconyNum", 1);
		this._roomAcreage = JsonUtil.get_string(_json, "roomAcreage", "0");
		this._parlourAcreage = JsonUtil.get_string(_json, "parlourAcreage", "0");
		this._kitchenAcreage = JsonUtil.get_string(_json, "kitchenAcreage", "0");
		this._toiletAcreage = JsonUtil.get_string(_json, "toiletAcreage", "0");
		this._balconyAcreage = JsonUtil.get_string(_json, "balconyAcreage", "0");
		this._floors = JsonUtil.get_string(_json, "floors", "1/1");
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

	public String get_region()
	{
		return _region;
	}

	public void set_region(String _region)
	{
		this._region = _region;
	}

	public String get_regionName()
	{
		return _regionName;
	}

	public void set_regionName(String _regionName)
	{
		this._regionName = _regionName;
	}

	public double get_homeSq()
	{
		return _homeSq;
	}

	public void set_homeSq(double _homeSq)
	{
		this._homeSq = _homeSq;
	}

	public int get_houseState()
	{
		return _houseState;
	}

	public void set_houseState(int _houseState)
	{
		this._houseState = _houseState;
	}

	public int get_houseType()
	{
		return _houseType;
	}

	public void set_houseType(int _houseType)
	{
		this._houseType = _houseType;
	}

	public String get_nums()
	{
		return _roomNum + "," + _parlourNum + "," + _kitchenNum + "," + _toiletNum + "," + _balconyNum;
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

	public String get_allAcreage()
	{
		return get_roomAcreage() + "," + get_parlourAcreage() + "," + get_kitchenAcreage() + "," + get_toiletAcreage() + "," + get_balconyAcreage();
	}

	public String get_roomAcreage()
	{
		return _roomAcreage;
	}

	public void set_roomAcreage(String _roomAcreage)
	{
		this._roomAcreage = _roomAcreage;
	}

	public String get_parlourAcreage()
	{
		return _parlourAcreage;
	}

	public void set_parlourAcreage(String _parlourAcreage)
	{
		this._parlourAcreage = _parlourAcreage;
	}

	public String get_kitchenAcreage()
	{
		return _kitchenAcreage;
	}

	public void set_kitchenAcreage(String _kitchenAcreage)
	{
		this._kitchenAcreage = _kitchenAcreage;
	}

	public String get_toiletAcreage()
	{
		return _toiletAcreage;
	}

	public void set_toiletAcreage(String _toiletAcreage)
	{
		this._toiletAcreage = _toiletAcreage;
	}

	public String get_balconyAcreage()
	{
		return _balconyAcreage;
	}

	public void set_balconyAcreage(String _balconyAcreage)
	{
		this._balconyAcreage = _balconyAcreage;
	}

	public String get_floors()
	{
		return _floors;
	}

	public void set_floors(String _floors)
	{
		this._floors = _floors;
	}
}
