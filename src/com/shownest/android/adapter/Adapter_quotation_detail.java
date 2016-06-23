package com.shownest.android.adapter;

import com.shownest.android.R;
import com.shownest.android.model.ItemDetail;
import com.shownest.android.model.OnChangeListener;
import com.shownest.android.utils.NumberUtil;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Adapter_quotation_detail extends BaseAdapter implements View.OnClickListener
{
	private Context _context;
	private LayoutInflater _inflater;
	private String _tag;
	private SparseArray<ItemDetail> _data;
	private OnChangeListener _listener;

	private static class ViewHolder
	{
		public TextView _text_name;
		public ImageView _image_edit;
		public TextView _text_fucai;
		public TextView _text_shuoming;
		public TextView _text_note_price;
		public TextView _text_price;
		public TextView _text_note_unit;
		public TextView _text_unit1;
		public TextView _text_unit2;
		public TextView _text_metricunit1;
		public TextView _text_metricunit2;
		public TextView _text_number;
		public TextView _text_total;
	}

	public Adapter_quotation_detail(Context _context, SparseArray<ItemDetail> _data)
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
	public ItemDetail getItem(int position)
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
		ItemDetail _temp_item = getItem(position);
		if (convertView == null)
		{
			_view = _inflater.inflate(R.layout.item_quotation_detail, null);
			_holder = new ViewHolder();
			_holder._text_name = (TextView) _view.findViewById(R.id.textview_name);
			_holder._image_edit = (ImageView) _view.findViewById(R.id.imageview_edit);
			_holder._text_fucai = (TextView) _view.findViewById(R.id.textview_fucai);
			_holder._text_shuoming = (TextView) _view.findViewById(R.id.textview_shuoming);
			_holder._text_price = (TextView) _view.findViewById(R.id.textview_price);
			_holder._text_unit1 = (TextView) _view.findViewById(R.id.textview_unit1);
			_holder._text_unit2 = (TextView) _view.findViewById(R.id.textview_unit2);
			_holder._text_metricunit1 = (TextView) _view.findViewById(R.id.textview_metricunit1);
			_holder._text_metricunit2 = (TextView) _view.findViewById(R.id.textview_metricunit2);
			_holder._text_number = (TextView) _view.findViewById(R.id.textview_number);
			_holder._text_total = (TextView) _view.findViewById(R.id.textview_total);
			_holder._text_name.setTextColor(_context.getResources().getColor(R.color.text_blue));
			_holder._image_edit.setOnClickListener(this);
			if (_temp_item.get_tag().equals("tax"))
			{
				_holder._text_note_price = (TextView) _view.findViewById(R.id.textview_note_price);
				_holder._text_note_unit = (TextView) _view.findViewById(R.id.textview_note_unit);
				_holder._text_note_price.setText("收费比例:");
				_holder._text_note_unit.setText("%");
			}
			_view.setTag(_holder);
		}
		else
		{
			_holder = (ViewHolder) _view.getTag();
		}
		_holder._image_edit.setId(position);
		_holder._text_name.setText(_temp_item.get_itemName());
		_holder._text_fucai.setText(_temp_item.get_material());
		_holder._text_shuoming.setText(_temp_item.get_technics());
		if (_temp_item.get_tag().equals("tax"))
			_holder._text_price.setText(String.valueOf(NumberUtil.mul(_temp_item.get_price(), 100)));
		else
		{
			_holder._text_price.setText(String.valueOf(_temp_item.get_price()));
			_holder._text_unit1.setText(_temp_item.get_unit());
		}
		_holder._text_unit2.setText(_temp_item.get_unit());
		_holder._text_metricunit1.setText(_temp_item.get_metricUnit());
		_holder._text_metricunit2.setText(_temp_item.get_metricUnit());
		_holder._text_number.setText(String.valueOf(_temp_item.get_number()));
		_holder._text_total.setText(String.valueOf(_temp_item.get_total()));

		return _view;
	}

	@Override
	public void onClick(View v)
	{
		if (_listener != null)
			_listener.onChange(_tag, new String[] { String.valueOf(v.getId()) });
	}
}
