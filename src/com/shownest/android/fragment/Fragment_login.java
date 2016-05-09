package com.shownest.android.fragment;

import com.shownest.android.R;
import com.shownest.android.activity.Activity_login;
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
import android.widget.TextView;
import android.widget.Toast;

public class Fragment_login extends Fragment
{
	private static boolean DEBUG = true;
	public RelativeLayout _relativelayout_wait;
	private EditText _edittext_username, _edittext_password;
	private TextView _textview_forget;
	private Button _button_login;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		if (DEBUG)
			System.out.println("Fragment_login onCreateView");
		View _view = inflater.inflate(R.layout.fragment_login, container, false);
		_relativelayout_wait = (RelativeLayout) _view.findViewById(R.id.relativelayout_wait);
		_button_login = (Button) _view.findViewById(R.id.button_login);
		_edittext_username = (EditText) _view.findViewById(R.id.edittext_username);
		_edittext_password = (EditText) _view.findViewById(R.id.edittext_password);
		_textview_forget = (TextView) _view.findViewById(R.id.textview_forget_pwd);

		_button_login.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				_relativelayout_wait.setVisibility(RelativeLayout.VISIBLE);
				String _string_username = _edittext_username.getText().toString().trim();
				String _string_password = _edittext_password.getText().toString();
				if (_string_username.isEmpty() || _string_password.isEmpty())
				{
					Toast.makeText(getActivity(), "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
				}
				else

					HttpUtil.user_login(Activity_login._handler, _string_username, _string_password, Activity_login.LOGIN_SUCCESSFUL, Activity_login.LOGIN_FAILED);
			}
		});

		_textview_forget.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				Toast.makeText(getActivity(), "forget password", Toast.LENGTH_SHORT).show();
			}
		});
		return _view;
	}

}
