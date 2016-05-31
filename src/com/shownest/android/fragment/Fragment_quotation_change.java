package com.shownest.android.fragment;

import com.shownest.android.R;
import com.shownest.android.activity.Activity_quotation_change;
import com.shownest.android.adapter.Adapter_quotation_change;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.model.ItemDetail;
import com.shownest.android.model.RoomDetail;
import com.shownest.android.utils.CommonUtil;
import com.shownest.android.utils.HttpUtil;
import com.shownest.android.widget.Linearlayout_edittext;
import com.shownest.android.widget.Linearlayout_listview;
import com.shownest.android.widget.RelativeLayout_edit_informationbar;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class Fragment_quotation_change extends DEBUG_Fragment implements OnClickListener
{
	private static final int LOCATION = 1;
	private LinearLayout _body;
	private Button _button_commit;
	private String _type, _room, _part;
	private int _room_index, _part_index;
	private ItemDetail _new_item;
	private Adapter_quotation_change _adapter;
	private Linearlayout_listview _list;
	private RelativeLayout_edit_informationbar _price, _number;
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
		System.out.println("type:" + _type + " room:" + _room + _room_index + " part:" + _part + _part_index);
		if (_type.equals("change"))
		{
			_new_item = Activity_quotation_change.get_item();
			System.out.println("will change:" + _new_item.get_itemName());

			// new RelativeLayout_edit_informationbar(getActivity(), _body, 5, new String[] { "修改项目", _new_item.get_itemName() }, false);
			String _str_price = String.valueOf(_new_item.get_price());
			String _str_unit = _new_item.get_unit() + "/" + _new_item.get_metricUnit();
			String _str_number = String.valueOf(_new_item.get_number());
			_price = new RelativeLayout_edit_informationbar(getActivity(), _body, 7, new String[] { "单价", _str_price, _str_unit }, true);
			_number = new RelativeLayout_edit_informationbar(getActivity(), _body, 7, new String[] { "工程量", _str_number, _new_item.get_metricUnit() }, true);
			_material = new Linearlayout_edittext(getActivity(), _body, new String[] { "辅材品牌型号", "", _new_item.get_material() });
			_technics = new Linearlayout_edittext(getActivity(), _body, new String[] { "工艺说明", "", _new_item.get_technics() });
		}
		else if (_type.equals("fix"))
		{
			RoomDetail _data = Activity_quotation_change.get_all_item();
			String _name = CommonUtil.get_chineseName(_part);

			_adapter = new Adapter_quotation_change(getActivity(), _part, _data.get_details(_part));
			_list = new Linearlayout_listview(getActivity(), _body, _part, new String[] { _name, "" }, _adapter);
			_list.set_collapse(false);
			// _adapter.setOnChangetListener(Activity_quotation_detail.get_instance());
			// _list.setOnChangetListener(Activity_quotation_detail.get_instance());
		}
	}

	@Override
	public void onClick(View v)
	{
		int _id = v.getId();
		if (_id == R.id.button_commit)
		{
			if (_type.equals("change"))
			{
				_new_item.set_price(Double.parseDouble(_price.getData()));
				_new_item.set_number(Double.parseDouble(_number.getData()));
				_new_item.set_material(_material.getData());
				_new_item.set_technics(_technics.getData());
				ContentValues _value = new ContentValues();
				_value.put("numerical", _new_item.get_numerical());
				_value.put(_room, _room_index);
				_value.put("number", _number.getData());
				_value.put("price", _price.getData());
				_value.put("material", _material.getData());
				_value.put("technics", _technics.getData());
				_value.put("actionType", 2);

				Activity_quotation_change.get_instance().show_wait();
				HttpUtil.update_quotation_item(Activity_quotation_change._handler, _room, _value, Activity_quotation_change.CHANGE_SUCCESSFUL, Activity_quotation_change.CHANGE_FAILED);
			}
			else if (_type.equals("fix"))
			{
				SparseIntArray _change = _adapter.get_change();
				if (_change.size() != 0)
				{
					Activity_quotation_change.set_total_commit(_change.size());
					Activity_quotation_change.get_instance().show_wait();
					for (int _temp_i = 0; _temp_i < _change.size(); _temp_i++)
					{
						int _key = _change.keyAt(_temp_i);
						int _mark = _change.valueAt(_temp_i);
						if (_mark == 1)
						{
							// 新增
							ContentValues _value = new ContentValues();
							_value.put("quotationId", "");
							_value.put("numerical", _adapter.getItem(_key).get_numerical());
							switch (_part)
							{
							case "ground":
								_value.put("assortment", 1);
								_value.put(_room, _room_index);
								break;
							case "wall":
								_value.put("assortment", 3);
								_value.put(_room, _room_index);
								break;
							case "roof":
								_value.put("assortment", 2);
								_value.put(_room, _room_index);
								break;
							}
							_value.put("actionType", 1);
							HttpUtil.update_quotation_item(Activity_quotation_change._handler, _room, _value, Activity_quotation_change.CHANGE_SUCCESSFUL, Activity_quotation_change.CHANGE_FAILED);
						}
						else
						{
							// 删除
							ContentValues _value = new ContentValues();
							_value.put("quotationId", "");
							_value.put("numerical", _adapter.getItem(_key).get_numerical());
							switch (_part)
							{
							case "ground":
								_value.put("assortment", 1);
								_value.put(_room, _room_index);
								break;
							case "wall":
								_value.put("assortment", 3);
								_value.put(_room, _room_index);
								break;
							case "roof":
								_value.put("assortment", 2);
								_value.put(_room, _room_index);
								break;
							}
							_value.put("actionType", 3);
							HttpUtil.update_quotation_item(Activity_quotation_change._handler, _room, _value, Activity_quotation_change.CHANGE_SUCCESSFUL, Activity_quotation_change.CHANGE_FAILED);
						}
					}
				}
			}
		}
	}
}
