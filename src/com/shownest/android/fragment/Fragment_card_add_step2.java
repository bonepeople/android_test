package com.shownest.android.fragment;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Fragment;

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

		_type.setOnClickListener(this);
		_button_code.setOnClickListener(this);
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
			Toast.makeText(getActivity(), "code", Toast.LENGTH_SHORT).show();
		}
		else if (_id == R.id.textview_type)
		{
			Toast.makeText(getActivity(), "type", Toast.LENGTH_SHORT).show();
		}
	}
}
