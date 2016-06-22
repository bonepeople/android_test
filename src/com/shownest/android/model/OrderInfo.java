package com.shownest.android.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.shownest.android.utils.JsonUtil;

/**
 * 订单信息
 * 
 * @author bonepeople
 */
public class OrderInfo
{
	private String _protocolId;// 协议ID "b01350d5ac984dca8ee1c2619866ace1"
	private String _protocolCode;// 协议编码 "BJSJ2016621061765"
	private String _protocolName;// 协议名称 "云趣园一区"
	private int _bookType;// 协议类型 12-设计标，13-施工标，14-监理标
	private int _desiBiddingTypeId;// 设计标类型 1-设计图服务，2-硬装全程设计服务，3-硬装软装全程设计服务
	private int _currentStageId;// 当前阶段 "1"
	private String _currentStageName;// 当前阶段名称 "平面布置图设计"
	private int _currentStageState;// 当前阶段状态 1未开始待托管(待业主托管)，2已托管(业主已托管，卖家工作中)，3申请验收(卖家申请验收 - 待业主验收)，4业主拒绝验收(待卖家整改)，5卖家申请调整费用 6业主同意调整(待业主托管调整费用)
									// 7业主已托管调整费用(业主已托管)，8业主取消调整费用(业主已托管)，9验收完成,本阶段结束
	private String _createDate;// 创建时间 "2016年06月23日"

	public OrderInfo(JSONObject _json) throws JSONException
	{
		this._protocolId = JsonUtil.get_string(_json, "protocolId", "");
		this._protocolCode = JsonUtil.get_string(_json, "protocolCode", "");
		this._protocolName = JsonUtil.get_string(_json, "protocolName", "");
		this._bookType = JsonUtil.get_int(_json, "bookType", 12);
		this._desiBiddingTypeId = JsonUtil.get_int(_json, "desiBiddingTypeId", 1);
		this._currentStageId = JsonUtil.get_int(_json, "currentStageId", 1);
		this._currentStageName = JsonUtil.get_string(_json, "currentStageName", "");
		this._currentStageState = JsonUtil.get_int(_json, "currentStageState", 1);
		this._createDate = JsonUtil.get_string(_json, "createDate", "2016年1月1日");
	}

	public String get_protocolId()
	{
		return _protocolId;
	}

	public void set_protocolId(String _protocolId)
	{
		this._protocolId = _protocolId;
	}

	public String get_protocolCode()
	{
		return _protocolCode;
	}

	public void set_protocolCode(String _protocolCode)
	{
		this._protocolCode = _protocolCode;
	}

	public String get_protocolName()
	{
		return _protocolName;
	}

	public void set_protocolName(String _protocolName)
	{
		this._protocolName = _protocolName;
	}

	public int get_bookType()
	{
		return _bookType;
	}

	public void set_bookType(int _bookType)
	{
		this._bookType = _bookType;
	}

	public int get_desiBiddingTypeId()
	{
		return _desiBiddingTypeId;
	}

	public void set_desiBiddingTypeId(int _desiBiddingTypeId)
	{
		this._desiBiddingTypeId = _desiBiddingTypeId;
	}

	public int get_currentStageId()
	{
		return _currentStageId;
	}

	public void set_currentStageId(int _currentStageId)
	{
		this._currentStageId = _currentStageId;
	}

	public String get_currentStageName()
	{
		return _currentStageName;
	}

	public void set_currentStageName(String _currentStageName)
	{
		this._currentStageName = _currentStageName;
	}

	public int get_currentStageState()
	{
		return _currentStageState;
	}

	public void set_currentStageState(int _currentStageState)
	{
		this._currentStageState = _currentStageState;
	}

	public String get_createDate()
	{
		return _createDate;
	}

	public void set_createDate(String _createDate)
	{
		this._createDate = _createDate;
	}
}
