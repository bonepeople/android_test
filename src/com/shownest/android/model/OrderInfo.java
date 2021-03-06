package com.shownest.android.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.shownest.android.utils.JsonUtil;

import android.util.SparseArray;

/**
 * 订单详细信息
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
	private String _ownerId;// 业主ID "764de29e9d214d6bafb029a0f97d1909"
	private String _ownerPhone;// 业主电话 "15210196421"
	private String _ownerRealName;// 业主姓名 "弗丁"
	private String _providerId;// 卖家ID "00110c30b0b346c4af5e00e1d51bfccf"
	private String _providerphone;// 卖家电话 "13555541368"
	private String _providerRealName;// 卖家姓名 "风行室内设计"
	private String _quotationId;// 报价单ID "1"
	private double _protocolSum;// 协议金额
	private int _currentStageId;// 当前阶段 "1"
	private String _currentStageName;// 当前阶段名称 "平面布置图设计"
	private int _currentStageState;// 当前阶段状态 1未开始待托管(待业主托管)，2已托管(业主已托管，卖家工作中)，3申请验收(卖家申请验收 - 待业主验收)，4业主拒绝验收(待卖家整改)，5卖家申请调整费用 6业主同意调整(待业主托管调整费用)
									// 7业主已托管调整费用(业主已托管)，8业主取消调整费用(业主已托管)，9验收完成,本阶段结束
	private SparseArray<OrderStageInfo> _stages = new SparseArray<OrderStageInfo>();// 阶段信息
	private String _createDate;// 创建时间 "2016年06月23日" 卖家的会带上时间 "2016年06月23日 10:52"

	public OrderInfo(JSONObject _json) throws JSONException
	{
		this._protocolId = JsonUtil.get_string(_json, "protocolId", "");
		this._protocolCode = JsonUtil.get_string(_json, "protocolCode", "");
		this._protocolName = JsonUtil.get_string(_json, "protocolName", "");
		this._bookType = JsonUtil.get_int(_json, "bookType", 12);
		this._desiBiddingTypeId = JsonUtil.get_int(_json, "desiBiddingTypeId", 1);
		this._ownerId = JsonUtil.get_string(_json, "ownerId", "");
		this._ownerPhone = JsonUtil.get_string(_json, "ownerPhone", "");
		this._ownerRealName = JsonUtil.get_string(_json, "ownerRealName", "");
		this._providerId = JsonUtil.get_string(_json, "providerId", "");
		this._providerphone = JsonUtil.get_string(_json, "providerphone", "");
		this._providerRealName = JsonUtil.get_string(_json, "providerRealName", "");
		this._quotationId = JsonUtil.get_string(_json, "quotationId", "");
		this._protocolSum = JsonUtil.get_double(_json, "protocolSum", 0);
		this._currentStageId = JsonUtil.get_int(_json, "currentStageId", 1);
		this._currentStageName = JsonUtil.get_string(_json, "currentStageName", "");
		this._currentStageState = JsonUtil.get_int(_json, "currentStageState", 1);
		this._createDate = JsonUtil.get_string(_json, "createDate", "2016年1月1日");

		JSONArray _array = JsonUtil.get_array(_json, "stageList");
		for (int _temp_i = 0; _array != null && _temp_i < _array.length(); _temp_i++)
		{
			OrderStageInfo _temp_info = new OrderStageInfo(_array.getJSONObject(_temp_i));
			_stages.put(_temp_info.get_stageId(), _temp_info);
		}
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

	public String get_desiBiddingTypeId_name()
	{
		String _result = "未知";
		switch (_desiBiddingTypeId)
		{
		case 1:
			_result = "设计图服务";
			break;
		case 2:
			_result = "硬装全程设计服务";
			break;
		case 3:
			_result = "硬装软装全程设计服务";
			break;
		}
		return _result;
	}

	public void set_desiBiddingTypeId(int _desiBiddingTypeId)
	{
		this._desiBiddingTypeId = _desiBiddingTypeId;
	}

	public String get_ownerId()
	{
		return _ownerId;
	}

	public void set_ownerId(String _ownerId)
	{
		this._ownerId = _ownerId;
	}

	public String get_ownerPhone()
	{
		return _ownerPhone;
	}

	public void set_ownerPhone(String _ownerPhone)
	{
		this._ownerPhone = _ownerPhone;
	}

	public String get_ownerRealName()
	{
		return _ownerRealName;
	}

	public void set_ownerRealName(String _ownerRealName)
	{
		this._ownerRealName = _ownerRealName;
	}

	public String get_providerId()
	{
		return _providerId;
	}

	public void set_providerId(String _providerId)
	{
		this._providerId = _providerId;
	}

	public String get_providerphone()
	{
		return _providerphone;
	}

	public void set_providerphone(String _providerphone)
	{
		this._providerphone = _providerphone;
	}

	public String get_providerRealName()
	{
		return _providerRealName;
	}

	public void set_providerRealName(String _providerRealName)
	{
		this._providerRealName = _providerRealName;
	}

	public String get_quotationId()
	{
		return _quotationId;
	}

	public void set_quotationId(String _quotationId)
	{
		this._quotationId = _quotationId;
	}

	public double get_protocolSum()
	{
		return _protocolSum;
	}

	public void set_protocolSum(double _protocolSum)
	{
		this._protocolSum = _protocolSum;
	}

	public int get_currentStageId()
	{
		return _currentStageId;
	}

	public String get_currentStageId_name()
	{
		String _result = "";
		switch (_currentStageId)
		{
		case 1:
			_result = "第一阶段";
			break;
		case 2:
			_result = "第二阶段";
			break;
		case 3:
			_result = "第三阶段";
			break;
		case 4:
			_result = "第四阶段";
			break;
		case 5:
			_result = "第五阶段";
			break;
		case 6:
			_result = "第六阶段";
			break;
		case 7:
			_result = "第七阶段";
			break;
		case 8:
			_result = "第八阶段";
			break;
		case 9:
			_result = "第九阶段";
		}
		return _result;
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

	public String get_currentStageState_name()
	{
		String _result = "";
		switch (_currentStageState)
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

	public String get_currentStageState_hint()
	{
		String _result = "";
		switch (_currentStageState)
		{
		case 2:
		case 7:
		case 8:
			_result = "申请验收";
			break;
		}
		return _result;
	}

	public void set_currentStageState(int _currentStageState)
	{
		this._currentStageState = _currentStageState;
	}

	public SparseArray<OrderStageInfo> get_stages()
	{
		return _stages;
	}

	public OrderStageInfo get_stage(int _index)
	{
		return _stages.get(_index);
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
