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

public class Adapter_bid_state extends BaseAdapter
{
	private Context _context;
	private LayoutInflater _inflater;
	private ArrayList<BidInfo_common> _data;

	private static class ViewHolder
	{
		public TextView _text_role;
		public TextView _textview_number;
		public TextView _textview_state;
		public TextView _textview_date;
		public TextView _textview_hint;
	}

	public Adapter_bid_state(Context _context, ArrayList<BidInfo_common> _data)
	{
		this._context = _context;
		_inflater = LayoutInflater.from(_context);
		this._data = _data;
	}

	@Override
	public int getCount()
	{
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
			_view = _inflater.inflate(R.layout.item_bid_state, null);
			_holder = new ViewHolder();
			_holder._text_role = (TextView) _view.findViewById(R.id.textview_role);
			_holder._textview_number = (TextView) _view.findViewById(R.id.textview_number);
			_holder._textview_state = (TextView) _view.findViewById(R.id.textview_state);
			_holder._textview_date = (TextView) _view.findViewById(R.id.textview_date);
			_holder._textview_hint = (TextView) _view.findViewById(R.id.textview_hint);
			_view.setTag(_holder);
		}
		else
		{
			_holder = (ViewHolder) _view.getTag();
		}

		_holder._text_role.setText(_temp_bid.get_bookType_name());
		_holder._textview_number.setText(String.valueOf(_temp_bid.get_bidNum()));
		_holder._textview_state.setText(_temp_bid.get_bidsState_name());
		_holder._textview_date.setText(_temp_bid.get_remainingDate());
		_holder._textview_hint.setText("2");

		return _view;
	}

}
