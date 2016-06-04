package com.shownest.android.fragment;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.widget.LinearLayout_picture;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class Fragment_test extends DEBUG_Fragment
{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);
		View _view = inflater.inflate(R.layout.fragment_test, container, false);

		LinearLayout _body = (LinearLayout) _view.findViewById(R.id.linearlayout_content);
		LinearLayout_picture _picture = new LinearLayout_picture(getActivity(), "pictures");
		
		_body.addView(_picture);
		return _view;
	}
}
