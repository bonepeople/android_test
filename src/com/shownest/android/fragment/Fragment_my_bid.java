package com.shownest.android.fragment;

import java.util.ArrayList;

import com.shownest.android.R;
import com.shownest.android.activity.Activity_my_bid;
import com.shownest.android.adapter.Adapter_bid_state;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.model.HouseBidState;
import com.shownest.android.widget.Widget_listview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Fragment_my_bid extends DEBUG_Fragment
{
	private LinearLayout _body, _buttons;
	private ArrayList<Adapter_bid_state> _adapters = new ArrayList<Adapter_bid_state>();
	private ArrayList<Widget_listview> _lists = new ArrayList<>();

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);
		View _view = inflater.inflate(R.layout.fragment_basic, container, false);
		_body = (LinearLayout) _view.findViewById(R.id.linearlayout_content);
		_buttons = (LinearLayout) _view.findViewById(R.id.linearlayout_buttons);
		_buttons.setVisibility(LinearLayout.GONE);

		return _view;
	}

	@Override
	public void setContent()
	{
		ArrayList<HouseBidState> _data = Activity_my_bid.get_data();
		if (_data != null)
		{
			if (_data.size() != 0)
			{
				for (int _temp_i = 0; _temp_i < _data.size(); _temp_i++)
				{
					Adapter_bid_state _temp_adapter = new Adapter_bid_state(getActivity(), _data.get(_temp_i).get_bids());
					Widget_listview _temp_list = new Widget_listview(getActivity(), _body, new String[] { _data.get(_temp_i).get_houseName(), "" }, _temp_adapter);
					_temp_list.set_backgroundColor(getResources().getColor(R.color.white));
					_temp_list.set_collapse(false);
					_adapters.add(_temp_adapter);
					_lists.add(_temp_list);
				}

			}
			else
			{
				Toast.makeText(getActivity(), "目前无招标信息", Toast.LENGTH_SHORT).show();
				Activity_my_bid.get_instance().finish();
			}
		}
	}
}
