package com.shownest.android.adapter;

import java.util.ArrayList;

import com.shownest.android.R;
import com.shownest.android.model.OnChangeListener;
import com.shownest.android.model.OrderInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Adapter_order_state_maijia extends BaseAdapter implements View.OnClickListener
{
	private Context _context;
	private LayoutInflater _inflater;
	private String _tag;
	private ArrayList<OrderInfo> _data;
	private OnChangeListener _listener;

	private static class ViewHolder
	{
		public LinearLayout _linearlayout_content;
		public TextView _textview_name;
		public TextView _textview_state;
		public TextView _textview_stage;
		public TextView _textview_note_type;
		public TextView _textview_type;
		public TextView _textview_phone;
		public TextView _textview_hint;
		public TextView _textview_date;
	}

	public Adapter_order_state_maijia(Context _context, ArrayList<OrderInfo> _data)
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
	public OrderInfo getItem(int position)
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
		OrderInfo _temp_order = getItem(position);
		if (convertView == null)
		{
			_view = _inflater.inflate(R.layout.item_bid_state_maijia, null);
			_holder = new ViewHolder();
			_holder._linearlayout_content = (LinearLayout) _view.findViewById(R.id.linearlayout_content);
			_holder._textview_name = (TextView) _view.findViewById(R.id.textview_name);
			_holder._textview_state = (TextView) _view.findViewById(R.id.textview_state);
			_holder._textview_stage = (TextView) _view.findViewById(R.id.textview_stage);
			_holder._textview_note_type = (TextView) _view.findViewById(R.id.textview_note_type);
			_holder._textview_type = (TextView) _view.findViewById(R.id.textview_type);
			_holder._textview_phone = (TextView) _view.findViewById(R.id.textview_phone);
			_holder._textview_hint = (TextView) _view.findViewById(R.id.textview_hint);
			_holder._textview_date = (TextView) _view.findViewById(R.id.textview_date);

			_holder._linearlayout_content.setOnClickListener(this);
			_holder._textview_hint.setOnClickListener(this);
			_view.setTag(_holder);
		}
		else
		{
			_holder = (ViewHolder) _view.getTag();
		}
		_holder._linearlayout_content.setId(position);
		_holder._textview_hint.setId(position + 50000);
		_holder._textview_name.setText(_temp_order.get_protocolName());
		_holder._textview_state.setText(_temp_order.get_currentStageState_name());
		_holder._textview_stage.setText(_temp_order.get_currentStageName());
		switch (_temp_order.get_bookType())
		{
		case 12:
			_holder._textview_note_type.setText("服务类型");
			_holder._textview_type.setText(_temp_order.get_desiBiddingTypeId_name());
			break;
		case 13:
			_holder._textview_note_type.setText("装修方式");
			_holder._textview_type.setText(_temp_order.get_desiBiddingTypeId_name());
			break;
		case 14:
			_holder._textview_note_type.setText("监理类型");
			_holder._textview_type.setText(_temp_order.get_desiBiddingTypeId_name());
			break;
		}
		_holder._textview_phone.setText(_temp_order.get_ownerPhone());
		if (!_temp_order.get_currentStageState_hint().equals(""))
		{
			_holder._textview_hint.setText(_temp_order.get_currentStageState_hint());
			_holder._textview_hint.setVisibility(TextView.VISIBLE);
		}
		else
			_holder._textview_hint.setVisibility(TextView.INVISIBLE);
		_holder._textview_date.setText(_temp_order.get_createDate());
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

	}

}
