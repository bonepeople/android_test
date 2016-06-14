package com.shownest.android.fragment;

import com.shownest.android.R;
import com.shownest.android.activity.Activity_bid_detail;
import com.shownest.android.activity.Activity_quota_list;
import com.shownest.android.activity.Activity_toubiao_shejishi;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.model.BidInfo_common;
import com.shownest.android.model.UserInfo;
import com.shownest.android.utils.NumberUtil;
import com.shownest.android.utils.UserManager;
import com.shownest.android.widget.InformationBar;
import com.shownest.android.widget.View_split_h;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment_bid_detail extends DEBUG_Fragment implements OnClickListener
{
	private LinearLayout _body;
	private Button _button_commit, _button_other;
	private int _userType = 100;
	// private LinearLayout_picturebox _picture;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);
		View _view = inflater.inflate(R.layout.fragment_basic, container, false);
		_body = (LinearLayout) _view.findViewById(R.id.linearlayout_content);
		_button_commit = (Button) _view.findViewById(R.id.button_commit);
		_button_other = (Button) _view.findViewById(R.id.button_other);

		UserInfo _info = UserManager.get_user_info();
		if (_info != null)
		{
			_userType = _info.get_userType();
			switch (_userType)
			{
			case 11:
				_button_commit.setVisibility(Button.GONE);
			case 12:
			case 13:
			case 14:
				_button_commit.setText("我要投标");
				_button_commit.setOnClickListener(this);
				_button_other.setVisibility(Button.VISIBLE);
				_button_other.setText("查看全部投标");
				_button_other.setOnClickListener(this);
				break;
			case 15:
			case 100:
				_button_commit.setVisibility(Button.GONE);
				break;
			}
		}
		else
		{
			_button_commit.setVisibility(Button.GONE);
		}

		return _view;
	}

	@Override
	public void setContent()
	{
		BidInfo_common _data = Activity_bid_detail.get_data();
		if (_data != null)
		{
			new InformationBar(getActivity(), _body, 5, new String[] { "小区名称", _data.get_houseName() }, false);
			new InformationBar(getActivity(), _body, 5, new String[] { "小区地址", _data.get_homeRegionName() }, false);
			new InformationBar(getActivity(), _body, 5, new String[] { "房屋类型", _data.get_houseType_name() }, false);
			new InformationBar(getActivity(), _body, 5, new String[] { "房屋状态", _data.get_houseState_name() }, false);
			new InformationBar(getActivity(), _body, 7, new String[] { "建筑面积", String.valueOf(_data.get_homeSq()), " m²" }, false);
			new InformationBar(getActivity(), _body, 4, new String[] { "户型结构", _data.get_rooms() }, false);
			// _picture = new LinearLayout_picturebox(this, "户型图（选填）", false);
			// _picture.add_image("http://t.shownest.com:86/_resources/upload/headerIcon/6dfdd424ea49110400eba7dceb7.jpg");
			// _picture.add_image("http://t.shownest.com:86/_resources/upload/headerIcon/6dfdd424ea49110400eba7dceb7.jpg");
			// _picture.add_image("http://t.shownest.com:86/_resources/upload/headerIcon/6dfdd424ea49110400eba7dceb7.jpg");
			// _body.addView(_picture);
			new InformationBar(getActivity(), _body, 5, new String[] { "业主称呼", _data.get_contacts() }, false);
			new InformationBar(getActivity(), _body, 5, new String[] { "联系方式", _data.get_phone() }, false);
			new View_split_h(getActivity(), _body, 5f);
			new InformationBar(getActivity(), _body, 5, new String[] { "招标类型", _data.get_bookTypeName() }, false);
			if (_data.get_bookType() == 13)
			{
				new InformationBar(getActivity(), _body, 5, new String[] { "装修方式", _data.get_consType_name() }, false);
				new InformationBar(getActivity(), _body, 7, new String[] { "装修预算", String.valueOf(_data.get_budget()), "元" }, false);
			}
			else if (_data.get_bookType() == 12)
			{
				new InformationBar(getActivity(), _body, 7, new String[] { "设计预算", String.valueOf(_data.get_budget()), "元" }, false);
			}
			else
			{
				new InformationBar(getActivity(), _body, 7, new String[] { "监理预算", String.valueOf(_data.get_budget()), "元" }, false);
			}
			new InformationBar(getActivity(), _body, 5, new String[] { "招标状态", _data.get_bidsState_name() }, false);
			new InformationBar(getActivity(), _body, 5, new String[] { "发布时间", _data.get_createDate() }, false);
			new InformationBar(getActivity(), _body, 5, new String[] { "需求描述", "" }, false);

			TextView _idea = new TextView(getActivity());
			_idea.setText(_data.get_ownerIdea());
			int padding = NumberUtil.get_px(getActivity(), 5);
			_idea.setPadding(padding, 0, padding, 0);
			_body.addView(_idea);

			if (_userType != _data.get_bookType())
				_button_commit.setVisibility(Button.GONE);
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		// _picture.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onClick(View v)
	{
		int _id = v.getId();
		if (_id == _button_commit.getId())
		{
			Intent _toubiao = new Intent();
			switch (UserManager.get_user_info().get_userType())
			{
			case 12:
				_toubiao.setClass(getActivity(), Activity_toubiao_shejishi.class);
				break;
			case 13:

			case 14:
				return;
			}
			_toubiao.putExtra("index", Activity_bid_detail.get_index());
			startActivity(_toubiao);
		}
		else if (_id == _button_other.getId())
		{
			Toast.makeText(getActivity(), "查看全部投标", Toast.LENGTH_SHORT).show();
			Intent _quota_list = new Intent(getActivity(), Activity_quota_list.class);
			_quota_list.putExtra("id", Activity_bid_detail.get_data().get_id());
			_quota_list.putExtra("has_detail", false);
			startActivity(_quota_list);
		}
	}
}
