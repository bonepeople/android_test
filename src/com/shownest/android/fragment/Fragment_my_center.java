package com.shownest.android.fragment;

import com.loopj.android.image.SmartImageView;
import com.shownest.android.R;
import com.shownest.android.activity.Activity_basicinfo;
import com.shownest.android.activity.Activity_my_center;
import com.shownest.android.activity.Activity_select_role;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.model.UserInfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Fragment_my_center extends DEBUG_Fragment implements View.OnClickListener
{
	private TextView _textview_money, _textview_name, _number_bowen, _number_guanzhu, _number_xiuyou, _textview_hint;
	private RelativeLayout _item_info, _item_money;
	private SmartImageView _imageview_header;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);

		View _view = inflater.inflate(R.layout.fragment_my_center, container, false);
		_textview_money = (TextView) _view.findViewById(R.id.textview_money);
		_textview_name = (TextView) _view.findViewById(R.id.textview_name);
		_number_bowen = (TextView) _view.findViewById(R.id.textview_number_bowen);
		_number_guanzhu = (TextView) _view.findViewById(R.id.textview_number_guanzhu);
		_number_xiuyou = (TextView) _view.findViewById(R.id.textview_number_xiuyou);
		_textview_hint = (TextView) _view.findViewById(R.id.textview_hint);
		_item_info = (RelativeLayout) _view.findViewById(R.id.item_info);
		_item_money = (RelativeLayout) _view.findViewById(R.id.item_money);
		_imageview_header = (SmartImageView) _view.findViewById(R.id.imageview_header);

		return _view;
	}

	@Override
	public void onResume()
	{
		setContent();
		super.onResume();
	}

	private void setContent()
	{
		UserInfo _info = Activity_my_center.get_userinfo();
		_textview_money.setText(String.valueOf(_info.get_money()));
		_textview_name.setText(_info.get_userName());
		_number_bowen.setText(String.valueOf(_info.get_blogNum()));
		String _url = "http://t.shownest.com:86/_resources/upload/headerIcon/" + Activity_my_center.get_userinfo().get_headerIcon();
		_imageview_header.setImageUrl(_url);

		switch (_info.get_userType())
		{
		case 11:
			break;
		case 12:
			break;
		case 13:
			break;
		case 14:
			break;
		case 15:
			break;
		case 100:
			_textview_hint.setVisibility(TextView.VISIBLE);
			_textview_hint.setOnClickListener(this);
			break;
		}
		_item_info.setOnClickListener(this);
		_item_money.setOnClickListener(this);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.textview_hint:
			Intent _select_role = new Intent(getActivity(), Activity_select_role.class);
			getActivity().startActivity(_select_role);
			break;
		case R.id.item_info:
			Intent _my_info = new Intent(getActivity(), Activity_basicinfo.class	);
			getActivity().startActivity(_my_info);
			break;

		case R.id.item_money:
			break;
		}

	}
}
