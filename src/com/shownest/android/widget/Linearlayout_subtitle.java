package com.shownest.android.widget;

import com.shownest.android.R;
import com.shownest.android.model.OnChangeListener;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Linearlayout_subtitle extends LinearLayout implements View.OnClickListener
{
	private String _tag;
	private OnChangeListener _listener;
	private int _selected = 1;
	private TextView[] _text = new TextView[3];
	private View[] _line = new View[3];

	public Linearlayout_subtitle(Context context)
	{
		super(context);
	}

	public void setOnChangeListener(String _tag, OnChangeListener _change_listener)
	{
		this._tag = _tag;
		_listener = _change_listener;
	}

	public Linearlayout_subtitle(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		View.inflate(context, R.layout.widget_subtitle, this);
		_text[0] = (TextView) findViewById(R.id.textview_subtitle_1);
		_text[1] = (TextView) findViewById(R.id.textview_subtitle_2);
		_text[2] = (TextView) findViewById(R.id.textview_subtitle_3);
		_line[0] = findViewById(R.id.view_subtitle_1);
		_line[1] = findViewById(R.id.view_subtitle_2);
		_line[2] = findViewById(R.id.view_subtitle_3);
		// 以下方式可以使用，在大量相似控件需要设置的时候可以放在循环里
		// _line[0] = findViewById(getResources().getIdentifier("view_subtitle_"+"1", "id", context.getPackageName()));
		// _line[1] = findViewById(getResources().getIdentifier("view_subtitle_"+"2", "id", context.getPackageName()));
		// _line[2] = findViewById(getResources().getIdentifier("view_subtitle_"+"3", "id", context.getPackageName()));

		int _temp_i = 0;
		for (; _temp_i < _text.length; _temp_i++)
			_text[_temp_i].setOnClickListener(this);
	}

	public Linearlayout_subtitle(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
	}

	public void set_text(String[] _text)
	{
		for (int _temp_i = 0; _temp_i < _text.length && _temp_i < this._text.length; _temp_i++)
		{
			this._text[_temp_i].setText(_text[_temp_i]);
		}
	}

	@Override
	public void onClick(View v)
	{
		int _select = 1;
		switch (v.getId())
		{
		case R.id.textview_subtitle_1:
			_select = 1;
			break;
		case R.id.textview_subtitle_2:
			_select = 2;
			break;
		case R.id.textview_subtitle_3:
			_select = 3;
			break;
		}
		if (_select != _selected)
		{
			_text[_selected - 1].setTextColor(getResources().getColor(R.color.text_black));
			_line[_selected - 1].setVisibility(View.INVISIBLE);
			_text[_select - 1].setTextColor(getResources().getColor(R.color.main_color));
			_line[_select - 1].setVisibility(View.VISIBLE);
			_selected = _select;
			if (_listener != null)
				_listener.onChange(_tag, new String[] { String.valueOf(_select) });
		}
	}
}
