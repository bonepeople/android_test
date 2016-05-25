package com.shownest.android.utils;

import android.content.Context;

public class NumberUtil
{

	public static int get_px(Context context,float _dp)
	{
		float scale = context.getResources().getDisplayMetrics().density;
		int _px = (int) (_dp * scale + 0.5f);
		
		return _px;
	}
}
