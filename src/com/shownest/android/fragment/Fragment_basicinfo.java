package com.shownest.android.fragment;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Fragment_basicinfo extends DEBUG_Fragment
{
	private TextView _name, _showname, _role, _phone;
	private ImageView _header;
	private RelativeLayout _item_name, _item_password, _item_role, _item_phone, _relativelayout_wait;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);

		View _view = inflater.inflate(R.layout.fragment_basicinfo, container, false);
		_name = (TextView) _view.findViewById(R.id.textview_name);
		_showname = (TextView) _view.findViewById(R.id.textview_showname);
		_role = (TextView) _view.findViewById(R.id.textview_role);
		_phone = (TextView) _view.findViewById(R.id.textview_phone);
		_header = (ImageView) _view.findViewById(R.id.imageview_header);
		_item_name = (RelativeLayout) _view.findViewById(R.id.relativelayout_name);
		_item_password = (RelativeLayout) _view.findViewById(R.id.relativelayout_password);
		_item_role = (RelativeLayout) _view.findViewById(R.id.relativelayout_role);
		_item_phone = (RelativeLayout) _view.findViewById(R.id.relativelayout_phone);
		_relativelayout_wait = (RelativeLayout) _view.findViewById(R.id.relativelayout_wait);

		setContent();
		return _view;
	}

	@Override
	protected String get_class()
	{
		return this.getClass().toString();
	}

	private void setContent()
	{
		// 进入本页面之前就应该已经将用户信息获取到了
		// 将信息显示出来
	}
}
