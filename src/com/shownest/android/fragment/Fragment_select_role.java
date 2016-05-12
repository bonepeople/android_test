package com.shownest.android.fragment;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.widget.Linearlayout_role;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment_select_role extends DEBUG_Fragment
{
	private Linearlayout_role _yezhu, _shejishi, _shigongdui, _jianli, _zhuangxiugongsi, _doubushi;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);

		View _view = inflater.inflate(R.layout.fragment_select_role, container, false);

		return _view;
	}

	@Override
	protected String get_class()
	{
		return this.getClass().toString();
	}

}
