package com.shownest.android.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class Listview_maxheight extends ListView
{

	public Listview_maxheight(Context context)
	{
		super(context);
	}

	public Listview_maxheight(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
	}

	public Listview_maxheight(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}
