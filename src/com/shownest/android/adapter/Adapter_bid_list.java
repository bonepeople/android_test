package com.shownest.android.adapter;

import java.util.ArrayList;

import com.shownest.android.R;
import com.shownest.android.model.BidInfo_common;
import com.shownest.android.utils.NumberUtil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Adapter_bid_list extends BaseAdapter
{
	private Context _context;
	private LayoutInflater _inflater;
	private ArrayList<BidInfo_common> _data;

	private static class ViewHolder
	{
		public TextView _text_name;
		public TextView _textview_room;
		public TextView _textview_area;
		public TextView _textview_money;
		public TextView _textview_number;
		public TextView _textview_date;
	}

	public Adapter_bid_list(Context _context, ArrayList<BidInfo_common> _data)
	{
		this._context = _context;
		_inflater = LayoutInflater.from(_context);
		this._data = _data;
	}

	@Override
	public int getCount()
	{
		System.out.println("data length:" + _data.size());
		return _data.size();
	}

	@Override
	public BidInfo_common getItem(int position)
	{
		return _data.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View _view = convertView;
		ViewHolder _holder;
		BidInfo_common _temp_bid = getItem(position);
		if (convertView == null)
		{
			_view = _inflater.inflate(R.layout.item_bid_list, null);
			_holder = new ViewHolder();
			_holder._text_name = (TextView) _view.findViewById(R.id.textview_name);
			_holder._textview_room = (TextView) _view.findViewById(R.id.textview_room);
			_holder._textview_area = (TextView) _view.findViewById(R.id.textview_area);
			_holder._textview_money = (TextView) _view.findViewById(R.id.textview_money);
			_holder._textview_number = (TextView) _view.findViewById(R.id.textview_number);
			_holder._textview_date = (TextView) _view.findViewById(R.id.textview_date);
			_view.setTag(_holder);
		}
		else
		{
			_holder = (ViewHolder) _view.getTag();
		}

		_holder._text_name.setText(_temp_bid.get_houseName());
		_holder._textview_room.setText(_temp_bid.get_rooms_name());
		_holder._textview_area.setText(_temp_bid.get_homeSq() + "m²");
		_holder._textview_money.setText("预算" + NumberUtil.div(_temp_bid.get_budget(), 10000, 2) + "万元");
		_holder._textview_number.setText(_temp_bid.get_bidNum() + "人参与");
		_holder._textview_date.setText(_temp_bid.get_createDate());

		return _view;
	}

}
