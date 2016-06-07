package com.shownest.android.widget;

import com.shownest.android.utils.NumberUtil;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TextView_ex extends TextView
{
	private ViewGroup _rootview;

	public TextView_ex(Context context, ViewGroup root)
	{
		super(context);
		this._rootview = root;
		_rootview.addView(this);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 设置文本框的边距(dp)
	 */
	public void setMargins(float left, float top, float right, float bottom)
	{
		setMargins(NumberUtil.get_px(getContext(), left), NumberUtil.get_px(getContext(), top), NumberUtil.get_px(getContext(), right), NumberUtil.get_px(getContext(), bottom));
	}

	/**
	 * 设置文本框的边距(px)
	 */
	public void setMargins(int left, int top, int right, int bottom)
	{
		if (_rootview instanceof LinearLayout)
		{
			LinearLayout.LayoutParams _param = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_param.setMargins(left, top, right, bottom);
			setLayoutParams(_param);
		}
		else if (_rootview instanceof GridLayout)
		{
			GridLayout.LayoutParams _param = new GridLayout.LayoutParams();
			_param.setMargins(left, top, right, bottom);
			setLayoutParams(_param);
		}
		else if (_rootview instanceof RelativeLayout)
		{
			RelativeLayout.LayoutParams _param = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_param.setMargins(left, top, right, bottom);
			setLayoutParams(_param);
		}
	}
}
