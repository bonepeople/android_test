package com.shownest.android.fragment;

import com.shownest.android.R;
import com.shownest.android.activity.Activity_location;
import com.shownest.android.activity.Activity_setinfo_shejishi;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.model.UserInfo;
import com.shownest.android.utils.HttpUtil;
import com.shownest.android.utils.UserManager;
import com.shownest.android.widget.LinearLayout_checkbox;
import com.shownest.android.widget.InformationBar;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Fragment_setinfo_shejishi_step2 extends DEBUG_Fragment implements OnClickListener
{
	private static final int LOCATION_WORK = 1;
	private static final int LOCATION_SERVICE = 2;
	private LinearLayout _body;
	private Button _button_commit;
	private InformationBar _date, _location, _address, _service, _serviceItem;
	private LinearLayout_checkbox _style;
	private int cityId = 0, provinceId = 0, countyId = 0;
	private String serviceRegion = "";
	private boolean[][] _service_select = new boolean[][] { { false, false, false }, { false, false, false } };
	private String[] _str_item = new String[] { "设计图服务", "硬装全程设计服务", "硬装软装全程设计服务" };

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);
		View _view = inflater.inflate(R.layout.fragment_basic, container, false);
		_body = (LinearLayout) _view.findViewById(R.id.linearlayout_content);
		_button_commit = (Button) _view.findViewById(R.id.button_commit);
		_button_commit.setText("保存");
		_button_commit.setOnClickListener(this);

		_date = new InformationBar(getActivity(), _body, 2, new String[] { "从业年份", "2016" }, true, this);
		_location = new InformationBar(getActivity(), _body, 2, new String[] { "办公地址", "" }, true, this);
		_address = new InformationBar(getActivity(), _body, 5, new String[] { "完善地址", "" }, true);
		_service = new InformationBar(getActivity(), _body, 2, new String[] { "服务区域", "" }, true, this);
		_serviceItem = new InformationBar(getActivity(), _body, 2, new String[] { "服务范围", "" }, true, this);

		String[] _items = new String[] { "简约", "现代", "中式", "欧式", "美式", "日式", "东南亚", "地中海", "混搭", "新古典", "田园", "其他" };
		_style = new LinearLayout_checkbox(getActivity(), "设计风格(最多选择三项)", _items, 3, "");
		_body.addView(_style);

		return _view;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if (resultCode == -1)
		{
			provinceId = data.getIntExtra("provinceId", 0);
			System.out.println("provinceId=" + provinceId);

			cityId = data.getIntExtra("cityId", 0);
			System.out.println("cityId=" + cityId);

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
			if (_str_service.isEmpty())
			{
				Toast.makeText(getActivity(), "请选择至少一项服务范围", Toast.LENGTH_SHORT).show();
			}
			else
			{
				StringBuilder _str_builder = new StringBuilder();
				for (int i = 0; i < 3; i++)
				{
					if (_service_select[0][i])
						_str_builder.append(String.valueOf(i + 1) + ",");
				}
				if (_str_builder.length() > 1)
					_str_builder.deleteCharAt(_str_builder.length() - 1);

				ContentValues _value = new ContentValues();
				_value.put("workYear", _date.getData());
				_value.put("workProvince", provinceId);
				_value.put("workCounty", countyId);
				_value.put("workCity", cityId);
				_value.put("workAddress", _address.getData());
				_value.put("serviceRegion", serviceRegion);
				_value.put("serviceItem", _str_builder.toString());
				_value.put("designStyle", _style.getData());

				Activity_setinfo_shejishi.get_instance().show_wait();
				HttpUtil.set_PersonalIntroduce(Activity_setinfo_shejishi._handler, _value, Activity_setinfo_shejishi.CHANGE_SUCCESSFUL, Activity_setinfo_shejishi.CHANGE_FAILED);
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
		UserInfo _info = UserManager.get_user_info();
		if (_info != null)
		{
			_date.setData(new String[] { String.valueOf(_info.get_workYear()) });
			_address.setData(new String[] { _info.get_workAddress() });

			StringBuilder _builder = new StringBuilder();
			String _temp_str[] = _info.get_serviceItem().split(",");
			for (String string : _temp_str)
			{
				try
				{
					int _temp_num = Integer.parseInt(string);
					if (_temp_num > 0 && _temp_num < 4)
					{
						_builder.append(_str_item[_temp_num - 1] + ",");
						_service_select[0][_temp_num - 1] = true;
					}
				}
				catch (Exception e)
				{
				}
			}
			if (_builder.length() > 1)
				_builder.deleteCharAt(_builder.length() - 1);
			_serviceItem.setData(new String[] { _builder.toString() });
			_style.setData(_info.get_designStyle());
		}
	}
}
