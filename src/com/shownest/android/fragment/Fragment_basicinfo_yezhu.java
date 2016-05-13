package com.shownest.android.fragment;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.widget.RelativeLayout_edit_informationbar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class Fragment_basicinfo_yezhu extends DEBUG_Fragment
{
	private LinearLayout _body;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);
		View _view = inflater.inflate(R.layout.fragment_setinfo_yezhu, container, false);
		_body = (LinearLayout) _view.findViewById(R.id.linearlayout_content);
		
		RelativeLayout_edit_informationbar _showname = new RelativeLayout_edit_informationbar(getActivity(), _body, 2, new String[]{"秀巢昵称","dqnq"},false);
		
		
		_body.addView(_showname);
		return _view;
	}

	@Override
	protected String get_class()
	{
		return this.getClass().toString();
	}

}
