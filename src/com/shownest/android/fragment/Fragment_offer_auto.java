package com.shownest.android.fragment;

import com.shownest.android.R;
import com.shownest.android.activity.Activity_location;
import com.shownest.android.activity.Activity_offer_auto;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.utils.HttpUtil;
import com.shownest.android.widget.LinearLayout_checkbox;
import com.shownest.android.widget.Linearlayout_listview;
import com.shownest.android.widget.RelativeLayout_edit_informationbar;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.NumberPicker;

public class Fragment_offer_auto extends DEBUG_Fragment implements OnClickListener
{
	private static final int LOCATION = 1;
	private LinearLayout _body;
	private Button _button_commit;
	private LinearLayout_checkbox _state, _type, _mode;
	private RelativeLayout_edit_informationbar _name, _region, _area, _house;
	private Linearlayout_listview _list;
	private AlertDialog _dialog;
	private NumberPicker _room, _parlour, _kitchen, _toilet, _balcony;
	private int cityId = 0, provinceId = 0, countyId = 0;
	private String serviceRegion = "";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);
		View _view = inflater.inflate(R.layout.fragment_setinfo, container, false);
		_body = (LinearLayout) _view.findViewById(R.id.linearlayout_content);
		_button_commit = (Button) _view.findViewById(R.id.button_commit);
		_button_commit.setText("保存");
		_button_commit.setOnClickListener(this);

		_name = new RelativeLayout_edit_informationbar(getActivity(), _body, 5, new String[] { "小区名称", "" }, true);
		_region = new RelativeLayout_edit_informationbar(getActivity(), _body, 2, new String[] { "所在区域", "" }, true, this);
		_state = new LinearLayout_checkbox(getActivity(), "房屋状态", new String[] { "二手旧房", "毛坯新房" }, 1, "1");
		_body.addView(_state);
		_type = new LinearLayout_checkbox(getActivity(), "房屋类型", new String[] { "平层住宅", "复试住宅", "别墅", "商业" }, 1, "1");
		_body.addView(_type);
		_mode = new LinearLayout_checkbox(getActivity(), "装修方式", new String[] { "半包", "全包", "清包" }, 1, "1");
		_body.addView(_mode);
		_area = new RelativeLayout_edit_informationbar(getActivity(), _body, 3, new String[] { "建筑面积", "0", "m²" }, true);
		_house = new RelativeLayout_edit_informationbar(getActivity(), _body, 4, new String[] { "户型结构", "1,1,1,1,1" }, true, this);
		_list = new Linearlayout_listview(getActivity(), _body, new String[] { "具体面积", "信息填写详细，会使您获得更精准的报价" });

		_area.setOnChangetListener(_list);
		_house.setOnChangetListener(_list);

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
			ContentValues _value = new ContentValues();
			_value.put("areaName", _name.getData());
			_value.put("houseRegion", serviceRegion);
			_value.put("houseState", _state.getData());
			_value.put("houseType", _type.getData());
			_value.put("houseMode", _mode.getData());
			_value.put("houseSq", _area.getData());
			_value.put("roomNum", _list._adapter.get_number("room"));
			_value.put("parlourNum", _list._adapter.get_number("parlour"));
			_value.put("kitchenNum", _list._adapter.get_number("kitchen"));
			_value.put("toiletNum", _list._adapter.get_number("toilet"));
			_value.put("balconyNum", _list._adapter.get_number("balcony"));
			_value.put("roomAcreage", _list._adapter.get_acreage("room"));
			_value.put("parlourAcreage", _list._adapter.get_acreage("parlour"));
			_value.put("kitchenAcreage", _list._adapter.get_acreage("kitchen"));
			_value.put("toiletAcreage", _list._adapter.get_acreage("toilet"));
			_value.put("balconyAcreage", _list._adapter.get_acreage("balcony"));

			Activity_offer_auto.get_instance().show_wait();
			HttpUtil.set_PersonalBaseInfor(Activity_offer_auto._handler, _value, Activity_offer_auto.NEXT_SUCCESSFUL, Activity_offer_auto.NEXT_FAILED);

		}
		else if (_id == _region.get_id())
		{
			Intent _location = new Intent(getActivity(), Activity_location.class);
			startActivityForResult(_location, LOCATION);
		}
		else if (_id == _house.get_id())
		{
			show_dialog();
		}
	}

	private void show_dialog()
	{
		View _view = View.inflate(getActivity(), R.layout.dialog_house, null);
		AlertDialog.Builder _builder = new Builder(getActivity());
		_dialog = _builder.create();
		_dialog.setView(_view, 0, 0, 0, 0);

		Button _button_commit = (Button) _view.findViewById(R.id.button_commit);

		_room = (NumberPicker) _view.findViewById(R.id.number_room);
		_parlour = (NumberPicker) _view.findViewById(R.id.number_parlour);
		_kitchen = (NumberPicker) _view.findViewById(R.id.number_kitchen);
		_toilet = (NumberPicker) _view.findViewById(R.id.number_toilet);
		_balcony = (NumberPicker) _view.findViewById(R.id.number_balcony);
		String[] _num = _house.getData().split(",");

		_room.setMinValue(1);
		_room.setMaxValue(9);
		_room.setValue(Integer.parseInt(_num[0]));

		_parlour.setMinValue(1);
		_parlour.setMaxValue(9);
		_parlour.setValue(Integer.parseInt(_num[1]));

		_kitchen.setMinValue(1);
		_kitchen.setMaxValue(9);
		_kitchen.setValue(Integer.parseInt(_num[2]));

		_toilet.setMinValue(1);
		_toilet.setMaxValue(9);
		_toilet.setValue(Integer.parseInt(_num[3]));

		_balcony.setMinValue(1);
		_balcony.setMaxValue(9);
		_balcony.setValue(Integer.parseInt(_num[4]));

		_button_commit.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				String _result = _room.getValue() + "," + _parlour.getValue() + "," + _kitchen.getValue() + "," + _toilet.getValue() + "," + _balcony.getValue();
				_house.setData(new String[] { _result });
				_dialog.dismiss();
			}
		});
		_dialog.show();
	}
}
