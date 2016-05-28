package com.shownest.android.fragment;

import com.shownest.android.R;
import com.shownest.android.activity.Activity_quotation_detail;
import com.shownest.android.adapter.Adapter_quotation_detail;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.model.RoomDetail;
import com.shownest.android.widget.Linearlayout_listview;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Fragment_quotation_change extends DEBUG_Fragment implements OnClickListener
{
	private static final int LOCATION = 1;
	private LinearLayout _body;
	private Button _button_commit;
	private Adapter_quotation_detail _adapter;
	private Linearlayout_listview _list;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);
		View _view = inflater.inflate(R.layout.fragment_basic, container, false);
		_body = (LinearLayout) _view.findViewById(R.id.linearlayout_content);
		_button_commit = (Button) _view.findViewById(R.id.button_commit);
		_button_commit.setText("确定");
		_button_commit.setOnClickListener(this);
		return _view;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if (resultCode == -1)
		{
			switch (requestCode)
			{
			case LOCATION:
			}
		}
		else
			System.out.println("resultCode=" + resultCode);
	}

	@Override
	public void setContent()
	{
		RoomDetail _data = Activity_quotation_detail.get_data();
		if (_data != null)
		{
			switch (Activity_quotation_detail.get_type())
			{
			case "tax":
				break;

			default:
				if (_data.get_ground().size() != 0)
				{
					_adapter = new Adapter_quotation_detail(getActivity(), "ground", _data.get_ground());
					_list = new Linearlayout_listview(getActivity(), _body, "ground", new String[] { "地面", "小计：" + _data.get_groundTotals() + "元" }, _adapter);
					_adapter.setOnChangetListener(Activity_quotation_detail.get_instance());
					_list.setOnChangetListener(Activity_quotation_detail.get_instance());
					_list.set_change("增减工艺");
				}
				if (_data.get_wall().size() != 0)
				{
					_adapter = new Adapter_quotation_detail(getActivity(), "wall", _data.get_wall());
					_list = new Linearlayout_listview(getActivity(), _body, "wall", new String[] { "墙面", "小计：" + _data.get_wallTotals() + "元" }, _adapter);
					_adapter.setOnChangetListener(Activity_quotation_detail.get_instance());
					_list.setOnChangetListener(Activity_quotation_detail.get_instance());
					_list.set_change("增减工艺");
				}
				if (_data.get_roof().size() != 0)
				{
					_adapter = new Adapter_quotation_detail(getActivity(), "roof", _data.get_roof());
					_list = new Linearlayout_listview(getActivity(), _body, "roof", new String[] { "顶面", "小计：" + _data.get_roofTotals() + "元" }, _adapter);
					_adapter.setOnChangetListener(Activity_quotation_detail.get_instance());
					_list.setOnChangetListener(Activity_quotation_detail.get_instance());
					_list.set_change("增减工艺");
				}
				if (_data.get_hydropower().size() != 0)
				{
					_adapter = new Adapter_quotation_detail(getActivity(), "hydropower", _data.get_hydropower());
					_list = new Linearlayout_listview(getActivity(), _body, "hydropower", new String[] { "水电", "小计：" + _data.get_hydropowerTotals() + "元" }, _adapter);
					_adapter.setOnChangetListener(Activity_quotation_detail.get_instance());
					_list.setOnChangetListener(Activity_quotation_detail.get_instance());
					_list.set_change("增减工艺");
				}
				if (_data.get_mount().size() != 0)
				{
					_adapter = new Adapter_quotation_detail(getActivity(), "mount", _data.get_mount());
					_list = new Linearlayout_listview(getActivity(), _body, "mount", new String[] { "安装", "小计：" + _data.get_mountTotals() + "元" }, _adapter);
					_adapter.setOnChangetListener(Activity_quotation_detail.get_instance());
					_list.setOnChangetListener(Activity_quotation_detail.get_instance());
					_list.set_change("增减工艺");
				}
				if (_data.get_cost().size() != 0)
				{
					_adapter = new Adapter_quotation_detail(getActivity(), "cost", _data.get_cost());
					_list = new Linearlayout_listview(getActivity(), _body, "cost", new String[] { "杂费", "小计：" + _data.get_costTotals() + "元" }, _adapter);
					_adapter.setOnChangetListener(Activity_quotation_detail.get_instance());
					_list.setOnChangetListener(Activity_quotation_detail.get_instance());
					_list.set_change("增减工艺");
				}

				break;
			}
		}
	}

	@Override
	public void onClick(View v)
	{
		int _id = v.getId();
		if (_id == R.id.button_commit)
		{
			// Activity_quotation_detail.get_instance().finish();
			Toast.makeText(getActivity(), "tijiao", Toast.LENGTH_SHORT).show();
		}
	}

}
