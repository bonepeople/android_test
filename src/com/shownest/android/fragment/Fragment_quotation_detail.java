package com.shownest.android.fragment;

import java.util.HashMap;

import com.shownest.android.R;
import com.shownest.android.activity.Activity_quotation_detail;
import com.shownest.android.adapter.Adapter_quotation_detail;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.model.RoomDetail;
import com.shownest.android.widget.Widget_listview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class Fragment_quotation_detail extends DEBUG_Fragment implements OnClickListener
{
	private LinearLayout _body;
	private Button _button_commit;
	private HashMap<String, Adapter_quotation_detail> _adapters = new HashMap<String, Adapter_quotation_detail>();
	private HashMap<String, Widget_listview> _lists = new HashMap<String, Widget_listview>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);
		View _view = inflater.inflate(R.layout.fragment_basic, container, false);
		_body = (LinearLayout) _view.findViewById(R.id.linearlayout_content);
		_button_commit = (Button) _view.findViewById(R.id.button_commit);
		_button_commit.setText("保存");
		_button_commit.setOnClickListener(this);
		return _view;
	}

	@Override
	public void setContent()
	{
		RoomDetail _data = Activity_quotation_detail.get_data();
		if (_data != null)
		{
			switch (Activity_quotation_detail.get_room())
			{
			case "tax":
				set_list(_data, "tax", "管理费和税金");
				break;

			default:
				set_list(_data, "ground", "地面");
				set_list(_data, "wall", "墙面");
				set_list(_data, "roof", "顶面");
				set_list(_data, "hydropower", "水电(按面积)");
				set_list(_data, "mount", "安装");
				set_list(_data, "cost", "施工杂费");
				break;
			}
		}
	}

	private void set_list(RoomDetail _data, String _part, String _name)
	{
		if (_data.has_details(_part))
		{
			Adapter_quotation_detail _adapter = new Adapter_quotation_detail(getActivity(), _data.get_details(_part));
			Widget_listview _list = new Widget_listview(getActivity(), _body, new String[] { _name, "小计：" + _data.get_totals(_part) + "元" }, _adapter);
			_adapter.setOnChangetListener("change " + _part, Activity_quotation_detail.get_instance());
			_list.set_change("增减工艺", "fix " + _part, Activity_quotation_detail.get_instance());
			_list.set_dividerheight(30);
			_list.set_collapse(false);
			_adapters.put(_part, _adapter);
			_lists.put(_part, _list);
		}
	}

	public void refresh(String _part, String _hint)
	{
		_adapters.get(_part).notifyDataSetChanged();
		_lists.get(_part).set_hint(_hint);
	}

	@Override
	public void onClick(View v)
	{
		int _id = v.getId();
		if (_id == R.id.button_commit)
		{
			Activity_quotation_detail.get_instance().finish();
		}
	}

}
