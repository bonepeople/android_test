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

public class Fragment_forget_set extends Fragment
{
	private static boolean DEBUG = true;
	public RelativeLayout _relativelayout_wait;
	private EditText _edittext_password, _edittext_password_confirm;
	private Button _button_commit;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		if (DEBUG)
			System.out.println("Fragment_forget_set onCreateView");
		View _view = inflater.inflate(R.layout.fragment_forget_set, container, false);
		_relativelayout_wait = (RelativeLayout) _view.findViewById(R.id.relativelayout_wait);
		_button_commit = (Button) _view.findViewById(R.id.button_commit);
		_edittext_password = (EditText) _view.findViewById(R.id.edittext_password);
		_edittext_password_confirm = (EditText) _view.findViewById(R.id.edittext_password_confirm);

		_button_commit.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				_relativelayout_wait.setVisibility(RelativeLayout.VISIBLE);
				String _password = _edittext_password.getText().toString();
				String _password_confirm = _edittext_password_confirm.getText().toString();

				if (_password.length() < 6 || _password.length() > 20)
				{
					Toast.makeText(getActivity(), "请确保密码为6-20位", Toast.LENGTH_SHORT).show();
				}
				else if (_password.equals(_password))
				{
					Toast.makeText(getActivity(), "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
				}
				else
				{
					Toast.makeText(getActivity(), "没有修改密码的接口", Toast.LENGTH_SHORT).show();
					// HttpUtil.user_login(Activity_login._handler, _string_username, _string_password, Activity_login.LOGIN_SUCCESSFUL, Activity_login.LOGIN_FAILED);
				}
			}
		});

		return _view;
	}

}
