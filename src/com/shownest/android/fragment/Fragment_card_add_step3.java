package com.shownest.android.fragment;

import com.shownest.android.R;
import com.shownest.android.activity.Activity_card_add;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.utils.NumberUtil;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Fragment_card_add_step3 extends DEBUG_Fragment implements OnClickListener
{
	private LinearLayout _body;
	private Button _button_commit;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);
		View _view = inflater.inflate(R.layout.fragment_basic, container, false);
		_body = (LinearLayout) _view.findViewById(R.id.linearlayout_content);
		_button_commit = (Button) _view.findViewById(R.id.button_commit);
		_button_commit.setText("查看我的银行卡");
		_button_commit.setOnClickListener(this);

		return _view;
	}

	@Override
	public void setContent()
	{
		int padding;
		ImageView _img = new ImageView(getActivity());
		_img.setImageDrawable(getResources().getDrawable(R.drawable.icon_ok));
		padding = NumberUtil.get_px(getActivity(), 25);
		_img.setPadding(padding, padding, padding, padding);
		_body.addView(_img);

		TextView _text = new TextView(getActivity());
		_text.setText("成功添加银行卡");
		_text.setTextSize(20);
		padding = NumberUtil.get_px(getActivity(), 5);
		_text.setPadding(padding, padding, padding, padding);
		_text.setGravity(Gravity.CENTER);
		_body.addView(_text);

		TextView _bank = new TextView(getActivity());
		String _number = Activity_card_add.get_bank_number();
		_bank.setText(Activity_card_add.get_bank_name() + " 尾号" + _number.substring(_number.length() - 4));
		_bank.setTextColor(getResources().getColor(R.color.text_gray));
		_bank.setGravity(Gravity.CENTER);
		_body.addView(_bank);
	}

	@Override
	public void onClick(View v)
	{
		int _id = v.getId();
		if (_id == R.id.button_commit)
		{
			getActivity().finish();
		}
	}
}
