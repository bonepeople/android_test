package com.shownest.android.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class Relativelayout_picture extends RelativeLayout implements View.OnLongClickListener
{
	private int _id;
	private ImageView _img, _close;
	private OnClickListener _listener;

	public Relativelayout_picture(Context context)
	{
		super(context);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 初始化一个多功能图片控件
	 * <p>
	 * 需要一个ID用来设置图片的ID，在初始化之后必须指定OnClickListener，本类不自己处理图片的点击事件
	 * 
	 * @param context
	 * @param _id
	 */
	public Relativelayout_picture(Context context, int _id)
	{
		super(context);
		_img = new ImageView(context);
		_img.setId(_id);
		_img.setScaleType(ImageView.ScaleType.CENTER_CROP);
		_img.setOnClickListener(_listener);
		_img.setOnLongClickListener(this);

		
		// _imageview.setImageBitmap(image);
		
		
		// GridLayout.LayoutParams _param = new GridLayout.LayoutParams();
		// _param.height = _picture_height;
		// _param.width = _picture_width;
		// _param.setMargins(_picture_margins, _picture_margins, _picture_margins, _picture_margins);
		// _imageview.setLayoutParams(_param);
		// _body.addView(_imageview, _body.getChildCount() - 1);
		// _images.put(_imageview.getId(), new Package(_imageview, _uri));
	}

	public void setOnClickListener(OnClickListener _listener)
	{
		this._listener = _listener;
	}

	/**
	 * 设置显示的图片
	 */
	public void setImageBitmap(Bitmap _bitmap)
	{
		_img.setImageBitmap(_bitmap);
	}

	/**
	 * 设置图片的大小(px)
	 */
	public void setSize(int _width, int _height)
	{
	}

	@Override
	public boolean onLongClick(View v)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
