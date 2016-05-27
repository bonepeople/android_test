package com.shownest.android.adapter;

import com.shownest.android.R;
import com.shownest.android.model.ItemDetail;
import com.shownest.android.model.Package;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Adapter_quotation_detail extends BaseAdapter implements View.OnClickListener
{
	private Context _context;
	private LayoutInflater _inflater;
	private SparseArray<Package> _data;

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
		public LinearLayout _linearlayout_fucai;
	}

	public Adapter_quotation_detail(Context _context, SparseArray<Package> _data)
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
		System.out.println("get view - " + position);
		View _view = convertView;
		ViewHolder _holder;

		if (convertView == null)
		{
			System.out.println("creat a new view - " + position);
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
			_holder._linearlayout_fucai = (LinearLayout) _view.findViewById(R.id.linearlayout_fucai);

			_holder._image_edit.setOnClickListener(this);
			_view.setTag(_holder);
		}
		else
		{
			_holder = (ViewHolder) _view.getTag();
		}
		ItemDetail _temp_item = (ItemDetail) _data.get(position + 1)._data;
		if (_temp_item == null)
			System.out.println("_temp_item  is null");
		_holder._text_name.setText(_temp_item.get_itemName());
		if (_temp_item.get_material().equals(""))
			_holder._linearlayout_fucai.setVisibility(LinearLayout.GONE);
		else
			_holder._text_fucai.setText(_temp_item.get_material());
		_holder._text_shuoming.setText(_temp_item.get_technics());
		_holder._text_price.setText(String.valueOf(_temp_item.get_price()));
		_holder._text_unit1.setText(_temp_item.get_unit());
		_holder._text_unit2.setText(_temp_item.get_unit());
		_holder._text_metricunit1.setText(_temp_item.get_metricUnit());
		_holder._text_metricunit2.setText(_temp_item.get_metricUnit());
		_holder._text_number.setText(String.valueOf(_temp_item.get_number()));
		double _total = _temp_item.get_price() * _temp_item.get_number();
		System.out.println("total is " + _total + "price:" + _temp_item.get_price() + "number" + _temp_item.get_number());
		_holder._text_total.setText(String.format("%.2f", _total));

		return _view;
	}

	@Override
	public void onClick(View v)
	{
		System.out.println("click view id is " + v.getId());
		switch (v.getId())
		{
		case R.id.button_commit:
			// 可以在这里检测输入的合理性
			break;
		case R.id.button_cancel:
			break;
		default:
			break;
		}
	}

}