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

		RelativeLayout_edit_informationbar _showname = new RelativeLayout_edit_informationbar(getActivity(), _body, 2, new String[] { "秀巢昵称", "dqnq" }, true);
		RelativeLayout_edit_informationbar _showname1 = new RelativeLayout_edit_informationbar(getActivity(), _body, 1, new String[] { "style1", "dqnq", "333" }, true);
		RelativeLayout_edit_informationbar _showname2 = new RelativeLayout_edit_informationbar(getActivity(), _body, 2, new String[] { "style2", "dqnq" }, true);
		RelativeLayout_edit_informationbar _showname3 = new RelativeLayout_edit_informationbar(getActivity(), _body, 3, new String[] { "style3", "dqnq", "33333" }, true);
		RelativeLayout_edit_informationbar _showname4 = new RelativeLayout_edit_informationbar(getActivity(), _body, 4, new String[] { "style4", "1", "2", "3", "4", "5" }, true);
		RelativeLayout_edit_informationbar _showname5 = new RelativeLayout_edit_informationbar(getActivity(), _body, 5, new String[] { "style5", "csacacsa" }, true);
		RelativeLayout_edit_informationbar _showname6 = new RelativeLayout_edit_informationbar(getActivity(), _body, 6, new String[] { "style6", "dqnq", "kikkytkt", "2" }, true);

		_body.addView(_showname);
		_body.addView(_showname1);
		_body.addView(_showname2);
		_body.addView(_showname3);
		_body.addView(_showname4);
		_body.addView(_showname5);
		_body.addView(_showname6);
		return _view;
	}

	@Override
	protected String get_class()
	{
		return this.getClass().toString();
	}

}
