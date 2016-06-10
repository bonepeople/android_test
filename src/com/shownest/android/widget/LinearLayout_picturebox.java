package com.shownest.android.widget;

import java.io.File;

import com.loopj.android.image.SmartImageView;
import com.shownest.android.R;
import com.shownest.android.model.Package;
import com.shownest.android.utils.ImageUtil;
import com.shownest.android.utils.NumberUtil;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Fragment;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * 添加和显示图片的控件
 * <p>
 * 该控件会获取屏幕宽度并计算出一行可以显示图片的个数。对于加载此控件的fragment需要将自身实例传递进来，控件会以fragment发出开启Activity的命令，
 * 使用此控件的fragment需要添加onActivityResult函数并调用控件内部的onActivityResult函数，其中106， 107为控件保留的两个返回值。
 * <p>
 * 目前该控件会以屏幕宽度为自己的最大宽度进行计算显示，请尽量将此控件放在足够宽的布局里
 * 
 * @param _name
 *            控件的标题
 * @param _addable
 *            是否可添加图片
 */
public class LinearLayout_picturebox extends LinearLayout implements View.OnClickListener, View.OnLongClickListener
{
	public static final int IMAGE_CAMERA = 106;
	public static final int IMAGE_SDCARD = 107;
	private Fragment _fragment;
	private boolean _addable = true;
	private TextView_ex _textview_name;
	private GridLayout _body;
	private int _image_count = 0;
	private int _picture_width = NumberUtil.get_px(getContext(), 75);
	private int _picture_height = NumberUtil.get_px(getContext(), 75);
	private int _picture_margins = NumberUtil.get_px(getContext(), 5);
	private SparseArray<Package> _images = new SparseArray<Package>();
	private Uri _image_uri;

	/**
	 * 初始化一个图片控件
	 * <p>
	 * 图片默认大小为75dp x 75dp，默认间距为10dp
	 * 
	 * @param _name
	 *            控件的标题
	 * @param _addable
	 *            是否可添加图片
	 */
	public LinearLayout_picturebox(Fragment _fragment, String _name, boolean _addable)
	{
		super(_fragment.getActivity());
		this._fragment = _fragment;
		this._addable = _addable;
		this.setOrientation(LinearLayout.VERTICAL);

		WindowManager wm = (WindowManager) _fragment.getActivity().getSystemService(Context.WINDOW_SERVICE);
		Point _outSize = new Point(0, 0);
		wm.getDefaultDisplay().getSize(_outSize);
		int screen_width = _outSize.x;

		_textview_name = new TextView_ex(_fragment.getActivity(), this);
		_textview_name.setText(_name);
		_textview_name.setMargins(10f, 5, 0, 5);

		_body = new GridLayout(getContext());
		int _ColumnCount = screen_width / (_picture_width + _picture_margins * 2);
		if (_ColumnCount == 0)
			_ColumnCount = 1;
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

	/**
	 * 接收并处理activity返回的信息，由fragment调用
	 */
	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		System.out.println("requestCode=" + requestCode + "resultCode=" + resultCode);
		if (resultCode == -1)
		{
			if (requestCode == IMAGE_SDCARD)
			{
				_image_uri = data.getData();
				add_image(_image_uri);
			}
			else if (requestCode == IMAGE_CAMERA)
			{
				add_image(_image_uri);
				// Bitmap bmap = data.getParcelableExtra("data");
				// add_image(bmap);
				// return;
			}
			// Intent intent = new Intent();
			//
			// intent.setAction("com.android.camera.action.CROP");
			// intent.setDataAndType(_image_uri, "image/*");// mUri是已经选择的图片Uri
			// intent.putExtra("crop", "true");
			// intent.putExtra("aspectX", 1);// 裁剪框比例
			// intent.putExtra("aspectY", 1);
			// intent.putExtra("outputX", 150);// 输出图片大小
			// intent.putExtra("outputY", 150);
			// intent.putExtra("return-data", true);
			//
			// startActivityForResult(intent, 200);
		}
	}

	/**
	 * 添加图片
	 */
	public void add_image(Uri _uri)
	{
		if (_uri != null)
		{
			File _imagefile = new File(_uri.getPath());
			Bitmap image = ImageUtil.getSmallBitmap(_imagefile.getAbsolutePath(), _picture_width, _picture_height);

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

	/**
	 * 添加图片
	 */
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

	/**
	 * 添加图片
	 */
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

	private void show_dialog()
	{
		final String[] _temp_str = new String[] { "拍摄照片", "选取照片", "取消" };

		AlertDialog.Builder _builder = new Builder(_fragment.getActivity());
		_builder.setItems(_temp_str, new DialogInterface.OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				switch (which)
				{
				case 0:
					try
					{
						Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
						intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
						intent.putExtra(MediaStore.EXTRA_OUTPUT, _image_uri);
						_fragment.startActivityForResult(intent, IMAGE_CAMERA);
					}
					catch (Exception e)
					{
						e.printStackTrace();
						Toast.makeText(_fragment.getActivity(), "您的手机不具备拍照功能", Toast.LENGTH_SHORT).show();
					}
					break;
				case 1:
					try
					{
						Intent intent = new Intent();
						intent.setType("image/*");
						intent.setAction(Intent.ACTION_GET_CONTENT);
						_fragment.startActivityForResult(intent, IMAGE_SDCARD);// data.getExtras()
					}
					catch (ActivityNotFoundException e)
					{
						Toast.makeText(_fragment.getActivity(), "您的手机不具备选择图片的功能", Toast.LENGTH_SHORT).show();
					}
					break;
				case 2:
					break;
				}
			}
		});
		_builder.show();
	}

	/**
	 * 获取图片信息
	 */
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
			// add
			File _file_dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/shownest_cache");
			File _file;
			if (_file_dir.mkdirs() || _file_dir.isDirectory())
			{
				_file = new File(_file_dir, "left.tmp");
				_image_uri = Uri.fromFile(_file);
				show_dialog();
			}
			else
				Toast.makeText(_fragment.getActivity(), "获取缓存失败", Toast.LENGTH_SHORT).show();
		}
		else
		{
			// see
			Package _package = _images.get(_id);

			if (_package._data1 instanceof SmartImageView)
			{
				// url
				String _url = _package._tag1;
				System.out.println("pic url =" + _url);
			}
			else
			{
				// uri
				Uri _uri = (Uri) _package._data2;
				System.out.println("pic uri =" + _uri);
			}
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
