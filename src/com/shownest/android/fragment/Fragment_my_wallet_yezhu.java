package com.shownest.android.fragment;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.model.UserInfo;
import com.shownest.android.utils.UserManager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment_my_wallet_yezhu extends DEBUG_Fragment implements View.OnClickListener
{
	private TextView _textview_money;
	private LinearLayout _item_money, _item_card;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);

		View _view = inflater.inflate(R.layout.fragment_my_wallet_yezhu, container, false);
		_textview_money = (TextView) _view.findViewById(R.id.textview_money);
		_item_money = (LinearLayout) _view.findViewById(R.id.item_money);
		_item_card = (LinearLayout) _view.findViewById(R.id.item_card);

		_item_money.setOnClickListener(this);
		_item_card.setOnClickListener(this);

		return _view;
	}

	@Override
	public void setContent()
	{
		UserInfo _info = UserManager.get_user_info();
		_textview_money.setText(_info.get_money() + "元");

		switch (_info.get_userType())
		{
		case 11:
			break;
		case 12:
		case 13:
		case 14:
		case 15:
			break;
		case 100:
			break;
		}
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.item_money:
			Toast.makeText(getActivity(), "交易明细", Toast.LENGTH_SHORT).show();
			break;

		case R.id.item_card:
			Toast.makeText(getActivity(), "银行卡", Toast.LENGTH_SHORT).show();
			break;
		}
	}
}
