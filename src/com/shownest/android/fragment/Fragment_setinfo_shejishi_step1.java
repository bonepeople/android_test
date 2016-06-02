package com.shownest.android.fragment;

import com.shownest.android.R;
import com.shownest.android.activity.Activity_change_phone;
import com.shownest.android.activity.Activity_setinfo_shejishi;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.model.UserInfo;
import com.shownest.android.utils.CommonUtil;
import com.shownest.android.utils.HttpUtil;
import com.shownest.android.utils.UserManager;
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
import android.widget.LinearLayout;
import android.widget.Toast;

public class Fragment_setinfo_shejishi_step1 extends DEBUG_Fragment implements OnClickListener
{
	private static final int REQUEST_PHONE = 1;
	private LinearLayout _body;
	private Button _button_commit;
	private RelativeLayout_edit_informationbar _showname, _phone, _sex;
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

		new RelativeLayout_edit_informationbar(getActivity(), _body, 5, new String[] { "身份类型", "设计师" }, false);
		_showname = new RelativeLayout_edit_informationbar(getActivity(), _body, 5, new String[] { "秀巢昵称", "" }, true);
		_phone = new RelativeLayout_edit_informationbar(getActivity(), _body, 2, new String[] { "联系手机", "" }, true, this);
		_sex = new RelativeLayout_edit_informationbar(getActivity(), _body, 6, new String[] { "设计师性别", "男", "女", "1" }, false);
		_edit = new Linearlayout_edittext(getActivity(), _body, new String[] { "自我介绍", "简单的说说你的竞争优势。", "" });

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
			ContentValues _value = new ContentValues();
			_value.put("userShowName", _showname.getData());
			_value.put("realSex", _sex.getData().equals("男") ? 1 : 0);
			_value.put("introduces", _edit.getData());

			Activity_setinfo_shejishi.get_instance().show_wait();
			HttpUtil.set_PersonalBaseInfor(Activity_setinfo_shejishi._handler, _value, Activity_setinfo_shejishi.CHANGE_SUCCESSFUL, Activity_setinfo_shejishi.CHANGE_FAILED);
		}
		else if (_id == _phone.get_id())
		{
			Intent _change_phone = new Intent(getActivity(), Activity_change_phone.class);
			startActivityForResult(_change_phone, REQUEST_PHONE);
		}
	}

	@Override
	public void setContent()
	{
		UserInfo _info = UserManager.get_user_info();
		if (_info != null)
		{
			_showname.setData(new String[] { _info.get_userShowName() });
			_phone.setData(new String[] { CommonUtil.showPhone(_info.get_userPhone()) });
			_sex.setData(new String[] { _info.get_realSex() == 1 ? "1" : "2" });
			_edit.setData(_info.get_introduces());
		}
	}
}
