package com.shownest.android.model;

/**
 * 标签或单选按钮组的改变事件，传递的是真实的数值1，2，3......（不是从0开始）
 * 
 * @author bonepeople
 *
 */
public interface OnSelectListener
{
	void onSelect(int _index);
}