package com.shownest.android.fragment;

import com.shownest.android.R;
import com.shownest.android.activity.Activity_quotation_change;
import com.shownest.android.activity.Activity_quotation_detail;
import com.shownest.android.adapter.Adapter_quotation_detail;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.model.ItemDetail;
import com.shownest.android.model.RoomDetail;
import com.shownest.android.widget.Linearlayout_listview;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Fragment_quotation_change extends DEBUG_Fragment implements OnClickListener
{
	private static final int LOCATION = 1;
	private LinearLayout _body;
	private Button _button_commit;
	private String _type, _room, _part;
	private int _room_index, _part_index;
	private Linearlayout_listview _list;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);
		View _view = inflater.inflate(R.layout.fragment_basic, container, false);
		_body = (LinearLayout) _view.findViewById(R.id.linearlayout_content);
		_button_commit = (Button) _view.findViewById(R.id.button_commit);
		_button_commit.setText("确定");
		_button_commit.setOnClickListener(this);

		Intent _intent = Activity_quotation_change.get_intent();
		_type = _intent.getStringExtra("type");
		_room = _intent.getStringExtra("room");
		_part = _intent.getStringExtra("part");
		_room_index = _intent.getIntExtra("room_index", 1);
		_part_index = _intent.getIntExtra("part_index", 0);

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
		System.out.println("type:" + _type + " room:" + _room + _room_index + " part:" + _part + _part_index);
		if (_data != null)
		{
			SparseArray<ItemDetail> _array = _data.get_details("ground");
			ItemDetail _item = _array.get(0);

			System.out.println("will change:" + _item.get_itemName());
		}
	}

	@Override
	public void onClick(View v)
	{
		int _id = v.getId();
		if (_id == R.id.button_commit)
		{
			// Activity_quotation_detail.get_instance().finish();
			Toast.makeText(getActivity(), "tijiao", Toast.LENGTH_SHORT).show();
		}
	}

}
