package com.shownest.android.fragment;

import com.shownest.android.R;
import com.shownest.android.activity.Activity_forget;
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
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Fragment_forget extends Fragment
{
	private static boolean DEBUG = true;
	public RelativeLayout _relativelayout_wait;
	private EditText _edittext_phone, _edittext_code;
	private Button _button_code, _button_next;
	private String _regist_phone = "";
	private boolean _code_wait = false;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		if (DEBUG)
			System.out.println("Fragment_regist onCreateView");
		View _view = inflater.inflate(R.layout.fragment_forget, container, false);
		_relativelayout_wait = (RelativeLayout) _view.findViewById(R.id.relativelayout_wait);
		_button_code = (Button) _view.findViewById(R.id.button_code);
		_button_next = (Button) _view.findViewById(R.id.button_next);
		_edittext_phone = (EditText) _view.findViewById(R.id.edittext_phone);
		_edittext_code = (EditText) _view.findViewById(R.id.edittext_code);

		_button_code.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				String _string_phone = _edittext_phone.getText().toString();
				if (!_code_wait)
					if (CommonUtil.isPhone(_string_phone))
					{
						_regist_phone = _string_phone;
						HttpUtil.check_loginname(Activity_forget._handler, _string_phone, Activity_forget.CHECK_SUCCESSFUL, Activity_forget.CHECK_FAILED);
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

				if (!CommonUtil.isPhone(_string_phone))
				{
					Toast.makeText(getActivity(), "请输入正确的电话号码", Toast.LENGTH_SHORT).show();
				}
				else if (_code.isEmpty())
				{
					Toast.makeText(getActivity(), "请输入短信验证码", Toast.LENGTH_SHORT).show();
				}
				else
				{
					_relativelayout_wait.setVisibility(RelativeLayout.VISIBLE);
					HttpUtil.check_mobcode(Activity_forget._handler, _string_phone, _code, Activity_forget.FORGET_SUCCESSFUL, Activity_forget.FORGET_FAILED);
				}

			}
		});

		return _view;
	}

	public void mobilcode_change()
	{
		String _temp_str = _button_code.getText().toString();
		if (_temp_str.equals("获取验证码"))
		{
			_button_code.setText("60");
			_code_wait = true;
		}
		else
		{
			int _second = Integer.parseInt(_temp_str);
			if (_second == 0)
			{
				_button_code.setText("获取验证码");
				_code_wait = false;
			}
			else
				_button_code.setText(String.valueOf(_second - 1));
		}
	}

	public String get_regist_phone()
	{
		return _regist_phone;
	}
}