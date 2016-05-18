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
import android.widget.LinearLayout;

public class Fragment_setinfo_shejishi_step1 extends DEBUG_Fragment
{
	private LinearLayout _body;
	private Button _button_commit;
	private RelativeLayout_edit_informationbar _showname, _phone, _sex;
	private Linearlayout_edittext _edit;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);
		View _view = inflater.inflate(R.layout.fragment_setinfo, container, false);
		_body = (LinearLayout) _view.findViewById(R.id.linearlayout_content);
		_button_commit = (Button) _view.findViewById(R.id.button_commit);
		_button_commit.setText("保存");

		new RelativeLayout_edit_informationbar(getActivity(), _body, 5, new String[] { "身份类型", "设计师" }, false);
		_showname = new RelativeLayout_edit_informationbar(getActivity(), _body, 5, new String[] { "秀巢昵称", "sn1234" }, true);
		_phone = new RelativeLayout_edit_informationbar(getActivity(), _body, 5, new String[] { "联系手机", "13029411209" }, true);
		_sex = new RelativeLayout_edit_informationbar(getActivity(), _body, 6, new String[] { "设计师性别", "男", "女", "1" }, false);
		_edit = new Linearlayout_edittext(getActivity(), _body, new String[] { "自我介绍", "简单的说说你的竞争优势。", "" });

		return _view;
	}
}
