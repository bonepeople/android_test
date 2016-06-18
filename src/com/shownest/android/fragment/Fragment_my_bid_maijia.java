package com.shownest.android.fragment;

import java.util.ArrayList;

import com.shownest.android.R;
import com.shownest.android.activity.Activity_my_bid_maijia;
import com.shownest.android.adapter.Adapter_bid_state_maijia;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.model.BidInfo_common;
import com.shownest.android.model.OnChangeListener;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

public class Fragment_my_bid_maijia extends DEBUG_Fragment implements OnChangeListener
{
	private String _tag;
	private LinearLayout _body, _buttons;
	private ArrayList<BidInfo_common> _data = new ArrayList<BidInfo_common>();
	private Adapter_bid_state_maijia _adapter;
	private ListView _list;

	public Fragment_my_bid_maijia(String _tag, ArrayList<BidInfo_common> _data)
	{
		this._tag = _tag;
		this._data = _data;
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);
		View _view = inflater.inflate(R.layout.fragment_no_scroll, container, false);
		_body = (LinearLayout) _view.findViewById(R.id.linearlayout_content);
		_buttons = (LinearLayout) _view.findViewById(R.id.linearlayout_buttons);
		_buttons.setVisibility(LinearLayout.GONE);

		return _view;
	}

	@Override
	public void setContent()
	{
		if (_data != null)
		{
			_adapter = new Adapter_bid_state_maijia(getActivity(), _data);
			_list = new ListView(getActivity());
			_adapter.setOnChangetListener(_tag, this);
			_list.setAdapter(_adapter);
			_list.setDivider(new ColorDrawable(getResources().getColor(R.color.background_main)));
			_list.setDividerHeight(30);
			_body.addView(_list);
		}
	}

	@Override
	public void onChange(String _tag, String[] _args)
	{
		if (_args[0].equals("commit"))
		{
			// 查看_args[1]位置的方案
			System.out.println("commit = " + _args[1]);
		}
		else
		{
			// 点击_args[1]位置的按钮
			System.out.println("hint = " + _args[1]);
		}
	}
}
