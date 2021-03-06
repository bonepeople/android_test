package com.shownest.android.fragment;

import java.util.ArrayList;

import com.shownest.android.R;
import com.shownest.android.activity.Activity_my_bid_yezhu;
import com.shownest.android.activity.Activity_quota_list;
import com.shownest.android.adapter.Adapter_bid_state_yezhu;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.model.HouseBidState;
import com.shownest.android.model.OnChangeListener;
import com.shownest.android.widget.Widget_listview;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Fragment_my_bid_yezhu extends DEBUG_Fragment implements OnChangeListener
{
	private LinearLayout _body, _buttons;
	private ArrayList<Adapter_bid_state_yezhu> _adapters = new ArrayList<Adapter_bid_state_yezhu>();
	private ArrayList<Widget_listview> _lists = new ArrayList<>();

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);
		View _view = inflater.inflate(R.layout.fragment_basic, container, false);
		_body = (LinearLayout) _view.findViewById(R.id.linearlayout_content);
		_buttons = (LinearLayout) _view.findViewById(R.id.linearlayout_buttons);
		_buttons.setVisibility(LinearLayout.GONE);

		_body.setBackgroundColor(getResources().getColor(R.color.background_main));
		return _view;
	}

	@Override
	public void setContent()
	{
		ArrayList<HouseBidState> _data = Activity_my_bid_yezhu.get_data();
		if (_data != null)
		{
			if (_data.size() != 0)
			{
				for (int _temp_i = 0; _temp_i < _data.size(); _temp_i++)
				{
					Adapter_bid_state_yezhu _temp_adapter = new Adapter_bid_state_yezhu(getActivity(), _data.get(_temp_i).get_bids());
					Widget_listview _temp_list = new Widget_listview(getActivity(), _body, new String[] { _data.get(_temp_i).get_houseName(), "" }, _temp_adapter);
					_temp_adapter.setOnChangetListener(String.valueOf(_temp_i), this);
					_temp_list.set_backgroundColor(getResources().getColor(R.color.white));
					_temp_list.set_collapse(false);
					_temp_list.set_enabled(false);
					_adapters.add(_temp_adapter);
					_lists.add(_temp_list);
				}

			}
			else
			{
				Toast.makeText(getActivity(), "目前无招标信息", Toast.LENGTH_SHORT).show();
				Activity_my_bid_yezhu.get_instance().finish();
			}
		}
	}

	@Override
	public void onChange(String _tag, String[] _args)
	{
		String _bidID = Activity_my_bid_yezhu.get_data().get(Integer.parseInt(_tag)).get_bids().get(Integer.parseInt(_args[0])).get_id();
		int _type = Activity_my_bid_yezhu.get_data().get(Integer.parseInt(_tag)).get_bids().get(Integer.parseInt(_args[0])).get_bookType();
		Intent _quota_list = new Intent(getActivity(), Activity_quota_list.class);
		_quota_list.putExtra("id", _bidID);
		_quota_list.putExtra("type", _type);
		_quota_list.putExtra("have_detail", true);
		startActivity(_quota_list);
	}
}
