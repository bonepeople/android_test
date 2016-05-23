package com.shownest.android.widget;

import java.io.ByteArrayOutputStream;
import java.io.File;

import com.shownest.android.R;
import com.shownest.android.model.OnChangeListener;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Base64;
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
	}

	@Override
	public void onChange(String[] args)
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

			System.out.println(_imagefile.getAbsolutePath());
			image = getSmallBitmap(_imagefile.getAbsolutePath());

			System.out.println("file size is " + _imagefile.length());

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

	private static Bitmap getSmallBitmap(String filePath)
	{
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(filePath, options);
		// Calculate inSampleSize
		options.inSampleSize = calculateInSampleSize(options, 480, 240);

		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;

		return BitmapFactory.decodeFile(filePath, options);
	}

	private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight)
	{
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth)
		{
			final int heightRatio = Math.round((float) height / (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}
		return inSampleSize;
	}

	// 把bitmap转换成String
	public static String bitmapToString(String filePath)
	{
		Bitmap bm = getSmallBitmap(filePath);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bm.compress(Bitmap.CompressFormat.JPEG, 40, baos);// 40是压缩值，100为不压缩
		byte[] b = baos.toByteArray();
		return Base64.encodeToString(b, Base64.DEFAULT);
	}
}
