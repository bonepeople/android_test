package com.shownest.android.adapter;

import java.util.ArrayList;

import com.shownest.android.R;
import com.shownest.android.model.OnChangeListener;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Adapter_offer_auto extends BaseAdapter implements View.OnClickListener, OnChangeListener
{
	private Context _context;
	private LayoutInflater _inflater;
	private float _total_area = 0;
	private int _total_num = 5;
	private float _every_area = 0;
	private int _room = 1;
	private int _parlour = 1;
	private int _kitchen = 1;
	private int _toilet = 1;
	private int _balcony = 1;
	private ArrayList<Float> _areas = new ArrayList<Float>();

	private AlertDialog _dialog;
	private EditText _edittext_dialog;
	private int _selected = 0;

	private static class ViewHolder
	{
		public TextView _text_name;
		public TextView _text_left;
		public TextView _text_right;
	}

	public Adapter_offer_auto(Context _context)
	{
		this._context = _context;
		_inflater = LayoutInflater.from(_context);
		for (int _temp_i = 0; _temp_i < _total_num; _temp_i++)
			_areas.add(_every_area);

	}

	@Override
	public int getCount()
	{
		return _areas.size();
	}

	@Override
	public String getItem(int position)
	{
		String _result = "";
		int _temp_i = 0, _temp_j = 1;

		// room
		while (_temp_j <= _room)
			if (_temp_i == position)
			{
				if (_temp_j == _room && _room == 1)
					_result = "卧室";
				else
					_result = "卧室" + _temp_j;
				return _result;
			}
			else
			{
				_temp_i++;
				_temp_j++;
			}
		// parlour
		_temp_j = 1;
		while (_temp_j <= _parlour)
			if (_temp_i == position)
			{
				if (_temp_j == _parlour && _parlour == 1)
					_result = "客厅";
				else
					_result = "客厅" + _temp_j;
				return _result;
			}
			else
			{
				_temp_i++;
				_temp_j++;
			}
		// kitchen
		_temp_j = 1;
		while (_temp_j <= _kitchen)
			if (_temp_i == position)
			{
				if (_temp_j == _kitchen && _kitchen == 1)
					_result = "厨房";
				else
					_result = "厨房" + _temp_j;
				return _result;
			}
			else
			{
				_temp_i++;
				_temp_j++;
			}

		// toilet
		_temp_j = 1;
		while (_temp_j <= _toilet)
			if (_temp_i == position)
			{
				if (_temp_j == _toilet && _toilet == 1)
					_result = "卫生间";
				else
					_result = "卫生间" + _temp_j;
				return _result;
			}
			else
			{
				_temp_i++;
				_temp_j++;
			}
		// balcony
		_temp_j = 1;
		while (_temp_j <= _balcony)
			if (_temp_i == position)
			{
				if (_temp_j == _balcony && _balcony == 1)
					_result = "阳台";
				else
					_result = "阳台" + _temp_j;
				return _result;
			}
			else
			{
				_temp_i++;
				_temp_j++;
			}

		return _result;
	}

	@Override
	public long getItemId(int position)
	{
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View _view = convertView;
		ViewHolder _holder;

		if (convertView == null)
		{
			_view = _inflater.inflate(R.layout.widget_edit_informationbar_style3, null);
			_holder = new ViewHolder();
			_holder._text_name = (TextView) _view.findViewById(R.id.textview_widget_name);
			_holder._text_left = (TextView) _view.findViewById(R.id.textview_widget_left);
			_holder._text_right = (TextView) _view.findViewById(R.id.textview_widget_right);
			_holder._text_right.setText(" m²");
			_view.setOnClickListener(this);
			_view.setTag(_holder);
		}
		else
		{
			_holder = (ViewHolder) _view.getTag();
		}
		String _temp_name = getItem(position);
		_holder._text_name.setText(_temp_name);
		_holder._text_left.setText(String.valueOf(_areas.get(position)));

		return _view;
	}


	private void refresh_data()
	{
		_areas.clear();
		for (int _temp_i = 0; _temp_i < _total_num; _temp_i++)
			_areas.add(_every_area);
		notifyDataSetChanged();
	}

	public int get_number(String _name)
	{
		int _result = 1;
		switch (_name)
		{
		case "room":
			_result = _room;
			break;
		case "parlour":
			_result = _parlour;
			break;
		case "kitchen":
			_result = _kitchen;
			break;
		case "toilet":
			_result = _toilet;
			break;
		case "balcony":
			_result = _balcony;
			break;
		}
		return _result;
	}

	public String get_acreage(String _name)
	{
		StringBuilder _builder = new StringBuilder();
		int _temp_i, _min = 0, _max = 0;
		switch (_name)
		{
		case "balcony":
			_min += _toilet;
			_max += _balcony;
		case "toilet":
			_min += _kitchen;
			_max += _toilet;
		case "kitchen":
			_min += _parlour;
			_max += _kitchen;
		case "parlour":
			_min += _room;
			_max += _parlour;
		case "room":
			_max += _room;
		}
		for (_temp_i = _min; _temp_i < _max; _temp_i++)
			_builder.append(_areas.get(_temp_i).toString() + ",");
		_builder.deleteCharAt(_builder.length() - 1);
		return _builder.toString();
	}

	private void show_dialog(String _value)
	{
		View _view = View.inflate(_context, R.layout.dialog_edit, null);
		AlertDialog.Builder _builder = new Builder(_context);
		_dialog = _builder.create();
		_dialog.setView(_view, 0, 0, 0, 0);

		Button _button_commit = (Button) _view.findViewById(R.id.button_commit);
		Button _button_cancel = (Button) _view.findViewById(R.id.button_cancel);
		_edittext_dialog = (EditText) _view.findViewById(R.id.edittext_dialog);
		_edittext_dialog.setText(_value);
		_edittext_dialog.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
		_edittext_dialog.selectAll();
		_button_commit.setOnClickListener(this);
		_button_cancel.setOnClickListener(this);
		_dialog.show();

		Window window = _dialog.getWindow();
		android.view.WindowManager.LayoutParams params = window.getAttributes();
		params.softInputMode = android.view.WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE;// 显示dialog的时候,就显示软键盘
		params.flags = android.view.WindowManager.LayoutParams.FLAG_DIM_BEHIND;// 就是这个属性导致不能获取焦点,默认的是FLAG_NOT_FOCUSABLE,故名思义不能获取输入焦点,
		window.setAttributes(params);
	}

	@Override
	public void onClick(View v)
	{
		// TODO Auto-generated method stub
		switch (v.getId())
		{
		case R.id.button_commit:
			// 可以在这里检测输入的合理性
//			String _temp_str = _edittext_dialog.getText().toString();
//			if (_temp_str.length() < 7 && _temp_str.length() > 0)
//			_areas.set(_selected, Float.valueOf(_temp_str))
//			_dialog.dismiss();
			break;
		case R.id.button_cancel:
//			_dialog.dismiss();
			break;
			default:
//				show_dialog(v.getdata);
				break;
		}
	}

	@Override
	public void onChange(String tag, String[] args)
	{
		switch (tag)
		{
		case "style3":
			_total_area = Float.parseFloat(args[0]);
			_every_area = _total_area / _total_num;
			refresh_data();
			break;

		case "style4":
			String[] _nums = args[0].split(",");
			_room = Integer.parseInt(_nums[0]);
			_parlour = Integer.parseInt(_nums[1]);
			_kitchen = Integer.parseInt(_nums[2]);
			_toilet = Integer.parseInt(_nums[3]);
			_balcony = Integer.parseInt(_nums[4]);

			_total_num = _room + _parlour + _kitchen + _balcony + _toilet;
			_every_area = _total_area / _total_num;
			refresh_data();
			break;
		}
	}
}
