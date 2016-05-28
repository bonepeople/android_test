package com.shownest.android.utils;

import android.content.Context;

public class NumberUtil
{

	public static int get_px(Context context, float _dp)
	{
		float scale = context.getResources().getDisplayMetrics().density;
		int _px = (int) (_dp * scale + 0.5f);

		return _px;
	}

	public static double double_format(double _number)
	{
		String _format = "%.2f";
		String _result = String.format(_format, _number);
		System.out.println(_result);
		return Double.parseDouble(_result);
	}

}
