package com.shownest.android.fragment;

import com.loopj.android.image.SmartImageView;
import com.shownest.android.R;
import com.shownest.android.activity.Activity_my_center;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.model.UserInfo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Fragment_basicinfo extends DEBUG_Fragment implements View.OnClickListener
{
	private TextView _name, _showname, _role, _phone;
	private SmartImageView _imageview_header;
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
		_imageview_header = (SmartImageView) _view.findViewById(R.id.imageview_header);
		_item_name = (RelativeLayout) _view.findViewById(R.id.relativelayout_name);
		_item_password = (RelativeLayout) _view.findViewById(R.id.relativelayout_password);
		_item_role = (RelativeLayout) _view.findViewById(R.id.relativelayout_role);
		_item_phone = (RelativeLayout) _view.findViewById(R.id.relativelayout_phone);
		_relativelayout_wait = (RelativeLayout) _view.findViewById(R.id.relativelayout_wait);

		setContent();
		return _view;
	}

	private void setContent()
	{
		UserInfo _info = Activity_my_center.get_userinfo();
		String _url = "http://t.shownest.com:86/_resources/upload/headerIcon/" + _info.get_headerIcon();
		_imageview_header.setImageUrl(_url);
		_name.setText(String.valueOf(_info.get_userName()));
		_showname.setText(_info.get_userShowName());
		_phone.setText(_info.get_userPhone());

		_imageview_header.setOnClickListener(this);
		if (_info.is_checkUsername())
			_name.setTextColor(getResources().getColor(R.color.text_gray));
		else
			_item_name.setOnClickListener(this);
		_item_password.setOnClickListener(this);
		_role.setTextColor(getResources().getColor(R.color.text_gray));
		switch (_info.get_userType())
		{
		case 11:
			_role.setText("业主");
			break;
		case 12:
			_role.setText("设计师");
			break;
		case 13:
			_role.setText("施工队");
			break;
		case 14:
			_role.setText("监理");
			break;
		case 15:
			_role.setText("装修公司");
			break;
		case 100:
			_role.setTextColor(getResources().getColor(R.color.main_color));
			_item_role.setOnClickListener(this);
			break;
		}
		_item_phone.setOnClickListener(this);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.imageview_header:
			break;

		case R.id.relativelayout_name:

			break;

		case R.id.relativelayout_password:
			break;

		case R.id.relativelayout_role:

			break;

		case R.id.relativelayout_phone:
			break;
		}

	}
}
