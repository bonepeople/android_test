package com.shownest.android.fragment;

import com.shownest.android.R;
import com.shownest.android.activity.Activity_change_phone;
import com.shownest.android.activity.Activity_my_center;
import com.shownest.android.activity.Activity_setinfo_shigongdui;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.model.UserInfo;
import com.shownest.android.utils.CommonUtil;
import com.shownest.android.utils.HttpUtil;
import com.shownest.android.widget.Linearlayout_edittext;
import com.shownest.android.widget.RelativeLayout_edit_informationbar;

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
import android.widget.LinearLayout;
import android.widget.Toast;

public class Fragment_setinfo_shigongdui_step1 extends DEBUG_Fragment implements OnClickListener
{
	private static final int REQUEST_PHONE = 1;
	private LinearLayout _body;
	private Button _button_commit;
	private RelativeLayout_edit_informationbar _showname, _phone, _location, _sex;
	private Linearlayout_edittext _edit;
	private int[] _PlaceID = new int[] { 1, 1013, 1012, 1011, 1018, 1019, 1016, 1006, 1022, 1004, 1005, 1015, -1 };
	private String[] _place = new String[] { "安徽", "浙江", "江苏", "湖北", "湖南", "山东", "山西", "四川", "重庆", "河北", "江西", "其他" };
	// { "安徽", "浙江", "江苏", "湖北", "湖南", "山东", "山西", "四川", "重庆", "河北", "江西", "其他" };

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
		_showname = new RelativeLayout_edit_informationbar(getActivity(), _body, 5, new String[] { "工队昵称", "" }, true);
		_phone = new RelativeLayout_edit_informationbar(getActivity(), _body, 2, new String[] { "联系手机", "" }, true, this);
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
			String _str_place = _location.getData();
			if (_str_place.isEmpty())
			{
				Toast.makeText(getActivity(), "请选择籍贯", Toast.LENGTH_SHORT).show();
			}
			else
			{
				ContentValues _value = new ContentValues();
				_value.put("userShowName", _showname.getData());
				_value.put("nativePlace", _PlaceID[_PlaceID[0]]);
				_value.put("realSex", _sex.getData().equals("男") ? 1 : 0);
				_value.put("introduces", _edit.getData());

				Activity_setinfo_shigongdui.get_instance().show_wait();
				HttpUtil.set_PersonalBaseInfor(Activity_setinfo_shigongdui._handler, _value, Activity_setinfo_shigongdui.CHANGE_SUCCESSFUL, Activity_setinfo_shigongdui.CHANGE_FAILED);
			}
		}
		else if (_id == _phone.get_id())
		{
			Intent _change_phone = new Intent(getActivity(), Activity_change_phone.class);
			startActivityForResult(_change_phone, REQUEST_PHONE);
		}
		else if (_id == _location.get_id())
		{

			AlertDialog.Builder _builder = new Builder(getActivity());
			_builder.setTitle("工长籍贯");
			_builder.setItems(_place, new DialogInterface.OnClickListener()
			{
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					_PlaceID[0] = which + 1;
					_location.setData(new String[] { _place[which] });
				}
			});

			_builder.show();
		}
	}

	@Override
	public void setContent()
	{
		UserInfo _info = Activity_my_center.get_userinfo();
		_showname.setData(new String[] { _info.get_userShowName() });
		_phone.setData(new String[] { CommonUtil.showPhone(_info.get_userPhone()) });
		int location = _info.get_nativePlace();
		for (int i = 0; i < _PlaceID.length; i++)
			if (location == _PlaceID[i])
			{
				_location.setData(new String[] { _place[i - 1] });
				break;
			}
		_sex.setData(new String[] { _info.get_realSex() == 1 ? "1" : "2" });
		_edit.setData(_info.get_introduces());
	}

}
