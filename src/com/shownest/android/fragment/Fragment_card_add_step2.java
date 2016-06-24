package com.shownest.android.fragment;

import com.shownest.android.R;
import com.shownest.android.activity.Activity_card_add;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.utils.CommonUtil;
import com.shownest.android.utils.HttpUtil;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
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

public class Fragment_card_add_step2 extends DEBUG_Fragment implements OnClickListener
{
	private LinearLayout _body;
	private Button _button_commit, _button_code;
	private EditText _name, _id, _phone, _code;
	private TextView _bank, _type;
	private String[] _types = new String[] { "身份证", "户口本", "军官证" };
	private int _mobilecode_wait = 0;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);
		View _view = inflater.inflate(R.layout.fragment_basic, container, false);
		_body = (LinearLayout) _view.findViewById(R.id.linearlayout_content);
		_button_commit = (Button) _view.findViewById(R.id.button_commit);
		_button_commit.setText("下一步");
		_button_commit.setOnClickListener(this);

		return _view;
	}

	@Override
	public void setContent()
	{
		View _view = View.inflate(getActivity(), R.layout.fragment_card_add_step2, _body);
		_bank = (TextView) _view.findViewById(R.id.textview_name);
		_type = (TextView) _view.findViewById(R.id.textview_type);
		_name = (EditText) _view.findViewById(R.id.edittext_username);
		_id = (EditText) _view.findViewById(R.id.edittext_number);
		_phone = (EditText) _view.findViewById(R.id.edittext_phone);
		_code = (EditText) _view.findViewById(R.id.edittext_code);
		_button_code = (Button) _view.findViewById(R.id.button_code);

		_bank.setText(Activity_card_add.get_bank_name() + " " + Activity_card_add.get_bank_type());
		_type.setOnClickListener(this);
		_button_code.setOnClickListener(this);
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

	@Override
	public void onClick(View v)
	{
		int _id = v.getId();
		if (_id == R.id.button_commit)
		{
			Toast.makeText(getActivity(), "commit", Toast.LENGTH_SHORT).show();
		}
		else if (_id == R.id.button_code)
		{
			String _string_phone = _phone.getText().toString();
			if (_mobilecode_wait == 0)
				if (CommonUtil.isPhone(_string_phone))
				{
					Activity_card_add.set_phone(_string_phone);
					Activity_card_add.get_instance().show_wait();
					HttpUtil.send_mobilecode(Activity_card_add._handler, _string_phone, Activity_card_add.SEND_SUCCESSFUL, Activity_card_add.SEND_FAILED);
				}
				else
					Toast.makeText(getActivity(), "请输入正确的电话号码", Toast.LENGTH_SHORT).show();
		}
		else if (_id == R.id.textview_type)
		{
			AlertDialog.Builder _builder = new Builder(getActivity());
			_builder.setTitle("证件类型");
			_builder.setItems(_types, new DialogInterface.OnClickListener()
			{
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					_type.setText(_types[which]);
				}
			});
			_builder.show();
		}
	}
}
