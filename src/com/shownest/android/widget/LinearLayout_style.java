package com.shownest.android.widget;

import com.shownest.android.R;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LinearLayout_style extends LinearLayout
{
	private static boolean DEBUG = true;
	private int _count = 0;

	public LinearLayout_style(Context context)
	{
		super(context);
		// TODO Auto-generated constructor stub
	}

	public LinearLayout_style(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public LinearLayout_style(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	public LinearLayout_style(Context context, String _name, String[] _args, int _count)
	{
		super(context);
		if (DEBUG)
			System.out.println("LinearLayout_style super");

		this._count = _count;
		this.setOrientation(LinearLayout.VERTICAL);

		TextView _textview_name = new TextView(context);
		_textview_name.setText(_name);
		_textview_name.setTextSize(18);
		_textview_name.setPadding(10, 0, 0, 0);
		this.addView(_textview_name);

		GridLayout _gridlayout = new GridLayout(context);
		_gridlayout.setColumnCount(3);

		TextView _textview_item;
		GridLayout.LayoutParams _params;
		for (String string : _args)
		{
			_textview_item = new TextView(context);
			_textview_item.setText(string);
			_textview_item.setTextSize(18);
			_textview_item.setGravity(Gravity.CENTER);
			_textview_item.setBackgroundResource(R.drawable.background_button_gray);
			_params = new GridLayout.LayoutParams();
			_params.setMargins(10, 10, 10, 10);
			_params.width = 120;
			_params.height = 50;
			_gridlayout.addView(_textview_item, _params);
		}

		this.addView(_gridlayout);
	}

}
