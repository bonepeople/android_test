package com.shownest.android.adapter;

import com.shownest.android.R;
import com.shownest.android.model.ItemDetail;
import com.shownest.android.model.OnChangeListener;

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
		public TextView _text_price;
		public TextView _text_unit1;
		public TextView _text_unit2;
		public TextView _text_metricunit1;
		public TextView _text_metricunit2;
		public TextView _text_number;
		public TextView _text_total;
	}

	public Adapter_quotation_detail(Context _context, String _tag, SparseArray<ItemDetail> _data)
	{
		this._context = _context;
		_inflater = LayoutInflater.from(_context);
		this._tag = _tag;
		this._data = _data;
	}

	public void setOnChangetListener(OnChangeListener _listener)
	{
		this._listener = _listener;
	}

	@Override
	public int getCount()
	{
		return _data.size();
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
		View _view = convertView;
		ViewHolder _holder;

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
			_holder._image_edit.setId(position);
			_holder._image_edit.setOnClickListener(this);
			_view.setTag(_holder);
		}
		else
		{
			_holder = (ViewHolder) _view.getTag();
		}
		ItemDetail _temp_item = _data.get(position);
		_holder._text_name.setText(_temp_item.get_itemName());
		_holder._text_fucai.setText(_temp_item.get_material());
		_holder._text_shuoming.setText(_temp_item.get_technics());
		_holder._text_price.setText(String.valueOf(_temp_item.get_price()));
		_holder._text_unit1.setText(_temp_item.get_unit());
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
			_listener.onChange("adapter change", new String[] { _tag, String.valueOf(v.getId()) });
	}
}
