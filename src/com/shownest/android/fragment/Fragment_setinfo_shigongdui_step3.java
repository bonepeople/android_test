package com.shownest.android.fragment;

import com.shownest.android.R;
import com.shownest.android.activity.Activity_change_phone;
import com.shownest.android.activity.Activity_location;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.widget.LinearLayout_idcard;
import com.shownest.android.widget.RelativeLayout_edit_informationbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Fragment_setinfo_shigongdui_step3 extends DEBUG_Fragment implements OnClickListener
{
	private LinearLayout _body;
	private Button _button_commit;
	private RelativeLayout_edit_informationbar _type, _name, _id_number;
	private LinearLayout_idcard _idcard;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);
		View _view = inflater.inflate(R.layout.fragment_setinfo, container, false);
		_body = (LinearLayout) _view.findViewById(R.id.linearlayout_content);
		_button_commit = (Button) _view.findViewById(R.id.button_commit);
		_button_commit.setText("申请认证");
		_button_commit.setOnClickListener(this);

		_type = new RelativeLayout_edit_informationbar(getActivity(), _body, 6, new String[] { "认证类型", "独立工队", "装修公司工队", "1" }, false);
		_name = new RelativeLayout_edit_informationbar(getActivity(), _body, 5, new String[] { "工长姓名", "222" }, true);
		_id_number = new RelativeLayout_edit_informationbar(getActivity(), _body, 5, new String[] { "身份证号", "" }, true);
		_idcard = new LinearLayout_idcard(getActivity(), "工长身份证");
		_body.addView(_idcard);

		_type.setOnSelectListener(_idcard);
		return _view;
	}
	
	@Override
	public void onClick(View v)
	{
		int _id = v.getId();
		if (_id == R.id.button_commit)
		{
			Toast.makeText(getActivity(), "commit", Toast.LENGTH_SHORT).show();
		}
	}
}
