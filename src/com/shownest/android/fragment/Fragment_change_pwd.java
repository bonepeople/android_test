package com.shownest.android.fragment;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.utils.CommonUtil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Fragment_change_pwd extends DEBUG_Fragment
{
	private RelativeLayout _relativelayout_wait = null;
	private EditText _edittext_pwd_old, _edittext_pwd, _edittext_pwd_confirm;
	private Button _button_change;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);
		View _view = inflater.inflate(R.layout.fragment_change_pwd, container, false);
		_relativelayout_wait = (RelativeLayout) _view.findViewById(R.id.relativelayout_wait);
		_edittext_pwd_old = (EditText) _view.findViewById(R.id.edittext_password_old);
		_edittext_pwd = (EditText) _view.findViewById(R.id.edittext_password);
		_edittext_pwd_confirm = (EditText) _view.findViewById(R.id.edittext_password_confirm);
		_button_change = (Button) _view.findViewById(R.id.button_change);

		_button_change.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				String _pwd_old = _edittext_pwd_old.getText().toString();
				String _pwd = _edittext_pwd.getText().toString();
				String _pwd_confirm = _edittext_pwd_confirm.getText().toString();

				if (_pwd_old.isEmpty() || _pwd.isEmpty() || _pwd_confirm.isEmpty())
				{
					Toast.makeText(getActivity(), "请填写密码", Toast.LENGTH_SHORT).show();
				}
				else if (_pwd.length() < 6 || _pwd.length() > 20)
				{
					Toast.makeText(getActivity(), "请确保密码为6-20位", Toast.LENGTH_SHORT).show();
				}
				else if (!CommonUtil.isPassword(_pwd))
				{
					Toast.makeText(getActivity(), "密码只能是英文字母和数字的组合", Toast.LENGTH_SHORT).show();
				}
				else if (!_pwd.equals(_pwd_confirm))
				{
					Toast.makeText(getActivity(), "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
				}
				else
				{
					show_wait();
					// 调用修改密码的接口
				}

			}
		});

		return _view;
	}

	@Override
	protected String get_class()
	{
		return this.getClass().toString();
	}

	public void show_wait()
	{
		if (_relativelayout_wait != null && _relativelayout_wait.getVisibility() != RelativeLayout.VISIBLE)
			_relativelayout_wait.setVisibility(RelativeLayout.VISIBLE);
	}

	public void close_wait()
	{
		if (_relativelayout_wait != null && _relativelayout_wait.getVisibility() == RelativeLayout.VISIBLE)
			_relativelayout_wait.setVisibility(RelativeLayout.INVISIBLE);
	}
}
