package com.shownest.android.adapter;

import java.util.ArrayList;

import com.loopj.android.image.SmartImageView;
import com.shownest.android.R;
import com.shownest.android.model.QuotaInfo;
import com.shownest.android.utils.CommonUtil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

public class Adapter_quota_state extends BaseAdapter
{
	private Context _context;
	private LayoutInflater _inflater;
	private ArrayList<QuotaInfo> _data;

	private static class ViewHolder
	{
		public SmartImageView _header;
		public TextView _text_name;
		public TextView _textview_role;
		public RatingBar _ratingbar;
		public TextView _textview_state;
		public TextView _textview_money;
	}

	public Adapter_quota_state(Context _context, ArrayList<QuotaInfo> _data)
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
	public QuotaInfo getItem(int position)
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
		QuotaInfo _temp_quota = getItem(position);
		if (convertView == null)
		{
			_view = _inflater.inflate(R.layout.item_quate_state, null);
			_holder = new ViewHolder();
			_holder._header = (SmartImageView) _view.findViewById(R.id.imageview_header);
			_holder._text_name = (TextView) _view.findViewById(R.id.textview_name);
			_holder._textview_role = (TextView) _view.findViewById(R.id.textview_role);
			_holder._ratingbar = (RatingBar) _view.findViewById(R.id.ratingbar);
			_holder._textview_state = (TextView) _view.findViewById(R.id.textview_state);
			_holder._textview_money = (TextView) _view.findViewById(R.id.textview_money);
			_view.setTag(_holder);
		}
		else
		{
			_holder = (ViewHolder) _view.getTag();
		}
		_holder._header.setImageUrl(CommonUtil.getUserHeaderIconUrl(_temp_quota.get_userType(), _temp_quota.get_headerIcon()));
		_holder._text_name.setText(_temp_quota.get_userShowName());
		_holder._textview_role.setText(_temp_quota.get_userType_name());
		_holder._ratingbar.setRating(CommonUtil.getRoleLevel(_temp_quota.get_gradePraise()));
		_holder._textview_state.setVisibility(TextView.VISIBLE);
		switch (_temp_quota.get_state())
		{
		case 2:
			_holder._textview_state.setBackgroundResource(R.drawable.bid_state_2);
			break;
		case 4:
			_holder._textview_state.setBackgroundResource(R.drawable.bid_state_4);
			break;
		case 5:
			_holder._textview_state.setBackgroundResource(R.drawable.bid_state_5);
			break;
		default:
			_holder._textview_state.setVisibility(TextView.INVISIBLE);
		}
		_holder._textview_money.setText(String.valueOf((int) _temp_quota.get_userPrice()));

		return _view;
	}

}
