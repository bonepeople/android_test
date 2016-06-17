package com.shownest.android.fragment;

import com.loopj.android.image.SmartImageView;
import com.shownest.android.R;
import com.shownest.android.activity.Activity_basicinfo;
import com.shownest.android.activity.Activity_my_bid_maijia;
import com.shownest.android.activity.Activity_my_bid_yezhu;
import com.shownest.android.activity.Activity_select_role;
import com.shownest.android.activity.Activity_webview;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.model.UserInfo;
import com.shownest.android.utils.CommonUtil;
import com.shownest.android.utils.UserManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment_my_center extends DEBUG_Fragment implements View.OnClickListener
{
	private TextView _textview_money, _textview_name, _number_bowen, _number_guanzhu, _number_xiuyou, _textview_hint;
	private TextView _zhaobiao, _zhaobiao_comment, _yuebiao_comment;
	private LinearLayout _line1, _line2, _line3;
	private RelativeLayout _item_bid, _item_offer, _item_zhuangxiu, _item_fangwu, _item_baojia, _item_gongdi, _item_info, _item_money;
	private SmartImageView _imageview_header;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);

		View _view = inflater.inflate(R.layout.fragment_my_center, container, false);
		_textview_money = (TextView) _view.findViewById(R.id.textview_money);
		_textview_name = (TextView) _view.findViewById(R.id.textview_name);
		_number_bowen = (TextView) _view.findViewById(R.id.textview_number_bowen);
		_number_guanzhu = (TextView) _view.findViewById(R.id.textview_number_guanzhu);
		_number_xiuyou = (TextView) _view.findViewById(R.id.textview_number_xiuyou);
		_textview_hint = (TextView) _view.findViewById(R.id.textview_hint);
		_line1 = (LinearLayout) _view.findViewById(R.id.linearlayout_center_line1);
		_line2 = (LinearLayout) _view.findViewById(R.id.linearlayout_center_line2);
		_line3 = (LinearLayout) _view.findViewById(R.id.linearlayout_center_line3);
		_item_bid = (RelativeLayout) _view.findViewById(R.id.item_bid);
		_item_offer = (RelativeLayout) _view.findViewById(R.id.item_offer);
		_item_zhuangxiu = (RelativeLayout) _view.findViewById(R.id.item_zhuangxiu);
		_item_fangwu = (RelativeLayout) _view.findViewById(R.id.item_fangwu);
		_item_baojia = (RelativeLayout) _view.findViewById(R.id.item_baojia);
		_item_gongdi = (RelativeLayout) _view.findViewById(R.id.item_gongdi);
		_item_info = (RelativeLayout) _view.findViewById(R.id.item_info);
		_item_money = (RelativeLayout) _view.findViewById(R.id.item_money);
		_zhaobiao = (TextView) _view.findViewById(R.id.textview_name_bid);
		_zhaobiao_comment = (TextView) _view.findViewById(R.id.textview_comment_bid);
		_yuebiao_comment = (TextView) _view.findViewById(R.id.textview_comment_offer);
		_imageview_header = (SmartImageView) _view.findViewById(R.id.imageview_header);

		_item_bid.setOnClickListener(this);
		_item_offer.setOnClickListener(this);
		_item_zhuangxiu.setOnClickListener(this);
		_item_fangwu.setOnClickListener(this);
		_item_baojia.setOnClickListener(this);
		_item_gongdi.setOnClickListener(this);
		_item_info.setOnClickListener(this);
		_item_money.setOnClickListener(this);

		return _view;
	}

	@Override
	public void setContent()
	{
		UserInfo _info = UserManager.get_user_info();
		_textview_money.setText(String.valueOf(_info.get_money()));
		_textview_name.setText(_info.get_userName());
		_number_bowen.setText(String.valueOf(_info.get_blogNum()));
		_number_guanzhu.setText(String.valueOf(_info.get_bookNum()));
		_number_xiuyou.setText(String.valueOf(_info.get_fansNum()));
		_imageview_header.setImageUrl(CommonUtil.getUserHeaderIconUrl(_info.get_userType(), _info.get_headerIcon()));

		switch (_info.get_userType())
		{
		case 11:
			_line1.setVisibility(LinearLayout.VISIBLE);
			_line2.setVisibility(LinearLayout.VISIBLE);
			break;
		case 12:
		case 13:
		case 14:
		case 15:
			_line1.setVisibility(LinearLayout.VISIBLE);
			_line3.setVisibility(LinearLayout.VISIBLE);
			_zhaobiao.setText("我的投标");
			_zhaobiao_comment.setText("查看您的投标信息，建立托管协议");
			_yuebiao_comment.setText("查看业主对您发起的预约，建立托管协议");
			break;
		case 100:
			_textview_hint.setVisibility(TextView.VISIBLE);
			_textview_hint.setOnClickListener(this);
			break;
		}
	}

	@Override
	public void onClick(View v)
	{
		Intent _web = new Intent(getActivity(), Activity_webview.class);
		String _url;
		switch (v.getId())
		{
		case R.id.textview_hint:
			Intent _select_role = new Intent(getActivity(), Activity_select_role.class);
			getActivity().startActivity(_select_role);
			break;

		case R.id.item_bid:
			Intent _bid = new Intent();
			if (UserManager.get_user_info().get_userType() == 11)
				_bid.setClass(getActivity(), Activity_my_bid_yezhu.class);
			else
				_bid.setClass(getActivity(), Activity_my_bid_maijia.class);
			startActivity(_bid);
			break;

		case R.id.item_offer:
			Toast.makeText(getActivity(), "约标", Toast.LENGTH_SHORT).show();
			break;

		case R.id.item_zhuangxiu:
			Toast.makeText(getActivity(), "装修", Toast.LENGTH_SHORT).show();
			break;

		case R.id.item_fangwu:
			_url = "http://app.shownest.com/house/getHouseList?ukey=" + UserManager.get_ukey();
			_web.putExtra("url", _url);
			_web.putExtra("had_title", true);
			_web.putExtra("title", "我的房屋");
			startActivity(_web);
			break;

		case R.id.item_baojia:
			switch (UserManager.get_user_info().get_userType())
			{
			case 12:
				_url = "http://app.shownest.com/shuttering/getDesiShutterList?ukey=" + UserManager.get_ukey();
				break;
			case 13:
				_url = "http://app.shownest.com/shuttering/getConsShutterList?ukey=" + UserManager.get_ukey();
				break;
			case 14:
				_url = "http://app.shownest.com/shuttering/getSupShutterList?ukey=" + UserManager.get_ukey();
				break;
			default:
				return;
			}
			_web.putExtra("url", _url);
			_web.putExtra("had_title", true);
			_web.putExtra("title", "报价模板");
			startActivity(_web);
			break;

		case R.id.item_gongdi:
			Toast.makeText(getActivity(), "工地", Toast.LENGTH_SHORT).show();
			break;
		case R.id.item_info:
			Intent _my_info = new Intent(getActivity(), Activity_basicinfo.class);
			getActivity().startActivity(_my_info);
			break;

		case R.id.item_money:
			Toast.makeText(getActivity(), "钱包", Toast.LENGTH_SHORT).show();
			break;
		}
	}
}
