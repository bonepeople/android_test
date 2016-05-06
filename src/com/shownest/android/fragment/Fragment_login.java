package com.shownest.android.fragment;

import com.shownest.android.R;
import com.shownest.android.thread.Thread_login;
import com.shownest.android.widget.RelativeLayout_edit_informationbar;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment_login extends Fragment
{
	private static boolean DEBUG = true;
	private LinearLayout _linearlayout_body;
	private EditText _edittext_username, _edittext_password;
	private TextView _textview_forget;
	private Button _button_login;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		if (DEBUG)
			System.out.println("Fragment_login onCreateView");
		View _view = inflater.inflate(R.layout.fragment_login, container, false);
		_button_login = (Button) _view.findViewById(R.id.button_login);
		_edittext_username = (EditText) _view.findViewById(R.id.edittext_username);
		_edittext_password = (EditText) _view.findViewById(R.id.edittext_password);
		_textview_forget = (TextView) _view.findViewById(R.id.textview_forget_pwd);

		_button_login.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				String _string_username = _edittext_username.getText().toString().trim();
				String _string_password = _edittext_password.getText().toString();
				new Thread_login(_string_username, _string_password).start();
			}
		});

		_textview_forget.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "forget password", Toast.LENGTH_SHORT).show();
			}
		});
		return _view;
	}

}
