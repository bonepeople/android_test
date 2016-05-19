package com.shownest.android.fragment;

import com.shownest.android.R;
import com.shownest.android.activity.Activity_change_phone;
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

public class Fragment_change_phone extends DEBUG_Fragment
{
	private EditText _edittext_phone, _edittext_code;
	private Button _button_code, _button_next;
	private int _mobilecode_wait = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);
		View _view = inflater.inflate(R.layout.fragment_change_phone, container, false);
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
				if (_mobilecode_wait == 0)
					if (CommonUtil.isPhone(_string_phone))
					{
						Activity_change_phone.get_instance().show_wait();
						HttpUtil.send_mobilecode(Activity_change_phone._handler, _string_phone, Activity_change_phone.SEND_SUCCESSFUL, Activity_change_phone.SEND_FAILED);
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
					Activity_change_phone.get_instance().show_wait();
					Activity_change_phone.get_instance().set_phone(_string_phone);
					HttpUtil.modify_Phone(Activity_change_phone._handler, _string_phone, _code, Activity_change_phone.CHANGE_SUCCESSFUL, Activity_change_phone.CHANGE_FAILED);
				}

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
}
