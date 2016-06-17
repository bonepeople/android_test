package com.shownest.android.adapter;

import java.util.ArrayList;

import com.shownest.android.R;
import com.shownest.android.model.BidInfo_common;
import com.shownest.android.model.OnChangeListener;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Adapter_bid_state_yezhu extends BaseAdapter implements View.OnClickListener
{
	private Context _context;
	private LayoutInflater _inflater;
	private String _tag;
	private ArrayList<BidInfo_common> _data;
	private OnChangeListener _listener;

	private static class ViewHolder
	{
		public TextView _text_role;
		public TextView _textview_number;
		public TextView _textview_state;
		public TextView _textview_date;
	}

	public Adapter_bid_state_yezhu(Context _context, ArrayList<BidInfo_common> _data)
	{
		this._context = _context;
		_inflater = LayoutInflater.from(_context);
		this._data = _data;
	}

	public void setOnChangetListener(String _tag, OnChangeListener _listener)
	{
		this._tag = _tag;
		this._listener = _listener;
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
			_view = _inflater.inflate(R.layout.item_bid_state_yezhu, null);
			_holder = new ViewHolder();
			_holder._text_role = (TextView) _view.findViewById(R.id.textview_role);
			_holder._textview_number = (TextView) _view.findViewById(R.id.textview_number);
			_holder._textview_state = (TextView) _view.findViewById(R.id.textview_state);
			_holder._textview_date = (TextView) _view.findViewById(R.id.textview_date);

			_view.setId(position);
			_view.setOnClickListener(this);
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

		return _view;
	}

	@Override
	public void onClick(View v)
	{
		if (_listener != null)
			_listener.onChange(_tag, new String[] { String.valueOf(v.getId()) });
	}

}
