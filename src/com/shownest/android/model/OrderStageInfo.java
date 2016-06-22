package com.shownest.android.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.shownest.android.utils.JsonUtil;

/**
 * 订单阶段详细信息
 * 
 * @author bonepeople
 */
public class OrderStageInfo
{
	private String _protocolId;// 协议ID "b01350d5ac984dca8ee1c2619866ace1"
	private String _protocolCode;// 协议编码 "BJSJ2016621061765"
	private int _stageId;// 当前阶段 "1"
	private String _stageName;// 当前阶段名称 "平面布置图设计"
	private int _stageState;// 当前阶段状态 1未开始待托管(待业主托管)，2已托管(业主已托管，卖家工作中)，3申请验收(卖家申请验收 - 待业主验收)，4业主拒绝验收(待卖家整改)，5卖家申请调整费用 6业主同意调整(待业主托管调整费用)
							// 7业主已托管调整费用(业主已托管)，8业主取消调整费用(业主已托管)，9验收完成,本阶段结束
	private double _stageMoney;// 阶段金额

	public OrderStageInfo(JSONObject _json) throws JSONException
	{
		this._protocolId = JsonUtil.get_string(_json, "protocolId", "");
		this._protocolCode = JsonUtil.get_string(_json, "protocolCode", "");
		this._stageId = JsonUtil.get_int(_json, "stageId", 1);
		this._stageName = JsonUtil.get_string(_json, "stageName", "");
		this._stageState = JsonUtil.get_int(_json, "stageState", 1);
		this._stageMoney = JsonUtil.get_double(_json, "stageMoney", 0);
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

	public int get_stageId()
	{
		return _stageId;
	}

	public void set_stageId(int _stageId)
	{
		this._stageId = _stageId;
	}

	public String get_stageName()
	{
		return _stageName;
	}

	public void set_stageName(String _stageName)
	{
		this._stageName = _stageName;
	}

	public int get_stageState()
	{
		return _stageState;
	}

	public String get_stageState_name()
	{
		String _result = "";
		switch (_stageState)
		{
		case 1:
			_result = "待业主托管";
			break;
		case 2:
			_result = "业主已托管，卖家工作中";
			break;
		case 3:
			_result = "卖家申请验收 - 待业主验收";
			break;
		case 4:
			_result = "待卖家整改";
			break;
		case 5:
			_result = "卖家申请调整费用";
			break;
		case 6:
			_result = "待业主托管调整费用";
			break;
		case 7:
			_result = "业主已托管";
			break;
		case 8:
			_result = "业主已托管";
			break;
		case 9:
			_result = "本阶段结束";
		}
		return _result;
	}

	public void set_stageState(int _stageState)
	{
		this._stageState = _stageState;
	}

	public double get_stageMoney()
	{
		return _stageMoney;
	}

	public void set_stageMoney(double _stageMoney)
	{
		this._stageMoney = _stageMoney;
	}

}
