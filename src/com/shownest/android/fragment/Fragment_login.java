package com.shownest.android.fragment;

import com.shownest.android.R;
import com.shownest.android.widget.RelativeLayout_edit_informationbar;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class Fragment_login extends Fragment
{
	private static boolean DEBUG = true;
	private LinearLayout _linearlayout_body;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		if (DEBUG)
			System.out.println("Fragment_login onCreateView");
		View _view = inflater.inflate(R.layout.fragment_login, container, false);


		return _view;
	}

}
