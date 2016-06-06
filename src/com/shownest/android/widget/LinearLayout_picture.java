package com.shownest.android.widget;

import java.io.File;
import com.loopj.android.image.SmartImageView;
import com.shownest.android.R;
import com.shownest.android.model.OnChangeListener;
import com.shownest.android.model.Package;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class LinearLayout_picture extends LinearLayout implements View.OnClickListener, View.OnLongClickListener
{
	private boolean _addable = true;
	private TextView _textview_name;
	private GridLayout _body;
	private int _image_count = 0;
	private OnChangeListener _listener;
	private int _picture_width = 150;
	private int _picture_height = 150;
	private int _picture_margins = 10;
	private SparseArray<Package> _images = new SparseArray<Package>();

	public LinearLayout_picture(Context context)
	{
		super(context);
		// TODO Auto-generated constructor stub
	}

	public LinearLayout_picture(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public LinearLayout_picture(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	public LinearLayout_picture(Context context, String _name, boolean _addable)
	{
		super(context);
		this._addable = _addable;
		this.setOrientation(LinearLayout.VERTICAL);

		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Point _outSize = new Point(0, 0);
		wm.getDefaultDisplay().getSize(_outSize);
		int screen_width = _outSize.x;

		_textview_name = new TextView(context);
		_textview_name.setText(_name);
		_textview_name.setPadding(10, 0, 0, 0);
		this.addView(_textview_name);

		_body = new GridLayout(getContext());
		int _ColumnCount = screen_width / (_picture_width + _picture_margins * 2);
		_body.setColumnCount(_ColumnCount);
		LinearLayout.LayoutParams _param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		_param.width = (_picture_width + _picture_margins * 2) * _ColumnCount;
		_param.gravity = Gravity.CENTER_HORIZONTAL;
		_body.setLayoutParams(_param);

		ImageView _image_add = new ImageView(getContext());
		_image_add.setId(_image_count++);
		_image_add.setBackgroundColor(getContext().getResources().getColor(R.color.text_blue));
		_image_add.setOnClickListener(this);
		GridLayout.LayoutParams _param_add = new GridLayout.LayoutParams();
		_param_add.height = _picture_height;
		_param_add.width = _picture_width;
		_param_add.setMargins(_picture_margins, _picture_margins, _picture_margins, _picture_margins);
		_image_add.setLayoutParams(_param_add);
		_body.addView(_image_add);
		if (!_addable)
		{
			_image_add.setVisibility(ImageView.GONE);
		}

		this.addView(_body);

	}

	public void add_image(Uri _uri)
	{
		if (_uri != null)
		{
			File _imagefile = new File(_uri.getPath());
			Bitmap image = getSmallBitmap(_imagefile.getAbsolutePath(), _picture_width, _picture_height);

			if (image != null)
			{
				ImageView _imageview = new ImageView(getContext());
				_imageview.setId(_image_count++);
				_imageview.setScaleType(ImageView.ScaleType.CENTER_CROP);
				_imageview.setImageBitmap(image);
				_imageview.setOnClickListener(this);
				_imageview.setOnLongClickListener(this);
				GridLayout.LayoutParams _param = new GridLayout.LayoutParams();
				_param.height = _picture_height;
				_param.width = _picture_width;
				_param.setMargins(_picture_margins, _picture_margins, _picture_margins, _picture_margins);
				_imageview.setLayoutParams(_param);
				_body.addView(_imageview, _body.getChildCount() - 1);
				_images.put(_imageview.getId(), new Package(_imageview, _uri));
			}
		}
	}

	public void add_image(String _url)
	{
		if (_url != null)
		{
			SmartImageView _imageview = new SmartImageView(getContext());
			_imageview.setId(_image_count++);
			_imageview.setScaleType(ImageView.ScaleType.CENTER_CROP);
			_imageview.setImageUrl(_url);
			_imageview.setOnClickListener(this);
			_imageview.setOnLongClickListener(this);
			GridLayout.LayoutParams _param = new GridLayout.LayoutParams();
			_param.height = _picture_height;
			_param.width = _picture_width;
			_param.setMargins(_picture_margins, _picture_margins, _picture_margins, _picture_margins);
			_imageview.setLayoutParams(_param);
			_body.addView(_imageview, _body.getChildCount() - 1);
			_images.put(_imageview.getId(), new Package(_imageview, _url));
		}
	}

	@Deprecated
	public void add_image(Bitmap _image)
	{
		if (_image != null)
		{
			ImageView _imageview = new ImageView(getContext());
			_imageview.setId(_image_count++);
			_imageview.setImageBitmap(_image);
			_imageview.setOnClickListener(this);
			_imageview.setOnLongClickListener(this);
			GridLayout.LayoutParams _param = new GridLayout.LayoutParams();
			_param.height = _picture_height;
			_param.width = _picture_width;
			_param.setMargins(_picture_margins, _picture_margins, _picture_margins, _picture_margins);
			_imageview.setLayoutParams(_param);
			_body.addView(_imageview, _body.getChildCount() - 1);
		}
	}

	private static Bitmap getSmallBitmap(String filePath, int _width, int _height)
	{
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(filePath, options);
		// Calculate inSampleSize
		options.inSampleSize = calculateInSampleSize(options, _width, _height);

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

	public void setOnChangeListener(OnChangeListener _listener)
	{
		this._listener = _listener;
	}

	public Package get_package(int _index)
	{
		return _images.get(_index);
	}

	@Override
	public void onClick(View v)
	{
		int _id = v.getId();
		if (_addable && _id == 0)
		{
			if (_listener != null)
				_listener.onChange("add", null);
		}
		else
		{
			if (_listener != null)
				_listener.onChange("see", new String[] { String.valueOf(_id) });
		}
	}

	@Override
	public boolean onLongClick(View v)
	{
		int _id = v.getId();
		Toast.makeText(getContext(), "image_" + _id + " has been Longclicked", Toast.LENGTH_SHORT).show();
		return true;
	}
}
