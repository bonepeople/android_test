package com.shownest.android.fragment;

import com.shownest.android.R;
import com.shownest.android.activity.Activity_offer_auto;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.model.OfferBill;
import com.shownest.android.model.Package;
import com.shownest.android.widget.RelativeLayout_edit_informationbar;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class Fragment_offer_auto_show extends DEBUG_Fragment implements OnClickListener
{
	private static final int LOCATION = 1;
	private LinearLayout _body;
	private Button _button_commit, _button_other;
	private SparseArray<Package> _items = new SparseArray<Package>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);
		View _view = inflater.inflate(R.layout.fragment_basic, container, false);
		_body = (LinearLayout) _view.findViewById(R.id.linearlayout_content);
		_button_commit = (Button) _view.findViewById(R.id.button_commit);
		_button_commit.setText("发布招标");
		_button_commit.setOnClickListener(this);
		_button_other = (Button) _view.findViewById(R.id.button_other);
		_button_other.setText("保存");
		_button_other.setVisibility(Button.VISIBLE);
		_button_other.setOnClickListener(this);

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
			ContentValues _value = new ContentValues();

			Activity_offer_auto.get_instance().show_wait();
			// HttpUtil.get_ownerquote(Activity_offer_auto._handler, _value, Activity_offer_auto.NEXT_SUCCESSFUL, Activity_offer_auto.NEXT_FAILED);

		}
		else if (_id == R.id.button_other)
		{
			// Intent _location = new Intent(getActivity(), Activity_location.class);
			// startActivityForResult(_location, LOCATION);
		}
	}

	@Override
	public void setContent()
	{
		OfferBill _data = Activity_offer_auto.get_data();
		if (_data != null)
		{
			RelativeLayout_edit_informationbar _title = new RelativeLayout_edit_informationbar(getActivity(), _body, 3, new String[] { "总报价", String.valueOf(_data.get_allTotal()), "元" }, false);
			_items.put(_title.get_id(), new Package(_title, "all", 1));
		}
	}
}
