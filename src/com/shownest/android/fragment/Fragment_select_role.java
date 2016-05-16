package com.shownest.android.fragment;

import com.shownest.android.R;
import com.shownest.android.activity.Activity_main;
import com.shownest.android.activity.Activity_select_role;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.utils.HttpUtil;
import com.shownest.android.widget.Linearlayout_role;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Fragment_select_role extends DEBUG_Fragment implements View.OnClickListener
{
	private Linearlayout_role _yezhu, _shejishi, _shigongdui, _jianli, _zhuangxiugongsi, _doubushi;
	private AlertDialog _dialog;
	private String _role_name = "";
	private int _role_code = 100;
	private Class<?> _intent_class = Activity_main.class;// 默认为主页，选择不同角色时切换不同的class

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);

		View _view = inflater.inflate(R.layout.fragment_select_role, container, false);

		_yezhu = (Linearlayout_role) _view.findViewById(R.id.linearlayout_role_yezhu);
		_shejishi = (Linearlayout_role) _view.findViewById(R.id.linearlayout_role_shejishi);
		_shigongdui = (Linearlayout_role) _view.findViewById(R.id.linearlayout_role_shigongdui);
		_jianli = (Linearlayout_role) _view.findViewById(R.id.linearlayout_role_jianli);
		_zhuangxiugongsi = (Linearlayout_role) _view.findViewById(R.id.linearlayout_role_zhuangxiugongsi);
		_doubushi = (Linearlayout_role) _view.findViewById(R.id.linearlayout_role_doubushi);

		_yezhu.setOnClickListener(this);
		_shejishi.setOnClickListener(this);
		_shigongdui.setOnClickListener(this);
		_jianli.setOnClickListener(this);
		_zhuangxiugongsi.setOnClickListener(this);
		_doubushi.setOnClickListener(this);
		return _view;
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.linearlayout_role_yezhu:
			_role_name = "业主";
			_role_code = 11;
			_intent_class = Activity_main.class;
			show_dialog();
			break;
		case R.id.linearlayout_role_shejishi:
			_role_name = "设计师";
			_role_code = 12;
			_intent_class = Activity_main.class;
			show_dialog();
			break;
		case R.id.linearlayout_role_shigongdui:
			_role_name = "施工队";
			_role_code = 13;
			_intent_class = Activity_main.class;
			show_dialog();
			break;
		case R.id.linearlayout_role_jianli:
			_role_name = "监理";
			_role_code = 14;
			_intent_class = Activity_main.class;
			show_dialog();
			break;
		case R.id.linearlayout_role_zhuangxiugongsi:
			_role_name = "装修公司";
			_role_code = 15;
			_intent_class = Activity_main.class;
			show_dialog();
			break;
		case R.id.linearlayout_role_doubushi:
			_role_name = "路人甲";
			_role_code = 100;
			_intent_class = Activity_main.class;
			show_dialog();
			break;
		case R.id.button_commit:
			_dialog.dismiss();
			HttpUtil.set_UserType(Activity_select_role._handler, _role_code, Activity_select_role.CHANGE_SUCCESSFUL, Activity_select_role.CHANGE_FAILED);
			break;
		case R.id.button_cancel:
			_dialog.dismiss();
			break;
		}
	}

	private void show_dialog()
	{
		View _view = View.inflate(getActivity(), R.layout.dialog_select_role, null);
		AlertDialog.Builder _builder = new Builder(getActivity());
		_dialog = _builder.create();
		_dialog.setView(_view, 0, 0, 0, 0);

		Button _button_commit = (Button) _view.findViewById(R.id.button_commit);
		Button _button_cancel = (Button) _view.findViewById(R.id.button_cancel);
		TextView _textview_name = (TextView) _view.findViewById(R.id.textview_name);
		_textview_name.setText(_role_name);

		_button_commit.setOnClickListener(this);
		_button_cancel.setOnClickListener(this);
		_dialog.show();
	}

	public Class<?> get_intent_class()
	{
		return _intent_class;
	}
}
