package com.shownest.android.fragment;

import com.loopj.android.image.SmartImageView;
import com.shownest.android.R;
import com.shownest.android.activity.Activity_my_center;
import com.shownest.android.basic.DEBUG_Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment_my_center extends DEBUG_Fragment
{
	private SmartImageView _imageview_header;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);

		View _view = inflater.inflate(R.layout.fragment_my_center, container, false);
		_imageview_header = (SmartImageView) _view.findViewById(R.id.imageview_header);
		String _url = "http://t.shownest.com:86/_resources/upload/headerIcon/" + Activity_my_center.get_userinfo().get_headerIcon();
		_imageview_header.setImageUrl(_url);

		return _view;
	}

	@Override
	protected String get_class()
	{
		return this.getClass().toString();
	}

}
