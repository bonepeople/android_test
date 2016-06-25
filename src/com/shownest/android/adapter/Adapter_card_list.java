package com.shownest.android.adapter;

import java.util.ArrayList;

import com.loopj.android.image.SmartImageView;
import com.shownest.android.R;
import com.shownest.android.model.BankCardInfo;
import com.shownest.android.model.OnChangeListener;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Adapter_card_list extends BaseAdapter implements View.OnClickListener
{
	private Context _context;
	private LayoutInflater _inflater;
	private String _tag;
	private ArrayList<BankCardInfo> _data;
	private OnChangeListener _listener;

	private static class ViewHolder
	{
		public SmartImageView _logo;
		public TextView _text_name;
		public TextView _text_type;
		public TextView _text_number;
		public TextView _text_delete;
	}

	public Adapter_card_list(Context _context, ArrayList<BankCardInfo> _data)
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
	public BankCardInfo getItem(int position)
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
		BankCardInfo _temp_card = getItem(position);
		if (convertView == null)
		{
			_view = _inflater.inflate(R.layout.item_card_list, null);
			_holder = new ViewHolder();
			_holder._logo = (SmartImageView) _view.findViewById(R.id.imageview_header);
			_holder._text_name = (TextView) _view.findViewById(R.id.textview_name);
			_holder._text_type = (TextView) _view.findViewById(R.id.textview_type);
			_holder._text_number = (TextView) _view.findViewById(R.id.textview_number);
			_holder._text_delete = (TextView) _view.findViewById(R.id.textview_commit);

			_view.setTag(_holder);
		}
		else
		{
			_holder = (ViewHolder) _view.getTag();
		}
		_holder._logo.setImageUrl(_temp_card.get_logoUrl());
		_holder._text_name.setText(_temp_card.get_bankName());
		_holder._text_type.setText(_temp_card.get_cardType());
		_holder._text_number.setText(_temp_card.get_bankCardNo());
		_holder._text_delete.setId(position);
		_holder._text_delete.setOnClickListener(this);

		return _view;
	}

	@Override
	public void onClick(View v)
	{
		int _id = v.getId();
		if (_listener != null)
			_listener.onChange(_tag, new String[] { String.valueOf(_id) });
	}

}
