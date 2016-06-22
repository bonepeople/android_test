package com.shownest.android.fragment;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Activity;
import com.shownest.android.basic.DEBUG_Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Fragment_title extends DEBUG_Fragment implements View.OnClickListener
{
	private TextView _text_title, _text_menu;
	private ImageView _image_back;
	private DEBUG_Activity _super_activity;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);
		View _view = inflater.inflate(R.layout.fragment_title, container, false);

		_text_title = (TextView) _view.findViewById(R.id.textview_title);
		_text_menu = (TextView) _view.findViewById(R.id.textview_menu);
		_image_back = (ImageView) _view.findViewById(R.id.imageview_back);

		_text_menu.setOnClickListener(this);
		_image_back.setOnClickListener(this);

		return _view;

	}

	public void setTitle(String _name)
	{
		_text_title.setText(_name);
	}

	public void setMenu(String _menu)
	{
		_text_menu.setText(_menu);
		_text_menu.setVisibility(TextView.VISIBLE);
	}

	public void setMenu(int _resid)
	{
		_text_menu.setBackgroundResource(_resid);
		_text_menu.setVisibility(TextView.VISIBLE);
	}

	@Override
	public void onClick(View v)
	{
		if (v.getId() == R.id.imageview_back)
		{
			sendMessage(1);
		}
		else
		{
			sendMessage(2);
		}
	}

	private void sendMessage(int _type)
	{
		if (_super_activity == null)
			_super_activity = (DEBUG_Activity) getActivity();

		if (_type == 1)
		{
			_super_activity.onBackPressed();
		}
		else
		{
			_super_activity.menu_click();
		}
	}
}
