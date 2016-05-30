package com.shownest.android.fragment;

import com.shownest.android.R;
import com.shownest.android.activity.Activity_quotation_change;
import com.shownest.android.activity.Activity_quotation_detail;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.model.ItemDetail;
import com.shownest.android.model.RoomDetail;
import com.shownest.android.widget.Linearlayout_edittext;
import com.shownest.android.widget.Linearlayout_listview;
import com.shownest.android.widget.RelativeLayout_edit_informationbar;

import android.content.ContentValues;
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
	private ItemDetail _new_item;
	private Linearlayout_listview _list;
	private RelativeLayout_edit_informationbar _name, _price, _number;
	private Linearlayout_edittext _material, _technics;

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
			if (_type.equals("fix"))
			{

			}
			else if (_type.equals("change"))
			{
				_new_item = Activity_quotation_change.get_item();
				System.out.println("will change:" + _new_item.get_itemName());

				_name = new RelativeLayout_edit_informationbar(getActivity(), _body, 5, new String[] { "修改项目", _new_item.get_itemName() }, false);
				String _str_price = String.valueOf(_new_item.get_price());
				String _str_unit = _new_item.get_unit() + "/" + _new_item.get_metricUnit();
				String _str_number = String.valueOf(_new_item.get_number());
				_price = new RelativeLayout_edit_informationbar(getActivity(), _body, 7, new String[] { "单价", _str_price, _str_unit }, true);
				_number = new RelativeLayout_edit_informationbar(getActivity(), _body, 7, new String[] { "工程量", _str_number, _new_item.get_metricUnit() }, true);
				_material = new Linearlayout_edittext(getActivity(), _body, new String[] { "辅材品牌型号", "", _new_item.get_material() });
				_technics = new Linearlayout_edittext(getActivity(), _body, new String[] { "工艺说明", "", _new_item.get_technics() });
			}
		}
	}

	@Override
	public void onClick(View v)
	{
		int _id = v.getId();
		if (_id == R.id.button_commit)
		{
			Activity_quotation_change.get_instance().show_wait();
			ContentValues _values = new ContentValues();

			// Activity_quotation_detail.get_instance().finish();
			Toast.makeText(getActivity(), "tijiao", Toast.LENGTH_SHORT).show();
		}
	}

}
