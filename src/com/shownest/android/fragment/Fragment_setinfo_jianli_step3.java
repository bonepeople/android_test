package com.shownest.android.fragment;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.widget.LinearLayout_idcard;
import com.shownest.android.widget.RelativeLayout_edit_informationbar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class Fragment_setinfo_jianli_step3 extends DEBUG_Fragment
{
	private LinearLayout _body;
	private Button _button_commit;
	private RelativeLayout_edit_informationbar _type, _name;
	private LinearLayout_idcard _idcard;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);
		View _view = inflater.inflate(R.layout.fragment_setinfo, container, false);
		_body = (LinearLayout) _view.findViewById(R.id.linearlayout_content);
		_button_commit = (Button) _view.findViewById(R.id.button_commit);
		_button_commit.setText("保存");

		_type = new RelativeLayout_edit_informationbar(getActivity(), _body, 6, new String[] { "认证类型", "独立监理", "装修公司监理", "1" }, false);
		_name = new RelativeLayout_edit_informationbar(getActivity(), _body, 5, new String[] { "真实姓名", "222" }, true);
		_idcard = new LinearLayout_idcard(getActivity(), "监理身份证");
		_body.addView(_idcard);

		_type.setOnSelectListener(_idcard);
		return _view;
	}
}
