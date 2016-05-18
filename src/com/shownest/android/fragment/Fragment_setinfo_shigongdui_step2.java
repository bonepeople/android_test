package com.shownest.android.fragment;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.widget.Linearlayout_edittext;
import com.shownest.android.widget.RelativeLayout_edit_informationbar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class Fragment_setinfo_shigongdui_step2 extends DEBUG_Fragment
{
	private LinearLayout _body;
	private Button _button_commit;
	private RelativeLayout_edit_informationbar _date, _number, _location, _address, _service;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);
		View _view = inflater.inflate(R.layout.fragment_setinfo, container, false);
		_body = (LinearLayout) _view.findViewById(R.id.linearlayout_content);
		_button_commit = (Button) _view.findViewById(R.id.button_commit);
		_button_commit.setText("保存");

		_date = new RelativeLayout_edit_informationbar(getActivity(), _body, 2, new String[] { "从业年份", "" }, true);
		_number = new RelativeLayout_edit_informationbar(getActivity(), _body, 3, new String[] { "工队人数", "", "人" }, true);
		_location = new RelativeLayout_edit_informationbar(getActivity(), _body, 2, new String[] { "办公地址", "" }, true);
		_address = new RelativeLayout_edit_informationbar(getActivity(), _body, 5, new String[] { "完善地址", "" }, true);
		_service = new RelativeLayout_edit_informationbar(getActivity(), _body, 2, new String[] { "服务区域", "" }, true);


		return _view;
	}
}
