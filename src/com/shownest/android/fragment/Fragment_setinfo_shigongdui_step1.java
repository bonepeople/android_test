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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Fragment_setinfo_shigongdui_step1 extends DEBUG_Fragment implements OnClickListener
{
	private static final int REQUEST_PHONE = 1;
	private static final int REQUEST_LOCATION = 2;
	private LinearLayout _body;
	private Button _button_commit;
	private RelativeLayout_edit_informationbar _showname, _phone, _location, _sex;
	private Linearlayout_edittext _edit;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);
		View _view = inflater.inflate(R.layout.fragment_setinfo, container, false);
		_body = (LinearLayout) _view.findViewById(R.id.linearlayout_content);
		_button_commit = (Button) _view.findViewById(R.id.button_commit);
		_button_commit.setText("保存");
		_button_commit.setOnClickListener(this);

		new RelativeLayout_edit_informationbar(getActivity(), _body, 5, new String[] { "身份类型", "施工队" }, false);
		_showname = new RelativeLayout_edit_informationbar(getActivity(), _body, 5, new String[] { "工队昵称", "sn1234" }, true);
		_phone = new RelativeLayout_edit_informationbar(getActivity(), _body, 2, new String[] { "联系手机", CommonUtil.showPhone("15210196421") }, true, this);
		_location = new RelativeLayout_edit_informationbar(getActivity(), _body, 2, new String[] { "工长籍贯", "" }, true, this);
		_sex = new RelativeLayout_edit_informationbar(getActivity(), _body, 6, new String[] { "工长性别", "男", "女", "1" }, false);
		_edit = new Linearlayout_edittext(getActivity(), _body, new String[] { "工队介绍", "简单的说说你们的竞争优势。", "" });

		return _view;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if (resultCode == -1)
		{
			switch (requestCode)
			{
			case REQUEST_PHONE:
				try
				{
					String _result = data.getStringExtra("phone");
					_phone.setData(new String[] { CommonUtil.showPhone(_result) });
				}
				catch (Exception e)
				{
					Toast.makeText(getActivity(), "返回异常", Toast.LENGTH_SHORT).show();
					e.printStackTrace();
				}
				break;
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
			_value.put("userShowName", _showname.getData());
			_value.put("nativePlace", _location.getData());// 需要变更
			_value.put("realSex", _sex.getData());// 需要变更
			_value.put("introduces", _edit.getData());

			Activity_setinfo_shigongdui.get_instance().show_wait();
			HttpUtil.set_PersonalBaseInfor(Activity_setinfo_shigongdui._handler, _value, Activity_setinfo_shigongdui.CHANGE_SUCCESSFUL, Activity_setinfo_shigongdui.CHANGE_FAILED);
		}
		else if (_id == _phone.get_id())
		{
			Intent _change_phone = new Intent(getActivity(), Activity_change_phone.class);
			startActivityForResult(_change_phone, REQUEST_PHONE);
		}
		else if (_id == _location.get_id())
		{
			Intent _location = new Intent(getActivity(), Activity_location.class);
			startActivityForResult(_location, REQUEST_LOCATION);
		}
	}
}
