package com.shownest.android.fragment;

import java.util.ArrayList;

import com.shownest.android.R;
import com.shownest.android.activity.Activity_my_order_yezhu;
import com.shownest.android.activity.Activity_offer_auto;
import com.shownest.android.activity.Activity_quotation_detail;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.model.HouseOrderState;
import com.shownest.android.model.OnChangeListener;
import com.shownest.android.model.OrderInfo;
import com.shownest.android.model.Package;
import com.shownest.android.widget.InformationBar;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment_my_order_yezhu extends DEBUG_Fragment implements OnChangeListener, View.OnClickListener
{
	private LinearLayout _body, _buttons;
	private SparseArray<Package> _informationbars = new SparseArray<Package>();

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
		ArrayList<HouseOrderState> _data = Activity_my_order_yezhu.get_data();
		if (_data != null)
		{
			if (_data.size() != 0)
			{
				for (int _temp_i = 0; _temp_i < _data.size(); _temp_i++)
				{
					TextView _name = new TextView(getActivity());
					_name.setText(_data.get(_temp_i).get_houseName());
					_body.addView(_name);

					ArrayList<OrderInfo> _infos = _data.get(_temp_i).get_orders();
					for (int _temp_j = 0; _temp_j < _infos.size(); _temp_j++)
					{
						OrderInfo _temp_info = _infos.get(_temp_j);
						InformationBar _informationbar;
						int _type = _temp_info.get_bookType();
						switch (_type)
						{
						case 12:
							_informationbar = new InformationBar(getActivity(), _body, 2, new String[] { "设计标", "" }, true, this);
							break;
						case 13:
							_informationbar = new InformationBar(getActivity(), _body, 2, new String[] { "施工标", "" }, true, this);
							break;
						case 14:
							_informationbar = new InformationBar(getActivity(), _body, 2, new String[] { "监理标", "" }, true, this);
							break;
						default:
							_informationbar = new InformationBar(getActivity(), _body, 2, new String[] { "未知", "" }, true, this);
						}
						_informationbars.put(_informationbar.get_id(), new Package(_informationbar, _temp_info.get_protocolId()));
					}
				}
			}
			else
			{
				Toast.makeText(getActivity(), "目前无订单信息", Toast.LENGTH_SHORT).show();
				Activity_my_order_yezhu.get_instance().finish();
			}
		}
	}

	@Override
	public void onChange(String _tag, String[] _args)
	{
		// String _bidID = Activity_my_bid_yezhu.get_data().get(Integer.parseInt(_tag)).get_bids().get(Integer.parseInt(_args[0])).get_id();
		// int _type = Activity_my_bid_yezhu.get_data().get(Integer.parseInt(_tag)).get_bids().get(Integer.parseInt(_args[0])).get_bookType();
		// Intent _quota_list = new Intent(getActivity(), Activity_quota_list.class);
		// _quota_list.putExtra("id", _bidID);
		// _quota_list.putExtra("type", _type);
		// _quota_list.putExtra("have_detail", true);
		// startActivity(_quota_list);
	}

	@Override
	public void onClick(View v)
	{
		int _id = v.getId();
		Package _package = _informationbars.get(_id);
		Toast.makeText(getActivity(), "protocolId=" + _package._tag1, Toast.LENGTH_SHORT).show();
	}
}
