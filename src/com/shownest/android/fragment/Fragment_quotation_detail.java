package com.shownest.android.fragment;

import com.shownest.android.R;
import com.shownest.android.activity.Activity_quotation_detail;
import com.shownest.android.adapter.Adapter_quotation_detail;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.model.RoomDetail;
import com.shownest.android.widget.Linearlayout_listview;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class Fragment_quotation_detail extends DEBUG_Fragment implements OnClickListener
{
	private static final int LOCATION = 1;
	private LinearLayout _body;
	private Button _button_commit;
	private Adapter_quotation_detail _adapter;
	private Adapter_quotation_detail _adapter2;
	private Adapter_quotation_detail _adapter3;
	private AlertDialog _dialog;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);
		View _view = inflater.inflate(R.layout.fragment_basic, container, false);
		_body = (LinearLayout) _view.findViewById(R.id.linearlayout_content);
		_button_commit = (Button) _view.findViewById(R.id.button_commit);
		_button_commit.setText("保存");
		_button_commit.setOnClickListener(this);
		return _view;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if (resultCode == -1)
		{

			switch (requestCode)
			{
			case LOCATION:
			}
		}
		else
			System.out.println("resultCode=" + resultCode);
	}

	@Override
	public void setContent()
	{
		RoomDetail _data = Activity_quotation_detail.get_data();
		if (_data != null)
		{
			switch (Activity_quotation_detail.get_type())
			{
			case "tax":
				break;

			default:
				
				_adapter3 = new Adapter_quotation_detail(getActivity(), _data.get_wall());
				new Linearlayout_listview(getActivity(), _body, new String[] { "地面", "小计：" + _data.get_groundTotals() + "元" }, _adapter3);
				
				_adapter = new Adapter_quotation_detail(getActivity(), _data.get_wall());
				new Linearlayout_listview(getActivity(), _body, new String[] { "墙面", "小计：" + _data.get_groundTotals() + "元" }, _adapter);
				
				_adapter2 = new Adapter_quotation_detail(getActivity(), _data.get_roof());
				new Linearlayout_listview(getActivity(), _body, new String[] { "顶面", "小计：" + _data.get_roofTotals() + "元" }, _adapter2);

				System.out.println("ground size is " + _data.get_ground().size());
				break;
			}
		}
	}

	@Override
	public void onClick(View v)
	{
		int _id = v.getId();
		if (_id == R.id.button_commit)
		{
			// ContentValues _value = new ContentValues();
			// _value.put("roomNum", _list._adapter.get_number("room"));
			// _value.put("parlourNum", _list._adapter.get_number("parlour"));
			// _value.put("kitchenNum", _list._adapter.get_number("kitchen"));
			// _value.put("toiletNum", _list._adapter.get_number("toilet"));
			// _value.put("balconyNum", _list._adapter.get_number("balcony"));
			// _value.put("roomAcreage", _list._adapter.get_acreage("room"));
			// _value.put("parlourAcreage", _list._adapter.get_acreage("parlour"));
			// _value.put("kitchenAcreage", _list._adapter.get_acreage("kitchen"));
			// _value.put("toiletAcreage", _list._adapter.get_acreage("toilet"));
			// _value.put("balconyAcreage", _list._adapter.get_acreage("balcony"));
			//
			// Activity_offer_auto.get_instance().show_wait();
			// HttpUtil.get_ownerquote(Activity_offer_auto._handler, _value, Activity_offer_auto.NEXT_SUCCESSFUL, Activity_offer_auto.NEXT_FAILED);
		}
	}

	private void show_dialog()
	{
		View _view = View.inflate(getActivity(), R.layout.dialog_house, null);
		AlertDialog.Builder _builder = new Builder(getActivity());
		_dialog = _builder.create();
		_dialog.setView(_view, 0, 0, 0, 0);

		Button _button_commit = (Button) _view.findViewById(R.id.button_commit);

		_button_commit.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				_dialog.dismiss();
			}
		});
		_dialog.show();
	}
}
