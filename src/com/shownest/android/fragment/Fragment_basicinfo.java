package com.shownest.android.fragment;

import java.io.File;

import com.loopj.android.image.SmartImageView;
import com.shownest.android.R;
import com.shownest.android.activity.Activity_basicinfo;
import com.shownest.android.activity.Activity_change_phone;
import com.shownest.android.activity.Activity_change_pwd;
import com.shownest.android.activity.Activity_select_role;
import com.shownest.android.basic.DEBUG_Fragment;
import com.shownest.android.model.UserInfo;
import com.shownest.android.utils.CommonUtil;
import com.shownest.android.utils.HttpUtil;
import com.shownest.android.utils.ImageUtil;
import com.shownest.android.utils.UserManager;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.net.Uri;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment_basicinfo extends DEBUG_Fragment implements View.OnClickListener
{
	public static final int IMAGE_CAMERA = 106;
	public static final int IMAGE_SDCARD = 107;
	public static final int IMAGE_CUT = 108;
	private TextView _name, _showname, _role, _phone;
	private SmartImageView _imageview_header;
	private RelativeLayout _item_name, _item_password, _item_role, _item_phone;
	private Uri _image_uri;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		super.onCreateView(inflater, container, savedInstanceState);

		View _view = inflater.inflate(R.layout.fragment_basicinfo, container, false);
		_name = (TextView) _view.findViewById(R.id.textview_name);
		_showname = (TextView) _view.findViewById(R.id.textview_showname);
		_role = (TextView) _view.findViewById(R.id.textview_role);
		_phone = (TextView) _view.findViewById(R.id.textview_phone);
		_imageview_header = (SmartImageView) _view.findViewById(R.id.imageview_header);
		_item_name = (RelativeLayout) _view.findViewById(R.id.relativelayout_name);
		_item_password = (RelativeLayout) _view.findViewById(R.id.relativelayout_password);
		_item_role = (RelativeLayout) _view.findViewById(R.id.relativelayout_role);
		_item_phone = (RelativeLayout) _view.findViewById(R.id.relativelayout_phone);

		return _view;
	}

	@Override
	public void setContent()
	{
		UserInfo _info = UserManager.get_user_info();
		if (_info != null)
		{
			_imageview_header.setImageUrl(CommonUtil.getUserHeaderIconUrl(_info.get_userType(), _info.get_headerIcon()));
			_name.setText(String.valueOf(_info.get_userName()));
			_showname.setText(_info.get_userShowName());
			_phone.setText(_info.get_userPhone());

			_imageview_header.setOnClickListener(this);
			if (_info.is_checkUsername())
				_name.setTextColor(getResources().getColor(R.color.text_gray));
			else
				_item_name.setOnClickListener(this);
			_item_password.setOnClickListener(this);
			_role.setTextColor(getResources().getColor(R.color.text_gray));
			switch (_info.get_userType())
			{
			case 11:
				_role.setText("业主");
				break;
			case 12:
				_role.setText("设计师");
				break;
			case 13:
				_role.setText("施工队");
				break;
			case 14:
				_role.setText("监理");
				break;
			case 15:
				_role.setText("装修公司");
				break;
			case 100:
				_role.setTextColor(getResources().getColor(R.color.main_color));
				_item_role.setOnClickListener(this);
				break;
			}
			_item_phone.setOnClickListener(this);
		}
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		System.out.println("requestCode=" + requestCode + "resultCode=" + resultCode);
		if (resultCode == -1)
		{
			switch (requestCode)
			{
			case IMAGE_SDCARD:
				_image_uri = data.getData();
			case IMAGE_CAMERA:
				Intent intent = new Intent();
				intent.setAction("com.android.camera.action.CROP");
				intent.setDataAndType(_image_uri, "image/*");// mUri是已经选择的图片Uri
				intent.putExtra("crop", "true");
				intent.putExtra("aspectX", 1);// 裁剪框比例
				intent.putExtra("aspectY", 1);
				intent.putExtra("outputX", 150);// 输出图片大小
				intent.putExtra("outputY", 150);
				intent.putExtra("return-data", true);
				startActivityForResult(intent, IMAGE_CUT);
				break;
			case IMAGE_CUT:
				Bitmap _itmap = data.getParcelableExtra("data");

				String _path = CommonUtil.getUserHeaderIconUrl(UserManager.get_user_info().get_userType());
				String _name = Activity_basicinfo.get_image_name();
				String _base64 = ImageUtil.bitmapToString(_itmap);

				ContentValues _value = new ContentValues();
				_value.put("path", _path);
				_value.put("imgName", _name);
				_value.put("imgBase64", _base64);
				Activity_basicinfo.get_instance().show_wait();
				HttpUtil.upload_image(Activity_basicinfo._handler, _value, Activity_basicinfo.UPLOAD_SUCCESSFUL, Activity_basicinfo.UPLOAD_FAILED);
				return;
			}
		}
	}

	private void show_dialog()
	{
		final String[] _temp_str = new String[] { "拍摄照片", "选取照片", "取消" };

		AlertDialog.Builder _builder = new Builder(getActivity());
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
						startActivityForResult(intent, IMAGE_CAMERA);
					}
					catch (Exception e)
					{
						e.printStackTrace();
						Toast.makeText(getActivity(), "您的手机不具备拍照功能", Toast.LENGTH_SHORT).show();
					}
					break;
				case 1:
					try
					{
						Intent intent = new Intent();
						intent.setType("image/*");
						intent.setAction(Intent.ACTION_GET_CONTENT);
						startActivityForResult(intent, IMAGE_SDCARD);// data.getExtras()
					}
					catch (ActivityNotFoundException e)
					{
						Toast.makeText(getActivity(), "您的手机不具备选择图片的功能", Toast.LENGTH_SHORT).show();
					}
					break;
				case 2:
					break;
				}
			}
		});
		_builder.show();
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.imageview_header:
			File _file_dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/shownest_cache");
			File _file;
			if (_file_dir.mkdirs() || _file_dir.isDirectory())
			{
				String _name = CommonUtil.get_imageName() + ".png";
				Activity_basicinfo.set_image_name(_name);
				_file = new File(_file_dir, _name);
				_image_uri = Uri.fromFile(_file);
				show_dialog();
			}
			else
				Toast.makeText(getActivity(), "获取缓存失败", Toast.LENGTH_SHORT).show();
			break;

		case R.id.relativelayout_name:
			Toast.makeText(getActivity(), "修改用户名", Toast.LENGTH_SHORT).show();
			break;

		case R.id.relativelayout_password:
			Intent _change_password = new Intent(getActivity(), Activity_change_pwd.class);
			getActivity().startActivity(_change_password);
			break;

		case R.id.relativelayout_role:
			Intent _select_role = new Intent(getActivity(), Activity_select_role.class);
			getActivity().startActivity(_select_role);
			break;

		case R.id.relativelayout_phone:
			AlertDialog.Builder _builder = new Builder(getActivity());
			_builder.setMessage("更换手机号？");
			_builder.setPositiveButton("确定", new OnClickListener()
			{

				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					Intent _change_phone = new Intent(Fragment_basicinfo.this.getActivity(), Activity_change_phone.class);
					Fragment_basicinfo.this.getActivity().startActivity(_change_phone);
				}
			});
			_builder.setNegativeButton("取消", null);
			_builder.show();
			break;
		}

	}
}
