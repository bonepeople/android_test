package com.shownest.android.fragment;

import java.util.ArrayList;

import com.shownest.android.R;
import com.shownest.android.activity.Activity_bid_detail;
import com.shownest.android.activity.Activity_quota_list;
import com.shownest.android.activity.Activity_webview;
import com.shownest.android.adapter.Adapter_quota_state;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.model.QuotaInfo;
import com.shownest.android.utils.NumberUtil;
import com.shownest.android.utils.UserManager;
import com.shownest.android.widget.InformationBar;
import com.shownest.android.widget.View_split_h;

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
			if (Activity_quota_list.have_detail())
			{
				switch (Activity_quota_list.get_type())
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
				new View_split_h(getActivity(), _body, 10f).set_color(getResources().getColor(R.color.background_main));
			}
			else if (_data.size() == 0)
			{
				Toast.makeText(getActivity(), "目前该标无人投标", Toast.LENGTH_SHORT).show();
				Activity_quota_list.get_instance().finish();
				return;
			}
			// ======================中标方案
			int _padding = NumberUtil.get_px(getActivity(), 10);
			if (Activity_quota_list.get_winner() != null)
			{
				TextView _zhongbiao = new TextView(getActivity());
				_zhongbiao.setText("中标方案");
				_zhongbiao.setPadding(_padding, _padding, _padding, _padding);
				_body.addView(_zhongbiao);
				new View_split_h(getActivity(), _body, 1f).set_color(getResources().getColor(R.color.background_main));
				ArrayList<QuotaInfo> _array_win = new ArrayList<QuotaInfo>();
				_array_win.add(Activity_quota_list.get_winner());
				ListView _list_win = new ListView(getActivity());
				Adapter_quota_state _adapter_win = new Adapter_quota_state(getActivity(), _array_win);
				_list_win.setAdapter(_adapter_win);
				_list_win.setOnItemClickListener(new OnItemClickListener()
				{
					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id)
					{
						int _bidType = Activity_quota_list.get_winner().get_userType();
						String _bidID = Activity_quota_list.get_bidID();
						String _userID = Activity_quota_list.get_winner().get_userId();
						String _ukey = UserManager.get_ukey();
						String _url = "";
						switch (_bidType)
						{
						case 12:
							_url = "http://app.shownest.com/bid/getDesiSelfRespBid?userId=" + _userID + "&homeId=" + _bidID + "&ukey=" + _ukey;
							break;
						case 13:
							_url = "http://app.shownest.com/bid/getConsSelfRespBid?userId=" + _userID + "&homeId=" + _bidID + "&ukey=" + _ukey;
							break;
						case 14:
							break;
						}
						Intent _zhaobiao = new Intent(getActivity(), Activity_webview.class);
						_zhaobiao.putExtra("url", _url);
						_zhaobiao.putExtra("have_title", true);
						_zhaobiao.putExtra("title", "招标详情");
						startActivity(_zhaobiao);
					}
				});
				_body.addView(_list_win);
			}
			// ======================所有投标记录
			TextView _name = new TextView(getActivity());
			_name.setText("投标记录(" + _data.size() + ")");
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
					int _bidType = Activity_quota_list.get_data().get(position).get_userType();
					String _bidID = Activity_quota_list.get_bidID();
					String _userID = Activity_quota_list.get_data().get(position).get_userId();
					String _ukey = UserManager.get_ukey();
					String _url = "";
					switch (_bidType)
					{
					case 12:
						_url = "http://app.shownest.com/bid/getDesiSelfRespBid?userId=" + _userID + "&homeId=" + _bidID + "&ukey=" + _ukey;
						break;
					case 13:
						_url = "http://app.shownest.com/bid/getConsSelfRespBid?userId=" + _userID + "&homeId=" + _bidID + "&ukey=" + _ukey;
						break;
					case 14:
						break;
					}
					Intent _zhaobiao = new Intent(getActivity(), Activity_webview.class);
					_zhaobiao.putExtra("url", _url);
					_zhaobiao.putExtra("have_title", true);
					_zhaobiao.putExtra("title", "招标详情");
					startActivity(_zhaobiao);
				}
			});
			_body.addView(_list);
		}
	}

	@Override
	public void onClick(View v)
	{
		Intent _detail = new Intent(getActivity(), Activity_bid_detail.class);
		_detail.putExtra("bidID", Activity_quota_list.get_bidID());
		_detail.putExtra("have_button", false);
		startActivity(_detail);
	}

}
