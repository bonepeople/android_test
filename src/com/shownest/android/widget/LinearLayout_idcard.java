package com.shownest.android.widget;

import com.shownest.android.R;
import com.shownest.android.model.OnSelectListener;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LinearLayout_idcard extends LinearLayout implements View.OnClickListener, OnSelectListener
{
	private static boolean DEBUG = true;
	private TextView _textview_name;
	private ImageView _imageview_left, _imageview_right, _imageview_bottom;
	private LinearLayout _linearlayout_bottom;

	public LinearLayout_idcard(Context context)
	{
		super(context);
		// TODO Auto-generated constructor stub
	}

	public LinearLayout_idcard(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public LinearLayout_idcard(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 初始化一个身份证上传控件
	 * 
	 * @param _name
	 *            控件标题
	 */
	public LinearLayout_idcard(Context context, ViewGroup root, String _name, OnClickListener _lintener)
	{
		super(context);
		if (DEBUG)
			System.out.println("LinearLayout_idcard super");

		// float scale = context.getResources().getDisplayMetrics().density;
		// int padding = (int) (5 * scale + 0.5f);

		ViewGroup _view = (ViewGroup) View.inflate(context, R.layout.widget_idcard, root);
		View _childview = _view.getChildAt(_view.getChildCount() - 1);

		_textview_name = (TextView) _childview.findViewById(R.id.textview_name);
		_imageview_left = (ImageView) _childview.findViewById(R.id.imageview_widget_left);
		_imageview_right = (ImageView) _childview.findViewById(R.id.imageview_widget_right);
		_imageview_bottom = (ImageView) _childview.findViewById(R.id.imageview_widget_bottom);
		_linearlayout_bottom = (LinearLayout) _childview.findViewById(R.id.linearlayout_idcard_bottom);

		if (_textview_name == null)
			System.out.println("name  is null");
		_textview_name.setText(_name);
		_imageview_left.setOnClickListener(_lintener);
		_imageview_right.setOnClickListener(_lintener);
		_imageview_bottom.setOnClickListener(_lintener);
		_linearlayout_bottom.setVisibility(LinearLayout.INVISIBLE);
		// _childview = _view.getChildAt(_view.getChildCount() - 1);
		//
		// _textview_name.setText(args[0]);
		// _textview_left.setText(args[1]);
		// _textview_right.setText(args[2]);
	}

	@Override
	public void onClick(View v)
	{

	}

	@Override
	public void onSelect(int _index)
	{
		if (_index == 1)
			_linearlayout_bottom.setVisibility(LinearLayout.INVISIBLE);
		else
			_linearlayout_bottom.setVisibility(LinearLayout.VISIBLE);
	}
}
