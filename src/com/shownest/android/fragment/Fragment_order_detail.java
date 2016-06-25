package com.shownest.android.fragment;

import com.shownest.android.R;
import com.shownest.android.activity.Activity_order_detail;
import com.shownest.android.activity.Activity_webview;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.model.OrderInfo;
import com.shownest.android.model.OrderStageInfo;
import com.shownest.android.utils.UserManager;
import com.shownest.android.widget.InformationBar;
import com.shownest.android.widget.View_split_h;
import com.shownest.android.widget.Widget_closeable_view;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Fragment_order_detail extends DEBUG_Fragment implements OnClickListener
{
	private LinearLayout _body, _buttons;
	private InformationBar _protocol, _quota, _tel;
	private Button _button_left;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);
		View _view = inflater.inflate(R.layout.fragment_basic, container, false);
		_body = (LinearLayout) _view.findViewById(R.id.linearlayout_content);
		_buttons = (LinearLayout) _view.findViewById(R.id.linearlayout_buttons);
		_buttons.setVisibility(LinearLayout.GONE);

		return _view;
	}

	@Override
	public void setContent()
	{
		OrderInfo _data = Activity_order_detail.get_data();
		if (_data != null)
		{
			new InformationBar(getActivity(), _body, 5, new String[] { "合作卖家", _data.get_providerRealName() }, false);
			new InformationBar(getActivity(), _body, 5, new String[] { "联系方式", _data.get_providerphone() }, false);
			new View_split_h(getActivity(), _body, 5).set_color(getResources().getColor(R.color.background_main));
			_protocol = new InformationBar(getActivity(), _body, 2, new String[] { "查看协议", "" }, true, this);
			_quota = new InformationBar(getActivity(), _body, 2, new String[] { "查看方案", "" }, true, this);
			_tel = new InformationBar(getActivity(), _body, 2, new String[] { "联系卖家", "" }, true, this);
			SparseArray<OrderStageInfo> _stages = _data.get_stages();
			for (int _temp_i = 0; _temp_i < _stages.size(); _temp_i++)
			{
				LinearLayout _stageView = new LinearLayout(getActivity());
				_stageView.setOrientation(LinearLayout.VERTICAL);
				LinearLayout.LayoutParams _param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
				_stageView.setLayoutParams(_param);

				new InformationBar(getActivity(), _stageView, 5, new String[] { "阶段名称", _stages.get(_temp_i + 1).get_stageName() }, false);
				new InformationBar(getActivity(), _stageView, 5, new String[] { "阶段状态", _stages.get(_temp_i + 1).get_stageState_name() }, false);
				new InformationBar(getActivity(), _stageView, 5, new String[] { "本阶段应托管", _stages.get(_temp_i + 1).get_stageMoney() + "元" }, false);

				if (_data.get_currentStageId() == _stages.get(_temp_i + 1).get_stageId())
				{
					LayoutInflater.from(getActivity()).inflate(R.layout.widget_buttonbar, _stageView);
					_button_left = (Button) _stageView.findViewById(R.id.button_commit);
					_button_left.setText("去托管");
					_button_left.setOnClickListener(this);
					Widget_closeable_view _view = new Widget_closeable_view(getActivity(), _body, new String[] { _stages.get(_temp_i + 1).get_stageId_name(), "" }, _stageView);
					_view.set_collapse(false);
				}
				else
				{
					new Widget_closeable_view(getActivity(), _body, new String[] { _stages.get(_temp_i + 1).get_stageId_name(), "" }, _stageView);
				}
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
			Toast.makeText(getActivity(), "commit", Toast.LENGTH_SHORT).show();
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
