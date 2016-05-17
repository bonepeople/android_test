package com.shownest.android.fragment;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.widget.RelativeLayout_edit_informationbar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Fragment_publish_yezhu extends DEBUG_Fragment
{
	private LinearLayout _linearlayout_body;
	private Button _button_next;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);
		View _view = inflater.inflate(R.layout.fragment_publish_yezhu, container, false);
		_linearlayout_body = (LinearLayout) _view.findViewById(R.id.linearlayout_fragment_body);
		_button_next = (Button) _view.findViewById(R.id.button_next);

		RelativeLayout_edit_informationbar _item1 = new RelativeLayout_edit_informationbar(getActivity(), _linearlayout_body, 1, new String[] { "first", "left", "right" }, true);
		RelativeLayout_edit_informationbar _item2 = new RelativeLayout_edit_informationbar(getActivity(), _linearlayout_body, 1, new String[] { "first", "left", "right" }, true);
		RelativeLayout_edit_informationbar _item3 = new RelativeLayout_edit_informationbar(getActivity(), _linearlayout_body, 2, new String[] { "style2", "value" }, true);
		RelativeLayout_edit_informationbar _item4 = new RelativeLayout_edit_informationbar(getActivity(), _linearlayout_body, 3, new String[] { "style3", "number", "unit" }, true);
		RelativeLayout_edit_informationbar _item5 = new RelativeLayout_edit_informationbar(getActivity(), _linearlayout_body, 4, new String[] { "style4", "1", "2", "3", "4", "5" }, true);

		new RelativeLayout_edit_informationbar(getActivity(), _linearlayout_body, 4, new String[] { "name", "1", "2", "3", "4", "5" }, false);
		new RelativeLayout_edit_informationbar(getActivity(), _linearlayout_body, 2, new String[] { "style2", "" }, false);
		new RelativeLayout_edit_informationbar(getActivity(), _linearlayout_body, 2, new String[] { "name", "value" }, false);
		new RelativeLayout_edit_informationbar(getActivity(), _linearlayout_body, 4, new String[] { "style4", "1", "2", "3", "4", "5" }, false);
		new RelativeLayout_edit_informationbar(getActivity(), _linearlayout_body, 4, new String[] { "style4", "1", "2", "3", "4", "5" }, false);
		new RelativeLayout_edit_informationbar(getActivity(), _linearlayout_body, 4, new String[] { "style4", "99", "99", "99", "99", "90" }, false);
		new RelativeLayout_edit_informationbar(getActivity(), _linearlayout_body, 4, new String[] { "style4", "1", "2", "3", "4", "5" }, false);

		_button_next.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Toast.makeText(getActivity(), "next", Toast.LENGTH_SHORT).show();
			}
		});
		return _view;
	}
}
