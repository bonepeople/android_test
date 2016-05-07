package com.shownest.android.fragment;

import com.shownest.android.R;
import com.shownest.android.thread.Thread_get_code;
import com.shownest.android.thread.Thread_login;
import com.shownest.android.utils.CommonUtil;
import com.shownest.android.widget.RelativeLayout_edit_informationbar;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment_regist extends Fragment
{
	private static boolean DEBUG = true;
	public RelativeLayout _relativelayout_wait;
	private EditText _edittext_phone, _edittext_code, _edittext_pwd, _edittext_pwd_confirm;
	private TextView _textview_agreement;
	private CheckBox _checkbox_agree;
	private Button _button_code, _button_next;

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
		_edittext_pwd_confirm = (EditText) _view.findViewById(R.id.edittext_password_confirm);
		_textview_agreement = (TextView) _view.findViewById(R.id.textview_agreement);
		_checkbox_agree = (CheckBox) _view.findViewById(R.id.checkbox_agree);

		_button_code.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				String _string_phone = _edittext_phone.getText().toString();
				if (CommonUtil.isPhone(_string_phone))

					new Thread_get_code(_string_phone).start();
				else
					Toast.makeText(getActivity(), "不是正常的电话号码", Toast.LENGTH_SHORT).show();
			}
		});

		_button_next.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				Toast.makeText(getActivity(), "regist  next", Toast.LENGTH_SHORT).show();
				// new Thread_login(_string_username, _string_password).start();
			}
		});

		_textview_agreement.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "view agreement", Toast.LENGTH_SHORT).show();
			}
		});
		return _view;
	}

}
