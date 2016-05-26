package com.shownest.android.fragment;

import java.util.ArrayList;

import com.shownest.android.R;
import com.shownest.android.activity.Activity_my_center;
import com.shownest.android.activity.Activity_offer_auto;
import com.shownest.android.activity.Activity_quotation_detail;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.model.OfferBill;
import com.shownest.android.model.Package;
import com.shownest.android.model.UserInfo;
import com.shownest.android.utils.NumberUtil;
import com.shownest.android.widget.RelativeLayout_edit_informationbar;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Fragment_offer_auto_show extends DEBUG_Fragment implements OnClickListener
{
	private static final int LOCATION = 1;
	private LinearLayout _body, _buttons;
	private Button _button_commit, _button_other;
	private String _quotationId = "";
	private SparseArray<Package> _items = new SparseArray<Package>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);
		View _view = inflater.inflate(R.layout.fragment_basic, container, false);

		UserInfo _user = Activity_my_center.get_userinfo();
		_body = (LinearLayout) _view.findViewById(R.id.linearlayout_content);
		_buttons = (LinearLayout) _view.findViewById(R.id.linearlayout_buttons);

		if (_user != null && _user.get_userType() != 11)
		{
			_buttons.setVisibility(LinearLayout.GONE);
		}
		else
		{
			_button_commit = (Button) _view.findViewById(R.id.button_commit);
			_button_commit.setText("发布招标");
			_button_commit.setOnClickListener(this);
			_button_other = (Button) _view.findViewById(R.id.button_other);
			_button_other.setText("保存");
			_button_other.setVisibility(Button.VISIBLE);
			_button_other.setOnClickListener(this);
		}

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
	public void onClick(View v)
	{
		int _id = v.getId();
		if (_id == R.id.button_commit)
		{
//			ContentValues _value = new ContentValues();

			Activity_offer_auto.get_instance().show_wait();
			// HttpUtil.get_ownerquote(Activity_offer_auto._handler, _value, Activity_offer_auto.NEXT_SUCCESSFUL, Activity_offer_auto.NEXT_FAILED);

		}
		else if (_id == R.id.button_other)
		{
			// Intent _location = new Intent(getActivity(), Activity_location.class);
			// startActivityForResult(_location, LOCATION);
		}
		else
		{
			Package _package = _items.get(_id);
			Intent _select = new Intent(getActivity(), Activity_quotation_detail.class);

			_select.putExtra("id", _quotationId);
			_select.putExtra("part", _package._tag1);
			_select.putExtra("name", _package._tag2);
			_select.putExtra("index", _package._int1);
			startActivity(_select);
		}
	}

	@Override
	public void setContent()
	{
		OfferBill _data = Activity_offer_auto.get_data();
		if (_data != null)
		{
			_quotationId = _data.get_quotationId();
			RelativeLayout_edit_informationbar _temp_bar;
			_temp_bar = new RelativeLayout_edit_informationbar(getActivity(), _body, 3, new String[] { "总报价", String.valueOf(_data.get_allTotal()), " 元" }, false);
			_temp_bar.set_textcolor(getResources().getColor(R.color.main_color));
			_items.put(_temp_bar.get_id(), new Package(_temp_bar, "all", "总报价", 1));
			set_part(_data.get_room(), "卧室", "room");
			set_part(_data.get_parlour(), "客厅", "parlour");
			set_part(_data.get_kitchen(), "厨房", "kitchen");
			set_part(_data.get_toilet(), "卫生间", "toilet");
			set_part(_data.get_balcony(), "阳台", "balcony");
			_temp_bar = new RelativeLayout_edit_informationbar(getActivity(), _body, 2, new String[] { "水电", String.valueOf(_data.get_hydropowerTotal()) + " 元" }, true, this);
			_items.put(_temp_bar.get_id(), new Package(_temp_bar, "hydropower", "水电", 1));
			_temp_bar = new RelativeLayout_edit_informationbar(getActivity(), _body, 2, new String[] { "安装", String.valueOf(_data.get_mountTotal()) + " 元" }, true, this);
			_items.put(_temp_bar.get_id(), new Package(_temp_bar, "mount", "安装", 1));
			_temp_bar = new RelativeLayout_edit_informationbar(getActivity(), _body, 2, new String[] { "杂费", String.valueOf(_data.get_costTotal()) + " 元" }, true, this);
			_items.put(_temp_bar.get_id(), new Package(_temp_bar, "cost", "杂费", 1));
			_temp_bar = new RelativeLayout_edit_informationbar(getActivity(), _body, 2, new String[] { "税费", String.valueOf(_data.get_taxTotal()) + " 元" }, true, this);
			_items.put(_temp_bar.get_id(), new Package(_temp_bar, "tax", "税费", 1));

			int padding = NumberUtil.get_px(getActivity(), 5);
			TextView _hint = new TextView(getActivity());
			_hint.setText("以上报价仅供参考，以实际工长报价为主");
			_hint.setTextColor(Color.parseColor("#3333aa"));
			_hint.setPadding(padding, padding, padding, padding);
			_body.addView(_hint);

		}
	}

	private void set_part(ArrayList<Double> _parts, String _part, String _tag)
	{
		for (int _temp_i = 0; _temp_i < _parts.size(); _temp_i++)
		{
			String _name = _part;
			if (_parts.size() != 1)
				_name = _name + (_temp_i + 1);
			String _value = String.valueOf(_parts.get(_temp_i)) + " 元";
			RelativeLayout_edit_informationbar _room = new RelativeLayout_edit_informationbar(getActivity(), _body, 2, new String[] { _name, _value }, true, this);
			_items.put(_room.get_id(), new Package(_room, _tag, _name, _temp_i + 1));
		}
	}
}
