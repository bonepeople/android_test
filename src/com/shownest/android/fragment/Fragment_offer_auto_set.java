package com.shownest.android.fragment;

import com.shownest.android.R;
import com.shownest.android.activity.Activity_location;
import com.shownest.android.activity.Activity_offer_auto;
import com.shownest.android.adapter.Adapter_offer_auto;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.model.OnChangeListener;
import com.shownest.android.utils.HttpUtil;
import com.shownest.android.widget.AlertDialog_rooms;
import com.shownest.android.widget.LinearLayout_checkbox;
import com.shownest.android.widget.Linearlayout_listview;
import com.shownest.android.widget.RelativeLayout_edit_informationbar;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Fragment_offer_auto_set extends DEBUG_Fragment implements OnClickListener, OnChangeListener
{
	private static final int LOCATION = 1;
	private LinearLayout _body;
	private Button _button_commit;
	private LinearLayout_checkbox _state, _type, _mode;
	private RelativeLayout_edit_informationbar _name, _region, _area, _house;
	private Adapter_offer_auto _adapter;
	private Linearlayout_listview _list;
	private int cityId = 0, provinceId = 0, countyId = 0;
	private String serviceRegion = "";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);
		View _view = inflater.inflate(R.layout.fragment_setinfo, container, false);
		_body = (LinearLayout) _view.findViewById(R.id.linearlayout_content);
		_button_commit = (Button) _view.findViewById(R.id.button_commit);
		_button_commit.setText("获取报价");
		_button_commit.setOnClickListener(this);

		_name = new RelativeLayout_edit_informationbar(getActivity(), _body, 5, new String[] { "小区名称", "" }, true);
		_region = new RelativeLayout_edit_informationbar(getActivity(), _body, 2, new String[] { "所在区域", "" }, true, this);
		_state = new LinearLayout_checkbox(getActivity(), "房屋状态", new String[] { "毛坯新房", "二手旧房" }, 1, "1");
		_body.addView(_state);
		_type = new LinearLayout_checkbox(getActivity(), "房屋类型", new String[] { "平层住宅", "复试住宅", "别墅", "商业" }, 1, "1");
		_body.addView(_type);
		_mode = new LinearLayout_checkbox(getActivity(), "装修方式", new String[] { "半包" }, 1, "1");
		_body.addView(_mode);
		_area = new RelativeLayout_edit_informationbar(getActivity(), _body, 7, new String[] { "建筑面积", "0.0", " m²" }, true);
		_house = new RelativeLayout_edit_informationbar(getActivity(), _body, 4, new String[] { "户型结构", "1,1,1,1,1" }, true, this);
		_adapter = new Adapter_offer_auto(getActivity());
		_list = new Linearlayout_listview(getActivity(), _body, "areas", new String[] { "具体面积", "信息填写详细，会使您获得更精准的报价" }, _adapter);
		_list.set_textcolor("hint", getResources().getColor(R.color.text_blue));
		_list.set_dividerheight(1);
		_area.setOnChangeListener(_adapter);
		_house.setOnChangeListener(_adapter);

		return _view;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if (resultCode == -1)
		{
			cityId = data.getIntExtra("cityId", 0);
			System.out.println("cityId=" + cityId);

			provinceId = data.getIntExtra("provinceId", 0);
			System.out.println("provinceId=" + provinceId);

			countyId = data.getIntExtra("countyId", 0);
			System.out.println("countyId=" + countyId);

			switch (requestCode)
			{
			case LOCATION:
				serviceRegion = String.valueOf(provinceId) + "," + String.valueOf(cityId) + "," + String.valueOf(countyId);
				System.out.println("serviceRegion=" + serviceRegion);
				_region.setData(new String[] { serviceRegion });
			}
		}
		else
			System.out.println("resultCode=" + resultCode);
	}

	@Override
	public void onClick(View v)
	{
		int _id = v.getId();
		if (_id == R.id.button_commit)
		{
			float _total = _adapter.get_totla_acreage();
			if (_name.getData().isEmpty())
			{
				Toast.makeText(getActivity(), "小区名称不能为空", Toast.LENGTH_SHORT).show();
			}
			else if (serviceRegion.isEmpty())
			{
				Toast.makeText(getActivity(), "请选择所在区域", Toast.LENGTH_SHORT).show();
			}
			else if (_area.getData().equals("0.0"))
			{
				Toast.makeText(getActivity(), "请输入房屋的建筑面积", Toast.LENGTH_SHORT).show();
			}
			else if (_total > Float.parseFloat(_area.getData()) + 1)
			{
				Toast.makeText(getActivity(), "输入的具体面积总和不应大于建筑面积", Toast.LENGTH_LONG).show();
			}
			else
			{
				ContentValues _value = new ContentValues();
				_value.put("areaName", _name.getData());
				_value.put("houseRegion", serviceRegion);
				_value.put("houseState", _state.getData());
				_value.put("houseType", _type.getData());
				_value.put("consType", Integer.parseInt(_mode.getData()) - 1);
				_value.put("houseSq", _area.getData());
				_value.put("roomNum", _adapter.get_number("room"));
				_value.put("parlourNum", _adapter.get_number("parlour"));
				_value.put("kitchenNum", _adapter.get_number("kitchen"));
				_value.put("toiletNum", _adapter.get_number("toilet"));
				_value.put("balconyNum", _adapter.get_number("balcony"));
				_value.put("roomAcreage", _adapter.get_acreage("room"));
				_value.put("parlourAcreage", _adapter.get_acreage("parlour"));
				_value.put("kitchenAcreage", _adapter.get_acreage("kitchen"));
				_value.put("toiletAcreage", _adapter.get_acreage("toilet"));
				_value.put("balconyAcreage", _adapter.get_acreage("balcony"));

				Activity_offer_auto.get_instance().show_wait();
				HttpUtil.get_ownerquote(Activity_offer_auto._handler, _value, Activity_offer_auto.NEXT_SUCCESSFUL, Activity_offer_auto.NEXT_FAILED);
			}
		}
		else if (_id == _region.get_id())
		{
			Intent _location = new Intent(getActivity(), Activity_location.class);
			startActivityForResult(_location, LOCATION);
		}
		else if (_id == _house.get_id())
		{
			new AlertDialog_rooms(getActivity(), _house.getData(), this);
		}
	}

	@Override
	public void onChange(String tag, String[] args)
	{
		if (tag.equals("house"))
		{
			_house.setData(new String[] { args[0] });
		}
	}
}
