package com.shownest.android.fragment;

import com.shownest.android.R;
import com.shownest.android.activity.Activity_regist;
import com.shownest.android.utils.CommonUtil;
import com.shownest.android.utils.HttpUtil;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment_regist extends Fragment
{
	private static boolean DEBUG = true;
	public RelativeLayout _relativelayout_wait;
	private EditText _edittext_phone, _edittext_code, _edittext_pwd;
	private TextView _textview_agreement;
	private CheckBox _checkbox_agree;
	private Button _button_code, _button_next;
	private String _regist_phone = "";
	private int _mobilecode_wait = 0;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		if (DEBUG)
			System.out.println("Fragment_regist onCreateView");
		View _view = inflater.inflate(R.layout.fragment_regist, container, false);
		_relativelayout_wait = (RelativeLayout) _view.findViewById(R.id.relativelayout_wait);
		_button_code = (Button) _view.findViewById(R.id.button_code);
		_button_next = (Button) _view.findViewById(R.id.button_next);
		_edittext_phone = (EditText) _view.findViewById(R.id.edittext_phone);
		_edittext_code = (EditText) _view.findViewById(R.id.edittext_code);
		_edittext_pwd = (EditText) _view.findViewById(R.id.edittext_password);
		_textview_agreement = (TextView) _view.findViewById(R.id.textview_agreement);
		_checkbox_agree = (CheckBox) _view.findViewById(R.id.checkbox_agree);

		_button_code.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				String _string_phone = _edittext_phone.getText().toString();
				if (_mobilecode_wait == 0)
					if (CommonUtil.isPhone(_string_phone))
					{
						_regist_phone = _string_phone;
						_relativelayout_wait.setVisibility(RelativeLayout.VISIBLE);
						HttpUtil.check_loginname(Activity_regist._handler, _string_phone, Activity_regist.CHECK_SUCCESSFUL, Activity_regist.CHECK_FAILED);
					}
					else
						Toast.makeText(getActivity(), "请输入正确的电话号码", Toast.LENGTH_SHORT).show();
			}
		});

		_button_next.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				String _string_phone = _edittext_phone.getText().toString();
				String _code = _edittext_code.getText().toString().trim();
				String _password = _edittext_pwd.getText().toString();

				if (!CommonUtil.isPhone(_string_phone))
				{
					Toast.makeText(getActivity(), "请输入正确的电话号码", Toast.LENGTH_SHORT).show();
				}
				else if (_code.isEmpty())
				{
					Toast.makeText(getActivity(), "请输入短信验证码", Toast.LENGTH_SHORT).show();
				}
				else if (_password.length() < 6 || _password.length() > 20)
				{
					Toast.makeText(getActivity(), "请确保密码为6-20位", Toast.LENGTH_SHORT).show();
				}
				else if(!CommonUtil.isPassword(_password))
				{
					Toast.makeText(getActivity(), "密码只能是英文字母和数字的组合", Toast.LENGTH_SHORT).show();
				}
				else if (!_checkbox_agree.isChecked())
				{
					Toast.makeText(getActivity(), "必须同意秀巢协议", Toast.LENGTH_SHORT).show();
				}
				else
				{
					_relativelayout_wait.setVisibility(RelativeLayout.VISIBLE);
					HttpUtil.submit_reg(Activity_regist._handler, _string_phone, _code, _password, Activity_regist.REGIST_SUCCESSFUL, Activity_regist.REGIST_FAILED);
				}

			}
		});

		_textview_agreement.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "详细协议请前往官网查看", Toast.LENGTH_SHORT).show();
			}
		});
		return _view;
	}

	public void mobilcode_change()
	{
		switch (_mobilecode_wait)
		{
		case 0:
			_mobilecode_wait = 60;
			_button_code.setText("重新发送(" + _mobilecode_wait + ")");
			break;
		case 1:
			_mobilecode_wait--;
			_button_code.setText("获取验证码");
			break;
		default:
			_mobilecode_wait--;
			_button_code.setText("重新发送(" + _mobilecode_wait + ")");

		}
	}

	public String get_regist_phone()
	{
		return _regist_phone;
	}
}
