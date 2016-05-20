package com.shownest.android.fragment;

import com.shownest.android.R;
import com.shownest.android.activity.Activity_setinfo_jianli;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.utils.CommonUtil;
import com.shownest.android.utils.HttpUtil;
import com.shownest.android.widget.LinearLayout_idcard;
import com.shownest.android.widget.RelativeLayout_edit_informationbar;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Fragment_setinfo_jianli_step3 extends DEBUG_Fragment implements OnClickListener
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

		_type = new RelativeLayout_edit_informationbar(getActivity(), _body, 6, new String[] { "认证类型", "独立监理", "装修公司监理", "1" }, false);
		_name = new RelativeLayout_edit_informationbar(getActivity(), _body, 5, new String[] { "真实姓名", "222" }, true);
		_id_number = new RelativeLayout_edit_informationbar(getActivity(), _body, 5, new String[] { "身份证号", "" }, true);

		_idcard = new LinearLayout_idcard(getActivity(), "监理身份证");
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
			String _str_id = _id_number.getData();
			if (!CommonUtil.isIDNumber(_str_id))
			{
				Toast.makeText(getActivity(), "身份证号码格式不正确", Toast.LENGTH_SHORT).show();
			}
			else
			{
				ContentValues _value = new ContentValues();
				_value.put("authenticationType", _type.getData());
				_value.put("authenticationName", _name.getData());
				_value.put("authenticationCode", _str_id);
				_value.put("authenticationCardPicF", "正面路径");// 需要变更
				_value.put("authenticationCardPicB", "反面路径");// 需要变更

				Activity_setinfo_jianli.get_instance().show_wait();
				HttpUtil.set_PersonalProve(Activity_setinfo_jianli._handler, _value, Activity_setinfo_jianli.CHANGE_SUCCESSFUL, Activity_setinfo_jianli.CHANGE_FAILED);
			}
		}
	}
}
