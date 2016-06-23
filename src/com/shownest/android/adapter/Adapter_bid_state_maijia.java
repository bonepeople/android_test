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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Adapter_bid_state_maijia extends BaseAdapter implements View.OnClickListener
{
	private Context _context;
	private LayoutInflater _inflater;
	private String _tag;
	private ArrayList<BidInfo_common> _data;
	private OnChangeListener _listener;

	private static class ViewHolder
	{
		public LinearLayout _linearlayout_content;
		public TextView _textview_name;
		public TextView _textview_showname;
		public TextView _textview_price;
		public TextView _textview_phone;
		public TextView _textview_number;
		public TextView _textview_state;
		public TextView _textview_hint;
		public TextView _textview_commit;
		public ImageView _imageview_state;
	}

	public Adapter_bid_state_maijia(Context _context, ArrayList<BidInfo_common> _data)
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
			_view = _inflater.inflate(R.layout.item_bid_state_maijia, null);
			_holder = new ViewHolder();
			_holder._linearlayout_content = (LinearLayout) _view.findViewById(R.id.linearlayout_content);
			_holder._textview_name = (TextView) _view.findViewById(R.id.textview_name);
			_holder._textview_showname = (TextView) _view.findViewById(R.id.textview_showname);
			_holder._textview_price = (TextView) _view.findViewById(R.id.textview_price);
			_holder._textview_phone = (TextView) _view.findViewById(R.id.textview_phone);
			_holder._textview_number = (TextView) _view.findViewById(R.id.textview_number);
			_holder._textview_state = (TextView) _view.findViewById(R.id.textview_state);
			_holder._textview_hint = (TextView) _view.findViewById(R.id.textview_hint);
			_holder._textview_commit = (TextView) _view.findViewById(R.id.textview_commit);
			_holder._imageview_state = (ImageView) _view.findViewById(R.id.imageview_state);

			_holder._linearlayout_content.setOnClickListener(this);
			_holder._textview_commit.setOnClickListener(this);
			_holder._textview_hint.setOnClickListener(this);
			_view.setTag(_holder);
		}
		else
		{
			_holder = (ViewHolder) _view.getTag();
		}
		_holder._linearlayout_content.setId(position);
		_holder._textview_commit.setId(position + 100000);
		_holder._textview_hint.setId(position + 50000);
		_holder._textview_name.setText(_temp_bid.get_houseName());
		_holder._textview_showname.setText(_temp_bid.get_contacts());
		_holder._textview_price.setText(_temp_bid.get_budget() + "元");
		_holder._textview_phone.setText(_temp_bid.get_phone());
		_holder._textview_number.setText(_temp_bid.get_bidNum() + "人");
		_holder._textview_state.setText(_temp_bid.get_providerState_name());
		if (!_temp_bid.get_providerState_hint().equals(""))
		{
			_holder._textview_hint.setText(_temp_bid.get_providerState_hint());
			_holder._textview_hint.setVisibility(TextView.VISIBLE);
		}
		else
			_holder._textview_hint.setVisibility(TextView.INVISIBLE);
		if (_tag.equals("3"))// 全部
		{
			_holder._imageview_state.setVisibility(ImageView.VISIBLE);
			switch (_temp_bid.get_providerState())
			{
			case 2:// 备选
				_holder._imageview_state.setImageResource(R.drawable.bid_state_2);
				break;
			case 4:// 淘汰
				_holder._imageview_state.setImageResource(R.drawable.bid_state_4);
				break;
			case 5:// 中标
				_holder._imageview_state.setImageResource(R.drawable.bid_state_5);
				break;
			default:
				_holder._imageview_state.setVisibility(ImageView.GONE);
			}
		}

		return _view;
	}

	@Override
	public void onClick(View v)
	{
		int _id = v.getId();
		if (_id < 50000)
		{
			// linearlayout_content
			if (_listener != null)
				_listener.onChange(_tag, new String[] { "detail", String.valueOf(_id) });
		}
		else if (_id < 100000)
		{
			// textview_hint
			if (_listener != null)
				_listener.onChange(_tag, new String[] { "hint", String.valueOf(_id - 50000) });
		}
		else
		{
			// textview_commit
			if (_listener != null)
				_listener.onChange(_tag, new String[] { "commit", String.valueOf(_id - 100000) });
		}
	}

}
