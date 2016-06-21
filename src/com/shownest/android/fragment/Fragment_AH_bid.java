package com.shownest.android.fragment;

import java.util.ArrayList;

import com.shownest.android.R;
import com.shownest.android.activity.Activity_AH_bid;
import com.shownest.android.activity.Activity_bid_detail;
import com.shownest.android.adapter.Adapter_bid_list;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.model.BidInfo_common;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;

public class Fragment_AH_bid extends DEBUG_Fragment
{
	private LinearLayout _body, _buttons;
	private Adapter_bid_list _adapter;
	private ListView _list;

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
		ArrayList<BidInfo_common> _data = Activity_AH_bid.get_data();
		if (_data != null)
		{
			_adapter = new Adapter_bid_list(getActivity(), _data);
			_list = new ListView(getActivity());
			_list.setAdapter(_adapter);
			_list.setDivider(new ColorDrawable(getResources().getColor(R.color.background_main)));
			_list.setDividerHeight(30);
			// LinearLayout.LayoutParams _param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
			// LinearLayout.LayoutParams.MATCH_PARENT);
			// _param.weight = 15;
			// _list.setLayoutParams(_param);
			_list.setOnItemClickListener(new OnItemClickListener()
			{
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id)
				{
					String _bidID = _adapter.getItem(position).get_id();
					Intent _detail = new Intent(getActivity(), Activity_bid_detail.class);
					_detail.putExtra("bidID", _bidID);
					_detail.putExtra("have_button", true);
					startActivity(_detail);
				}
			});
			_body.addView(_list);
		}
	}

}
