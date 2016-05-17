package com.shownest.android.fragment;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.widget.RelativeLayout_edit_informationbar;

import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Fragment_setinfo_shigongdui_step1 extends DEBUG_Fragment
{
	private LinearLayout _body;
	private Button _button_commit;
	private RelativeLayout_edit_informationbar _showname, _phone, _location, _sex;
	private EditText _jieshao;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);
		View _view = inflater.inflate(R.layout.fragment_setinfo, container, false);
		_body = (LinearLayout) _view.findViewById(R.id.linearlayout_content);
		_button_commit = (Button) _view.findViewById(R.id.button_commit);
		_button_commit.setText("保存");

		new RelativeLayout_edit_informationbar(getActivity(), _body, 5, new String[] { "身份类型", "施工队" }, false);
		_showname = new RelativeLayout_edit_informationbar(getActivity(), _body, 5, new String[] { "工队昵称", "sn1234" }, true);
		_phone = new RelativeLayout_edit_informationbar(getActivity(), _body, 5, new String[] { "联系手机", "13029411209" }, true);
		_location = new RelativeLayout_edit_informationbar(getActivity(), _body, 2, new String[] { "工长籍贯", "" }, true);
		_sex = new RelativeLayout_edit_informationbar(getActivity(), _body, 6, new String[] { "工长性别", "男", "女", "1" }, false);

		TextView _temp_text = new TextView(getActivity());
		_temp_text.setText("工队介绍");
		_temp_text.setPadding(5, 5, 5, 5);
		_body.addView(_temp_text);

		_jieshao = new EditText(getActivity());
		_jieshao.setLines(3);
		_jieshao.setMaxLines(5);
		_jieshao.setInputType(android.text.InputType.TYPE_TEXT_FLAG_MULTI_LINE);
		_jieshao.setHorizontallyScrolling(true);

		_body.addView(_jieshao);

		return _view;
	}
}
