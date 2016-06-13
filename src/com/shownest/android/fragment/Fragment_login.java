package com.shownest.android.fragment;

import com.shownest.android.R;
import com.shownest.android.activity.Activity_forget;
import com.shownest.android.activity.Activity_login;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.utils.CommonUtil;
import com.shownest.android.utils.HttpUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment_login extends DEBUG_Fragment
{
	private EditText _edittext_username, _edittext_password;
	private TextView _textview_forget;
	private Button _button_login, _yezhu, _shigongdui, _shejishi, _jianli;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);
		View _view = inflater.inflate(R.layout.fragment_login, container, false);
		_button_login = (Button) _view.findViewById(R.id.button_login);
		_edittext_username = (EditText) _view.findViewById(R.id.edittext_username);
		_edittext_password = (EditText) _view.findViewById(R.id.edittext_password);
		_textview_forget = (TextView) _view.findViewById(R.id.textview_forget_pwd);
		// =================测试使用
		_yezhu = (Button) _view.findViewById(R.id.button_yezhu);
		_shigongdui = (Button) _view.findViewById(R.id.button_shigongdui);
		_shejishi = (Button) _view.findViewById(R.id.button_shejishi);
		_jianli = (Button) _view.findViewById(R.id.button_jianli);
		_yezhu.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				_edittext_username.setText("15210196421");
				_edittext_password.setText("123456");
			}
		});
		_shigongdui.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				_edittext_username.setText("13788816776");
				_edittext_password.setText("111111");
			}
		});
		_shejishi.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				_edittext_username.setText("13555541368");
				_edittext_password.setText("111111");
			}
		});
		_jianli.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				_edittext_username.setText("15072058793");
				_edittext_password.setText("111111");
			}
		});
		// =================测试使用
		_button_login.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String _string_username = _edittext_username.getText().toString().trim();
				String _string_password = _edittext_password.getText().toString();
				if (_string_username.isEmpty() || _string_password.isEmpty())
				{
					Toast.makeText(getActivity(), "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
				}
				else if (!CommonUtil.isPhone(_string_username))
				{
					Toast.makeText(getActivity(), "请输入正确的电话号码", Toast.LENGTH_SHORT).show();
				}
				else
				{
					Activity_login.get_instance().show_wait();
					HttpUtil.user_login(Activity_login._handler, _string_username, _string_password, Activity_login.LOGIN_SUCCESSFUL, Activity_login.LOGIN_FAILED);
				}
			}
		});

		_textview_forget.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent _forget = new Intent(getActivity(), Activity_forget.class);
				getActivity().startActivity(_forget);
			}
		});
		return _view;
	}
}
