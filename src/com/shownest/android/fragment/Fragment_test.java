package com.shownest.android.fragment;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.widget.LinearLayout_style;

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

		String[] _items = new String[] { "简约", "现代", "中式", "欧式", "美式", "日式", "东南亚", "地中海", "混搭", "新古典", "田园", "其他" };
		LinearLayout_style _choose = new LinearLayout_style(getActivity(), "倾向风格(可多选)", _items, 5);

		_body.addView(_choose);
		return _view;
	}

	@Override
	protected String get_class()
	{
		return this.getClass().toString();
	}
}
