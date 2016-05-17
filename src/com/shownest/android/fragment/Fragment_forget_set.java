package com.shownest.android.fragment;

import com.shownest.android.R;
import com.shownest.android.activity.Activity_forget;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.utils.CommonUtil;
import com.shownest.android.utils.HttpUtil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Fragment_forget_set extends DEBUG_Fragment
{
	private EditText _edittext_password, _edittext_password_confirm;
	private Button _button_commit;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);
		View _view = inflater.inflate(R.layout.fragment_forget_set, container, false);
		_button_commit = (Button) _view.findViewById(R.id.button_commit);
		_edittext_password = (EditText) _view.findViewById(R.id.edittext_password);
		_edittext_password_confirm = (EditText) _view.findViewById(R.id.edittext_password_confirm);

		_button_commit.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String _password = _edittext_password.getText().toString();
				String _password_confirm = _edittext_password_confirm.getText().toString();

				if (_password.length() < 6 || _password.length() > 20)
				{
					Toast.makeText(getActivity(), "请确保密码为6-20位", Toast.LENGTH_SHORT).show();
				}
				else if (!CommonUtil.isPassword(_password))
				{
					Toast.makeText(getActivity(), "密码只能是英文字母和数字的组合", Toast.LENGTH_SHORT).show();
				}
				else if (!_password.equals(_password_confirm))
				{
					Toast.makeText(getActivity(), "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
				}
				else
				{
					Activity_forget.get_instance().show_wait();
					HttpUtil.forget_pwd(Activity_forget._handler, Activity_forget.get_forget_phone(), Activity_forget.get_forget_code(), _password, _password_confirm,
							Activity_forget.FORGET_SUCCESSFUL, Activity_forget.FORGET_FAILED);
				}
			}
		});
		return _view;
	}
}
