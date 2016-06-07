package com.shownest.android.widget;

import java.io.File;

import com.shownest.android.R;
import com.shownest.android.model.OnChangeListener;
import com.shownest.android.utils.ImageUtil;
import com.shownest.android.utils.NumberUtil;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LinearLayout_idcard extends LinearLayout implements OnChangeListener
{
	private static boolean DEBUG = true;
	private TextView _textview_name;
	private ImageView _imageview_left, _imageview_right, _imageview_bottom;
	private LinearLayout _linearlayout_bottom;

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

		ViewGroup _view = (ViewGroup) View.inflate(context, R.layout.widget_idcard, root);
		View _childview = _view.getChildAt(_view.getChildCount() - 1);

		_textview_name = (TextView) _childview.findViewById(R.id.textview_name);
		_imageview_left = (ImageView) _childview.findViewById(R.id.imageview_widget_left);
		_imageview_right = (ImageView) _childview.findViewById(R.id.imageview_widget_right);
		_imageview_bottom = (ImageView) _childview.findViewById(R.id.imageview_widget_bottom);
		_linearlayout_bottom = (LinearLayout) _childview.findViewById(R.id.linearlayout_idcard_bottom);

		_textview_name.setText(_name);
		_imageview_left.setOnClickListener(_lintener);
		_imageview_right.setOnClickListener(_lintener);
		_imageview_bottom.setOnClickListener(_lintener);
		_linearlayout_bottom.setVisibility(LinearLayout.INVISIBLE);
	}

	@Override
	public void onChange(String tag, String[] args)
	{
		if (args[0].equals("1"))
			_linearlayout_bottom.setVisibility(LinearLayout.INVISIBLE);
		else
			_linearlayout_bottom.setVisibility(LinearLayout.VISIBLE);
	}

	public void setData(Uri _uri, int _where)
	{
		if (_uri != null)
		{
			Bitmap image;
			File _imagefile = new File(_uri.getPath());
			image = ImageUtil.getSmallBitmap(_imagefile.getAbsolutePath(), NumberUtil.get_px(getContext(), 120), NumberUtil.get_px(getContext(), 70));
			if (image != null)
			{
				switch (_where)
				{
				case 0:
					_imageview_left.setImageBitmap(image);
					break;
				case 1:
					_imageview_right.setImageBitmap(image);
					break;
				case 2:
					_imageview_bottom.setImageBitmap(image);
					break;
				}
			}
		}
	}
}
