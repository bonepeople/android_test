package com.shownest.android.widget;

import com.shownest.android.utils.NumberUtil;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class View_split_h extends View
{

	public View_split_h(Context context)
	{
		super(context);
	}

	public View_split_h(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public View_split_h(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
	}

	/**
	 * 分割线控件
	 * 
	 * @param root
	 *            父容器，创建的分割线会默认加载到父容器中，为null则不加载
	 * @param _height
	 *            控件高度(px)
	 */
	public View_split_h(Context context, ViewGroup root, int _height)
	{
		super(context);
		set_height(_height);
		if (root != null)
			root.addView(this);
	}

	/**
	 * 分割线控件
	 * 
	 * @param root
	 *            父容器，创建的分割线会默认加载到父容器中，为null则不加载
	 * @param _height
	 *            控件高度(dp)
	 */
	public View_split_h(Context context, ViewGroup root, float _height)
	{
		super(context);
		set_height(_height);
		if (root != null)
			root.addView(this);
	}

	/**
	 * 设置高度
	 * <p>
	 * 控件设置高度的同时，宽度会自动变为"MATCH_PARENT"
	 * 
	 * @param _height
	 *            控件高度(px)
	 */
	public void set_height(int _height)
	{
		ViewGroup.LayoutParams _param = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, _height);
		this.setLayoutParams(_param);
	}

	/**
	 * 设置高度
	 * <p>
	 * 控件设置高度的同时，宽度会自动变为"MATCH_PARENT"
	 * 
	 * @param _height
	 *            控件高度(dp)
	 */
	public void set_height(float _height)
	{
		int _height_px = NumberUtil.get_px(getContext(), _height);
		ViewGroup.LayoutParams _param = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, _height_px);
		this.setLayoutParams(_param);
	}

	/**
	 * 设置颜色
	 */
	public void set_color(int _color)
	{
		this.setBackgroundColor(_color);
	}
}
