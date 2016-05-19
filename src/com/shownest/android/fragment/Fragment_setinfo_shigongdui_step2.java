package com.shownest.android.fragment;

import com.shownest.android.R;
import com.shownest.android.activity.Activity_change_phone;
import com.shownest.android.activity.Activity_location;
import com.shownest.android.activity.Activity_setinfo_shigongdui;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.utils.CommonUtil;
import com.shownest.android.utils.HttpUtil;
import com.shownest.android.widget.Linearlayout_edittext;
import com.shownest.android.widget.RelativeLayout_edit_informationbar;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Fragment_setinfo_shigongdui_step2 extends DEBUG_Fragment implements OnClickListener
{
	private static final int REQUEST_LOCATION = 2;
	private LinearLayout _body;
	private Button _button_commit;
	private RelativeLayout_edit_informationbar _date, _number, _location, _address, _service, _serviceItem;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);
		View _view = inflater.inflate(R.layout.fragment_setinfo, container, false);
		_body = (LinearLayout) _view.findViewById(R.id.linearlayout_content);
		_button_commit = (Button) _view.findViewById(R.id.button_commit);
		_button_commit.setText("保存");
		_button_commit.setOnClickListener(this);

		_date = new RelativeLayout_edit_informationbar(getActivity(), _body, 2, new String[] { "从业年份", "" }, true, this);
		_number = new RelativeLayout_edit_informationbar(getActivity(), _body, 3, new String[] { "工队人数", "", "人" }, true);
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
			switch (requestCode)
			{
			case REQUEST_LOCATION:
				System.out.println("cityId=" + data.getIntExtra("cityId", 0));
				System.out.println("provinceId=" + data.getIntExtra("provinceId", 0));
				System.out.println("countyId=" + data.getIntExtra("countyId", 0));
				break;
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
			_value.put("workYear", _date.getData());// 需要变更
			_value.put("peopleNum", _number.getData());// 需要变更
			_value.put("workProvince", _location.getData());// 需要变更
			_value.put("workCounty", _location.getData());// 需要变更
			_value.put("workCity", _location.getData());// 需要变更
			_value.put("workAddress", _address.getData());// 需要变更
			_value.put("serviceRegion", _service.getData());// 需要变更
			_value.put("serviceItem", _serviceItem.getData());// 需要变更

			Activity_setinfo_shigongdui.get_instance().show_wait();
			HttpUtil.set_PersonalIntroduce(Activity_setinfo_shigongdui._handler, _value, Activity_setinfo_shigongdui.CHANGE_SUCCESSFUL, Activity_setinfo_shigongdui.CHANGE_FAILED);
		
		}
		else if (_id == _date.get_id())
		{
		}
		else if (_id == _location.get_id())
		{
			Intent _location = new Intent(getActivity(), Activity_location.class);
			startActivityForResult(_location, REQUEST_LOCATION);
		}
		else if (_id == _service.get_id())
		{
		}
	}
}
