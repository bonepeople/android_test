package com.shownest.android.fragment;

import com.shownest.android.R;
import com.shownest.android.activity.Activity_card_add;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.utils.HttpUtil;
import com.shownest.android.utils.NumberUtil;

import android.content.ContentValues;
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

public class Fragment_card_add_step1 extends DEBUG_Fragment implements OnClickListener
{
	private LinearLayout _body;
	private Button _button_commit;
	private EditText _number;

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
		int _padding = NumberUtil.get_px(getActivity(), 10);
		TextView _name = new TextView(getActivity());
		_name.setText("银行卡:");
		_name.setTextSize(18);
		_name.setPadding(_padding, _padding, _padding, _padding);
		_body.addView(_name);
		_number = new EditText(getActivity());
		_number.setHint("请输入卡号");
		_number.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
		_body.setFocusableInTouchMode(true);
		_body.addView(_number);
	}

	@Override
	public void onClick(View v)
	{
		int _id = v.getId();
		if (_id == R.id.button_commit)
		{
			if (_number.getText().toString().isEmpty())
			{
				Toast.makeText(getActivity(), "请输入银行卡号", Toast.LENGTH_SHORT).show();
			}
			else
			{
				_number.clearFocus();
				ContentValues _value = new ContentValues();
				_value.put("cardid", _number.getText().toString());
				Activity_card_add.set_bank_number(_number.getText().toString());
				Activity_card_add.get_instance().show_wait();
				HttpUtil.get_card_type(Activity_card_add._handler, _value, Activity_card_add.GET_SUCCESSFUL, Activity_card_add.GET_FAILED);
			}
		}
	}
}
