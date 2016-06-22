package com.shownest.android.fragment;

import com.shownest.android.R;
import com.shownest.android.activity.Activity_order_detail;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.model.OrderInfo;
import com.shownest.android.model.OrderStageInfo;
import com.shownest.android.widget.InformationBar;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class Fragment_order_detail extends DEBUG_Fragment implements OnClickListener
{
	private LinearLayout _body, _buttons;

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
				_body.addView(_stageView);
			}
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
		// int _id = v.getId();
		// if (_id == _button_commit.getId())
		// {
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
		// }
		// else if (_id == _button_other.getId())
		// {
		// Intent _quota_list = new Intent(getActivity(), Activity_quota_list.class);
		// _quota_list.putExtra("id", _bidID);
		// _quota_list.putExtra("type", Activity_bid_detail.get_data().get_bookType());
		// _quota_list.putExtra("have_detail", false);
		// startActivity(_quota_list);
		// }
	}
}
