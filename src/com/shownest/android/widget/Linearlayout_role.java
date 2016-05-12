package com.shownest.android.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Linearlayout_role extends LinearLayout
{
	private static boolean DEBUG = true;
	private ImageView _imageview;
	private TextView _textview;

	public Linearlayout_role(Context context)
	{
		super(context);
		// TODO Auto-generated constructor stub
	}

	public Linearlayout_role(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		if (DEBUG)
			System.out.println("Linearlayout_role super");

		// android:background="#808080"
		// android:orientation="vertical"
		this.setBackgroundColor(Color.parseColor("#80808080"));
		this.setOrientation(LinearLayout.VERTICAL);
		_imageview = new ImageView(context, attrs);
		// android:padding="1dp"
		_imageview.setPadding(1, 1, 1, 1);

		_textview = new TextView(context, attrs);
		// android:gravity="center"
		// android:padding="5dp"
		_textview.setPadding(5, 5, 5, 5);
		_textview.setGravity(android.view.Gravity.CENTER);

		addView(_imageview);
		addView(_textview);

	}

	public Linearlayout_role(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

}
