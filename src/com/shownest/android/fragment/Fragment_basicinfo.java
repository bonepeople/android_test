package com.shownest.android.fragment;

import com.loopj.android.image.SmartImageView;
import com.shownest.android.R;
import com.shownest.android.activity.Activity_change_phone;
import com.shownest.android.activity.Activity_change_pwd;
import com.shownest.android.activity.Activity_select_role;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.model.UserInfo;
import com.shownest.android.utils.UserManager;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment_basicinfo extends DEBUG_Fragment implements View.OnClickListener
{
	private TextView _name, _showname, _role, _phone;
	private SmartImageView _imageview_header;
	private RelativeLayout _item_name, _item_password, _item_role, _item_phone;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);

		View _view = inflater.inflate(R.layout.fragment_basicinfo, container, false);
		_name = (TextView) _view.findViewById(R.id.textview_name);
		_showname = (TextView) _view.findViewById(R.id.textview_showname);
		_role = (TextView) _view.findViewById(R.id.textview_role);
		_phone = (TextView) _view.findViewById(R.id.textview_phone);
		_imageview_header = (SmartImageView) _view.findViewById(R.id.imageview_header);
		_item_name = (RelativeLayout) _view.findViewById(R.id.relativelayout_name);
		_item_password = (RelativeLayout) _view.findViewById(R.id.relativelayout_password);
		_item_role = (RelativeLayout) _view.findViewById(R.id.relativelayout_role);
		_item_phone = (RelativeLayout) _view.findViewById(R.id.relativelayout_phone);

		return _view;
	}

	@Override
	public void setContent()
	{
		UserInfo _info = UserManager.get_user_info();
		if (_info != null)
		{
			String _url = "http://t.shownest.com:86/_resources/upload/headerIcon/" + _info.get_headerIcon();
			_imageview_header.setImageUrl(_url);
			_name.setText(String.valueOf(_info.get_userName()));
			_showname.setText(_info.get_userShowName());
			_phone.setText(_info.get_userPhone());

			_imageview_header.setOnClickListener(this);
			if (_info.is_checkUsername())
				_name.setTextColor(getResources().getColor(R.color.text_gray));
			else
				_item_name.setOnClickListener(this);
			_item_password.setOnClickListener(this);
			_role.setTextColor(getResources().getColor(R.color.text_gray));
			switch (_info.get_userType())
			{
			case 11:
				_role.setText("业主");
				break;
			case 12:
				_role.setText("设计师");
				break;
			case 13:
				_role.setText("施工队");
				break;
			case 14:
				_role.setText("监理");
				break;
			case 15:
				_role.setText("装修公司");
				break;
			case 100:
				_role.setTextColor(getResources().getColor(R.color.main_color));
				_item_role.setOnClickListener(this);
				break;
			}
			_item_phone.setOnClickListener(this);
		}
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.imageview_header:
			Toast.makeText(getActivity(), "修改头像", Toast.LENGTH_SHORT).show();
			break;

		case R.id.relativelayout_name:
			Toast.makeText(getActivity(), "修改用户名", Toast.LENGTH_SHORT).show();
			break;

		case R.id.relativelayout_password:
			Intent _change_password = new Intent(getActivity(), Activity_change_pwd.class);
			getActivity().startActivity(_change_password);
			break;

		case R.id.relativelayout_role:
			Intent _select_role = new Intent(getActivity(), Activity_select_role.class);
			getActivity().startActivity(_select_role);
			break;

		case R.id.relativelayout_phone:
			AlertDialog.Builder _builder = new Builder(getActivity());
			_builder.setMessage("更换手机号？");
			_builder.setPositiveButton("确定", new OnClickListener()
			{

				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					Intent _change_phone = new Intent(Fragment_basicinfo.this.getActivity(), Activity_change_phone.class);
					Fragment_basicinfo.this.getActivity().startActivity(_change_phone);
				}
			});
			_builder.setNegativeButton("取消", null);
			_builder.show();
			break;
		}

	}
}
