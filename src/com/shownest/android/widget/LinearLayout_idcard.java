package com.shownest.android.widget;

import com.shownest.android.model.OnSelectListener;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LinearLayout_idcard extends LinearLayout implements View.OnClickListener, OnSelectListener
{
	private static boolean DEBUG = true;
	private TextView _textview_name;

	public LinearLayout_idcard(Context context)
	{
		super(context);
		// TODO Auto-generated constructor stub
	}

	public LinearLayout_idcard(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public LinearLayout_idcard(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 初始化一个身份证上传控件
	 * 
	 * @param _name
	 *            控件标题
	 */
	public LinearLayout_idcard(Context context, String _name)
	{
		super(context);
		if (DEBUG)
			System.out.println("LinearLayout_idcard super");

		this.setOrientation(LinearLayout.VERTICAL);

		_textview_name = new TextView(context);
		_textview_name.setText(_name);
		// _textview_name.setTextSize(18);

		float scale = context.getResources().getDisplayMetrics().density;
		int padding = (int) (5 * scale + 0.5f);

		_textview_name.setPadding(padding, padding, 0, 0);
		this.addView(_textview_name);
	}

	@Override
	public void onClick(View v)
	{

	}

	@Override
	public void onSelect(int _index)
	{
		_textview_name.setText("selected : " + _index);
	}
}
