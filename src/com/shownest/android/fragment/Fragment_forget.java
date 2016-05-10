package com.shownest.android.fragment;

import com.shownest.android.R;
import com.shownest.android.activity.Activity_forget;
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
	private int _mobilecode_wait = 0;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		if (DEBUG)
			System.out.println("Fragment_forget onCreateView");
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
				if (_mobilecode_wait == 0)
					if (CommonUtil.isPhone(_string_phone))
					{
						Activity_forget.set_forget_code(_string_phone);
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
					Activity_forget.set_forget_phone(_string_phone);
					Activity_forget.set_forget_code(_code);
					// 开下一个fragment
					HttpUtil.check_mobcode(Activity_forget._handler, _string_phone, _code, Activity_forget.NEXT_SUCCESSFUL, Activity_forget.NEXT_FAILED);
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
