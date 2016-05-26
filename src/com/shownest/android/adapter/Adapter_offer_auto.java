package com.shownest.android.adapter;

import java.util.ArrayList;

import com.shownest.android.R;
import com.shownest.android.model.OnChangeListener;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Adapter_offer_auto extends BaseAdapter implements OnChangeListener
{
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

	private static class ViewHolder
	{
		public TextView _text_name;
		public TextView _text_left;
		public TextView _text_right;
	}

	public Adapter_offer_auto(Context _context)
	{
		_inflater = LayoutInflater.from(_context);
		refresh_data();
	}

	public void set_area(float _area)
	{
		_total_area = _area;
		_every_area = _total_area / _total_num;
		refresh_data();
	}

	/**
	 * 设置户型结构
	 * 
	 * @param _num
	 *            3,2,2,2,4 室，厅，厨，卫，阳台
	 */
	public void set_num(String _num)
	{
		String[] _nums = _num.split(",");
		_room = Integer.parseInt(_nums[0]);
		_parlour = Integer.parseInt(_nums[1]);
		_kitchen = Integer.parseInt(_nums[2]);
		_toilet = Integer.parseInt(_nums[3]);
		_balcony = Integer.parseInt(_nums[4]);

		_total_num = _room + _parlour + _kitchen + _balcony + _toilet;
		_every_area = _total_area / _total_num;
		refresh_data();
	}

	public void set_acreage(String number, int position)
	{
		_areas.set(position, Float.valueOf(number));
		notifyDataSetChanged();
	}

	private void refresh_data()
	{
		_areas.clear();
		for (int _temp_i = 0; _temp_i < _total_num; _temp_i++)
			_areas.add(_every_area);
		notifyDataSetChanged();
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

	@Override
	public void onChange(String tag, String[] args)
	{
		// TODO Auto-generated method stub
		
	}

}
