package com.shownest.android.fragment;

import java.util.ArrayList;

import com.shownest.android.R;
import com.shownest.android.activity.Activity_bid_detail;
import com.shownest.android.activity.Activity_my_order_maijia;
import com.shownest.android.activity.Activity_order_detail;
import com.shownest.android.adapter.Adapter_order_state_maijia;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.model.OnChangeListener;
import com.shownest.android.model.OrderInfo;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class Fragment_my_order_maijia extends DEBUG_Fragment implements OnChangeListener
{
	private LinearLayout _body, _buttons;
	private ArrayList<OrderInfo> _data;
	private Adapter_order_state_maijia _adapter;
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
		_data = Activity_my_order_maijia.get_data();
		if (_data != null)
		{
			_adapter = new Adapter_order_state_maijia(getActivity(), _data);
			_adapter.setOnChangetListener("order", this);
			_list = new ListView(getActivity());
			_list.setAdapter(_adapter);
			_list.setDivider(new ColorDrawable(getResources().getColor(R.color.background_main)));
			_list.setDividerHeight(30);
			_list.setOnItemClickListener(new OnItemClickListener()
			{
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id)
				{
					String _protocolId = _data.get(position).get_protocolId();
					Intent _detail = new Intent(getActivity(), Activity_order_detail.class);
					_detail.putExtra("protocolId", _protocolId);
					startActivity(_detail);
				}
			});
			_body.addView(_list);
		}
	}

	@Override
	public void onChange(String _tag, String[] _args)
	{
		if (_args[0].equals("hint"))
		{
			// 点击左下方按钮
			System.out.println("hint = " + _args[1]);
			// String _url = "";
			// String _bidID = _data.get(Integer.parseInt(_args[1])).get_id();
			// String _title = _data.get(Integer.parseInt(_args[1])).get_houseName();
			// _url = "http://app.shownest.com/agreement/createCooperationAgreement?homeId=" + _bidID + "&ukey=" + UserManager.get_ukey();
			// Intent _xieyi = new Intent(getActivity(), Activity_webview.class);
			// _xieyi.putExtra("url", _url);
			// _xieyi.putExtra("have_title", true);
			// _xieyi.putExtra("title", _title);
			// startActivity(_xieyi);
		}
	}
}
