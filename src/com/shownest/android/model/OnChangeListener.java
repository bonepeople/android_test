package com.shownest.android.model;

/**
 * 数据变更回调接口
 * 
 * @author bonepeople
 */
public interface OnChangeListener
{
	/**
	 * 标签或单选按钮组的改变事件，传递的是真实的数值1，2，3......（不是从0开始）
	 * 
	 * @param _index
	 */
	void onSelect(int _index);
	void onChange(String[] args);
}
