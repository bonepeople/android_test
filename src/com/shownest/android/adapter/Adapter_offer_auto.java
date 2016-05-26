package com.shownest.android.adapter;

import java.util.ArrayList;

import com.shownest.android.R;
import com.shownest.android.model.OnChangeListener;
import com.shownest.android.model.Package;

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
	private float _every_area = 0;
	private ArrayList<Package> _areas = new ArrayList<Package>();

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
		construct(1, 1, 1, 1, 1);
	}

	private void construct()
	{
		System.out.println("constructor in adapter");
		int _temp_i;
		for (_temp_i = 0; _temp_i < _areas.size(); _temp_i++)
		{
			_areas.get(_temp_i)._data = _every_area;
		}
	}

	private void construct(int _room, int _parlour, int _kitchen, int _toilet, int _balcony)
	{
		System.out.println("constructor in adapter");
		_areas.clear();
		int _temp_i;
		// room
		for (_temp_i = 0; _temp_i < _room; _temp_i++)
		{
			if (_room == 1)
			{
				_areas.add(new Package(_every_area, "room", "卧室"));
			}
			else
			{
				_areas.add(new Package(_every_area, "room", "卧室" + (_temp_i + 1)));
			}
		}
		// parlour
		for (_temp_i = 0; _temp_i < _parlour; _temp_i++)
		{
			if (_parlour == 1)
			{
				_areas.add(new Package(_every_area, "parlour", "客厅"));
			}
			else
			{
				_areas.add(new Package(_every_area, "parlour", "客厅" + (_temp_i + 1)));
			}
		}
		// kitchen
		for (_temp_i = 0; _temp_i < _kitchen; _temp_i++)
		{
			if (_kitchen == 1)
			{
				_areas.add(new Package(_every_area, "kitchen", "厨房"));
			}
			else
			{
				_areas.add(new Package(_every_area, "kitchen", "厨房" + (_temp_i + 1)));
			}
		}
		// toilet
		for (_temp_i = 0; _temp_i < _toilet; _temp_i++)
		{
			if (_toilet == 1)
			{
				_areas.add(new Package(_every_area, "toilet", "卫生间"));
			}
			else
			{
				_areas.add(new Package(_every_area, "toilet", "卫生间" + (_temp_i + 1)));
			}
		}

		// balcony
		for (_temp_i = 0; _temp_i < _balcony; _temp_i++)
		{
			if (_balcony == 1)
			{
				_areas.add(new Package(_every_area, "balcony", "阳台"));
			}
			else
			{
				_areas.add(new Package(_every_area, "balcony", "阳台" + (_temp_i + 1)));
			}
		}
	}

	@Override
	public int getCount()
	{
		return _areas.size();
	}

	@Override
	public Object getItem(int position)
	{
		return null;
	}

	@Override
	public long getItemId(int position)
	{
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		System.out.println("get view - " + position);
		View _view = convertView;
		ViewHolder _holder;

		if (convertView == null)
		{
			System.out.println("creat a new view - " + position);
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
		_view.setId(position);
		_holder._text_name.setText(_areas.get(position)._tag2);
		_holder._text_left.setText(_areas.get(position)._data.toString());

		return _view;
	}

	public int get_number(String _name)
	{
		int _result = 0;
		int _temp_i = 0;
		for (; _temp_i < _areas.size(); _temp_i++)
		{
			if (_areas.get(_temp_i)._tag1.equals(_name))
			{
				_result++;
			}
		}
		return _result;
	}

	public String get_acreage(String _name)
	{
		StringBuilder _builder = new StringBuilder();
		int _temp_i = 0;
		for (; _temp_i < _areas.size(); _temp_i++)
		{
			if (_areas.get(_temp_i)._tag1.equals(_name))
			{
				_builder.append(_areas.get(_temp_i)._data.toString() + ",");
			}
		}
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
		System.out.println("click view id is " + v.getId());
		switch (v.getId())
		{
		case R.id.button_commit:
			// 可以在这里检测输入的合理性
			// String _temp_str = _edittext_dialog.getText().toString();
			// if (_temp_str.length() < 7 && _temp_str.length() > 0)
			// _areas.set(_selected, Float.valueOf(_temp_str))
			// _dialog.dismiss();
			break;
		case R.id.button_cancel:
			// _dialog.dismiss();
			break;
		default:
			// show_dialog(v.getdata);
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
			_every_area = _total_area / _areas.size();
			construct();
			break;

		case "style4":
			String[] _nums = args[0].split(",");
			int _room = Integer.parseInt(_nums[0]);
			int _parlour = Integer.parseInt(_nums[1]);
			int _kitchen = Integer.parseInt(_nums[2]);
			int _toilet = Integer.parseInt(_nums[3]);
			int _balcony = Integer.parseInt(_nums[4]);

			_every_area = _total_area / (_room + _parlour + _kitchen + _toilet + _balcony);
			construct(_room, _parlour, _kitchen, _toilet, _balcony);
			break;
		}
		notifyDataSetChanged();
	}

}
