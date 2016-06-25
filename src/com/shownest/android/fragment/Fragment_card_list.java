package com.shownest.android.fragment;

import java.util.ArrayList;

import com.shownest.android.R;
import com.shownest.android.activity.Activity_card_list;
import com.shownest.android.adapter.Adapter_card_list;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.model.BankCardInfo;
import com.shownest.android.model.OnChangeListener;
import com.shownest.android.utils.HttpUtil;

import android.content.ContentValues;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

public class Fragment_card_list extends DEBUG_Fragment implements OnChangeListener
{
	private LinearLayout _body, _buttons;
	private ArrayList<BankCardInfo> _data;
	private Adapter_card_list _adapter;
	private ListView _list;
	private int _index;

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
		_data = Activity_card_list.get_data();
		if (_data != null)
		{
			_adapter = new Adapter_card_list(getActivity(), _data);
			_adapter.setOnChangetListener("card", this);
			_list = new ListView(getActivity());
			_list.setAdapter(_adapter);
			_list.setDivider(new ColorDrawable(getResources().getColor(R.color.background_main)));
			_list.setDividerHeight(20);
			// _list.setOnItemClickListener(new OnItemClickListener()
			// {
			// @Override
			// public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			// {
			// String _bidID = _adapter.getItem(position).get_id();
			// Intent _detail = new Intent(getActivity(), Activity_bid_detail.class);
			// _detail.putExtra("bidID", _bidID);
			// _detail.putExtra("have_button", true);
			// startActivity(_detail);
			// }
			// });
			_body.addView(_list);
		}
	}

	public void refresh(String _type)
	{
		System.out.println("refresh " + _type);
		if (_type.equals("del"))
		{
			_data.remove(_index);
		}
		else
		{

		}
		_adapter.notifyDataSetChanged();
	}

	@Override
	public void onChange(String _tag, String[] _args)
	{
		_index = Integer.parseInt(_args[0]);
		Toast.makeText(getActivity(), "delete " + _index, Toast.LENGTH_SHORT).show();
		ContentValues _value = new ContentValues();
		_value.put("bankCardId", _data.get(_index).get_bankCardId());
		Activity_card_list.get_instance().show_wait();
		HttpUtil.delete_card(Activity_card_list._handler, _value, Activity_card_list.DELETE_SUCCESSFUL, Activity_card_list.DELETE_FAILED);
	}

}
