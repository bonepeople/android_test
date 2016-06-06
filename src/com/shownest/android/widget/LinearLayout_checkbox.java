package com.shownest.android.widget;

import java.util.ArrayList;

import com.shownest.android.R;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LinearLayout_checkbox extends LinearLayout implements View.OnClickListener
{
	private static boolean DEBUG = true;
	private int _column = 2;
	private int _count_max = 0;
	private int _count = 0;
	private ArrayList<TextView> _text = new ArrayList<TextView>();
	private ArrayList<Integer> _choose = new ArrayList<Integer>();

	public LinearLayout_checkbox(Context context)
	{
		super(context);
		// TODO Auto-generated constructor stub
	}

	public LinearLayout_checkbox(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public LinearLayout_checkbox(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 初始化一个可选列表
	 * 
	 * @param _name
	 *            控件标题
	 * @param _args
	 *            控件内部标签名称
	 * @param _max
	 *            可选数量
	 * @param _choose
	 *            已被选择的控件序号。例："1，3，7"
	 */
	public LinearLayout_checkbox(Context context, String _name, String[] _args, int _max, String _choose)
	{
		super(context);
		if (DEBUG)
			System.out.println("LinearLayout_style super");

		this._count_max = _max;
		this.setOrientation(LinearLayout.VERTICAL);

		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Point outSize = new Point(0, 0);
		wm.getDefaultDisplay().getSize(outSize);
		int screen_width = outSize.x;

		TextView _textview_name = new TextView(context);
		_textview_name.setText(_name);
		// _textview_name.setTextSize(18);
		_textview_name.setPadding(10, 0, 0, 0);
		this.addView(_textview_name);

		GridLayout _gridlayout = new GridLayout(context);
		_gridlayout.setColumnCount(_column);

		TextView _textview_item;
		GridLayout.LayoutParams _params;
		int _id = 0;
		for (String string : _args)
		{
			_textview_item = new TextView(context);
			_text.add(_textview_item);
			this._choose.add(isChecked(_id + 1, _choose) ? 1 : 0);
			_textview_item.setId(_id);
			_textview_item.setText(string);
			_textview_item.setTextSize(18);
			_textview_item.setGravity(Gravity.CENTER);
			if (isChecked(_id + 1, _choose))
			{
				_textview_item.setBackgroundResource(R.drawable.background_button);
				this._count++;
			}
			else
				_textview_item.setBackgroundResource(R.drawable.background_button_gray);
			_textview_item.setOnClickListener(this);
			_params = new GridLayout.LayoutParams();
			_params.setMargins(10, 10, 10, 10);
			_params.width = screen_width / _column - 20;
			_params.height = 70;//_params.width * 2 / 5 / 2;
			_gridlayout.addView(_textview_item, _params);
			_id++;
		}

		this.addView(_gridlayout);
	}

	@Override
	public void onClick(View v)
	{
		if (_count_max != 1)
		{
			TextView _temp_textview = _text.get(v.getId());
			if (_choose.get(v.getId()) == 0)
			{
				if (_count < _count_max)
				{
					_temp_textview.setBackgroundResource(R.drawable.background_button);
					_choose.set(v.getId(), 1);
					_count++;
				}
			}
			else
			{
				_temp_textview.setBackgroundResource(R.drawable.background_button_gray);
				_choose.set(v.getId(), 0);
				_count--;
			}
		}
		else
		{
			setData(String.valueOf(v.getId() + 1));
		}
	}

	/**
	 * 判断所给id是否已被选中
	 * 
	 * @param _choose
	 *            已被选择的控件序号。例：{1，3，7}
	 */
	private boolean isChecked(int _id, String _choose)
	{
		String[] _number = _choose.split(",");
		for (String string : _number)
			try
			{
				if (_id == Integer.parseInt(string))
					return true;
			}
			catch (Exception e)
			{
			}
		return false;
	}

	public String getData()
	{
		StringBuilder _string = new StringBuilder();

		for (int _temp_i = 0; _temp_i < _choose.size(); _temp_i++)
		{
			if (_choose.get(_temp_i) == 1)
			{
				_string.append(_temp_i + 1);
				_string.append(',');
			}
		}
		if (_string.length() != 0)
			_string.deleteCharAt(_string.length() - 1);
		return _string.toString();
	}

	/**
	 * 设置风格选框中的已选样式
	 * 
	 * @param _choose
	 *            已被选择的控件序号。例："1，3，7"
	 */
	public void setData(String _choose)
	{
		int _id = 0;
		for (; _id < this._choose.size(); _id++)
		{
			if (isChecked(_id + 1, _choose) && this._choose.get(_id) == 0)
			{
				_text.get(_id).setBackgroundResource(R.drawable.background_button);
				this._choose.set(_id, 1);
			}
			else if (!isChecked(_id + 1, _choose) && this._choose.get(_id) == 1)
			{
				_text.get(_id).setBackgroundResource(R.drawable.background_button_gray);
				this._choose.set(_id, 0);
			}
		}
	}
}
