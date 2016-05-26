package com.shownest.android.model;

/**
 * 工艺详细信息
 * 
 * <p>
 * 包括工艺名称，辅材型号，工艺说明，单价，工程量等。主要是用于保存报价单中每个工艺的详细信息
 * 
 * @author bonepeople
 */
public class ItemDetail
{
	private int _common;// 工艺标志，1常用 2非常用
	private int _delMarks;// 删除修改标记
	private int _itemId;// 工艺ID
	private String _itemName;// 工艺名称
	private String _material;// 辅材品牌型号
	private String _metricUnit;// 计算单位
	private double _number;// 工程量
	private double _price;// 单价
	private double _total;// 合计，由单价乘以工程量计算得出
	private String _technics;// 工艺说明
	private String _unit;// 货币单位：元
}
