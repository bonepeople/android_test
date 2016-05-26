package com.shownest.android.fragment;

import com.shownest.android.R;
import com.shownest.android.activity.Activity_location;
import com.shownest.android.activity.Activity_my_center;
import com.shownest.android.activity.Activity_setinfo_shigongdui;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.model.UserInfo;
import com.shownest.android.utils.HttpUtil;
import com.shownest.android.widget.RelativeLayout_edit_informationbar;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Fragment_setinfo_shigongdui_step2 extends DEBUG_Fragment implements OnClickListener
{
	private static final int LOCATION_WORK = 1;
	private static final int LOCATION_SERVICE = 2;
	private LinearLayout _body;
	private Button _button_commit;
	private RelativeLayout_edit_informationbar _date, _number, _location, _address, _service, _serviceItem;
	private int cityId = 0, provinceId = 0, countyId = 0;
	private String serviceRegion = "";
	private boolean[][] _service_select = new boolean[][] { { false, false, false }, { false, false, false } };
	private String[] _str_item = new String[] { "半包", "全包", "清包" };

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);
		View _view = inflater.inflate(R.layout.fragment_setinfo, container, false);
		_body = (LinearLayout) _view.findViewById(R.id.linearlayout_content);
		_button_commit = (Button) _view.findViewById(R.id.button_commit);
		_button_commit.setText("保存");
		_button_commit.setOnClickListener(this);

		_date = new RelativeLayout_edit_informationbar(getActivity(), _body, 2, new String[] { "从业年份", "2016" }, true, this);
		_number = new RelativeLayout_edit_informationbar(getActivity(), _body, 3, new String[] { "工队人数", "1", "人" }, true);
		_location = new RelativeLayout_edit_informationbar(getActivity(), _body, 2, new String[] { "办公地址", "" }, true, this);
		_address = new RelativeLayout_edit_informationbar(getActivity(), _body, 5, new String[] { "完善地址", "" }, true);
		_service = new RelativeLayout_edit_informationbar(getActivity(), _body, 2, new String[] { "服务区域", "" }, true, this);
		_serviceItem = new RelativeLayout_edit_informationbar(getActivity(), _body, 2, new String[] { "服务范围", "" }, true, this);
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
			String _temp_str = "provinceId=" + provinceId + "cityId=" + cityId + "countyId=" + countyId;

			switch (requestCode)
			{
			case LOCATION_WORK:
				_location.setData(new String[] { _temp_str });
				break;
			case LOCATION_SERVICE:
				serviceRegion = String.valueOf(provinceId) + "," + String.valueOf(cityId) + "," + String.valueOf(countyId);
				System.out.println("serviceRegion=" + serviceRegion);
				_service.setData(new String[] { serviceRegion });
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
			String _str_service = _serviceItem.getData();
			String _str_number = _number.getData();
			if (_str_service.isEmpty())
			{
				Toast.makeText(getActivity(), "请选择至少一项服务范围", Toast.LENGTH_SHORT).show();
			}
			else if (_str_number.equals("0"))
			{
				Toast.makeText(getActivity(), "工队至少包含1名工人", Toast.LENGTH_SHORT).show();
			}
			else
			{
				StringBuilder _str_builder = new StringBuilder();
				for (int i = 0; i < 3; i++)
				{
					if (_service_select[0][i])
						_str_builder.append(i + ",");
				}
				if (_str_builder.length() > 1)
					_str_builder.deleteCharAt(_str_builder.length() - 1);

				ContentValues _value = new ContentValues();
				_value.put("workYear", _date.getData());
				_value.put("peopleNum", _str_number);
				_value.put("workProvince", provinceId);
				_value.put("workCounty", countyId);
				_value.put("workCity", cityId);
				_value.put("workAddress", _address.getData());
				_value.put("serviceRegion", serviceRegion);
				_value.put("serviceItem", _str_builder.toString());

				Activity_setinfo_shigongdui.get_instance().show_wait();
				HttpUtil.set_PersonalIntroduce(Activity_setinfo_shigongdui._handler, _value, Activity_setinfo_shigongdui.CHANGE_SUCCESSFUL, Activity_setinfo_shigongdui.CHANGE_FAILED);
			}
		}
		else if (_id == _date.get_id())
		{
			final String[] _temp_str = new String[] { "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016" };

			AlertDialog.Builder _builder = new Builder(getActivity());
			_builder.setTitle("从业年份");
			_builder.setItems(_temp_str, new DialogInterface.OnClickListener()
			{
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					_date.setData(new String[] { _temp_str[which] });
				}
			});

			_builder.show();
		}
		else if (_id == _location.get_id())
		{
			Intent _location = new Intent(getActivity(), Activity_location.class);
			startActivityForResult(_location, LOCATION_WORK);
		}
		else if (_id == _service.get_id())
		{
			Intent _location = new Intent(getActivity(), Activity_location.class);
			startActivityForResult(_location, LOCATION_SERVICE);
		}
		else if (_id == _serviceItem.get_id())
		{
			AlertDialog.Builder _builder = new Builder(getActivity());
			_builder.setTitle("服务范围");
			for (int i = 0; i < 3; i++)
				_service_select[1][i] = _service_select[0][i];
			_builder.setMultiChoiceItems(_str_item, _service_select[1], new OnMultiChoiceClickListener()
			{
				@Override
				public void onClick(DialogInterface dialog, int which, boolean isChecked)
				{
					// 这个监听不需要添加代码，但一定要保留，有这个监听_service_select[1]才会被系统更改。
				}
			});
			_builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
			{
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					StringBuilder _builder = new StringBuilder();
					for (int i = 0; i < 3; i++)
					{
						_service_select[0][i] = _service_select[1][i];
						if (_service_select[1][i])
							_builder.append(_str_item[i] + ",");
					}
					if (_builder.length() > 1)
						_builder.deleteCharAt(_builder.length() - 1);
					_serviceItem.setData(new String[] { _builder.toString() });
				}
			});
			_builder.show();
		}
	}

	@Override
	public void setContent()
	{
		UserInfo _info = Activity_my_center.get_userinfo();
		if (_info != null)
		{
			_date.setData(new String[] { String.valueOf(_info.get_workYear()) });
			_number.setData(new String[] { String.valueOf(_info.get_peopleNum()) });
			_address.setData(new String[] { _info.get_workAddress() });

			StringBuilder _builder = new StringBuilder();
			String _temp_str[] = _info.get_serviceItem().split(",");
			for (String string : _temp_str)
			{
				int _temp_num = Integer.parseInt(string);
				if (_temp_num > -1 && _temp_num < 3)
				{
					_builder.append(_str_item[_temp_num] + ",");
					_service_select[0][_temp_num] = true;
				}
			}
			if (_builder.length() > 1)
				_builder.deleteCharAt(_builder.length() - 1);
			_serviceItem.setData(new String[] { _builder.toString() });
		}
	}
}
