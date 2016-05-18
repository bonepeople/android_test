package com.shownest.android.widget;

import com.shownest.android.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Linearlayout_edittext extends LinearLayout
{
	private Context _context;
	private ViewGroup _rootview;
	private TextView _name;
	private EditText _text;

	public Linearlayout_edittext(Context context)
	{
		super(context);
	}

	public Linearlayout_edittext(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public Linearlayout_edittext(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
	}

	/**
	 * 带名称的输入框
	 * 
	 * @param root
	 *            父控件
	 * @param args
	 *            必须是3个字符串，分别代表name hint text，字符串可以为空
	 */
	public Linearlayout_edittext(Context context, ViewGroup root, String[] args)
	{
		super(context);
		this._context = context;
		this._rootview = root;
		setContentView(args);
	}

	private void setContentView(String[] args)
	{
		ViewGroup _view;
		View _childview = null;

		_view = (ViewGroup) View.inflate(this._context, R.layout.widget_edittext, this._rootview);
		_childview = _view.getChildAt(_view.getChildCount() - 1);

		_name = (TextView) _childview.findViewById(R.id.textview_widget_name);
		_text = (EditText) _childview.findViewById(R.id.edittext_widget);

		if (args.length == 3)
		{
			_name.setText(args[0]);
			_text.setHint(args[1]);
			_text.setText(args[2]);
		}
		else
			System.out.println("Linearlayout_edittext init failed:miss string array");
	}

	public String getData()
	{
		return _text.getText().toString();
	}
}
