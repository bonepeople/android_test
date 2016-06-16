package com.shownest.android.fragment;

import java.util.ArrayList;

import com.shownest.android.R;
import com.shownest.android.activity.Activity_quota_list;
import com.shownest.android.adapter.Adapter_quota_state;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.model.QuotaInfo;
import com.shownest.android.utils.NumberUtil;
import com.shownest.android.widget.InformationBar;
import com.shownest.android.widget.View_split_h;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment_quota_list extends DEBUG_Fragment implements View.OnClickListener
{
	private LinearLayout _body, _buttons;
	private InformationBar _detail;
	private Adapter_quota_state _adapter;
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
			if (Activity_quota_list.is_has_detail())
			{
				if (_data.size() != 0)
				{
					switch (_data.get(0).get_userType())
					{
					case 12:
						_detail = new InformationBar(getActivity(), _body, 2, new String[] { "设计标详情", "" }, true, this);
						break;
					case 13:
						_detail = new InformationBar(getActivity(), _body, 2, new String[] { "施工标详情", "" }, true, this);
						break;
					case 14:
						_detail = new InformationBar(getActivity(), _body, 2, new String[] { "监理标详情", "" }, true, this);
						break;
					default:
						_detail = new InformationBar(getActivity(), _body, 2, new String[] { "投标详情", "" }, true, this);
					}
				}
				else
				{
					Toast.makeText(getActivity(), "目前该标无人投标", Toast.LENGTH_SHORT).show();
					Activity_quota_list.get_instance().finish();
				}
				new View_split_h(getActivity(), _body, 10f).set_color(getResources().getColor(R.color.background_main));
			}

			TextView _name = new TextView(getActivity());
			_name.setText("投标记录(" + _data.size() + ")");
			int _padding = NumberUtil.get_px(getActivity(), 10);
			_name.setPadding(_padding, _padding, _padding, _padding);
			_body.addView(_name);
			new View_split_h(getActivity(), _body, 1f).set_color(getResources().getColor(R.color.background_main));
			_adapter = new Adapter_quota_state(getActivity(), _data);
			_list = new ListView(getActivity());
			_list.setAdapter(_adapter);
			_list.setDivider(new ColorDrawable(getResources().getColor(R.color.background_main)));
			_list.setDividerHeight(2);
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

	@Override
	public void onClick(View v)
	{
		// TODO Auto-generated method stub
		Toast.makeText(getActivity(), "查看标的详情", Toast.LENGTH_SHORT).show();
	}

}
