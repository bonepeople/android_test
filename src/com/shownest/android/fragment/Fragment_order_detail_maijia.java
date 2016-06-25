package com.shownest.android.fragment;

import com.shownest.android.R;
import com.shownest.android.activity.Activity_order_detail;
import com.shownest.android.activity.Activity_webview;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.model.OrderInfo;
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
import android.widget.Toast;

public class Fragment_order_detail_maijia extends DEBUG_Fragment implements OnClickListener
{
	private LinearLayout _body;
	private InformationBar _protocol, _quota, _tel;
	private Button _button_commit;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);
		View _view = inflater.inflate(R.layout.fragment_basic, container, false);
		_body = (LinearLayout) _view.findViewById(R.id.linearlayout_content);
		_button_commit = (Button) _view.findViewById(R.id.button_commit);
		_button_commit.setOnClickListener(this);

		return _view;
	}

	@Override
	public void setContent()
	{
		OrderInfo _data = Activity_order_detail.get_data();
		if (_data != null)
		{
			new InformationBar(getActivity(), _body, 5, new String[] { "项目名称", _data.get_protocolName() }, false);
			switch (_data.get_bookType())
			{
			case 12:
				new InformationBar(getActivity(), _body, 5, new String[] { "服务类型", _data.get_desiBiddingTypeId_name() }, false);
				break;
			case 13:
				new InformationBar(getActivity(), _body, 5, new String[] { "装修方式", _data.get_desiBiddingTypeId_name() }, false);
				break;
			case 14:
				new InformationBar(getActivity(), _body, 5, new String[] { "监理类型", _data.get_desiBiddingTypeId_name() }, false);
				break;
			}
			new InformationBar(getActivity(), _body, 5, new String[] { "业主称呼", _data.get_ownerRealName() }, false);
			new InformationBar(getActivity(), _body, 5, new String[] { "签约时间", _data.get_createDate() }, false);
			if (_data.get_currentStageId() != _data.get_stages().size() && _data.get_currentStageState() != 9)
			{
				new View_split_h(getActivity(), _body, 5).set_color(getResources().getColor(R.color.background_main));
				new InformationBar(getActivity(), _body, 5, new String[] { "当前阶段", _data.get_currentStageName() }, false);
				new InformationBar(getActivity(), _body, 5, new String[] { "阶段状态", _data.get_currentStageState_name() }, false);
				new InformationBar(getActivity(), _body, 5, new String[] { "本阶段应收费用", _data.get_stages().get(_data.get_currentStageId()).get_stageMoney() + "元" }, false);
			}
			else
			{
				new InformationBar(getActivity(), _body, 5, new String[] { "协议金额", _data.get_protocolSum() + "元" }, false);
			}
			new View_split_h(getActivity(), _body, 5).set_color(getResources().getColor(R.color.background_main));
			_protocol = new InformationBar(getActivity(), _body, 2, new String[] { "查看协议", "" }, true, this);
			_quota = new InformationBar(getActivity(), _body, 2, new String[] { "查看方案", "" }, true, this);
			_tel = new InformationBar(getActivity(), _body, 2, new String[] { "联系卖家", "" }, true, this);

			switch (_data.get_currentStageState())
			{
			case 2:
			case 7:
			case 8:
				_button_commit.setText("申请验收");
				break;
			default:
				_button_commit.setVisibility(Button.GONE);
			}
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{

	}

	@Override
	public void onClick(View v)
	{
		int _id = v.getId();
		if (_id == R.id.button_commit)
		{
			Toast.makeText(getActivity(), "申请验收", Toast.LENGTH_SHORT).show();
			// String _url = "";
			// Intent _toubiao = new Intent(getActivity(), Activity_webview.class);
			// switch (UserManager.get_user_info().get_userType())
			// {
			// case 12:
			// _url = "http://app.shownest.com/bid/getDesiBidDetail?homeId=" + _bidID + "&ukey=" + UserManager.get_ukey();
			// break;
			// case 13:
			// _url = "http://app.shownest.com/bid/getConsBidDetail?homeId=" + _bidID + "&ukey=" + UserManager.get_ukey();
			// break;
			// case 14:
			// return;
			// }
			// _toubiao.putExtra("url", _url);
			// _toubiao.putExtra("have_title", false);
			// startActivity(_toubiao);
		}
		else if (_id == _protocol.get_id())
		{
			Intent _web = new Intent(getActivity(), Activity_webview.class);
			String _url;
			_url = "http://app.shownest.com/agreement/viewAgreementDetail?protocolId=" + Activity_order_detail.get_data().get_protocolId() + "&ukey=" + UserManager.get_ukey();
			_web.putExtra("url", _url);
			_web.putExtra("have_title", true);
			_web.putExtra("title", "托管协议");
			startActivity(_web);
		}
		else if (_id == _quota.get_id())
		{
			Intent _web = new Intent(getActivity(), Activity_webview.class);
			String _url;
			if (Activity_order_detail.get_data().get_bookType() == 12)
				_url = "http://app.shownest.com/bid/getDesiSelfRespBid?userId=" + UserManager.get_user_info().get_userId() + "&homeId=" + Activity_order_detail.get_data().get_quotationId() + "&ukey="
						+ UserManager.get_ukey();
			else
				_url = "http://app.shownest.com/bid/getConsSelfRespBid?userId=" + UserManager.get_user_info().get_userId() + "&homeId=" + Activity_order_detail.get_data().get_quotationId() + "&ukey="
						+ UserManager.get_ukey();
			_web.putExtra("url", _url);
			_web.putExtra("have_title", true);
			_web.putExtra("title", "报价单详情");
			startActivity(_web);
		}
		else if (_id == _tel.get_id())
		{
			Toast.makeText(getActivity(), "tel", Toast.LENGTH_SHORT).show();
		}
	}
}
