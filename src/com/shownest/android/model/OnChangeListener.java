package com.shownest.android.model;

/**
 * 数据变更回调接口
 * 
 * @author bonepeople
 */
public interface OnChangeListener
{
	/**
	 * 标签或单选按钮组的改变事件，传递的是真实的数值1，2，3......（不是从0开始） 其他数据改变的情况参照控件类型
	 * 
	 * @param tag
	 *            标签 用来区分传递的类别
	 * @param args
	 *            数据
	 */
	void onChange(String tag, String[] args);
}
