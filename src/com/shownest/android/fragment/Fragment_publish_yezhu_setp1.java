package com.shownest.android.fragment;

import java.util.ArrayList;

import com.shownest.android.R;
import com.shownest.android.activity.Activity_location;
import com.shownest.android.activity.Activity_publish_yezhu;
import com.shownest.android.adapter.Adapter_rooms_area;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.model.HouseInfo;
import com.shownest.android.model.OnChangeListener;
import com.shownest.android.widget.AlertDialog_rooms;
import com.shownest.android.widget.LinearLayout_checkbox;
import com.shownest.android.widget.LinearLayout_picturebox;
import com.shownest.android.widget.Widget_listview;
import com.shownest.android.widget.InformationBar;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Fragment_publish_yezhu_setp1 extends DEBUG_Fragment implements OnClickListener, OnChangeListener
{
	private static final int REQUEST_LOCATION = 1;
	private LinearLayout _body;
	private Button _button_commit;
	private InformationBar _name, _location, _address, _areas, _house, _floor_all, _floor_current;
	private LinearLayout_checkbox _state, _type;
	private LinearLayout_picturebox _img;
	private Adapter_rooms_area _adapter;
	private Widget_listview _list;
	private ArrayList<HouseInfo> _data;
	private int _index = 0;
	private String _houseId;
	private int _cityId = 0, _provinceId = 0, _countyId = 0;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);
		View _view = inflater.inflate(R.layout.fragment_basic, container, false);
		_body = (LinearLayout) _view.findViewById(R.id.linearlayout_content);
		_button_commit = (Button) _view.findViewById(R.id.button_commit);
		_button_commit.setText("下一步");
		_button_commit.setOnClickListener(this);

		ImageView _image_title = new ImageView(getActivity());
		_image_title.setImageDrawable(getResources().getDrawable(R.drawable.publish_step1));
		_body.addView(_image_title);

		_name = new InformationBar(getActivity(), _body, 2, new String[] { "我的房屋", "" }, true, this);
		_location = new InformationBar(getActivity(), _body, 2, new String[] { "所在区域", "" }, true, this);
		_address = new InformationBar(getActivity(), _body, 5, new String[] { "详细地址", "" }, true);
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
		_list = new Widget_listview(getActivity(), _body, new String[] { "具体面积", "信息详细，报价准确" }, _adapter);
		_list.set_textcolor("hint", getResources().getColor(R.color.main_color));
		_list.set_dividerheight(1);
		_areas.setOnChangeListener("area", _adapter);
		_house.setOnChangeListener("room", _adapter);

		return _view;
	}

	@Override
	public void setContent()
	{
		_data = Activity_publish_yezhu.get_house();
		if (_data != null && _data.size() != 0)
		{
			HouseInfo _temp_house = _data.get(_index);
			_houseId = _temp_house.get_houseId();
			_name.setData(new String[] { _temp_house.get_houseName() });
			_location.setData(new String[] { _temp_house.get_regionName() });
			String[] _region = _temp_house.get_region().split(",");
			_provinceId = Integer.parseInt(_region[0]);
			_cityId = Integer.parseInt(_region[1]);
			_countyId = Integer.parseInt(_region[2]);
			_address.setData(new String[] { _temp_house.get_homeAddress() });
			_areas.setData(new String[] { String.valueOf(_temp_house.get_homeSq()) });
			_house.setData(new String[] { _temp_house.get_nums() });
			_state.setData(String.valueOf(_temp_house.get_houseState()));
			_type.setData(String.valueOf(_temp_house.get_houseType()));
			String[] _floor = _temp_house.get_floors().split("/");
			_floor_current.setData(new String[] { _floor[0] });
			_floor_all.setData(new String[] { _floor[1] });
			_adapter.set_acreage(_temp_house.get_allAcreage());
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		_img.onActivityResult(requestCode, resultCode, data);
		if (resultCode == -1)
		{
			_provinceId = data.getIntExtra("provinceId", 0);
			System.out.println("provinceId=" + _provinceId);

			_cityId = data.getIntExtra("cityId", 0);
			System.out.println("cityId=" + _cityId);

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

	public ContentValues get_values()
	{
		float _total = _adapter.get_totla_acreage();
		if (_address.getData().isEmpty())
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
		else
		{
			ContentValues _value = new ContentValues();
			_value.put("provinceId", _provinceId);
			_value.put("cityId", _cityId);
			_value.put("countyId", _countyId);
			_value.put("houseId", _houseId);
			_value.put("houseName", _name.getData());
			_value.put("homeAddress", _address.getData());
			_value.put("houseType", _type.getData());
			_value.put("houseState", _state.getData());
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
			return _value;
		}
		return null;
	}

	@Override
	public void onClick(View v)
	{
		int _id = v.getId();
		if (_id == R.id.button_commit)
		{
			if (get_values() != null)
				Activity_publish_yezhu.add_fragment(Activity_publish_yezhu.get_instance(), new Fragment_publish_yezhu_setp2(), true);
		}
		else if (_id == _name.get_id())
		{
			String[] _names = new String[_data.size()];
			for (int _temp_i = 0; _temp_i < _data.size(); _temp_i++)
				_names[_temp_i] = _data.get(_temp_i).get_houseName();

			AlertDialog.Builder _builder = new Builder(getActivity());
			_builder.setTitle("我的房屋");
			_builder.setItems(_names, new DialogInterface.OnClickListener()
			{
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					_index = which;
					setContent();
				}
			});
			_builder.show();
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
