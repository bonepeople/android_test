package com.shownest.android.fragment;

import java.util.ArrayList;

import com.shownest.android.R;
import com.shownest.android.activity.Activity_quota_list;
import com.shownest.android.adapter.Adapter_quota_list;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.model.QuotaInfo;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;

public class Fragment_quota_list extends DEBUG_Fragment
{
	private LinearLayout _body, _buttons;
	private Adapter_quota_list _adapter;
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
		ArrayList<QuotaInfo> _data = Activity_quota_list.get_data();
		if (_data != null)
		{
			_adapter = new Adapter_quota_list(getActivity(), _data);
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
					System.out.println("clicked index:" + position);
					// Intent _detail = new Intent(getActivity(), Activity_bid_detail.class);
					// _detail.putExtra("index", position);
					// startActivity(_detail);
				}
			});
			_body.addView(_list);
		}
	}

}
