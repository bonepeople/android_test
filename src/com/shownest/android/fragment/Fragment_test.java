package com.shownest.android.fragment;

import com.shownest.android.R;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.widget.LinearLayout_picture;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class Fragment_test extends DEBUG_Fragment
{
	private LinearLayout_picture _picture;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);
		View _view = inflater.inflate(R.layout.fragment_test, container, false);

		LinearLayout _body = (LinearLayout) _view.findViewById(R.id.linearlayout_content);
		_picture = new LinearLayout_picture(this, _body, "pictures", true);
		_picture.add_image("http://t.shownest.com:86/_resources/upload/headerIcon/6dfdd424ea49110400eba7dceb7.jpg");
		_picture.add_image("http://t.shownest.com:86/_resources/upload/headerIcon/6dfdd424ea49110400eba7dceb7.jpg");
		_picture.add_image("http://t.shownest.com:86/_resources/upload/headerIcon/6dfdd424ea49110400eba7dceb7.jpg");
		_picture.add_image("http://t.shownest.com:86/_resources/upload/headerIcon/6dfdd424ea49110400eba7dceb7.jpg");
		_body.addView(_picture);
		return _view;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		_picture.onActivityResult(requestCode, resultCode, data);
	}

}
