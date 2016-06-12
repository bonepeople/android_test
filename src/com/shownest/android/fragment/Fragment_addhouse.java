package com.shownest.android.fragment;

import com.shownest.android.R;
import com.shownest.android.activity.Activity_addhouse;
import com.shownest.android.activity.Activity_location;
import com.shownest.android.adapter.Adapter_rooms_area;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.model.OnChangeListener;
import com.shownest.android.utils.HttpUtil;
import com.shownest.android.widget.AlertDialog_rooms;
import com.shownest.android.widget.LinearLayout_checkbox;
import com.shownest.android.widget.LinearLayout_picturebox;
import com.shownest.android.widget.Linearlayout_listview;
import com.shownest.android.widget.InformationBar;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Fragment_addhouse extends DEBUG_Fragment implements View.OnClickListener, OnChangeListener
{
	private static final int REQUEST_LOCATION = 1;
	private LinearLayout _body;
	private Button _button_commit;
	private InformationBar _name, _location, _address, _areas, _house, _floor_all, _floor_current;
	private LinearLayout_checkbox _state, _type;
	private LinearLayout_picturebox _img;
	private Adapter_rooms_area _adapter;
	private Linearlayout_listview _list;
	private int _cityId = 0, _provinceId = 0, _countyId = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);
		View _view = inflater.inflate(R.layout.fragment_basic, container, false);
		_body = (LinearLayout) _view.findViewById(R.id.linearlayout_content);
		_button_commit = (Button) _view.findViewById(R.id.button_commit);
		_button_commit.setText("保存");
		_button_commit.setOnClickListener(this);

		_name = new InformationBar(getActivity(), _body, 5, new String[] { "小区名称", "" }, true);
		_location = new InformationBar(getActivity(), _body, 2, new String[] { "所在区域", "" }, true, this);
		_address = new InformationBar(getActivity(), _body, 5, new String[] { "完善地址", "" }, true);
		_areas = new InformationBar(getActivity(), _body, 7, new String[] { "建筑面积", "0.0", "平米" }, true);
		_house = new InformationBar(getActivity(), _body, 4, new String[] { "户型结构", "1,1,1,1,1" }, true, this);
		_state = new LinearLayout_checkbox(getActivity(), "房屋状态", new String[] { "毛坯新房", "二手旧房" }, 1, "1");
		_body.addView(_state);
		_type = new LinearLayout_checkbox(getActivity(), "房屋类型", new String[] { "平层住宅", "复试住宅", "别墅", "商业" }, 1, "1");
		_body.addView(_type);
		_floor_current = new InformationBar(getActivity(), _body, 3, new String[] { "所在楼层", "1", "层" }, true);
		_floor_all = new InformationBar(getActivity(), _body, 3, new String[] { "总楼层", "1", "层" }, true);
		_img = new LinearLayout_picturebox(this, "户型图(选填)", true);
		_body.addView(_img);

		_adapter = new Adapter_rooms_area(getActivity());
		_list = new Linearlayout_listview(getActivity(), _body, new String[] { "具体面积", "信息详细，报价准确" }, _adapter);
		_list.set_textcolor("hint", getResources().getColor(R.color.main_color));
		_list.set_dividerheight(1);
		_areas.setOnChangeListener(_adapter);
		_house.setOnChangeListener(_adapter);

		return _view;
	}

	@Override
	public void setContent()
	{

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		_img.onActivityResult(requestCode, resultCode, data);
		if (resultCode == -1)
		{
			_cityId = data.getIntExtra("cityId", 0);
			System.out.println("cityId=" + _cityId);

			_provinceId = data.getIntExtra("provinceId", 0);
			System.out.println("provinceId=" + _provinceId);

			_countyId = data.getIntExtra("countyId", 0);
			System.out.println("countyId=" + _countyId);

			switch (requestCode)
			{
			case REQUEST_LOCATION:
				String serviceRegion = String.valueOf(_provinceId) + "," + String.valueOf(_cityId) + "," + String.valueOf(_countyId);
				_location.setData(new String[] { serviceRegion });
			}
		}
		else
			System.out.println("resultCode=" + resultCode);
	}

	@Override
	public void onClick(View v)
	{
		// TODO Auto-generated method stub
		int _id = v.getId();
		if (_id == _button_commit.getId())
		{
			float _total = _adapter.get_totla_acreage();

			if (_name.getData().isEmpty())
			{
				Toast.makeText(getActivity(), "小区名称不能为空", Toast.LENGTH_SHORT).show();
			}
			else if (_location.getData().isEmpty())
			{
				Toast.makeText(getActivity(), "请选择所在区域", Toast.LENGTH_SHORT).show();
			}
			else if (_address.getData().isEmpty())
			{
				Toast.makeText(getActivity(), "请填写详细地址", Toast.LENGTH_SHORT).show();
			}
			else if (_areas.getData().equals("0.0"))
			{
				Toast.makeText(getActivity(), "请输入房屋的建筑面积", Toast.LENGTH_SHORT).show();
			}
			else if (_total > Float.parseFloat(_areas.getData()) + 1)
			{
				Toast.makeText(getActivity(), "输入的具体面积总和不应大于建筑面积", Toast.LENGTH_LONG).show();
			}
			else if (Integer.parseInt(_floor_current.getData()) > Integer.parseInt(_floor_all.getData()))
			{
				Toast.makeText(getActivity(), "输入的楼层不正确", Toast.LENGTH_LONG).show();
			}
			else
			{
				ContentValues _value = new ContentValues();
				_value.put("houseId", Activity_addhouse.get_houseId());
				_value.put("houseName", _name.getData());
				_value.put("houseType", _type.getData());
				_value.put("houseState", _state.getData());
				_value.put("provinceId", _provinceId);
				_value.put("cityId", _cityId);
				_value.put("countyId", _countyId);
				_value.put("homeAddress", _address.getData());
				_value.put("homeSq", _areas.getData());
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
				_value.put("floors", _floor_current.getData() + "/" + _floor_all.getData());

				Activity_addhouse.get_instance().show_wait();
				HttpUtil.add_house(Activity_addhouse._handler, _value, Activity_addhouse.ADD_SUCCESSFUL, Activity_addhouse.ADD_FAILED);
			}
		}
		else if (_id == _house.get_id())
		{
			new AlertDialog_rooms(getActivity(), _house.getData(), this);
		}
		else if (_id == _location.get_id())
		{
			Intent _location = new Intent(getActivity(), Activity_location.class);
			startActivityForResult(_location, REQUEST_LOCATION);
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
