package com.shownest.android.fragment;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.widget.RelativeLayout_edit_informationbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Fragment_addhouse extends DEBUG_Fragment implements View.OnClickListener
{
	private static final int REQUEST_LOCATION = 1;
	private LinearLayout _body;
	private Button _button_commit;
	private RelativeLayout_edit_informationbar _name, _location, _areas, _sex;

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
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if (resultCode == -1)
		{
			switch (requestCode)
			{
			case REQUEST_LOCATION:
				try
				{
					String _result = data.getStringExtra("phone");
					// _phone.setData(new String[] { CommonUtil.showPhone(_result) });
				}
				catch (Exception e)
				{
					Toast.makeText(getActivity(), "返回异常", Toast.LENGTH_SHORT).show();
					e.printStackTrace();
				}
				break;
			}
		}
		else
			System.out.println("resultCode=" + resultCode);
	}

	@Override
	public void onClick(View v)
	{
		// TODO Auto-generated method stub

	}

}
